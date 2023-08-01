package com.base.jpaproject.main.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SheetHandler extends DefaultHandler {

    private final int READ_CNT = 100; //페이징할 row 개수
    private List<List<String>> rows = new ArrayList<>(READ_CNT);
    private List<String> row = new ArrayList<>();
    private List<String> header = new ArrayList<>();
    private StringBuilder contents = new StringBuilder();
    private ReadOnlySharedStringsTable readData;

    private long rowNumber = 1;
    private int pagingRowNumber = 0;
    private int currentCol = -1;

    private boolean isCellVal;
    private boolean isCellStr;
    private String cellId;

    public void readExcel(File file){
        try{
            OPCPackage opcPackage = OPCPackage.open(file);

            XSSFReader xssfReader = new XSSFReader(opcPackage);

            //excel에서 읽은 모든 데이터
            readData = new ReadOnlySharedStringsTable(opcPackage);

            InputStream inputStream = xssfReader.getSheetsData().next();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse(inputStream, this);

            inputStream.close();
            opcPackage.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private boolean isRow(String qName){
        return "row".equals(qName);
    }

    private boolean isCell(String qName){
        return "c".equals(qName);
    }

    public List<Map<String,Object>> getRowsMapList(){
        if(rows.isEmpty()){
            log.error("rows is empty");
        }

        return rows.stream()
                .filter(this::isRowNotEmpty)
                .map(data -> mappingHeaderToData(header,data))
                .collect(Collectors.toList());
    }

    //util
    public boolean isRowNotEmpty(List<String> dataList){
        return Boolean.FALSE.equals(
                dataList == null
                        || dataList.isEmpty()
                        || "".equals(String.join("",dataList))
                        || "".equals(dataList.get(0))
                );
    }
    //header - data 매핑
    public Map<String, Object> mappingHeaderToData(List<String > header, List<String> dataList){
        return IntStream.range(0, header.size())
                .boxed()
                .collect(Collectors.toMap(i -> header.get(i), i -> dataList.get(i)));
    }

    private void cell(){
        String cellData = "";

        if(isCellVal && isCellStr){
            int idx = Integer.parseInt(contents.toString());
            cellData = new XSSFRichTextString(
                    readData.getItemAt(idx).getString()
            ).toString();
        }else if(isCellVal){
            cellData = contents.toString();
        }else{
            return;
        }

        //empty cell 처리
        int iCol = new CellReference(cellId).getCol();
        int emptyCol = iCol-currentCol-1;

        for(int i=0; i<emptyCol; i++){
            row.add("");
        }
        currentCol = iCol;
        isCellVal = false;

        row.add(cellData);
    }

    private void endRow(){
        if(rowNumber == 1){
            header.addAll(new ArrayList<>(row));
        }
        else{
            rows.add(new ArrayList<>(row));
            pagingRowNumber++;
        }

        if(pagingRowNumber == READ_CNT) { //paging처리
            processRow(); // insert 실행
            pagingRowNumber = 0;
            rows.clear();
        }
        row.clear();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contents = new StringBuilder();

        if(isRow(qName)){
            String strRowNum = attributes.getValue("r");
            rowNumber = Long.parseLong(strRowNum);
        }
        else if(isCell(qName)){
            cellId = attributes.getValue("r");
            String cellType = attributes.getValue("t");
            isCellStr = cellType != null && "s".equals(cellType);
        }
        else if("v".equals(qName)){
            isCellVal = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(isRow(qName)){
            this.endRow();
            currentCol = -1;
        }else if(isCell(qName)){
            this.cell();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contents.append(new String(ch, start, length));
    }

    @Override
    public void endDocument() throws SAXException {
        processRow(); //마지막 페이지 실행
    }

    private void processRow(){
        try{
            executeMapper();
            Thread.sleep(100);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    //각 서비스에서 override해서 사용
    public void executeMapper() throws ExecutionException {
    }
}
