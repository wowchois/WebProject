package com.base.jpaproject.main.service;

import com.base.jpaproject.main.util.SheetHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
@Slf4j
public class ExcelHandlerService extends SheetHandler {

    @Override
    protected void executeMapper() {
        log.info("[ExcelHandlerService] now paging : {} / rows : {}"
                ,this.getRowNumber()
                ,this.getRowsMapList());
    }

    @Override
    public void startDocument() throws SAXException {
        log.info("start document");
    }
}
