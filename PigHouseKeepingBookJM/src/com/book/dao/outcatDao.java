package com.book.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.book.vo.incatVO;
import com.book.vo.outcatVO;

public class outcatDao {

	/*변수*/
	DataSource ds;
	
	public outcatDao() {
		try {
			//pool 찾아옴
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*카테고리 리스트를 가져옴*/
	public ArrayList<outcatVO> getCats() {
		
		ArrayList <outcatVO> oc_list;
		oc_list = new ArrayList<>();
		
		try {
	
			Connection con = ds.getConnection();
			Statement stat = con.createStatement();
			String q = "select code, name from outcat";
			ResultSet rs = stat.executeQuery(q);
			
			while (rs.next()) {
				int code = rs.getInt(1);
				String name = rs.getString(2);
				outcatVO tmp = new outcatVO(code, name);
				oc_list.add(tmp);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return oc_list;
	}

}
