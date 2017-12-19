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

/*접속 주소: http://localhost:8080/PigHouseKeepingBookJM/welcome.book*/
public class BookFilter implements Filter {

	/*생성자*/
    public BookFilter() { }

    
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;	//형변환
		HttpServletResponse res = (HttpServletResponse) response;	//형변환
		req.setCharacterEncoding("euc-kr");
		
		//컨트롤러 생성
		BookController bc = new BookController();
		String reqString = req.getServletPath();	//client가 요청한 경로
		
		/*초기화면*/
		if (reqString.equals("/welcome.book")) {
			bc.welcome(req, res);
		}
		
		/*로그인 시도*/
		else if (reqString.equals("/login.book")) {
			bc.login(req,res);
		}
		
		/*회원가입 정보 입력*/
		else if (reqString.equals("/joinForm.book")) {
			bc.joinForm(req,res);
		}
		
		/*회원 가입*/
		else if (reqString.equals("/join.book")) {
			bc.join(req, res);
		}
		
		/*매인 화면*/
		else if (reqString.equals("/mainPage.book")) {
			bc.mainPage(req, res);
		}
		
		/*기간 수정 정보 입력*/
		else if (reqString.equals("/changePeriodForm.book")) {
			bc.changePeriodForm(req, res);
		}
		
		/*기간 수정*/
		else if (reqString.equals("/changePeriod.book")) {
			bc.changePeriod(req, res);
		}
		
		/*수입 정보 입력*/
		else if (reqString.equals("/regInputForm.book")) {
			bc.regInputForm(req, res);
		}
		
		/*수입 등록*/
		else if (reqString.equals("/regInput.book")) {
			bc.regInput(req, res);
		}
		
		/*지출 정보 입력*/
		else if (reqString.equals("/regOutputForm.book")) {
			bc.regOutputForm(req, res);
		}
		
		/*지출 등록*/
		else if (reqString.equals("/regOutput.book")) {
			bc.regOutput(req, res);
		}
		
		/*수입 상세 보기*/
		else if (reqString.equals("/inputDetail.book")) {
			bc.inputDetail(req, res);
		}
		
		/*지출 상세 보기*/
		else if (reqString.equals("/outputDetail.book")) {
			bc.outputDetail(req, res);
		}
		
		/*내역 삭제*/
		else if (reqString.equals("/deleteDetail.book")) {
			bc.deleteDetail(req, res);
		}
		
		/*수입 액셀에 저장하기*/
		else if (reqString.equals("/excelConvertInput.book")) {
			bc.excelConvert(req, res);
		}
		
		/*지출 액셀에 저장하기*/
		else if (reqString.equals("/excelConvertOutput.book")) {
			bc.excelConvertOut(req, res);
		}
		
		/*로그아웃*/
		else if (reqString.equals("/logout.book")) {
			bc.logout(req, res);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException { }

}
