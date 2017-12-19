package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.book.vo.memberVO;

public class memberDao {

	/*����*/
	DataSource ds;
	
	/*������*/
	public memberDao() {
		try {
			//pool ã�ƿ�
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*�α��� ���� Ȯ��*/
	public boolean loginCheck(String id, String password) {
		
		String userPassword=null;
		
		try {
			//2.connection �����
			Connection con = ds.getConnection();
			//3.statement �����
			Statement stat = con.createStatement();
			//4.query ����
			String q = "select password from member where mid='" + id + "'";
			//System.out.println(q);
			ResultSet rs = stat.executeQuery(q);
			
			rs.next();
			userPassword = rs.getString(1);
			
			stat.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("get: " + password);
		//System.out.println("db: " + userPassword);
		return password.equals(userPassword);
		
	}

	/*ȸ������*/
	public void join(memberVO mToJoin) {
		
		try {
			Connection con = ds.getConnection();
			String q = "insert into member values(?,?,?,?)";
			PreparedStatement pstat = con.prepareStatement(q);	//query ã�Ƽ� statement �غ�
			
			pstat.setString(1, mToJoin.getMid());
			pstat.setString(2, mToJoin.getPassword());
			pstat.setString(3, mToJoin.getName());
			pstat.setString(4, mToJoin.getEmail());
			
			//���� ����
			int result = pstat.executeUpdate();
			
			pstat.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String getMname(String id) {
		String mname=null;
		
		try {
			Connection con = ds.getConnection();
			Statement stat = con.createStatement();
			String q = "select name from member where mid='" + id + "'";
			//System.out.println(q);
			ResultSet rs = stat.executeQuery(q);
			
			rs.next();
			mname = rs.getString(1);
			
			stat.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mname;
	}
	
	

}
