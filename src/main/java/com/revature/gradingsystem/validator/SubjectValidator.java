package com.revature.gradingsystem.validator;

import java.util.List;

import com.revature.gradingsystem.dao.SubjectDAO;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.Subject;

public class SubjectValidator {

	public void subjectWiseRankHolder(String subCode) throws ValidatorException {

		if (subCode == null || "".equals(subCode.trim()) || subCode.length() != 5)
			throw new ValidatorException("Invalid Subject Code");
		
		List<Subject> subjectsList = null;
		try {
			subjectsList = new SubjectDAO().findAll();
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		for (Subject subject : subjectsList) {
			
			if (!subject.getCode().equalsIgnoreCase(subCode)) 
				throw new ValidatorException("This Subject code is not exist");
		}	
	}

	public List<Subject> findAllSubject() {

		List<Subject> subjects = null;
		try {
			subjects = new SubjectDAO().findAll();
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return subjects;
	}

	public int markValidation(String name, String mark) throws ValidatorException {

		int markInt= 0;
		try {
			markInt = Integer.parseInt(mark);
		} catch (Exception e) {
			throw new ValidatorException("Invalid Mark, Please try again..");
		}
		
		if( markInt > 100 || markInt < 0 )
			throw new ValidatorException("Mark is out of boundaries, Enter between ( 0-100 ).");
		
		return markInt;
	}
	
}
