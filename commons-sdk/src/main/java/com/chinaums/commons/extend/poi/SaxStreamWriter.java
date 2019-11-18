package com.chinaums.commons.extend.poi;

import com.chinaums.commons.extend.poi.converter.FieldConverter;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * sax stream 方式写excel
 *
 * @author xionglei
 * @create 2018-09-13 23:44
 */

public class SaxStreamWriter {

    private static final Logger logger = LoggerFactory.getLogger(SaxStreamWriter.class);

    private Class<?> clazz;

    private List<ExportItem> exportItemList = new ArrayList<>();

    private static final String EMPTY_CELL_VALUE = "";

    /**
     * 默认持久化阀值(内存中保存行数)
     */
    private static final int DEFAULT_ROW_ACCESS_WINDOW_SIZ = 1000;

    /**
     * 单sheet默认上限
     */
    private static final int DEFAULT_SHEET_MAX_ROW_SIZE = 1000000;

    public SaxStreamWriter(Class<?> clazz) {
        this.clazz = clazz;
        initExportItem();
    }

    /**
     * 初始化每列
     */
    private void initExportItem() {
        if (this.clazz != null) {
            Field[] fields = this.clazz.getDeclaredFields();
            for (Field field : fields) {
                initOneExportItem(field);
            }
            Collections.sort(exportItemList);
        }
    }

    /**
     * 初始化一列
     *
     * @param field
     */
    private void initOneExportItem(Field field) {
        ExportConfig exportConfig = field.getAnnotation(ExportConfig.class);
        if (exportConfig != null) {
            ExportItem exportItem = new ExportItem();
            exportItem.setField(field);
            exportItem.setDisplay(exportConfig.value());
            exportItem.setIndex(exportConfig.index());
            exportItem.setWidth(exportConfig.width());
            exportItem.setExportData(exportConfig.isExportData());
            exportItem.setFormat(exportConfig.format());
            exportItem.setEnumClass(exportConfig.enumClass());
            exportItem.setMethodName(exportConfig.methodName());
            exportItem.setConverter(exportConfig.converter());
            exportItem.setExportType(exportConfig.exportType());
            exportItemList.add(exportItem);
        }
    }

