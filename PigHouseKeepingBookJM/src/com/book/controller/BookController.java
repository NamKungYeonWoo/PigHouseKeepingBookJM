package com.book.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.dao.accountDao;
import com.book.dao.incatDao;
import com.book.dao.memberDao;
import com.book.dao.outcatDao;
import com.book.vo.accountVO;
import com.book.vo.incatVO;
import com.book.vo.memberVO;
import com.book.vo.outcatVO;

public class BookController {
	
	memberDao md;
	accountDao ad;
	incatDao icd;
	outcatDao ocd;
	
	public BookController () {
		md = new memberDao();
		ad = new accountDao();
		icd = new incatDao();
		ocd = new outcatDao();
	}
	

	/*초기화면으로 이동*/
	public void welcome(HttpServletRequest req, HttpServletResponse res) {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/welcome.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/*로그인 시도*/
	public void login(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		//RequestDispatcher dispatcher;
		String nextPage=null;
		
		if (md.loginCheck(id, password)) {	//로그인 정보 일치
			
			HttpSession session = req.getSession();//session 얻기
			session.setAttribute("id", id);//session에 id 라는 이름으로 값 저장
			
			//dispatcher = req.getRequestDispatcher("/view/mainPage.jsp");
			nextPage = "mainPage.book";
		}
		else {	//로그인 정보 불일치
			//dispatcher = req.getRequestDispatcher("/view/welcome.jsp");
			nextPage = "welcome.book";
		}
		
		try {
			//dispatcher.forward(req, res);		//////포워드 ???????
			res.sendRedirect(nextPage);		//////리다이렉트
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*회원가입 페이지로 이동*/
	public void joinForm(HttpServletRequest req, HttpServletResponse res) {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/joinForm.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	/*회원 가입*/
	public void join(HttpServletRequest req, HttpServletResponse res) {
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		memberVO mToJoin = new memberVO(id, password, name, email);
		md.join(mToJoin);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/welcome.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
	/*메인 페이지*/
	public void mainPage(HttpServletRequest req, HttpServletResponse res) {
		
		int total_input;
		int total_output;
		String id ;
		String stDate ;
		String edDate ;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session 얻기
		stDate = (String)session.getAttribute("stDate");//session 얻기
		edDate = (String)session.getAttribute("edDate");//session 얻기
		
		//아이디 속성 저장
		req.setAttribute("id", id);
		req.setAttribute("stDate", stDate);
		req.setAttribute("edDate", edDate);
		
		//수입 총액 받아와 저장
		total_input= ad.getTotalInput(id, stDate, edDate);
		req.setAttribute("tinput", total_input);
		
		//지출 총액 받아와 저장
		total_output= ad.getTotalOutput(id, stDate, edDate);
		req.setAttribute("toutput", total_output);
		
		//별명 저장
		String mname = md.getMname(id);
		req.setAttribute("mname", mname);

		
		int tsub = total_input - total_output;
		//수입 지출의 차 저장
		req.setAttribute("tsub", tsub);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/mainPage.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*수입 상세 정보*/
	public void inputDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session 얻기
		stDate = (String)session.getAttribute("stDate");//session 얻기
		edDate = (String)session.getAttribute("edDate");//session 얻기
		
		ArrayList <accountVO> a_list = ad.getInputDetail(id, stDate, edDate);
		req.setAttribute("inputlist", a_list);	
		
		//수입 총액 받아와 저장
		int total_input= ad.getTotalInput(id, stDate, edDate);
		req.setAttribute("tinput", total_input);
		
		//별명 저장
		String mname = md.getMname(id);
		req.setAttribute("mname", mname);
		
		//System.out.println(a_list.size());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/inputDetail.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/*지출 상세 정보*/
	public void outputDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session 얻기
		stDate = (String)session.getAttribute("stDate");//session 얻기
		edDate = (String)session.getAttribute("edDate");//session 얻기
		
		ArrayList <accountVO> a_list = ad.getOutputDetail(id, stDate, edDate);
		req.setAttribute("outputlist", a_list);	
		
		//지출 총액 받아와 저장
		int total_output= ad.getTotalOutput(id, stDate, edDate);
		req.setAttribute("toutput", total_output);
		
		//별명 저장
		String mname = md.getMname(id);
		req.setAttribute("mname", mname);
		
		//System.out.println(a_list.size());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/outputDetail.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}


	/*기간 수정 화면으로 이동*/
	public void changePeriodForm(HttpServletRequest req, HttpServletResponse res) {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/changePeriodForm.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*기간 수정, 세션 저장*/
	public void changePeriod(HttpServletRequest req, HttpServletResponse res) {
		String stDate = req.getParameter("stDate");
		String edDate = req.getParameter("edDate");
		
		
		HttpSession session = req.getSession();	//session 얻기
		session.setAttribute("stDate", stDate);	//세션 저장
		session.setAttribute("edDate", edDate);
		
		//메인 페이지로 이동 (redirect)
		
		try {
			res.sendRedirect("mainPage.book");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*수입 입력화면으로 이동*/
	public void regInputForm(HttpServletRequest req, HttpServletResponse res) {
		
		ArrayList <incatVO> ic_list = icd.getCats();
		req.setAttribute("ic_list", ic_list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/regInputForm.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*수입 내역 등록하기*/
	public void regInput(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		String mid = (String)session.getAttribute("id");//session 얻기
		
		String mdate = req.getParameter("mdate");
		String content = req.getParameter("content");
		String balance = req.getParameter("balance");
		int i_balance = Integer.parseInt(balance);
		String cate = req.getParameter("cate");
		int i_cate = Integer.parseInt(cate);
		
		accountVO accountToInsert = new accountVO("", mid, mdate, content, i_balance, i_cate, "i", "");
		ad.insertVO(accountToInsert, mid);
		
		//창이동
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/regInput.jsp");
//		
//		try {
//			dispatcher.forward(req, res);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		}
		
		try {
			res.sendRedirect("mainPage.book");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*지출 입력 페이지 이동*/
	public void regOutputForm(HttpServletRequest req, HttpServletResponse res) {
		ArrayList <outcatVO> oc_list = ocd.getCats();
		req.setAttribute("oc_list", oc_list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/regOutputForm.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
		
	}


	/*지출 내역 등록하기*/
	public void regOutput(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		String mid = (String)session.getAttribute("id");//session 얻기
		
		String mdate = req.getParameter("mdate");
		String content = req.getParameter("content");
		String balance = req.getParameter("balance");
		int i_balance = Integer.parseInt(balance);
		String cate = req.getParameter("cate");
		int i_cate = Integer.parseInt(cate);
		
		accountVO accountToInsert = new accountVO("", mid, mdate, content, i_balance, i_cate, "o", "");
		ad.insertVO(accountToInsert, mid);
		
		//창이동
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/regOutput.jsp");
//		
//		try {
//			dispatcher.forward(req, res);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		}
		
		try {
			res.sendRedirect("mainPage.book");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	/*내역 삭제*/
	public void deleteDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String aid = req.getParameter("aid");
		ad.deleteVO(aid);
		
		//System.out.println("req.getHeader(\"referer\")"+req.getHeader("referer"));
		// 상세 페이지에 머물러있음(redirect)
		try {
			//res.sendRedirect("mainPage.book");
			res.sendRedirect(req.getHeader("referer"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*옜 옜 옜 옜*/
	public void excelConvert(HttpServletRequest req, HttpServletResponse res) {
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session 얻기
		stDate = (String)session.getAttribute("stDate");//session 얻기
		edDate = (String)session.getAttribute("edDate");//session 얻기
		
		ArrayList <accountVO> a_list = ad.getInputDetail(id, stDate, edDate);
		req.setAttribute("inputlist", a_list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/excelConvertIn.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	public void excelConvertOut(HttpServletRequest req, HttpServletResponse res) {
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session 얻기
		stDate = (String)session.getAttribute("stDate");//session 얻기
		edDate = (String)session.getAttribute("edDate");//session 얻기
		
		ArrayList <accountVO> a_list = ad.getOutputDetail(id, stDate, edDate);
		req.setAttribute("outputlist", a_list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/excelConvertOut.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*옜옜 � 옜옜옜*/
	public void logout(HttpServletRequest req, HttpServletResponse res) {
		
		//로그아웃 프로세스 후, 웰컴 페이지로!!
		HttpSession session = req.getSession();//session 얻기
		session.setAttribute("id", null);//session에 id 라는 이름으로 값 저장
		
		try {
			res.sendRedirect("welcome.book");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
