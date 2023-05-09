package com.kh.miniprojectHD.controller;


import com.kh.miniprojectHD.dao.ReservationDAO;
import com.kh.miniprojectHD.dao.RestaurantDAO;
import com.kh.miniprojectHD.vo.ReservationVO;
import com.kh.miniprojectHD.vo.RestJoinVO;
import com.kh.miniprojectHD.vo.RestaurantVO;
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
public class RestaurantController {
    @Autowired
    private RestaurantDAO dao;
    //Get : 문의 조회
    @GetMapping("/restName")
    public ResponseEntity<RestaurantVO> resvList (@RequestParam String name) {
        RestaurantVO vo = dao.restNameSelect(name);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    // 상단 고정 상세 정보
    @GetMapping("/restaurant")
    public ResponseEntity<List<RestJoinVO>> joinInfo (@RequestParam String restaurantId){

        RestaurantVO vo = new RestaurantVO();
        vo.setRestId(restaurantId);

        List<RestJoinVO> list = dao.rtSelect(vo);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
