package com.chinaums.commons.transport;

import java.util.List;

/**
 * 文件传输工具
 *
 * @auther xionglei
 * @create 2018-02-02 10:27
 */

public interface FileTransport {

    /**
     * 上传文件
     * @param fileName 文件名称
     * @param fileContent 文件内容
     */
    boolean upload(String fileName, byte[] fileContent);

    /**
     * 下载文件
     * @param fileName 文件名称
     * @return
     */
    byte[] download(String fileName);

    /**
     * 通过文件名称前缀下载
     * @param fileNamePrefix
     * @return
     */
    List<byte[]> downloadByFileNamePrefix(String fileNamePrefix);

    /**
     * 删除文件
     * @param fileName 文件名称
     * @return
     */
    void delete(String fileName);
}
