package com.base.jpaproject.main.service;

import com.base.jpaproject.main.util.ExcelSheetHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ExcelService {

    public void readToList(MultipartFile file){
        ExcelSheetHandler handler = ExcelSheetHandler.readExcel(file);
        List<String> header = handler.getHeader();
        List<List<String>> rows = handler.getRows();

        log.info("## header {}",header);
        log.info("## rows size {}",rows.size());

    }

}
