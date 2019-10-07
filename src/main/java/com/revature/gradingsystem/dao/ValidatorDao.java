package com.revature.gradingsystem.dao;


import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.ScoreRange;

public interface ValidatorDao {

	ScoreRange findGrade(String grade) throws DBException;

	ScoreRange findRange(int min) throws DBException;

	int findRegNo(int regno) throws DBException;

	String isMarkUpdated(int regno) throws DBException;

	String findByEmail(String email) throws DBException;

	String findByMobNo(long mobno) throws DBException;

}