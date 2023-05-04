package com.kh.miniprojectHD.dao;

import com.kh.miniprojectHD.common.Common;
import com.kh.miniprojectHD.vo.ReservationVO;
import com.kh.miniprojectHD.vo.RestaurantVO;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class RestaurantDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pStmt = null;

    //매장 이름 조회

    public RestaurantVO restNameSelect (String id) {
        RestaurantVO vo = null;
        try {
            conn = Common.getConnection(); //연결
            stmt = conn.createStatement(); //정적인 sql 사용
            String sql= "SELECT * FROM RESTAURANT WHERE RESTAURANT_ID = '"+id+"'";
            rs = stmt.executeQuery(sql); //
            while(rs.next()){ //읽을 행이 있으면 참
                String memId = rs.getString("MEMBER_ID");
                String restId = rs.getString("RESTAURANT_ID");
                String restName = rs.getString("RESTAURANT_NAME");
                Date restDate = rs.getDate("RESTAURANT_DATE");
                int isAvailable = rs.getInt("RESERVATION_POSSIBILITY");
                String category = rs.getString("RESTAURANT_CATEGORY");
                vo = new RestaurantVO(memId,restId,restName,restDate,isAvailable,category);

            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();

        }
        return vo;
    }
}
