package com.fca.calidad.servicio;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.List;

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
       User nuevoUsuario = new User("nombre", "email", "password");
       when(dao.findUserByEmail("email")).thenReturn(null);
       when(dao.save(any(User.class))).thenReturn(1); 
       User resultado = servicio.createUser("nombre", "email", "password");
       assertThat(resultado.getName(), is("nombre"));
       assertThat(resultado.getEmail(), is("email"));
       assertThat(resultado.getPassword(), is("password"));
       assertThat(resultado.getId(), is(1));
   }	

	    @Test
		public void buscarEmailTest () {
			User resultadoEsperado = new User("name" , "email" , "password");
			when(dao.findUserByEmail("email")).thenReturn(resultadoEsperado);
			
			User resultado = servicio.findUserByEmail("email");

			assertThat(resultado.getName(), is(resultadoEsperado.getName()));
			assertThat(resultado.getEmail(), is(resultadoEsperado.getEmail()));
			assertThat(resultado.getPassword(), is(resultadoEsperado.getPassword()));
			
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
	    @Test
		 void findAllUsersTest() {
		     User usuario1 = new User("Maria", "ana@gmail.com", "contra123");
		     usuario1.setId(1);
		     baseDatos.put(usuario1.getId(), usuario1);
	 
		     User usuario2 = new User("Carlos", "ana@gmail.com", "contra456");
		     usuario2.setId(2);
		     baseDatos.put(usuario2.getId(), usuario2);
	 
		     when(dao.findAll()).thenReturn(baseDatos.values().stream().toList());
	 
		     List<User> result = servicio.findAllUsers();
	 
		     assertThat(result, hasSize(2));
		     assertThat(result, containsInAnyOrder(usuario1, usuario2));
	 
		     // Caso sin usuarios
		     when(dao.findAll()).thenReturn(List.of());
		     List<User> emptyResult = servicio.findAllUsers();
		     assertThat(emptyResult, is(empty()));
		 }

}
