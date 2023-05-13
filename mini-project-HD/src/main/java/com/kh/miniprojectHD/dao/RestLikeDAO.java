package com.kh.miniprojectHD.dao;

import com.kh.miniprojectHD.common.Common;
import com.kh.miniprojectHD.vo.InquiryVO;
import com.kh.miniprojectHD.vo.RestLikeVO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestLikeDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pStmt = null;

    //좋아요 조회
    public List<RestLikeVO> restLikeSelect(String id) {
        List<RestLikeVO> list =new ArrayList<>();
        try {
            conn = Common.getConnection(); //연결
            stmt = conn.createStatement(); //정적인 sql 사용
            System.out.println(id);
            String sql = "SELECT * FROM RESTAURANT_LIKE WHERE MEMBER_ID = "+ "'" + id + "'";

            rs = stmt.executeQuery(sql); //
            while(rs.next()){ //읽을 행이 있으면 참
                String restId = rs.getString("RESTAURANT_ID");
                String memId = rs.getString("MEMBER_ID");
                String restName = rs.getString("RESTAURANT_NAME");
                int restRating = rs.getInt("RESTAURANT_RATING");

                RestLikeVO vo = new RestLikeVO(restId,memId,restName,restRating);
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
    // 찜 등록
    public boolean addRestLike(String restId, String memId,String name) {
        int result = 0;
        String sql = "INSERT INTO RESTAURANT_LIKE(RESTAURANT_ID,MEMBER_ID,RESTAURANT_NAME) VALUES(?,?,?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, restId);
            pStmt.setString(2, memId);
            pStmt.setString(3, name);

            result = pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        if(result == 1) return true;
        else return false;
    }
    // 찜 삭제
    public boolean delRestLike(String restId,String memId) {
        int result = 0;
        String sql = "DELETE FROM RESTAURANT_LIKE WHERE RESTAURANT_ID = ? AND MEMBER_ID = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, restId);
            pStmt.setString(2, memId);

            result = pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        if(result == 1) return true;
        else return false;
    }

}
