package com.base.jpaproject.main.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//XSSFSheetXMLHandler : excel의 sheet를 스트리밍 처리해서 대용량 데이터 read가 가능하다.
public class ExcelSheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

    private List<List<String>> rows = new ArrayList<>();
    private List<String> row = new ArrayList<>();
    private List<String> header = new ArrayList<>();
    private int currentCol = -1; //cell
    private int currentRowNum = 0; //row

    public static ExcelSheetHandler readExcel(MultipartFile file){
        ExcelSheetHandler handler = new ExcelSheetHandler();

        try{
            OPCPackage opcPkg = OPCPackage.open(file.getInputStream()); //apache
            XSSFReader reader = new XSSFReader(opcPkg);

            //ReadOnlySharedStringsTable : sheet를 테이블처럼 읽을 수 있는 라이브러리
            ReadOnlySharedStringsTable readData = new ReadOnlySharedStringsTable(opcPkg);

            InputStream sheetStream = reader.getSheetsData().next(); //첫번째 sheet
            InputSource sheetSource = new InputSource(sheetStream);

            StylesTable style = reader.getStylesTable();

            //xml handler 생성
            ContentHandler contentHandler = new XSSFSheetXMLHandler(style,readData,handler,false);

            XMLReader xmlReader = SAXHelper.newXMLReader(); //sax형식 xmlreader생성
            xmlReader.setContentHandler(contentHandler); //xmlhandler 재정의
            xmlReader.parse(sheetSource); //xml로 파싱

            sheetStream.close();
            opcPkg.close();

        }catch (Exception e){
            log.error("### excel parsing error", e.getMessage());
        }

        return handler;
    }

    public List<List<String>> getRows(){
        return this.rows;
    }
    public List<String> getHeader(){
        return this.header;
    }

    @Override
    public void startRow(int rowNum) {
        this.currentCol = -1;
        this.currentRowNum = rowNum;
    }

    @Override
    public void endRow(int rowNum) {
        if(rowNum == 0){ //header 경우
            header = new ArrayList<>(row);
        }else{
            //header가 row 보다 더 긴 경우, row의 나머지 컬럼들은 빈값 설정.
            if(row.size() < header.size()){
                for(int i=row.size(); i<header.size(); i++){
                    row.add("");
                }
            }
            rows.add(new ArrayList<>(row));
        }
        row.clear();
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        int iCol = new CellReference(cellReference).getCol();
        int emptyCol = iCol-currentCol-1;

        for(int i=0; i<emptyCol; i++){
            row.add("");
        }
        currentCol = iCol;
        row.add(formattedValue);
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {

    }
}
