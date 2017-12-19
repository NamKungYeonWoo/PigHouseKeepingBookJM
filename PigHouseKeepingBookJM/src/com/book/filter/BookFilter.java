package com.book.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.book.controller.BookController;

/*���� �ּ�: http://localhost:8080/PigHouseKeepingBookJM/welcome.book*/
public class BookFilter implements Filter {

	/*������*/
    public BookFilter() { }

    
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;	//����ȯ
		HttpServletResponse res = (HttpServletResponse) response;	//����ȯ
		req.setCharacterEncoding("euc-kr");
		
		//��Ʈ�ѷ� ����
		BookController bc = new BookController();
		String reqString = req.getServletPath();	//client�� ��û�� ���
		
		/*�ʱ�ȭ��*/
		if (reqString.equals("/welcome.book")) {
			bc.welcome(req, res);
		}
		
		/*�α��� �õ�*/
		else if (reqString.equals("/login.book")) {
			bc.login(req,res);
		}
		
		/*ȸ������ ���� �Է�*/
		else if (reqString.equals("/joinForm.book")) {
			bc.joinForm(req,res);
		}
		
		/*ȸ�� ����*/
		else if (reqString.equals("/join.book")) {
			bc.join(req, res);
		}
		
		/*���� ȭ��*/
		else if (reqString.equals("/mainPage.book")) {
			bc.mainPage(req, res);
		}
		
		/*�Ⱓ ���� ���� �Է�*/
		else if (reqString.equals("/changePeriodForm.book")) {
			bc.changePeriodForm(req, res);
		}
		
		/*�Ⱓ ����*/
		else if (reqString.equals("/changePeriod.book")) {
			bc.changePeriod(req, res);
		}
		
		/*���� ���� �Է�*/
		else if (reqString.equals("/regInputForm.book")) {
			bc.regInputForm(req, res);
		}
		
		/*���� ���*/
		else if (reqString.equals("/regInput.book")) {
			bc.regInput(req, res);
		}
		
		/*���� ���� �Է�*/
		else if (reqString.equals("/regOutputForm.book")) {
			bc.regOutputForm(req, res);
		}
		
		/*���� ���*/
		else if (reqString.equals("/regOutput.book")) {
			bc.regOutput(req, res);
		}
		
		/*���� �� ����*/
		else if (reqString.equals("/inputDetail.book")) {
			bc.inputDetail(req, res);
		}
		
		/*���� �� ����*/
		else if (reqString.equals("/outputDetail.book")) {
			bc.outputDetail(req, res);
		}
		
		/*���� ����*/
		else if (reqString.equals("/deleteDetail.book")) {
			bc.deleteDetail(req, res);
		}
		
		/*���� �׼��� �����ϱ�*/
		else if (reqString.equals("/excelConvertInput.book")) {
			bc.excelConvert(req, res);
		}
		
		/*���� �׼��� �����ϱ�*/
		else if (reqString.equals("/excelConvertOutput.book")) {
			bc.excelConvertOut(req, res);
		}
		
		/*�α׾ƿ�*/
		else if (reqString.equals("/logout.book")) {
			bc.logout(req, res);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException { }

}
