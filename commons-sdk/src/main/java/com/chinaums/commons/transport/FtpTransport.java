package com.chinaums.commons.transport;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * FtpTransport
 *
 * @auther xionglei
 * @create 2018-02-02 9:48
 */
public class FtpTransport implements FileTransport {
    private static final Logger logger = LoggerFactory.getLogger(FtpTransport.class);

    /**
     * ftp 地址
     */
    private String host;
    /**
     * 端口号
     */
    private Integer port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 远程目录
     */
    private String remoteDir;
    /**
     * 编码
     */
    private String encoding = "UTF-8";
    /**
     * 文件传输类型，默认为二进制方式
     */
    private int fileType = FTP.BINARY_FILE_TYPE;
    /**
     * FTP连接方式，默认为主动方式
     */
    private int connectionMode = FTPClient.ACTIVE_LOCAL_DATA_CONNECTION_MODE;

    public FtpTransport() {
    }

    public FtpTransport(String host, Integer port, String username, String password, String remoteDir) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.remoteDir = remoteDir;
    }

    /**
     * 上传文件
     * @param fileName 文件名称
     * @param fileContent 文件内容
     */
    @Override
    public boolean upload(String fileName, byte[] fileContent){
        FTPClient ftpClient = new FTPClient();
        InputStream is = null;
        try {
            connect(ftpClient);
            is = new ByteArrayInputStream(fileContent);
            boolean bl = ftpClient.storeFile(fileName, is);
            return bl;
        } catch (Exception e) {
            logger.error("FTP upload file failed.", e);
            return false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("FTP upload stream close exception", e);
                }
            }

            disconnect(ftpClient);
        }
    }

    /**
     * 下载文件
     * @param fileName 文件名称
     * @return
     */
    @Override
    public byte[] download(String fileName){
        FTPClient ftpClient = new FTPClient();
        ByteArrayOutputStream output = null;
        try {
            connect(ftpClient);
            List<String> fileNames = listFileName(ftpClient);
            if (!fileNames.contains(fileName)) {
                logger.error("FTP download file [" + fileName +"] not found.");
                throw new IOException("FTP download file not found.");
            }

            output = new ByteArrayOutputStream();
            boolean bl = ftpClient.retrieveFile(fileName, output);
            if(!bl){
                throw new IOException("FTP download file fail.");
            }
            byte[] bytes = output.toByteArray();
            return bytes;
        } catch (Exception e) {
            logger.error("FTP download file failed.", e);
            throw new RuntimeException(e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    logger.error("FTP download stream close exception", e);
                }
            }

            disconnect(ftpClient);
        }
    }

    /**
     * 通过文件名称前缀下载
     * @param fileNamePrefix
     * @return
     */
    @Override
    public List<byte[]> downloadByFileNamePrefix(String fileNamePrefix) {
        FTPClient ftpClient = new FTPClient();
        try {
            connect(ftpClient);
            List<String> fileNames = listFileName(ftpClient);
            List<String> needFileNames = getByPrefix(fileNames, fileNamePrefix);
            if (needFileNames == null || needFileNames.size() == 0) {
                logger.error("FTP download file [" + fileNamePrefix +"] not found.");
                throw new IOException("FTP download file not found.");
            }

            List<byte[]> rList = new ArrayList<>();
            for(String fileName : needFileNames){
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    boolean bl = ftpClient.retrieveFile(fileName, output);
                    if(!bl){
                        throw new IOException("FTP download file fail.");
                    }
                    byte[] bytes = output.toByteArray();
                    rList.add(bytes);
                }finally {
                    try {
                        output.close();
                    }catch (Exception e){
                        logger.error("FTP download stream close exception", e);
                    }
                }
            }

            return rList;
        } catch (Exception e) {
            logger.error("FTP download file failed.", e);
            throw new RuntimeException(e);
        } finally {
            disconnect(ftpClient);
        }
    }

    @Override
    public void delete(String fileName) {
        FTPClient ftpClient = new FTPClient();
        try {
            connect(ftpClient);
            List<String> fileNames = listFileName(ftpClient);
            if (!fileNames.contains(fileName)) {
                logger.error("FTP delete file [" + fileName +"] not found.");
                throw new IOException("FTP delete file not found.");
            }
            ftpClient.deleteFile(fileName);

        } catch (Exception e) {
            logger.error("FTP delete file failed.", e);
            throw new RuntimeException(e);
        } finally {
            disconnect(ftpClient);
        }
    }


    /**
     * 建立ftp连接
     * @param ftpClient
     * @throws Exception
     */
    private void connect(FTPClient ftpClient) throws Exception {
        //1.连接ftp server
        ftpClient.connect(this.host, this.port);
        ftpClient.setConnectTimeout(60000);
        int reply = ftpClient.getReplyCode();
        logger.debug("FTP server reply is:" + reply);
        if (!FTPReply.isPositiveCompletion(reply)) {
            throw new IOException("FTP server refused connection.");
        }

        //2.登录ftp server
        if (!ftpClient.login(this.username, this.password)) {
            throw new IOException("FTP server login failed.");
        }

        //3.设置参数
        ftpClient.setControlEncoding(encoding);
        ftpClient.setFileType(fileType);
        if (connectionMode == FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE) {
            ftpClient.enterLocalPassiveMode();
        }

        //4.切换工作目录
        if (!ftpClient.changeWorkingDirectory(this.remoteDir)) {
            throw new IOException("FTP change working directory failed.");
        }
    }

    /**
     * 断开ftp连接
     * @param ftpClient
     */
    private void disconnect(FTPClient ftpClient) {
        if (ftpClient != null) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.logout();
                }catch (Exception e){
                    logger.error("FTP server logout exception", e);
                }

                try {
                    ftpClient.disconnect();
                }catch (Exception e){
                    logger.error("FTP server disconnect exception", e);
                }
            }
        }
    }

    /**
     * 获取当前工作目录下的所有文件
     * @param ftpClient
     * @return
     * @throws Exception
     */
    private List<String> listFileName(FTPClient ftpClient) throws Exception {
        List<String> ftpFileNameList = new ArrayList<String>();
        FTPFile[] ftpfiles = ftpClient.listFiles();
        for (int i = 0,len = ftpfiles.length; i < len; i++) {
            FTPFile ftpFile = ftpfiles[i];
            if (ftpFile.isFile()) {
                ftpFileNameList.add(ftpFile.getName());
            }
        }
        return ftpFileNameList;
    }

    private List<String> getByPrefix(List<String> list, String fileNamePrefix){
        List<String> rList = new ArrayList<>();
        if(list != null && list.size() > 0){
            for(int i=0,len=list.size();i<len;i++){
                if(list.get(i).startsWith(fileNamePrefix)){
                    rList.add(list.get(i));
                }
            }
        }
        return rList;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(int connectionMode) {
        this.connectionMode = connectionMode;
    }
}
