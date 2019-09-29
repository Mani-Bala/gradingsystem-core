package com.revature.gradingsystem.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.UserDetails;

public class UserDaoTest {

	@Test
	public void testValidUserLogin() {
		String name = "Mano";
		String password = "pwd100";
		UserDao userdao = new UserDaoImpl();
		UserDetails userdetail = null;
		try {
			userdetail = userdao.findUserByNamePassword(name, password);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(userdetail);
	}
	
	@Test
	public void testInValidUserLogin() {
		String name = "";
		String password = "";
		UserDao userdao = new UserDaoImpl();
		UserDetails userdetail = null;
		try {
			userdetail = userdao.findUserByNamePassword(name, password);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNull(userdetail);
	}


}
