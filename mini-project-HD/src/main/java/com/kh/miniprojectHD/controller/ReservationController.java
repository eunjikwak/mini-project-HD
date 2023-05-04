package com.kh.miniprojectHD.controller;

import com.kh.miniprojectHD.dao.InquiryDAO;
import com.kh.miniprojectHD.dao.ReservationDAO;
import com.kh.miniprojectHD.vo.InquiryVO;
import com.kh.miniprojectHD.vo.ReservationVO;
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
public class ReservationController {

    @Autowired
    private ReservationDAO dao;
    //Get : 문의 조회
    @GetMapping("/resv")
    public ResponseEntity<List<ReservationVO>> resvList (@RequestParam String name, String stat) {
        System.out.println(name + stat);
        List<ReservationVO> list = dao.resvSelect(name,stat);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
