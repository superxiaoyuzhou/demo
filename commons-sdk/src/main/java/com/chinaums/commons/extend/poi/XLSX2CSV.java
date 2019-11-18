package com.chinaums.commons.extend.poi;

import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * xlsx to csv 工具类
 * 备注：
 * @author xionglei
 * @create 2018-08-14 21:51
 */
public class XLSX2CSV {

    private static final Logger logger = LoggerFactory.getLogger(XLSX2CSV.class);

    private final OPCPackage xlsxPackage;

    /**
     * csv数据
     */
    private final List<List<String>> excelData = new ArrayList<>();

    public XLSX2CSV(OPCPackage pkg) {
        this.xlsxPackage = pkg;
    }

    public XLSX2CSV(String file) throws InvalidFormatException {
        this.xlsxPackage = OPCPackage.open(file, PackageAccess.READ);
    }

    public XLSX2CSV(File file) throws InvalidFormatException {
        this.xlsxPackage = OPCPackage.open(file, PackageAccess.READ);
    }

    public XLSX2CSV(InputStream inputStream) throws IOException, InvalidFormatException {
        this.xlsxPackage = OPCPackage.open(inputStream);
    }

    /**
     * sheet 解析
     * @param sheetIndex 工作簿索引，从0开始
     * @param startRowIndex 开始行，从0开始，默认0
     * @param endRowIndex 结束行(闭区间)，默认-1，表示不限制结束行
     * @param startColIndex 开始列，从0开始，默认0
     * @param endColIndex 结束列(闭区间)，默认-1，表示不限制结束列
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    public void process(int sheetIndex, int startRowIndex, int endRowIndex, int startColIndex, int endColIndex) throws IOException, OpenXML4JException, SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        int index = 0;
        while (iter.hasNext()) {
            try (InputStream stream = iter.next()) {
                if(index == sheetIndex){
                    String sheetName = iter.getSheetName();
                    logger.info("开始读取sheet[{}],sheet名称[{}]", index, sheetName);
                    SheetToCSV sheetToCSV = new SheetToCSV(startRowIndex, endRowIndex, startColIndex, endColIndex);
                    processSheet(styles, strings, sheetToCSV, stream);
                    excelData.addAll(sheetToCSV.getSheetData());
                }
            }
            ++index;
        }
    }

    /**
     * sheet 解析
     * @param styles
     * @param strings
     * @param sheetHandler
     * @param sheetInputStream
     * @throws IOException
     * @throws SAXException
     */
    public void processSheet(Styles styles, SharedStrings strings, XSSFSheetXMLHandler.SheetContentsHandler sheetHandler, InputStream sheetInputStream) throws IOException, SAXException {
        DataFormatter formatter = new DataFormatter();
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = SAXHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(
                    styles, null, strings, sheetHandler, formatter, false);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch(ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }

    public List<List<String>> getExcelData() {
        return excelData;
    }

    /**
     * SheetContentsHandler
     */
    private class SheetToCSV implements XSSFSheetXMLHandler.SheetContentsHandler {
        private int currentRow = -1;
        private int currentCol = -1;

        private int startRowIndex = 0;
        private int endRowIndex = -1;
        private int startColIndex = 0;
        private int endColIndex = -1;
        private List<List<String>> sheetData = new ArrayList<>();
        private List<String> rowData;

        public SheetToCSV(){
            super();
        }

        public SheetToCSV(int startRowIndex, int endRowIndex, int startColIndex, int endColIndex){
            this.startRowIndex = startRowIndex;
            this.endRowIndex = endRowIndex;
            this.startColIndex = startColIndex;
            this.endColIndex = endColIndex;
        }

        /**
         * 空行处理
         * @param rowNumber
         */
        private void doBlankRow(int rowNumber) {
            for(int i=currentRow; i < rowNumber - 1; i++){
                logger.warn("row index:[{}] is blank", rowNumber);
            }
        }

        private boolean isDoRow(int rowNumber){
            if(rowNumber >= startRowIndex){
                if(endRowIndex == -1 || rowNumber <= endRowIndex){
                    return true;
                }
            }
            return false;
        }

        private boolean isDoCol(int colNumber){
            if(colNumber >= startColIndex){
                if(endColIndex == -1 || colNumber <= endColIndex){
                    return true;
                }
            }
            return false;
        }

        private boolean isDoCell(int rowNumber, int colNumber){
            return isDoRow(rowNumber) && isDoCol(colNumber);
        }

        @Override
        public void startRow(int rowNum) {
            // 判断当前行是否需要处理
            if(!isDoRow(rowNum)){
                return;
            }

            // 处理空数据行
            doBlankRow(rowNum);

            // 初始化
            currentRow = rowNum;
            currentCol = -1;
            rowData = new ArrayList<>();
        }

        @Override
        public void endRow(int rowNum) {
            // 判断当前行是否需要处理
            if(!isDoRow(rowNum)){
                return;
            }

            // 补足数据列
            for (int i=(currentCol>=startColIndex?currentCol+1:startColIndex); i<=endColIndex; i++) {
                rowData.add(null);
            }

            sheetData.add(rowData);
        }

        @Override
        public void cell(String cellReference, String formattedValue,
                         XSSFComment comment) {
            // gracefully handle missing CellRef here in a similar way as XSSFCell does
            if(cellReference == null) {
                cellReference = new CellAddress(currentRow, currentCol).formatAsString();
            }
            int thisRow = (new CellReference(cellReference)).getRow();
            int thisCol = (new CellReference(cellReference)).getCol();

            // 判断当前单元格是否需要处理
            if(!isDoCell(thisRow, thisCol)){
                return;
            }

            // 处理空数据列(如果某行中间有单元格为空，则不会进入此方法，即空单元格的情况不会进入此方法)
            for (int i=(currentCol>=startColIndex?currentCol+1:startColIndex); i<thisCol; i++) {
                rowData.add(null);
            }
            currentCol = thisCol;

            rowData.add(formattedValue == null ? null : formattedValue.trim());
        }

        public List<List<String>> getSheetData() {
            return sheetData;
        }
    }
}