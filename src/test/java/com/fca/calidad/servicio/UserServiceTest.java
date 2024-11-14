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

	    @Test
	    void createUserTest() {
	        String name = "usuario1";
	        String email = "example@example.com";
	        String password = "123456789"; 

	        User result = servicio.createUser(name, email, password);

	        assertThat(result, is(nullValue())); 
	    }

	    @Test 
	    void findUserByEmailTest() {
	    	User usuario = new User("name", "email", "password");
	    	usuario.setId(1);
	    	when(dao.findUserByEmail("email")).thenReturn(usuario);
	    	
	    	User resultado = servicio.findUserByEmail("email");
	    	assertThat(resultado.getEmail(), is(resultado.getEmail()));
	    	assertThat(resultado.getEmail(), is(resultado.getName()));
	
	    	
	    }
	    @Test 
	    void findUserById() {
	    	User usuario = new User("name", "email", "password");
	    	when(dao.findById(1)).thenReturn(usuario);
	    	
	    	int id = 1;
	    	User resultado = servicio.findUserById(id);
	    	assertThat(resultado.getName(), is("name"));
	    	assertThat(resultado.getEmail(),is("email"));
	    	assertThat(resultado.getPassword(), is("password"));
	    }
	    @Test
		void testDeleteUser() {
		    User usuario = new User("nombre1", "email", "password");
		    usuario.setId(1);
		    baseDatos.put(usuario.getId(), usuario);
		    
		    when(dao.findById(1)).thenReturn(usuario);
		    when(dao.deleteById(1)).thenAnswer(new Answer<Boolean>() {
		        public Boolean answer(InvocationOnMock invocation) {
		            int id = (Integer) invocation.getArguments()[0];
		            return baseDatos.remove(id) != null;
		        }
		    });
		    
		    boolean result = servicio.deleteUser(1);
		    
		    assertThat(result, is(true));
		    assertThat(baseDatos.containsKey(1), is(false));
		}

}


















