package com.namoo.club.factory;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;
import com.namoo.club.dao.jdbc.MariaDBDaoFactory;

public class DaoFactoryTest {

	@Before
	public void setUp() throws Exception {
		//
	}

	@After
	public void tearDown() throws Exception {
		//
	}
	
	@Test
	public void testCreateFactory() {
		//
		DaoFactory daoFactory = DaoFactory.createFactory(DbType.MariaDB);
		assertTrue(daoFactory instanceof MariaDBDaoFactory);
	}

}
