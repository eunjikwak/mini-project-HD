package com.kh.miniprojectHD.controller;

import com.kh.miniprojectHD.dao.MemberDAO;
import com.kh.miniprojectHD.dao.RestaurantDAO;
import com.kh.miniprojectHD.dao.RestaurantInfoDAO;
import com.kh.miniprojectHD.vo.MemberVO;
import com.kh.miniprojectHD.vo.RestaurantInfoVO;
import com.kh.miniprojectHD.vo.RestaurantVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // POST : 매장 상세정보 업데이트(곽은지)
    @PostMapping("/business/restInfo/update")
    public ResponseEntity<Boolean> memberUpdate(@RequestBody Map<String, RestaurantInfoVO> data) {
        RestaurantInfoVO vo = data.get("vo");
        boolean result = dao.restInfoUpdate(vo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