    /**
     * 写excel
     *
     * @param sheetName
     * @param data
     * @param response
     */
    public void write(String sheetName, List<?> data, HttpServletResponse response) {
        try {
            if (response != null) {
                String fileName = String.format("%s%s", getExportFileName(sheetName), getExcelSuffix());
                response.setContentType(getContentType());
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                write(sheetName, data, response.getOutputStream());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 写excel
     *
     * @param sheetName
     * @param data
     * @param outputStream
     */
    public void write(String sheetName, List<?> data, OutputStream outputStream) {
        long begin = System.currentTimeMillis();
        SXSSFWorkbook wb = new SXSSFWorkbook(DEFAULT_ROW_ACCESS_WINDOW_SIZ);
        wb.setCompressTempFiles(true);

        if (CollectionUtils.isEmpty(data) || data.size() <= DEFAULT_SHEET_MAX_ROW_SIZE) {
            writeSheet(wb, sheetName, data);
        } else {
            List<? extends List<?>> partition = Lists.partition(data, DEFAULT_SHEET_MAX_ROW_SIZE);
            for (int i = 0, len = partition.size(); i < len; i++) {
                writeSheet(wb, new StringBuilder(sheetName).append("-").append(i).toString(), partition.get(i));
            }
        }

        try {
            wb.write(outputStream);
            outputStream.flush();
            wb.dispose();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info(String.format("Excel处理完成,共生成数据:%s行 (不包含表头),耗时：%s seconds.", data != null ? data.size() : 0, (float) (System.currentTimeMillis() - begin) / 1000.0F));
    }

    /**
     * 写sheet
     *
     * @param wb
     * @param sheetName
     * @param data
     */
    public void writeSheet(SXSSFWorkbook wb, String sheetName, List<?> data) {
        writeSheet(wb, sheetName, data, new OnSettingHanlder() {
            @Override
            public CellStyle getHeadCellStyle(SXSSFWorkbook workbook) {
                CellStyle cellStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                cellStyle.setFillForegroundColor((short) 12);
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setBorderTop(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setBorderBottom(BorderStyle.THIN);
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle.setFillForegroundColor((short) 17);
                cellStyle.setFillBackgroundColor((short) 17);
                font.setFontHeightInPoints((short) 14);
                font.setColor((short) 9);
                cellStyle.setFont(font);
                return cellStyle;
            }

            @Override
            public CellStyle getBodyCellStyle(SXSSFWorkbook workbook) {
                return null;
            }
        });
    }

    /**
     * 写sheet
     *
     * @param wb
     * @param sheetName
     * @param data
     * @param handler
     */
    private void writeSheet(SXSSFWorkbook wb, String sheetName, List<?> data, OnSettingHanlder handler) {
        Sheet sheet = wb.createSheet(sheetName);
        writeHeader(wb, sheet, handler);
        writeBody(wb, sheet, data, handler);
    }

    /**
     * 写sheet表头
     *
     * @param wb
     * @param sheet
     * @param handler
     */
    private void writeHeader(SXSSFWorkbook wb, Sheet sheet, OnSettingHanlder handler) {
        Row header = sheet.createRow(0);
        for (int i = 0, len = exportItemList.size(); i < len; i++) {
            ExportItem exportItem = exportItemList.get(i);
            sheet.setColumnWidth(i, (short) ((int) ((double) exportItem.getWidth() * 35.7D)));
            Cell cell = header.createCell(i);
            cell.setCellValue(exportItem.getDisplay());
            CellStyle style = handler.getHeadCellStyle(wb);
            if (style != null) {
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 写sheet表体
     *
     * @param wb
     * @param sheet
     * @param data
     * @param handler
     */
    private void writeBody(SXSSFWorkbook wb, Sheet sheet, List<?> data, OnSettingHanlder handler) {
        if (data != null && data.size() > 0) {
            CellStyle cellStyle = wb.createCellStyle();
            DataFormat format = wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("0.00"));

            CellStyle cellStyleWithInt = wb.createCellStyle();
            DataFormat formatWithInt = wb.createDataFormat();
            cellStyleWithInt.setDataFormat(formatWithInt.getFormat("0"));

            CellStyle cellStyleWithAmt = wb.createCellStyle();
            DataFormat formatWithAmt = wb.createDataFormat();
            cellStyleWithAmt.setDataFormat(formatWithAmt.getFormat("###,###,###,###,##0.00"));

            for (int i = 0, len = data.size(); i < len; i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0, count = exportItemList.size(); j < count; j++) {
                    ExportItem exportItem = exportItemList.get(j);
                    sheet.setColumnWidth(j, (short) ((int) ((double) exportItem.getWidth() * 35.7D)));
                    Cell cell = row.createCell(j);
                    if (exportItem.getIsExportData()) {
                        try {
                            String cellValue = doItemValue(data.get(i), exportItem);
                            if (StringUtils.isNotBlank(cellValue)) {
                                Class<?> fieldType = exportItem.getField().getType();
                                if (fieldType == Integer.class) {
                                    cell.setCellValue(Integer.valueOf(cellValue));
                                    cell.setCellStyle(cellStyleWithInt);
                                } else if (fieldType == Double.class) {
                                    cell.setCellValue(Double.valueOf(cellValue));
                                    cell.setCellStyle(cellStyle);
                                } else if (fieldType == Float.class) {
                                    cell.setCellValue(Float.valueOf(cellValue));
                                    cell.setCellStyle(cellStyle);
                                } else if (fieldType == BigDecimal.class) {
                                    if (ExportTypeEnum.AMT == exportItem.getExportType()) {
                                        cell.setCellValue(Double.valueOf(cellValue));
                                        cell.setCellStyle(cellStyleWithAmt);
                                    } else if (ExportTypeEnum.PERCENT == exportItem.getExportType()) {
                                        BigDecimal value = new BigDecimal(cellValue);
                                        cell.setCellValue(value.multiply(new BigDecimal(100)).doubleValue());
                                        cell.setCellStyle(cellStyle);
                                    } else if (ExportTypeEnum.PERMILLAGE == exportItem.getExportType()) {
                                        BigDecimal value = new BigDecimal(cellValue);
                                        cell.setCellValue(value.multiply(new BigDecimal(1000)).doubleValue());
                                        cell.setCellStyle(cellStyle);
                                    } else {
                                        cell.setCellValue(Double.valueOf(cellValue));
                                        cell.setCellStyle(cellStyle);
                                    }
                                } else {
                                    cell.setCellValue(cellValue);
                                }
                            } else {
                                cell.setCellValue(EMPTY_CELL_VALUE);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        cell.setCellValue("******");
                    }

                    CellStyle style = handler.getBodyCellStyle(wb);
                    if (style != null) {
                        cell.setCellStyle(style);
                    }
                }
            }
        }
    }

    /**
     * 字段值处理
     *
     * @param bean
     * @param exportItem
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private String doItemValue(Object bean, ExportItem exportItem) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Object itemValue = getItemValue(bean, exportItem);

        //字段值转换器处理
        if (exportItem.getConverter() != null && exportItem.getConverter() != FieldConverter.class) {
            itemValue = doConvert(itemValue, exportItem);
        }

        //日期格式化处理
        if (itemValue != null && !StringUtils.equalsIgnoreCase("null", String.valueOf(itemValue)) && StringUtils.isNotBlank(String.valueOf(itemValue))) {
            if (exportItem.getField().getType().isAssignableFrom(Date.class)) {
                return doDateFormat(itemValue, exportItem);
            } else {
                //枚举处理
                Class<?> enumClass = exportItem.getEnumClass();
                if (enumClass != null && enumClass != Void.class) {
                    return doEnum(itemValue, exportItem);
                } else {
                    return String.valueOf(itemValue);
                }
            }
        } else {
            return EMPTY_CELL_VALUE;
        }
    }

    /**
     * 处理字段值转换
     *
     * @param itemValue
     * @param exportItem
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object doConvert(Object itemValue, ExportItem exportItem) throws IllegalAccessException, InstantiationException {
        return exportItem.getConverter().newInstance().convert(itemValue);
    }

    /**
     * 处理日期format
     *
     * @param value
     * @param exportItem
     * @return
     */
    private String doDateFormat(Object value, ExportItem exportItem) {
        if (StringUtils.isNotBlank(exportItem.getFormat())) {
            return new SimpleDateFormat(exportItem.getFormat()).format((Date) value);
        } else {
            return new SimpleDateFormat("yyyy-MM-dd").format((Date) value);
        }
    }

    /**
     * 处理枚举翻译
     *
     * @param value
     * @param exportItem
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private String doEnum(Object value, ExportItem exportItem) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> enumClass = exportItem.getEnumClass();
        String methodName = exportItem.getMethodName();
        if (StringUtils.isNotBlank(methodName)) {
            Object[] enumConstants = enumClass.getEnumConstants();
            if (enumConstants != null && enumConstants.length > 0) {
                Method method = enumClass.getDeclaredMethod(methodName, String.class);
                return (String) method.invoke(enumConstants[0], value);
            } else {
                return String.valueOf(value);
            }
        } else {
            return String.valueOf(value);
        }
    }

    /**
     * 获取字段值
     *
     * @param bean
     * @param exportItem
     * @return
     * @throws IllegalAccessException
     */
    private Object getItemValue(Object bean, ExportItem exportItem) throws IllegalAccessException {
        Field[] fields = bean.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return null;
        }
        for (Field field : fields) {
            if (field.getName().equals(exportItem.getField().getName())) {
                field.setAccessible(true);
                return field.get(bean);
            }
        }
        return null;
    }

    private String getExcelSuffix() {
        return ".xlsx";
    }

    private String getContentType() {
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    private String getExportFileName(String sheetName) {
        return String.format("导出-%s-%s", sheetName, System.currentTimeMillis());
    }
}
