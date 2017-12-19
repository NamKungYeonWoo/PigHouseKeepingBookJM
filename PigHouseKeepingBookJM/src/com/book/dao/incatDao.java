package com.book.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.book.vo.accountVO;
import com.book.vo.incatVO;

public class incatDao {

	/*����*/
	DataSource ds;

	/*������*/
	public incatDao() {
		try {
			//pool ã�ƿ�
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*ī�װ� ����Ʈ�� ������*/
	public ArrayList<incatVO> getCats() {
		
		ArrayList <incatVO> ic_list;
		ic_list = new ArrayList<>();
		
		try {
	
			Connection con = ds.getConnection();
			Statement stat = con.createStatement();
			String q = "select code, name from incat";
			ResultSet rs = stat.executeQuery(q);
			
			while (rs.next()) {
				int code = rs.getInt(1);
				String name = rs.getString(2);
				incatVO tmp = new incatVO(code, name);
				ic_list.add(tmp);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ic_list;
	}

}
