package com.fca.calidad.unitaria;
 
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
class CalculadoraTest {
	
	private double num1= 10;
	private double num2= 2;
	private Calculadora calculadora = null;
	
	@BeforeEach
	void setup() {
		double num1= 10;
		double num2= 2;
		calculadora = new Calculadora();
		
	}
 
	@Test
	void suma2numerosPsositivosTest () {
		double resEsperado = 12;
		Calculadora calculadora = new Calculadora();
		double resEjecucion = calculadora.suma(num1, num2);
		assertThat(resEsperado, is(resEjecucion));
	
	}
	@Test
	void restar2numerosPsositivosTest () {
		double resEsperado = 8;
		Calculadora calculadora = new Calculadora();
		double resEjecucion = calculadora.resta(num1, num2);
		assertThat(resEsperado, is(resEjecucion));
	
	}
	
	@Test
	void multiplicar2numerosPsositivosTest () {
		double resEsperado = 20;
		Calculadora calculadora = new Calculadora();
		double resEjecucion = calculadora.multiplica(num1, num2);

		assertThat(resEsperado, is(resEjecucion));
	
	}
	
	@Test
	void dividir2numerosPsositivosTest () {
		double resEsperado = 5;
		Calculadora calculadora = new Calculadora();

		double resEjecucion = calculadora.divide(num1, num2);

		assertThat(resEsperado, is(resEjecucion));
	
	}
	
	@AfterEach
	void print() {
		System.out.println("esto se imprime desp de cada prueba");
	}
 
}