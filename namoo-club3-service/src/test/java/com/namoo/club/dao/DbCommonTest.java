package com.namoo.club.dao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.namoo.club.dao.jdbc.DbConnection;


public class DbCommonTest {
	//
	private IDatabaseTester databaseTester;

	@BeforeClass
	public static void createSchema() throws SQLException {
		//
		DbConnection.overrideProperties("test-jdbc.properties");
		
		InputStream is = UserDaoTest.class.getResourceAsStream("schema.sql");
		Reader reader = new InputStreamReader(is);
		
		RunScript.execute(DbConnection.getConnection(), reader);
	}
	
	@Before
	public void setUp() throws Exception {
		//
		// load dataset
		InputStream is = this.getClass().getResourceAsStream(this.getClass().getSimpleName()+"_dataset.xml");
		IDataSet dataset = new FlatXmlDataSet(is);
		
		String url = DbConnection.getUrl();
		String username = DbConnection.getUsername();
		String password = DbConnection.getPassword();
		String driver = DbConnection.getDriverClassName();
		
		//prepare database tester
		databaseTester = new JdbcDatabaseTester(driver, url, username, password);
		databaseTester.setDataSet(dataset);
		
		//setup database
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		
	}
	
	@After
	public void tearDown() throws Exception {
		//
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.onTearDown();
	}
}
