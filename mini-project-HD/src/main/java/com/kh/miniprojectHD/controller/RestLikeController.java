package com.kh.miniprojectHD.controller;

import com.kh.miniprojectHD.dao.RestLikeDAO;
import com.kh.miniprojectHD.dao.ReviewDAO;
import com.kh.miniprojectHD.vo.RestLikeVO;
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
public class RestLikeController {
    @Autowired
    private RestLikeDAO dao;
    //Get : 찜가게 조회
    @GetMapping("/restLike")
    public ResponseEntity<List<RestLikeVO>> restLikeList (@RequestParam String name) {
        List<RestLikeVO> list = dao.restLikeSelect(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
