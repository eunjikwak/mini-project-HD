package com.kh.miniprojectHD.controller;


import com.kh.miniprojectHD.dao.InquiryDAO;
import com.kh.miniprojectHD.vo.InquiryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000" )
@RestController
public class InquiryController {

    @Autowired
    private InquiryDAO dao;
    //Get : 문의 조회
    @GetMapping("/inquiry")
    public ResponseEntity<List<InquiryVO>> inquiryList (@RequestParam String name) {
        List<InquiryVO> list = dao.inquirySelect(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }




}
