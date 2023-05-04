package com.kh.miniprojectHD.controller;

import com.kh.miniprojectHD.dao.ReviewDAO;
import com.kh.miniprojectHD.vo.InquiryVO;
import com.kh.miniprojectHD.vo.ReviewVO;
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
public class ReviewController {
    @Autowired
    private ReviewDAO dao;
    //Get : 리뷰 조회
    @GetMapping("/review")
    public ResponseEntity<List<ReviewVO>> reviewList (@RequestParam String name) {
        List<ReviewVO> list = dao.reviewSelect(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
