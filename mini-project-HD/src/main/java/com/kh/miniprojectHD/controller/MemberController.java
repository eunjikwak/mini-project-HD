package com.kh.miniprojectHD.controller;


import com.kh.miniprojectHD.dao.MemberDAO;
import com.kh.miniprojectHD.vo.MemberVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000" )
@RestController
public class MemberController {

    //Get :회원조회
    @GetMapping("/member/{name}")
    public ResponseEntity<List<MemberVO>> memberList(@PathVariable("name") String name){
        MemberDAO dao = new MemberDAO();
        List<MemberVO> list = dao.memberSelect(name);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
    // POST : 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String user = loginData.get("id");
        String pwd = loginData.get("pwd");
        MemberDAO dao = new MemberDAO();
        boolean result = dao.loginCheck(user, pwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
