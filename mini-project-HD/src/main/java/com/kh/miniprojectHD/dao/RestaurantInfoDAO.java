package com.kh.miniprojectHD.dao;

import com.kh.miniprojectHD.common.Common;
import com.kh.miniprojectHD.vo.RestaurantInfoVO;
import com.kh.miniprojectHD.vo.RestaurantVO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RestaurantInfoDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 매장 상세 정보 불러오기
    public List<RestaurantInfoVO> infoSelect(RestaurantVO restaurantVO){
        List<RestaurantInfoVO> list = new ArrayList<>();
        try{
            String sql = "SELECT RESTAURANT_NOTICE,RESTAURANT_PHONE,RESTAURANT_INTRODUCE,RESTAURANT_HOURS,RESTAURANT_ADDR FROM RESTAURANT_INFO WHERE RESTAURANT_ID = ?";
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,restaurantVO.getRestId());
            rs = pStmt.executeQuery();
            while (rs.next()){
                String notice = rs.getString("RESTAURANT_NOTICE");
                String phone = rs.getString("RESTAURANT_PHONE");
                String introduce = rs.getString("RESTAURANT_INTRODUCE");
                String hours = rs.getString("RESTAURANT_HOURS");
                String addr = rs.getString("RESTAURANT_ADDR");

                RestaurantInfoVO vo = new RestaurantInfoVO();
                vo.setRestNotice(notice);
                vo.setRestPhoneNum(phone);
                vo.setRestIntro(introduce);
                vo.setRestHours(hours);
                vo.setRestAddr(addr);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 매장 상세 정보 불러오기
    public RestaurantInfoVO restInfoSelect(String restId){

        RestaurantInfoVO vo = null;
        try{
            String sql = "SELECT * FROM RESTAURANT_INFO WHERE RESTAURANT_ID = ?";
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,restId);
            rs = pStmt.executeQuery();
            while (rs.next()){
                String id = rs.getString("RESTAURANT_ID");
                String restImgFileName = rs.getString("RESTAURANT_IMAGE_FILE_NAME");
                String restPhoneNum = rs.getString("RESTAURANT_PHONE");
                String restAddr = rs.getString("RESTAURANT_ADDR");
                String restNotice = rs.getString("RESTAURANT_NOTICE");
                String restHours = rs.getString("RESTAURANT_HOURS");
                String restIntro = rs.getString("RESTAURANT_INTRODUCE");

                vo  = new RestaurantInfoVO(id,restImgFileName,restPhoneNum,restAddr,restNotice,restHours,restIntro);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
}
