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
		}else if("/toAddCost.do".equals(path)){
			toAddCost(req,res);
		}else if("/addCost.do".equals(path)){
			addCost(req,res);
		}else if("/toUpdateCost.do".equals(path)){
			toUpdateCost(req,res);
		}else if("/updateCost.do".equals(path)){
			updateCost(req,res);
		}
		else{
			throw new RuntimeException("查无此页");
		}
	}
	protected void updateCost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		res.sendRedirect("findCost.do");
	}
	protected void toUpdateCost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, res);
	}
	protected void addCost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			Cost c = new Cost();
			String name = req.getParameter("name");
			String costType = req.getParameter("costType");
			String baseDuration = req.getParameter("baseDuration");
			String baseCost = req.getParameter("baseCost");
			String unitCost = req.getParameter("unitCost");
			String descr = req.getParameter("descr");
			c.setName(name);
			System.out.println(costType);
			c.setCostType(costType);
			if(baseDuration!=null &&!baseDuration.equals("")){
				c.setBaseDuration(new Integer(baseDuration));
			}
			if(baseCost!=null
					&& !baseCost.equals("")) {
					c.setBaseCost(
						new Double(baseCost));
				}
				if(unitCost!=null
					&& !unitCost.equals("")) {
					c.setUnitCost(
						new Double(unitCost));
				}
			c.setDescr(descr);
			CostDao dao = new CostDaoImpl();
			dao.addCost(c);
			res.sendRedirect("findCost.do");
	}
	
	protected void toAddCost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);;
	}
	
	protected void finCost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			CostDao dao = new CostDaoImpl();
			List<Cost> list = dao.findAll();
			req.setAttribute("costs", list);
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);;
	}
	
}
