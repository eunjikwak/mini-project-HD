package com.kh.miniprojectHD.controller;

import com.kh.miniprojectHD.dao.RestMenuDAO;
import com.kh.miniprojectHD.dao.RestaurantDAO;
import com.kh.miniprojectHD.vo.RestMenuVO;
import com.kh.miniprojectHD.vo.RestaurantVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class RestMenuController {
    // 메뉴 정보 불러오기
    @GetMapping("/restaurant/menu")
    public ResponseEntity<List<RestMenuVO>> restaurantMenu(@RequestParam String restaurantId){
        RestMenuDAO dao = new RestMenuDAO();
        RestaurantVO vo = new RestaurantVO();
        vo.setRestId(restaurantId);

        List<RestMenuVO> list = dao.menuSelect(vo);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
