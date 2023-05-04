package com.kh.miniprojectHD.controller;


import com.kh.miniprojectHD.dao.MemberDAO;
import com.kh.miniprojectHD.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000" )
@RestController
public class MemberController {
    @Autowired
    private MemberDAO dao; //autowired로 의존성 주입
    //Get :회원조회
    @GetMapping("/member")
    public ResponseEntity<List<MemberVO>> memberList(@RequestParam String name){
        List<MemberVO> list = dao.memberSelect(name);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
    // POST : 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String user = loginData.get("id");
        String pwd = loginData.get("pwd");
        boolean result = dao.loginCheck(user, pwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST : 회원정보 업데이트(곽은지)
    @PostMapping("/update")
    public ResponseEntity<Boolean> memberUpdate(@RequestBody Map<String, MemberVO> data) {
        MemberVO vo = data.get("vo");
        boolean result = dao.memberUpdate(vo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
