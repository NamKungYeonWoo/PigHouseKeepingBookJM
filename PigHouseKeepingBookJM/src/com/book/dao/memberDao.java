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

	/*변수*/
	DataSource ds;
	
	/*생성자*/
	public memberDao() {
		try {
			//pool 찾아옴
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*로그인 정보 확인*/
	public boolean loginCheck(String id, String password) {
		
		String userPassword=null;
		
		try {
			//2.connection 만들기
			Connection con = ds.getConnection();
			//3.statement 만들기
			Statement stat = con.createStatement();
			//4.query 전달
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

	/*회원가입*/
	public void join(memberVO mToJoin) {
		
		try {
			Connection con = ds.getConnection();
			String q = "insert into member values(?,?,?,?)";
			PreparedStatement pstat = con.prepareStatement(q);	//query 찾아서 statement 준비
			
			pstat.setString(1, mToJoin.getMid());
			pstat.setString(2, mToJoin.getPassword());
			pstat.setString(3, mToJoin.getName());
			pstat.setString(4, mToJoin.getEmail());
			
			//쿼리 실행
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
