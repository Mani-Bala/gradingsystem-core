package com.revature.gradingsystem.validator;

import java.util.List;

import com.revature.gradingsystem.dao.SubjectDaoImpl;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.Subject;

public class SubjectValidator {

	public void subjectWiseRankHolder(String subCode) throws ValidatorException, DBException {

		if (subCode == null || "".equals(subCode.trim()) || subCode.length() != 5)
			throw new ValidatorException("Invalid Subject Code");
		
		List<Subject> subjectsList = null;
		try {
			subjectsList = new SubjectDaoImpl().findAll();
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		}
		for (Subject subject : subjectsList) {
			
			if (!subject.getCode().equalsIgnoreCase(subCode)) 
				throw new ValidatorException("This Subject code is not exist");
		}	
	}

	public List<Subject> findAllSubject() throws DBException {

		List<Subject> subjects = null;
		try {
			subjects = new SubjectDaoImpl().findAll();
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		}
		return subjects;
	}
}
