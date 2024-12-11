package com.fca.calidad.servicio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
 
import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.modelo.User;
 
class LoginServiceTest {

 
	LoginService service;
	IDAOUser dao;
	@Test
	void LoginServicetest() {
		dao = mock(IDAOUser.class);
		service = new LoginService(dao);
		User usuario= new User("nombre1","email@email.com", "12345");
		when(dao.findByUserName("nombre1")).thenReturn(usuario);
		boolean result= service.login("nombre1", "12345");
		assertThat(result, is(true));
	}
	@Test
	void LoginNoExisteUsuariotest() {
		dao = mock(IDAOUser.class);
		service = new LoginService(dao);
		when(dao.findByUserName("nombre1")).thenReturn(null);
		boolean result= service.login("nombre1", "12345");
		assertThat(result, is(false));
	}

 
}
