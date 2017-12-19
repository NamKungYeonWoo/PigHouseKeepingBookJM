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
	

	/*�ʱ�ȭ������ �̵�*/
	public void welcome(HttpServletRequest req, HttpServletResponse res) {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/welcome.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/*�α��� �õ�*/
	public void login(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		//RequestDispatcher dispatcher;
		String nextPage=null;
		
		if (md.loginCheck(id, password)) {	//�α��� ���� ��ġ
			
			HttpSession session = req.getSession();//session ���
			session.setAttribute("id", id);//session�� id ��� �̸����� �� ����
			
			//dispatcher = req.getRequestDispatcher("/view/mainPage.jsp");
			nextPage = "mainPage.book";
		}
		else {	//�α��� ���� ����ġ
			//dispatcher = req.getRequestDispatcher("/view/welcome.jsp");
			nextPage = "welcome.book";
		}
		
		try {
			//dispatcher.forward(req, res);		//////������ ???????
			res.sendRedirect(nextPage);		//////�����̷�Ʈ
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*ȸ������ �������� �̵�*/
	public void joinForm(HttpServletRequest req, HttpServletResponse res) {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/joinForm.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	/*ȸ�� ����*/
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

	
	/*���� ������*/
	public void mainPage(HttpServletRequest req, HttpServletResponse res) {
		
		int total_input;
		int total_output;
		String id ;
		String stDate ;
		String edDate ;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session ���
		stDate = (String)session.getAttribute("stDate");//session ���
		edDate = (String)session.getAttribute("edDate");//session ���
		
		//���̵� �Ӽ� ����
		req.setAttribute("id", id);
		req.setAttribute("stDate", stDate);
		req.setAttribute("edDate", edDate);
		
		//���� �Ѿ� �޾ƿ� ����
		total_input= ad.getTotalInput(id, stDate, edDate);
		req.setAttribute("tinput", total_input);
		
		//���� �Ѿ� �޾ƿ� ����
		total_output= ad.getTotalOutput(id, stDate, edDate);
		req.setAttribute("toutput", total_output);
		
		//���� ����
		String mname = md.getMname(id);
		req.setAttribute("mname", mname);

		
		int tsub = total_input - total_output;
		//���� ������ �� ����
		req.setAttribute("tsub", tsub);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/mainPage.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*���� �� ����*/
	public void inputDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session ���
		stDate = (String)session.getAttribute("stDate");//session ���
		edDate = (String)session.getAttribute("edDate");//session ���
		
		ArrayList <accountVO> a_list = ad.getInputDetail(id, stDate, edDate);
		req.setAttribute("inputlist", a_list);	
		
		//���� �Ѿ� �޾ƿ� ����
		int total_input= ad.getTotalInput(id, stDate, edDate);
		req.setAttribute("tinput", total_input);
		
		//���� ����
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
	
	/*���� �� ����*/
	public void outputDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session ���
		stDate = (String)session.getAttribute("stDate");//session ���
		edDate = (String)session.getAttribute("edDate");//session ���
		
		ArrayList <accountVO> a_list = ad.getOutputDetail(id, stDate, edDate);
		req.setAttribute("outputlist", a_list);	
		
		//���� �Ѿ� �޾ƿ� ����
		int total_output= ad.getTotalOutput(id, stDate, edDate);
		req.setAttribute("toutput", total_output);
		
		//���� ����
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


	/*�Ⱓ ���� ȭ������ �̵�*/
	public void changePeriodForm(HttpServletRequest req, HttpServletResponse res) {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/changePeriodForm.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*�Ⱓ ����, ���� ����*/
	public void changePeriod(HttpServletRequest req, HttpServletResponse res) {
		String stDate = req.getParameter("stDate");
		String edDate = req.getParameter("edDate");
		
		
		HttpSession session = req.getSession();	//session ���
		session.setAttribute("stDate", stDate);	//���� ����
		session.setAttribute("edDate", edDate);
		
		//���� �������� �̵� (redirect)
		
		try {
			res.sendRedirect("mainPage.book");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*���� �Է�ȭ������ �̵�*/
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


	/*���� ���� ����ϱ�*/
	public void regInput(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		String mid = (String)session.getAttribute("id");//session ���
		
		String mdate = req.getParameter("mdate");
		String content = req.getParameter("content");
		String balance = req.getParameter("balance");
		int i_balance = Integer.parseInt(balance);
		String cate = req.getParameter("cate");
		int i_cate = Integer.parseInt(cate);
		
		accountVO accountToInsert = new accountVO("", mid, mdate, content, i_balance, i_cate, "i", "");
		ad.insertVO(accountToInsert, mid);
		
		//â�̵�
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

	
	/*���� �Է� ������ �̵�*/
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


	/*���� ���� ����ϱ�*/
	public void regOutput(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		String mid = (String)session.getAttribute("id");//session ���
		
		String mdate = req.getParameter("mdate");
		String content = req.getParameter("content");
		String balance = req.getParameter("balance");
		int i_balance = Integer.parseInt(balance);
		String cate = req.getParameter("cate");
		int i_cate = Integer.parseInt(cate);
		
		accountVO accountToInsert = new accountVO("", mid, mdate, content, i_balance, i_cate, "o", "");
		ad.insertVO(accountToInsert, mid);
		
		//â�̵�
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


	/*���� ����*/
	public void deleteDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String aid = req.getParameter("aid");
		ad.deleteVO(aid);
		
		//System.out.println("req.getHeader(\"referer\")"+req.getHeader("referer"));
		// �� �������� �ӹ�������(redirect)
		try {
			//res.sendRedirect("mainPage.book");
			res.sendRedirect(req.getHeader("referer"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*�� �� �� ��*/
	public void excelConvert(HttpServletRequest req, HttpServletResponse res) {
		String id ;
		String stDate;
		String edDate;
		
		HttpSession session = req.getSession();
		id = (String)session.getAttribute("id");//session ���
		stDate = (String)session.getAttribute("stDate");//session ���
		edDate = (String)session.getAttribute("edDate");//session ���
		
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
		id = (String)session.getAttribute("id");//session ���
		stDate = (String)session.getAttribute("stDate");//session ���
		edDate = (String)session.getAttribute("edDate");//session ���
		
		ArrayList <accountVO> a_list = ad.getOutputDetail(id, stDate, edDate);
		req.setAttribute("outputlist", a_list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/excelConvertOut.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/*���� � ������*/
	public void logout(HttpServletRequest req, HttpServletResponse res) {
		
		//�α׾ƿ� ���μ��� ��, ���� ��������!!
		HttpSession session = req.getSession();//session ���
		session.setAttribute("id", null);//session�� id ��� �̸����� �� ����
		
		try {
			res.sendRedirect("welcome.book");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
