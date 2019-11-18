package com.chinaums.commons.extend.poi;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author xionglei
 * @create 2018-09-15 22:54
 */

public interface OnSettingHanlder {
    CellStyle getHeadCellStyle(SXSSFWorkbook wb);

    CellStyle getBodyCellStyle(SXSSFWorkbook wb);
}
