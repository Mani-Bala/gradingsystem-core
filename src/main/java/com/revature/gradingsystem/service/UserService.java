package com.revature.gradingsystem.service;

import java.util.List;

import com.revature.gradingsystem.dao.EmployeeDaoImpl;
import com.revature.gradingsystem.dao.StudentGradeDaoImpl;
import com.revature.gradingsystem.dao.StudentMarkDaoImpl;
import com.revature.gradingsystem.dao.UserDao;
import com.revature.gradingsystem.dao.UserDaoImpl;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.util.MessageConstant;
import com.revature.gradingsystem.validator.EmployeeValidator;

public class UserService {
	
	public UserDetails userLogin(String name, String pass) throws ServiceException {
		
		UserDao userdao=new UserDaoImpl();
		UserDetails userdetail = null;
		
		try {
			userdetail=userdao.findUserByNamePassword(name, pass);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
		
		if (userdetail == null) {
			throw new ServiceException(MessageConstant.INVALID_INPUT);
		}
		
		return userdetail;

	}

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
	
	public void updateEmployeeService( UserDetails user ) throws ServiceException {
		
		EmployeeValidator employeeValidator = new EmployeeValidator();
		EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
		try {
			
			employeeValidator.addedEmployeeValidation(user);
			employeeDao.updateEmployee(user);
			
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
