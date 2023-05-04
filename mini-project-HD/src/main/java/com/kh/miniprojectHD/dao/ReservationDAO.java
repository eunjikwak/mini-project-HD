package com.kh.miniprojectHD.dao;

import com.kh.miniprojectHD.common.Common;
import com.kh.miniprojectHD.vo.MemberVO;
import com.kh.miniprojectHD.vo.ReservationVO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pStmt = null;

    //예약조회
    public List<ReservationVO> resvSelect(String id, String stat) {
        List<ReservationVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); //연결
            stmt = conn.createStatement(); //정적인 sql 사용
            System.out.println(id);
            String sql = "SELECT RESERVATION_ID, RE.MEMBER_ID, RE.RESTAURANT_ID, R.RESTAURANT_NAME, RESERVATION_DATE, APPLICATION_DATE, CONFIRM_DATE, RESERVATION_REQUEST, RESERVATION_SEAT, RESERVATION_PEOPLE, RESERVATION_CONDITION " +
                        "FROM RESERVATION RE JOIN RESTAURANT R ON RE.RESTAURANT_ID = R.RESTAURANT_ID " +
                        "WHERE RE.MEMBER_ID = '" + id + "' AND RESERVATION_CONDITION = '" + stat + "'";
            rs = stmt.executeQuery(sql); //
            while(rs.next()){ //읽을 행이 있으면 참
                int resvId = rs.getInt("RESERVATION_ID");
                String memId = rs.getString("MEMBER_ID");
                String restId = rs.getString("RESTAURANT_ID");
                String restName = rs.getString("RESTAURANT_NAME");
                Date resvDate = rs.getDate("RESERVATION_DATE");
                Date applicationDate = rs.getDate("APPLICATION_DATE");
                Date confirmDate = rs.getDate("CONFIRM_DATE");
                String resvRequest = rs.getString("RESERVATION_REQUEST");
                int resvSeat = rs.getInt("RESERVATION_SEAT");
                int resvPeople = rs.getInt("RESERVATION_PEOPLE");
                String resvStat = rs.getString("RESERVATION_CONDITION");
                ReservationVO vo = new ReservationVO(resvId,memId,restId,restName,resvDate,applicationDate,confirmDate,resvRequest,resvSeat,resvPeople,resvStat);
                list.add(vo);

            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();

        }
        return list;
    }
}
