package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.book.vo.accountVO;

public class accountDao {
	
	/*변수*/
	DataSource ds;

	/*생성자*/
	public accountDao() {
		try {
			//pool 찾아옴
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*해당 기간 동안과 해당 id의 총수입 계산*/
	public int getTotalInput(String id, String stDate, String edDate) {
		
		int total=0;
		
		try {
			
			Connection con = ds.getConnection();
			Statement stat = con.createStatement();
			
			String q = "select balance from account "
					+ "where inout='i' and mid='" + id + "' and mdate >='" + stDate + "' and mdate <= '" + edDate + "'";

			//System.out.println(q);
			
			ResultSet rs = stat.executeQuery(q);
			
			//총 합계산
			while (rs.next()) {
				int tmp = rs.getInt(1);
				total += tmp;
			}
			
			stat.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("total: "+ total);
		return total;
		
	}
	
	/*기간 동안과 해당 id의 총지출 계산*/
	public int getTotalOutput(String id, String stDate, String edDate) {
		
		int total=0;
		
		try {
			
			Connection con = ds.getConnection();
			Statement stat = con.createStatement();
			
			String q = "select balance from account "
					+ "where inout='o' and mid='" + id + "' and mdate >='" + stDate + "' and mdate <= '" + edDate + "'";
			//System.out.println(q);
			
			ResultSet rs = stat.executeQuery(q);
			
			//총 합계산
			while (rs.next()) {
				total += rs.getInt(1);
			}
			
			stat.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("out total: "+ total);
		return total;
		
	}

	/*수입 상세 정보를 반환*/
	public ArrayList<accountVO> getInputDetail(String id, String stDate, String edDate) {
		ArrayList <accountVO> a_list;
		a_list = new ArrayList<accountVO>();
		
		try {
			//2.connection 만들기
			Connection con = ds.getConnection();
			//3.statement 만들기
			Statement stat = con.createStatement();
			//4.query 전달
			String q = "select aid, mid, mdate, content, balance, a.code, i.name " + 
					"from account a inner join incat i " + 
					"on a.code = i.code " + 
					"where inout='i' and mid='" + id + "' " +
					"and mdate >='" + stDate + "' and mdate <= '" + edDate + "' order by mdate";
			//System.out.println(q);
			ResultSet rs = stat.executeQuery(q);
			
			while(rs.next()) {
				String aid = rs.getString(1);
				String mid = rs.getString(2);
				String mdate = rs.getString(3).substring(0,10);
				String content = rs.getString(4);
				int balance = rs.getInt(5);
				int code = rs.getInt(6);
				String cname = rs.getString(7);
				
				accountVO vo = new accountVO(aid, mid, mdate, content, balance, code, "i", cname);
				a_list.add(vo);
			}

			//6.마무리
			stat.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a_list;
	}
	
	/*지출 상세 정보를 반환*/
	public ArrayList<accountVO> getOutputDetail(String id, String stDate, String edDate) {
		ArrayList <accountVO> a_list;
		a_list = new ArrayList<accountVO>();
		
		try {
			//2.connection 만들기
			Connection con = ds.getConnection();
			//3.statement 만들기
			Statement stat = con.createStatement();
			//4.query 전달
			//String q = "select aid, mid, mdate, content, balance, code from account where inout='i' and mid='" + id + "'";
			String q = "select aid, mid, mdate, content, balance, a.code, o.name " + 
					"from account a inner join outcat o " + 
					"on a.code = o.code " + 
					"where inout='o' and mid='" + id + "' " +
					"and mdate >='" + stDate + "' and mdate <= '" + edDate + "' order by mdate";
			//System.out.println(q);
			ResultSet rs = stat.executeQuery(q);
			
			while(rs.next()) {
				String aid = rs.getString(1);
				String mid = rs.getString(2);
				String mdate = rs.getString(3).substring(0,10);
				String content = rs.getString(4);
				int balance = rs.getInt(5);
				int code = rs.getInt(6);
				String cname = rs.getString(7);
				
				accountVO vo = new accountVO(aid, mid, mdate, content, balance, code, "i", cname);
				a_list.add(vo);
			}

			//6.마무리
			stat.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a_list;
	}

	public void insertVO(accountVO accountToInsert, String mid) {
		
		try {
			Connection con = ds.getConnection();
			String q = "insert into account values(anum.nextval, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstat = con.prepareStatement(q);	//query 찾아서 statement 준비
			
			pstat.setString(1, mid);
			pstat.setString(2, accountToInsert.getMdate());
			pstat.setString(3, accountToInsert.getContent());
			pstat.setString(4, String.valueOf(accountToInsert.getBalance()));
			pstat.setString(5, String.valueOf(accountToInsert.getCode()));
			pstat.setString(6, accountToInsert.getInout());
			
			
			//쿼리 실행
			int result = pstat.executeUpdate();
			
			pstat.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void deleteVO(String aid) {
		
		try {
			Connection con = ds.getConnection();
			String q = "delete from account where aid=?";
			PreparedStatement pstat = con.prepareStatement(q);	//query 찾아서 statement 준비
			
			pstat.setString(1, aid);

			//쿼리 실행
			int result = pstat.executeUpdate();
			
			pstat.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
