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
	

	/*ÃÊ±âÈ­¸éÀ¸·Î ÀÌµ¿*/
	public void welcome(HttpServletRequest req, HttpServletResponse res) {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/welcome.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/*·Î±×ÀÎ ½Ãµµ*/
	public void login(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		//RequestDispatcher dispatcher;
		String nextPage=null;
		
		if (md.loginCheck(id, password)) {	//·Î±×ÀÎ Á¤º¸ ÀÏÄ¡
			
			HttpSession session = req.getSession();//session ¾ò±â
			session.setAttribute("id", id);//session¿¡ id ¶ó´Â ÀÌ¸§À¸·Î °ª ÀúÀå
			
			//dispatcher = req.getRequestDispatcher("/view/mainPage.jsp");
			nextPage = "mainPage.book";
		}
		else {	//·Î±×ÀÎ Á¤º¸ ºÒÀÏÄ¡
			//dispatcher = req.getRequestDispatcher("/view/welcome.jsp");
			nextPage = "welcome.book";
		}
		
		try {
			//dispatcher.forward(req, res);		//////Æ÷¿öµå ???????
			res.sendRedirect(nextPage);		//////¸®´ÙÀÌ·ºÆ®
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*È¸¿ø°¡ÀÔ ÆäÀÌÁö·Î ÀÌµ¿*/
	public void joinForm(HttpServletRequest req, HttpServletResponse res) {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/joinForm.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	/*È¸¿ø °¡ÀÔ*/
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

	
	/*¸ÞÀÎ ÆäÀÌÁö*/
	public void mainPage(HttpServletRequest req, HttpServletResponse res) {
		
		int total_input;
		int total_output;
		String id ;
		String stDate ;
		String edDate ;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session ¾ò±â
		stDate = (String)session.getAttribute("stDate");//session ¾ò±â
		edDate = (String)session.getAttribute("edDate");//session ¾ò±â
		
		//¾ÆÀÌµð ¼Ó¼º ÀúÀå
		req.setAttribute("id", id);
		req.setAttribute("stDate", stDate);
		req.setAttribute("edDate", edDate);
		
		//¼öÀÔ ÃÑ¾× ¹Þ¾Æ¿Í ÀúÀå
		total_input= ad.getTotalInput(id, stDate, edDate);
		req.setAttribute("tinput", total_input);
		
		//ÁöÃâ ÃÑ¾× ¹Þ¾Æ¿Í ÀúÀå
		total_output= ad.getTotalOutput(id, stDate, edDate);
		req.setAttribute("toutput", total_output);
		
		//º°¸í ÀúÀå
		String mname = md.getMname(id);
		req.setAttribute("mname", mname);

		
		int tsub = total_input - total_output;
		//¼öÀÔ ÁöÃâÀÇ Â÷ ÀúÀå
		req.setAttribute("tsub", tsub);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/mainPage.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*¼öÀÔ »ó¼¼ Á¤º¸*/
	public void inputDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session ¾ò±â
		stDate = (String)session.getAttribute("stDate");//session ¾ò±â
		edDate = (String)session.getAttribute("edDate");//session ¾ò±â
		
		ArrayList <accountVO> a_list = ad.getInputDetail(id, stDate, edDate);
		req.setAttribute("inputlist", a_list);	
		
		//¼öÀÔ ÃÑ¾× ¹Þ¾Æ¿Í ÀúÀå
		int total_input= ad.getTotalInput(id, stDate, edDate);
		req.setAttribute("tinput", total_input);
		
		//º°¸í ÀúÀå
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
	
	/*ÁöÃâ »ó¼¼ Á¤º¸*/
	public void outputDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session ¾ò±â
		stDate = (String)session.getAttribute("stDate");//session ¾ò±â
		edDate = (String)session.getAttribute("edDate");//session ¾ò±â
		
		ArrayList <accountVO> a_list = ad.getOutputDetail(id, stDate, edDate);
		req.setAttribute("outputlist", a_list);	
		
		//ÁöÃâ ÃÑ¾× ¹Þ¾Æ¿Í ÀúÀå
		int total_output= ad.getTotalOutput(id, stDate, edDate);
		req.setAttribute("toutput", total_output);
		
		//º°¸í ÀúÀå
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


	/*±â°£ ¼öÁ¤ È­¸éÀ¸·Î ÀÌµ¿*/
	public void changePeriodForm(HttpServletRequest req, HttpServletResponse res) {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/changePeriodForm.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*±â°£ ¼öÁ¤, ¼¼¼Ç ÀúÀå*/
	public void changePeriod(HttpServletRequest req, HttpServletResponse res) {
		String stDate = req.getParameter("stDate");
		String edDate = req.getParameter("edDate");
		
		
		HttpSession session = req.getSession();	//session ¾ò±â
		session.setAttribute("stDate", stDate);	//¼¼¼Ç ÀúÀå
		session.setAttribute("edDate", edDate);
		
		//¸ÞÀÎ ÆäÀÌÁö·Î ÀÌµ¿ (redirect)
		
		try {
			res.sendRedirect("mainPage.book");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*¼öÀÔ ÀÔ·ÂÈ­¸éÀ¸·Î ÀÌµ¿*/
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


	/*¼öÀÔ ³»¿ª µî·ÏÇÏ±â*/
	public void regInput(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		String mid = (String)session.getAttribute("id");//session ¾ò±â
		
		String mdate = req.getParameter("mdate");
		String content = req.getParameter("content");
		String balance = req.getParameter("balance");
		int i_balance = Integer.parseInt(balance);
		String cate = req.getParameter("cate");
		int i_cate = Integer.parseInt(cate);
		
		accountVO accountToInsert = new accountVO("", mid, mdate, content, i_balance, i_cate, "i", "");
		ad.insertVO(accountToInsert, mid);
		
		//Ã¢ÀÌµ¿
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

	
	/*ÁöÃâ ÀÔ·Â ÆäÀÌÁö ÀÌµ¿*/
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


	/*ÁöÃâ ³»¿ª µî·ÏÇÏ±â*/
	public void regOutput(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		String mid = (String)session.getAttribute("id");//session ¾ò±â
		
		String mdate = req.getParameter("mdate");
		String content = req.getParameter("content");
		String balance = req.getParameter("balance");
		int i_balance = Integer.parseInt(balance);
		String cate = req.getParameter("cate");
		int i_cate = Integer.parseInt(cate);
		
		accountVO accountToInsert = new accountVO("", mid, mdate, content, i_balance, i_cate, "o", "");
		ad.insertVO(accountToInsert, mid);
		
		//Ã¢ÀÌµ¿
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


	/*³»¿ª »èÁ¦*/
	public void deleteDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String aid = req.getParameter("aid");
		ad.deleteVO(aid);
		
		//System.out.println("req.getHeader(\"referer\")"+req.getHeader("referer"));
		// »ó¼¼ ÆäÀÌÁö¿¡ ¸Ó¹°·¯ÀÖÀ½(redirect)
		try {
			//res.sendRedirect("mainPage.book");
			res.sendRedirect(req.getHeader("referer"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*¿¿ ¿¿ ¿¿ ¿¿*/
	public void excelConvert(HttpServletRequest req, HttpServletResponse res) {
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session ¾ò±â
		stDate = (String)session.getAttribute("stDate");//session ¾ò±â
		edDate = (String)session.getAttribute("edDate");//session ¾ò±â
		
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
		id = (String)session.getAttribute("id");//session ¾ò±â
		stDate = (String)session.getAttribute("stDate");//session ¾ò±â
		edDate = (String)session.getAttribute("edDate");//session ¾ò±â
		
		ArrayList <accountVO> a_list = ad.getOutputDetail(id, stDate, edDate);
		req.setAttribute("outputlist", a_list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/excelConvertOut.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*·¿×¾¿ô Ã±â¸é·Î*/
	public void logout(HttpServletRequest req, HttpServletResponse res) {
		
		//·Î±×¾Æ¿ô ÇÁ·Î¼¼½º ÈÄ, À£ÄÄ ÆäÀÌÁö·Î!!
		HttpSession session = req.getSession();//session ¾ò±â
		session.setAttribute("id", null);//session¿¡ id ¶ó´Â ÀÌ¸§À¸·Î °ª ÀúÀå
		
		try {
			res.sendRedirect("welcome.book");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
