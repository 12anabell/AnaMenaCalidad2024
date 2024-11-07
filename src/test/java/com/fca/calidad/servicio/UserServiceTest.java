package com.fca.calidad.servicio;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.modelo.User;

class UserServiceTest {
	private User usuario;
	private UserService servicio;
	private IDAOUser dao;
	private HashMap<Integer, User> baseDatos;
	
	@BeforeEach
	void setup() {
		dao = mock(IDAOUser.class);
		servicio = new UserService(dao);
		baseDatos = new HashMap<Integer, User>();
	}
	@Test
	void updateTest() {
		//Inicializacion
		User usuarioviejo = new User("nombre1", "email", "password");
		usuarioviejo.setId(1);
		
		User usuarionuevo = new User("nuevoNombre", "email", "nuevoPassword");
		usuarionuevo.setId(1);
		baseDatos.put(usuarioviejo.getId(), usuarioviejo);
		
		when(dao.findById(1)).thenReturn(usuarioviejo);
		when(dao.updateUser(any(User.class))).thenAnswer(new Answer<User>() {
			
			public User answer(InvocationOnMock invocation) throws Throwable{
				
				User arg = (User) invocation.getArguments()[0];
				baseDatos.replace(arg.getId(), arg);
				
				return baseDatos.get(arg.getId());
				}
			}
		);
		//Ejercicio
		User result = servicio.updateUser(usuarionuevo);
		
		//Verificar
		assertThat("nuevoPassword", is(result.getPassword()));
		assertThat("nuevoNombre",is(result.getName()));
	}

	/* @Test
	    void deleteUserTest_Success() {
	        int userIdToDelete = 1; 
	        
	        when(dao.deleteById(userIdToDelete)).thenReturn(true);

	        boolean result = servicio.deleteUser(userIdToDelete);

	        assertThat(result, is(true));
	    }
	 @Test
	    void deleteUserTest() {
	        
	        int userIdToDelete = 2;  
	        
	        when(dao.deleteById(userIdToDelete)).thenReturn(false);

	        boolean result = servicio.deleteUser(userIdToDelete);

	        assertThat(result, is(false));
	    }

	    @Test
	    void createUserTest() {
	        String name = "usuario1";
	        String email = "example@example.com";
	        String password = "123456789"; 

	        User result = servicio.createUser(name, email, password);

	        assertThat(result, is(nullValue())); 
	    }*/

	    @Test 
	    void findUserByEmailTest() {
	    	User resultadoEsperado = new User("name", "email", "password");
	    	when(dao.findUserByEmail("email")).thenReturn(resultadoEsperado);
	    	
	    	User resultado = servicio.findUserByEmail("email");
	    	assertThat(resultado, is(resultadoEsperado.getName()));
	    	assertThat(resultado, is(resultadoEsperado.getEmail()));
	    	assertThat(resultado, is(resultadoEsperado.getPassword()));
	    	
	    }
	    @Test 
	    void findUserById() {
	    	User resultadoEsperado = new User("name", "email", "password");
	    	when(dao.findById(1)).thenReturn(resultadoEsperado);
	    	
	    	int id = 1;
	    	User resultado = servicio.findUserById(id);
	    	assertThat(resultado, is(resultadoEsperado.getId()));
	    }

}


















