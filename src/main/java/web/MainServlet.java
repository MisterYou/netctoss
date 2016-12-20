package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CostDao;
import dao.CostDaoImpl;
import entity.Cost;
    
public class MainServlet extends HttpServlet {


	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String path = req.getServletPath();
		if("/findCost.do".equals(path)){
			finCost(req,res);
		}else if("/toaddCost.do".equals(path)){
			
		}else{
			throw new RuntimeException("查无此页");
		}
	}
	protected void finCost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			CostDao dao = new CostDaoImpl();
			List<Cost> list = dao.findAll();
			
			req.setAttribute("costs", list);
		
		req.getRequestDispatcher("WEB-INF/main/find.jsp").forward(req, res);;
	}
	
}
