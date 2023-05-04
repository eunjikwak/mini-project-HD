package com.kh.miniprojectHD.dao;

import com.kh.miniprojectHD.common.Common;
import com.kh.miniprojectHD.vo.InquiryVO;
import com.kh.miniprojectHD.vo.ReviewVO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pStmt = null;

    //리뷰 조회

    public List<ReviewVO> reviewSelect(String id) {
        List<ReviewVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); //연결
            stmt = conn.createStatement(); //정적인 sql 사용
            System.out.println(id);
            String sql= "SELECT REVIEW_ID, RE.MEMBER_ID, R.RESTAURANT_ID, R.RESTAURANT_NAME, REVIEW_TITLE, REVIEW_CONTENT, REVIEW_IMAGE_FILE_NAME, REVIEW_DATE, RATING\n" +
                    "FROM REVIEW RE\n" +
                    "JOIN RESTAURANT R ON RE.RESTAURANT_ID = R.RESTAURANT_ID\n" +
                    "WHERE RE.MEMBER_ID ='" + id + "'";
            rs = stmt.executeQuery(sql); //
            while(rs.next()){
                int reviewId = rs.getInt("REVIEW_ID");
                String memId = rs.getString("MEMBER_ID");
                String restId = rs.getString("RESTAURANT_ID");
                String restName = rs.getString("RESTAURANT_NAME");
                String reviewTitle = rs.getString("REVIEW_TITLE");
                String reviewContent = rs.getString("REVIEW_CONTENT");
                String reviewFileName = rs.getString("REVIEW_IMAGE_FILE_NAME");
                Date reviewDate = rs.getDate("REVIEW_DATE");
                int rating = rs.getInt("RATING");
                ReviewVO vo = new ReviewVO(reviewId,memId,restId,restName,reviewTitle,reviewContent,reviewFileName,reviewDate,rating); //하나의 행(레코드)에 대한 정보저장을 위한 객체생성
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
