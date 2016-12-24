package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DbUtils;
import entity.Cost;

public class CostDaoImpl implements CostDao {

	public List<Cost> findAll() {
		 Connection conn =null;
		 try {
			
			 conn = DbUtils.getConnection();
			
			String sql = "Select * from cost";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<Cost> list = new ArrayList<Cost>();
			while(rs.next()){
				Cost c = createCost(rs);
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询出错",e);
		}finally{
			DbUtils.close(conn);
		}
	}

	private Cost createCost(ResultSet rs) throws SQLException {
		Cost c = new Cost();
		c.setCostId(rs.getInt("cost_id"));
		c.setName(rs.getString("name"));
		c.setBaseDuration(rs.getInt("base_duration"));
		c.setBaseCost(rs.getDouble("base_cost"));
		c.setUnitCost(rs.getDouble("unit_cost"));
		c.setStatus(rs.getString("status"));
		c.setDescr(rs.getString("descr"));
		c.setCreatime(rs.getTimestamp("creatime"));
		c.setStartime(rs.getTimestamp("startime"));
		c.setCostType(rs.getString("cost_type"));
		return c;
	}
	public static void main(String[] args) {
		CostDao dao = new CostDaoImpl();
		Cost c = new Cost();
	}

	public void addCost(Cost c) {
		Connection conn=null;
		try {
			conn = DbUtils.getConnection();
		
			String sql = "insert into cost(name,base_duration,base_cost,unit_cost,status,descr,startime,cost_type) values("
				+ "?,?,?,?,1,?,null,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setObject(2, c.getBaseDuration());
			ps.setObject(3, c.getBaseCost());
			ps.setObject(4, c.getUnitCost());
			ps.setString(5, c.getDescr());
			ps.setString(6, c.getCostType());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加员工失败",e);
		}finally{
			DbUtils.close(conn);
		}
	}

	public Cost findById(int id) {
		Connection conn=null;
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from cost where cost_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return createCost(rs);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询资费失败", e);
		}finally{
			DbUtils.close(conn);
		}
		
	}
	
}
