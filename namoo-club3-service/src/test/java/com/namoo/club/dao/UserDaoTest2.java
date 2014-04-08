package com.namoo.club.dao;

import java.io.IOException;
import java.io.InputStream;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.club.dao.factory.DaoFactory;
import com.namoo.club.dao.factory.DaoFactory.DbType;

import dom.entity.SocialPerson;

public class UserDaoTest2 {

	IDatabaseTester databaseTester;
	UserDao userDao;
	
	@Before
	public void setUp() throws Exception {
		userDao = DaoFactory.createFactory(DbType.MariaDB).getUserDao();
		prepareDatabaseTester();
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		
	}
	
	@After
	public void tearDown() throws Exception {
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
		databaseTester.onTearDown();
	}
	
	
	private void prepareDatabaseTester() throws DataSetException, IOException {
		//
		String url = "jdbc:mariadb://192.168.0.15:3306/namooclubdb";
		String driver = "org.mariadb.jdbc.Driver";
		String username = "namoouser";
		String password = "namoouser";
		
		databaseTester = new JdbcDatabaseTester(driver, url, username, password);
		databaseTester.setDataSet(readDataset());
	}
	
	private IDataSet readDataset() throws DataSetException, IOException {
		//
		InputStream is = this.getClass().getResourceAsStream("dataset.xml");
		IDataSet dataset = new FlatXmlDataSet(is);
		return dataset;
	}
	
	
	@Test
	public void test() {
		//
		SocialPerson person = userDao.readUser("wntjd1211@nate.com");
		System.out.println(person.getName());
	}
	
	@Test
	public void createUser() {
		//
//		SocialPerson user = new so
//		SocialPerson person = userDao.createUser(user);
//		System.out.println(person.getName());
	}

}
