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
	    void deleteUserTest_Success() {
	        // Preparación del escenario de éxito
	        int userIdToDelete = 1;  // ID del usuario a eliminar
	        
	        // Simulamos que el método deleteById devuelve true cuando se elimina el usuario correctamente
	        when(dao.deleteById(userIdToDelete)).thenReturn(true);

	        // Ejecutamos el método bajo prueba
	        boolean result = servicio.deleteUser(userIdToDelete);

	        // Verificamos que el resultado sea verdadero, indicando que la eliminación fue exitosa
	        assertThat(result, is(true));
	    }
	 @Test
	    void deleteUserTest_Failure() {
	        // Preparación del escenario de fallo
	        int userIdToDelete = 2;  // ID del usuario que no se puede eliminar
	        
	        // Simulamos que el método deleteById devuelve false cuando el usuario no se encuentra
	        when(dao.deleteById(userIdToDelete)).thenReturn(false);

	        // Ejecutamos el método bajo prueba
	        boolean result = servicio.deleteUser(userIdToDelete);

	        // Verificamos que el resultado sea falso, indicando que la eliminación falló
	        assertThat(result, is(false));
	    }
	
	 @Test
	    void createUserTest_Success() {
	        // Preparación del escenario de éxito
	        String name = "John Doe";
	        String email = "johndoe@example.com";
	        String password = "securePassword123"; // Contraseña válida (longitud entre 8 y 16 caracteres)

	        User user = new User(name, email, password);
	        user.setId(1);  // Suponemos que el ID generado es 1

	        // Simulamos que el método findUserByEmail devuelve null, indicando que el usuario no existe
	        when(dao.findUserByEmail(email)).thenReturn(null);

	        // Simulamos que el método save devuelve el ID 1 cuando el usuario es guardado exitosamente
	        when(dao.save(any(User.class))).thenReturn(1);

	        // Ejecutamos el método bajo prueba
	        User result = servicio.createUser(name, email, password);

	        // Verificamos que el usuario fue creado y que su ID es 1
	        assertThat(result, is(notNullValue()));
	        assertThat(result.getName(), is(name));
	        assertThat(result.getEmail(), is(email));
	        assertThat(result.getPassword(), is(password));
	        assertThat(result.getId(), is(1));  // Verificamos que el ID fue asignado correctamente
	    }
	 @Test
	    void createUserTest_PasswordTooShort() {
	        // Preparación del escenario de contraseña demasiado corta
	        String name = "Jane Doe";
	        String email = "janedoe@example.com";
	        String password = "short";  // Contraseña demasiado corta (menos de 8 caracteres)

	        // Ejecutamos el método bajo prueba
	        User result = servicio.createUser(name, email, password);

	        // Verificamos que el usuario no se creó porque la contraseña es demasiado corta
	        assertThat(result, is(nullValue()));
	    }

	    @Test
	    void createUserTest_PasswordTooLong() {
	        // Preparación del escenario de contraseña demasiado larga
	        String name = "Alice";
	        String email = "alice@example.com";
	        String password = "thisisaverylongpassword";  // Contraseña demasiado larga (más de 16 caracteres)

	        // Ejecutamos el método bajo prueba
	        User result = servicio.createUser(name, email, password);

	        // Verificamos que el usuario no se creó porque la contraseña es demasiado larga
	        assertThat(result, is(nullValue()));
	    }

	    @Test
	    void createUserTest_UserAlreadyExists() {
	        // Preparación del escenario cuando el usuario ya existe
	        String name = "Existing User";
	        String email = "existinguser@example.com";
	        String password = "validPassword123"; // Contraseña válida

	        // Simulamos que el usuario ya existe en la base de datos
	        User existingUser = new User(name, email, password);
	        existingUser.setId(1);

	        when(dao.findUserByEmail(email)).thenReturn(existingUser); // El método findUserByEmail devuelve el usuario existente

	        // Ejecutamos el método bajo prueba
	        User result = servicio.createUser(name, email, password);

	        // Verificamos que el resultado sea el usuario existente y no se haya creado uno nuevo
	        assertThat(result, is(sameInstance(existingUser)));
	    }
}


















