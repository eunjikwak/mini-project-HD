package com.kh.miniprojectHD.controller;

import com.kh.miniprojectHD.dao.MemberDAO;
import com.kh.miniprojectHD.dao.RestaurantDAO;
import com.kh.miniprojectHD.dao.RestaurantInfoDAO;
import com.kh.miniprojectHD.vo.RestaurantInfoVO;
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

public class RestaurantInfoController {
    @Autowired
    private RestaurantInfoDAO dao; //autowired로 의존성 주입

    @GetMapping("/restaurant/info")
    public ResponseEntity<List<RestaurantInfoVO>> restaurantInfo(@RequestParam String restaurantId){
        RestaurantInfoDAO dao = new RestaurantInfoDAO();
        RestaurantVO vo = new RestaurantVO();
        vo.setRestId(restaurantId);

        List<RestaurantInfoVO> list = dao.infoSelect(vo);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/business/restaurantInfo")
    public ResponseEntity<RestaurantInfoVO> restaurantAllInfo(@RequestParam String restId){
        RestaurantInfoVO vo =  dao.restInfoSelect(restId);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }
}
