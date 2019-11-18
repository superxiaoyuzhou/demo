package com.chinaums.commons.extend.poi;

import com.chinaums.commons.extend.poi.converter.FieldConverter;

import java.lang.reflect.Field;

/**
 * 数据项
 * @author xionglei
 * @create 2018-06-07 9:44
 */

public class ExportItem implements Comparable<ExportItem> {
    private Field field;
    private String display;
    private int index;
    private Short width;
    private Boolean isExportData;
    private String format;
    private Class<?> enumClass;
    private String methodName;
    private Class<? extends FieldConverter> converter;
    private ExportTypeEnum exportType;


    @Override
    public int compareTo(ExportItem o) {
        int x = this.index;
        int y = o.getIndex();
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public Boolean getIsExportData() {
        return isExportData;
    }

    public void setIsExportData(Boolean isExportData) {
        isExportData = isExportData;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Boolean getExportData() {
        return isExportData;
    }

    public void setExportData(Boolean exportData) {
        isExportData = exportData;
    }

    public Class<?> getEnumClass() {
        return enumClass;
    }

    public void setEnumClass(Class<?> enumClass) {
        this.enumClass = enumClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<? extends FieldConverter> getConverter() {
        return converter;
    }

    public void setConverter(Class<? extends FieldConverter> converter) {
        this.converter = converter;
    }

    public ExportTypeEnum getExportType() {
        return exportType;
    }

    public void setExportType(ExportTypeEnum exportType) {
        this.exportType = exportType;
    }
}
