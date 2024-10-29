package com.fca.calidad.doubles;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class DependencyTest {
		private Dependency dependency;
		private SubDependency sub;
		
		@BeforeEach
		void setup() {
			sub = mock(SubDependency.class);
			dependency = mock(Dependency.class);
			
		}
		@Test
		public void testAnswer() {
			when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
				public Integer answer(InvocationOnMock invocation) throws Throwable{
					int arg = (Integer)invocation.getArguments()[0];
					return 20*2+10+arg;
				}
			});
			int resultadoEsperado = 55;
			assertThat(resultadoEsperado, is(dependency.addTwo(5)));
		}
		
		
	/*	@Test
		void test() {
			System.out.println(sub.getClassName()); 
		}*/
		
		@Test
		public void testDependency() {
			when(dependency.getClassName()).thenReturn("Hi");
			String resultadoEsperado = "Hi";
			String resultadoReal = dependency.getClassName();
			assertThat("Hi", is(dependency.getClassName()));
		}
		
}
