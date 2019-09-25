package com.revature.gradingsystem.service;

import java.util.List;

import com.revature.gradingsystem.dao.StudentGradeDaoImpl;
import com.revature.gradingsystem.dao.StudentMarkDaoImpl;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.dto.StudentMarkDTO;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.StudentMark;

public class UserFeatureService {

	public void updateMarksAndGradeService(int regno, List<StudentMark> marks) throws ServiceException {
		
		try {
			
			new StudentMarkDaoImpl().insertMarks(regno, marks);
			new StudentGradeDaoImpl().insertGrade(regno, marks);
			
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<StudentGradeDTO> findByGradeService(String grade) throws ServiceException {

		List<StudentGradeDTO> list = null;
		try {
			list = new StudentGradeDaoImpl().findByGrade(grade);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public List<StudentMark> findBySubjectCodeService(String subCode) throws ServiceException {

		List<StudentMark> list = null;
		 try {
			list = new StudentMarkDaoImpl().findBySubjectCode(subCode);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return list;
	}

	public StudentGradeDTO getStudentResult(int regno) throws DBException {

		StudentGradeDTO studentDetail =null;
		
		try {
			studentDetail =new StudentGradeDaoImpl().findByRegNo(regno);
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		}
		return studentDetail;
	}

	public List<StudentMark> getStudentMarks(int regno) throws ServiceException {

		List<StudentMark> marks = null;
		
		try {
			marks = new StudentMarkDaoImpl().getMarksByRegNo(regno);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
		return marks;
	}

	public List<StudentGradeDTO> listOfStudentService() throws ServiceException {
		
		List<StudentGradeDTO> listOfStudent = null;
		
		try {
			listOfStudent = new StudentGradeDaoImpl().listOfStudent();
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return listOfStudent;
	}

}
