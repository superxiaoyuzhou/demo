package com.chinaums.commons.transport;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * SftpTransport
 *
 * @auther xionglei
 * @create 2018-02-02 9:48
 */


public class SftpTransport implements FileTransport {
    private static final Logger logger = LoggerFactory.getLogger(SftpTransport.class);

    /**
     * sftp 地址
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


    public SftpTransport() {
    }

    public SftpTransport(String host, Integer port, String username, String password, String remoteDir) {
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
        Session session = null;
        ChannelSftp channel = null;
        InputStream is = null;
        try {


            //6.文件上传
            is = new ByteArrayInputStream(fileContent);
            channel.put(is, fileName);
            return true;
        } catch (Exception e) {
            logger.error("SFTP upload file failed.", e);
            return false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("SFTP upload stream close exception", e);
                }
            }

            disconnect(session, channel);
        }
    }

    /**
     * 下载文件
     * @param fileName 文件名称
     * @return
     */
    @Override
    public byte[] download(String fileName){
        Session session = null;
        ChannelSftp channel = null;
        ByteArrayOutputStream output = null;
        try {
            //1.创建session
            JSch jsch = new JSch();
            session = jsch.getSession(this.username, this.host, this.port);
            session.setPassword(this.password);
            session.setServerAliveInterval(60000);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            //2.通过Session建立链接
            session.connect();

            //3.创建sftp通道
            channel = (ChannelSftp)session.openChannel("sftp");

            //4.建立SFTP通道的连接
            channel.connect();
            //channel.setFilenameEncoding(this.encoding);

            //5.切换工作目录
            channel.cd(this.remoteDir);

            //6.文件下载
            List<String> fileNames = listFileName(channel);
            if (!fileNames.contains(fileName)) {
                logger.error("SFTP download file [" + fileName +"] not found.");
                throw new IOException("SFTP download file not found.");
            }

            output = new ByteArrayOutputStream();
            channel.get(fileName, output);
            byte[] bytes = output.toByteArray();
            return bytes;
        } catch (Exception e) {
            logger.error("SFTP download file failed.", e);
            throw new RuntimeException(e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    logger.error("SFTP download stream close exception", e);
                }
            }

            disconnect(session, channel);
        }
    }

    /**
     * 通过文件名称前缀下载
     * @param fileNamePrefix
     * @return
     */
    @Override
    public List<byte[]> downloadByFileNamePrefix(String fileNamePrefix) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            //1.创建session
            JSch jsch = new JSch();
            session = jsch.getSession(this.username, this.host, this.port);
            session.setPassword(this.password);
            session.setServerAliveInterval(60000);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            //2.通过Session建立链接
            session.connect();

            //3.创建sftp通道
            channel = (ChannelSftp)session.openChannel("sftp");

            //4.建立SFTP通道的连接
            channel.connect();
            //channel.setFilenameEncoding(this.encoding);

            //5.切换工作目录
            channel.cd(this.remoteDir);

            //6.下载文件
            List<String> fileNames = listFileName(channel);
            List<String> needFileNames = getByPrefix(fileNames, fileNamePrefix);
            if (needFileNames == null || needFileNames.size() == 0) {
                logger.error("SFTP download file [" + fileNamePrefix +"] not found.");
                throw new IOException("SFTP download file not found.");
            }

            List<byte[]> rList = new ArrayList<>();
            for(String fileName :  needFileNames){
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    channel.get(fileName, output);
                    byte[] bytes = output.toByteArray();
                    rList.add(bytes);
                }finally {
                    try {
                        output.close();
                    }catch (Exception e){
                        logger.error("SFTP download stream close exception", e);
                    }
                }
            }

            return rList;
        } catch (Exception e) {
            logger.error("SFTP download file failed.", e);
            throw new RuntimeException(e);
        } finally {
            disconnect(session, channel);
        }
    }

    @Override
    public void delete(String fileName) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            //1.创建session
            JSch jsch = new JSch();
            session = jsch.getSession(this.username, this.host, this.port);
            session.setPassword(this.password);
            session.setServerAliveInterval(60000);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            //2.通过Session建立链接
            session.connect();

            //3.创建sftp通道
            channel = (ChannelSftp)session.openChannel("sftp");

            //4.建立SFTP通道的连接
            channel.connect();
            //channel.setFilenameEncoding(this.encoding);

            //5.切换工作目录
            channel.cd(this.remoteDir);

            //6.文件下载
            List<String> fileNames = listFileName(channel);
            if (!fileNames.contains(fileName)) {
                logger.error("SFTP delete file [" + fileName +"] not found.");
                throw new IOException("SFTP delete file not found.");
            }

            channel.rm(fileName);
        } catch (Exception e) {
            logger.error("SFTP delete file failed.", e);
            throw new RuntimeException(e);
        } finally {
            disconnect(session, channel);
        }
    }

    /**
     * 建立sftp连接
     * @param session
     * @param channel
     * @throws Exception
     */
    private void connect(Session session, ChannelSftp channel) throws Exception {
        //1.创建session
        JSch jsch = new JSch();
        session = jsch.getSession(this.username, this.host, this.port);
        session.setPassword(this.password);
        session.setServerAliveInterval(60000);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        //2.通过Session建立链接
        session.connect();

        //3.创建sftp通道
        channel = (ChannelSftp)session.openChannel("sftp");

        //4.建立SFTP通道的连接
        channel.connect();
        channel.setFilenameEncoding(this.encoding);

        //5.切换工作目录
        channel.cd(this.remoteDir);
    }

    /**
     * 断开sftp连接
     * @param session
     * @param channel
     */
    private void disconnect(Session session, Channel channel) {
        if(channel != null){
            if(channel.isConnected()){
                try {
                    channel.disconnect();
                }catch (Exception e){
                    logger.error("SFTP server channel disconnect exception", e);
                }
            }
        }

        if(session != null){
            if(session.isConnected()){
                try {
                    session.disconnect();
                }catch (Exception e){
                    logger.error("SFTP server session disconnect exception", e);
                }
            }
        }
    }

    /**
     * 获取当前工作目录下的所有文件
     * @param channel
     * @return
     * @throws Exception
     */
    private List<String> listFileName(ChannelSftp channel) throws Exception {
        List<String> sftpFileNameList = new ArrayList();
        if (channel != null) {
            Vector vector = channel.ls(this.remoteDir);
            if(vector != null && vector.size() > 0){
                Object[] array = vector.toArray();
                for (int i = 0,len = array.length; i < len; i++) {
                    ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) array[i];
                    if(!lsEntry.getAttrs().isDir()){
                        sftpFileNameList.add(lsEntry.getFilename());
                    }
                }
            }
        }
        return sftpFileNameList;
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


    /**
     * 创建一个文件目录
     */
    public void createDir(String createpath) {

        Session session = null;
        ChannelSftp channel = null;
        try {
            //1.创建session
            JSch jsch = new JSch();
            session = jsch.getSession(this.username, this.host, this.port);
            session.setPassword(this.password);
            session.setServerAliveInterval(60000);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            //2.通过Session建立链接
            session.connect();

            //3.创建sftp通道
            channel = (ChannelSftp)session.openChannel("sftp");

            //4.建立SFTP通道的连接
            channel.connect();

            if (isDirExist(createpath,channel)) {
                channel.cd(createpath);
            }

            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString(),channel)) {
                    channel.cd(filePath.toString());
                } else {
                    // 建立目录
                    channel.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    channel.cd(filePath.toString());
                }
            }
        } catch (Exception e) {
            logger .error("创建路径错误：" + createpath,e);
        }finally {
            disconnect(session,channel);
        }
    }

    /**
     * 判断目录是否存在
     */
    public boolean isDirExist(String directory,ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
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
}
