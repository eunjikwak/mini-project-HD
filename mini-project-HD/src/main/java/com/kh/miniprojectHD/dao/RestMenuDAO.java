package com.kh.miniprojectHD.dao;

import com.kh.miniprojectHD.common.Common;
import com.kh.miniprojectHD.vo.RestMenuVO;
import com.kh.miniprojectHD.vo.RestaurantVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RestMenuDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 메뉴 정보 불러오기
    public List<RestMenuVO> menuSelect(RestaurantVO restaurantVO){
        List<RestMenuVO> list = new ArrayList<>();

        try{
            String sql ="SELECT MENU_NAME, MENU_PRICE, MENU_DESC FROM R_MENU WHERE RESTAURANT_ID = ?";
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, restaurantVO.getRestId());
            rs = pStmt.executeQuery();
            while (rs.next()){

                String name = rs.getString("MENU_NAME");
                int price = rs.getInt("MENU_PRICE");
                String desc = rs.getString("MENU_DESC");
                RestMenuVO vo = new RestMenuVO();
                vo.setMenuName(name);
                vo.setMenuPrice(price);
                vo.setMenuDesc(desc);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
