package com.fca.calidad.integracion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fca.calidad.dao.DAOUserSQLite;
import com.fca.calidad.modelo.User;
import com.fca.calidad.servicio.UserService;

class UserServiceTest extends DBTestCase {
	private DAOUserSQLite dao;
	private UserService userService;

	public UserServiceTest(){
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.sqlite.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:sqlite:/Users/fca/Desktop/users.db");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}
	@BeforeEach
	public void setUp() {
		dao = new DAOUserSQLite();
		userService = new UserService(dao);
		IDatabaseConnection connection;
		try {
			connection = getConnection();
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo");
		}
		
	}
	
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/resources/initDB.xml"));
	}
	@Test
	void createUserTest() {
		User usuario = userService.createUser("id", "nombre", "email", "password");
		int resultadoEsperado = 1;
		IDatabaseConnection connection;
		try {
			connection = getConnection();
			IDataSet databaseDataSet = connection.createDataSet();
			ITable tablaReal = databaseDataSet.getTable("usuarios");
			String nombreReal = (String) tablaReal.getValue(0, "nombre");
			String nombreEsperado = "nombre";
			String emailReal = (String) tablaReal.getValue(0, "email");
			String emailEsperado = "email";
			String passwordR = (String) tablaReal.getValue(0, "password");
			String passwordE = "email";
			String idEsperado = (String) tablaReal.getValue(0, id);
			String idResultado = "id";
			assertEquals(nombreReal, nombreEsperado);
			assertEquals(emailReal, emailEsperado);
			assertEquals(passwordR, passwordE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
