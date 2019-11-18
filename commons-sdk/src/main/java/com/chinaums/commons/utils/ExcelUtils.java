package com.chinaums.commons.utils;

import com.chinaums.commons.extend.poi.OnReadDataHandler;
import com.chinaums.commons.extend.poi.SaxStreamWriter;
import com.chinaums.commons.extend.poi.XLSX2CSV;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Excel工具类
 * 备注：
 * @author xionglei
 * @create 2018-08-14 21:51
 */

public class ExcelUtils {

    /**
     * 读取excel
     * @param file
     * @param onReadDataHandler
     */
    public static void readExcel(String file, OnReadDataHandler onReadDataHandler){
        readExcel(file).forEach(list -> onReadDataHandler.handler(list));
    }

    /**
     * 读取excel
     * @param file
     * @param onReadDataHandler
     */
    public static void readExcel(File file, OnReadDataHandler onReadDataHandler){
        readExcel(file).forEach(list -> onReadDataHandler.handler(list));
    }

    /**
     * 读取excel
     * @param bytes
     * @param onReadDataHandler
     */
    public static void readExcel(byte[] bytes, OnReadDataHandler onReadDataHandler){
        readExcel(bytes).forEach(list -> onReadDataHandler.handler(list));
    }

    /**
     * 读取excel
     * @param inputStream
     * @param onReadDataHandler
     */
    public static void readExcel(InputStream inputStream, OnReadDataHandler onReadDataHandler){
        readExcel(inputStream).forEach(list -> onReadDataHandler.handler(list));
    }

    /**
     * 读取excel
     * @param file
     * @return
     */
    public static List<List<String>> readExcel(String file){
        return readExcel(file, 0, 1, -1, 0, -1);
    }

    /**
     * 读取excel
     * @param file
     * @return
     */
    public static List<List<String>> readExcel(File file){
        return readExcel(file, 0, 1, -1, 0, -1);
    }

    /**
     * 读取excel
     * @param bytes
     * @return
     */
    public static List<List<String>> readExcel(byte[] bytes){
        return readExcel(bytes, 0, 1, -1, 0, -1);
    }

    /**
     * 读取excel
     * @param inputStream
     * @return
     */
    public static List<List<String>> readExcel(InputStream inputStream){
        return readExcel(inputStream, 0, 1, -1, 0, -1);
    }

    /**
     * 读取excel
     * @param file
     * @param sheetIndex
     * @param startRowIndex
     * @param startColIndex
     * @return
     */
    public static List<List<String>> readExcel(String file, int sheetIndex, int startRowIndex, int startColIndex){
        return readExcel(file, sheetIndex, startRowIndex, -1, startColIndex, -1);
    }

    /**
     * 读取excel
     * @param file
     * @param sheetIndex
     * @param startRowIndex
     * @param startColIndex
     * @return
     */
    public static List<List<String>> readExcel(File file, int sheetIndex, int startRowIndex, int startColIndex){
        return readExcel(file, sheetIndex, startRowIndex, -1, startColIndex, -1);
    }

    /**
     * 读取excel
     * @param bytes
     * @param sheetIndex
     * @param startRowIndex
     * @param startColIndex
     * @return
     */
    public static List<List<String>> readExcel(byte[] bytes, int sheetIndex, int startRowIndex, int startColIndex){
        return readExcel(bytes, sheetIndex, startRowIndex, -1, startColIndex, -1);
    }

    /**
     * 读取excel
     * @param inputStream
     * @param sheetIndex
     * @param startRowIndex
     * @param startColIndex
     * @return
     */
    public static List<List<String>> readExcel(InputStream inputStream, int sheetIndex, int startRowIndex, int startColIndex){
        return readExcel(inputStream, sheetIndex, startRowIndex, -1, startColIndex, -1);
    }

    /**
     * 读取excel
     * @param file
     * @param sheetIndex
     * @param startRowIndex
     * @param endRowIndex
     * @param startColIndex
     * @param endColIndex
     * @return
     */
    public static List<List<String>> readExcel(String file, int sheetIndex, int startRowIndex, int endRowIndex, int startColIndex, int endColIndex) {
        try {
            return readExcel(new FileInputStream(file), sheetIndex, startRowIndex, endRowIndex, startColIndex, endColIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取excel
     * @param file
     * @param sheetIndex
     * @param startRowIndex
     * @param endRowIndex
     * @param startColIndex
     * @param endColIndex
     * @return
     */
    public static List<List<String>> readExcel(File file, int sheetIndex, int startRowIndex, int endRowIndex, int startColIndex, int endColIndex) {
        try {
            return readExcel(new FileInputStream(file), sheetIndex, startRowIndex, endRowIndex, startColIndex, endColIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取excel
     * @param bytes
     * @param sheetIndex
     * @param startRowIndex
     * @param endRowIndex
     * @param startColIndex
     * @param endColIndex
     * @return
     */
    public static List<List<String>> readExcel(byte[] bytes, int sheetIndex, int startRowIndex, int endRowIndex, int startColIndex, int endColIndex) {
        try {
            return readExcel(new ByteArrayInputStream(bytes), sheetIndex, startRowIndex, endRowIndex, startColIndex, endColIndex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取excel
     * @param inputStream
     * @param sheetIndex
     * @param startRowIndex
     * @param endRowIndex
     * @param startColIndex
     * @param endColIndex
     * @return
     */
    public static List<List<String>> readExcel(InputStream inputStream, int sheetIndex, int startRowIndex, int endRowIndex, int startColIndex, int endColIndex) {
        try {
            XLSX2CSV xlsx2csv = new XLSX2CSV(inputStream);
            xlsx2csv.process(sheetIndex, startRowIndex, endRowIndex, startColIndex, endColIndex);
            return xlsx2csv.getExcelData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取excel
     * @param file
     * @param onReadDataHandler
     * @param sheetIndex
     * @param startRowIndex
     * @param endRowIndex
     * @param startColIndex
     * @param endColIndex
     */
    public static void readExcel(String file, OnReadDataHandler onReadDataHandler, int sheetIndex, int startRowIndex, int endRowIndex, int startColIndex, int endColIndex){
        readExcel(file, sheetIndex, startRowIndex, endRowIndex, startColIndex, endColIndex).forEach(list -> onReadDataHandler.handler(list));
    }

    /**
     * 写excel
     * @param sheetName
     * @param data
     * @param outputStream
     * @param clazz
     */
    public static void toExcel(String sheetName, List<?> data, OutputStream outputStream, Class<?> clazz){
        new SaxStreamWriter(clazz).write(sheetName, data, outputStream);
    }

    /**
     * 写excel
     * @param sheetName
     * @param data
     * @param response
     * @param clazz
     */
    public static void toExcel(String sheetName, List<?> data, HttpServletResponse response, Class<?> clazz){
        new SaxStreamWriter(clazz).write(sheetName, data, response);
    }
}
