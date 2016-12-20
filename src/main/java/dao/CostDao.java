package dao;

import java.util.List;

import entity.Cost;

public interface CostDao {
	List<Cost> findAll();
	void addCost(Cost c);
	Cost findById(int id);
}
