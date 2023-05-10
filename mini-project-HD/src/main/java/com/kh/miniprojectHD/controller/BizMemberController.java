package com.kh.miniprojectHD.controller;

import com.kh.miniprojectHD.dao.BizMemberDAO;
import com.kh.miniprojectHD.vo.BizMemberVO;
import com.kh.miniprojectHD.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000" )
@RestController
public class BizMemberController {
    @Autowired
    private BizMemberDAO dao;
    //Get :회원조회
    @GetMapping("/business/member")
    public ResponseEntity<List<BizMemberVO>> bizMemberList(@RequestParam String name){
        List<BizMemberVO> list = dao.bizMemberSelect(name);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
    // POST : 로그인
    @PostMapping("/business/login")
    public ResponseEntity<Boolean> bizMemberLogin(@RequestBody Map<String, String> loginData) {
        String user = loginData.get("id");
        String pwd = loginData.get("pwd");
        boolean result = dao.loginCheck(user, pwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
