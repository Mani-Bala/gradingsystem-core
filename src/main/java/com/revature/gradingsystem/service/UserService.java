package com.revature.gradingsystem.service;

import com.revature.gradingsystem.dao.UserDao;
import com.revature.gradingsystem.dao.UserDaoImpl;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.util.MessageConstant;

public class UserService {

	public UserDetails userLogin(String name, String pass) throws ServiceException {
		
		UserDao userdao=new UserDaoImpl();
		UserDetails userdetail = null;
		
		try {
			userdetail=userdao.findUserByNamePassword(name, pass);
			if (userdetail == null) {
				throw new ServiceException(MessageConstant.INVALID_INPUT);
			}
		} catch (DBException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return userdetail;

	}

}
