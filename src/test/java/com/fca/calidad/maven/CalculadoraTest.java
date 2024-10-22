package com.fca.calidad.maven;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fca.calidad.unitaria.Calculadora;

class CalculadoraTest {
	private double num1 = 2;
	private double num2 = 5;
	private Calculadora calculadora = null;
	@BeforeEach
	void setup() {
		double num1 = 2;
		double num2 = 5;
		calculadora = new Calculadora();
	}
	@Test
	void suma2numerosPositivosTest() {
	/*	double num1 = 2;
		double num2 = 5;*/
		double resEsperado = 7;
		Calculadora calculadora = new Calculadora();
		
		double resEjecucion = calculadora.suma(num1, num2);
		
		assertThat(resEsperado, is(resEjecucion));
	}
	@Test
	void resta2numerosTest() {
	/*	double num1 = 2;
		double num2 = 5;*/
		double resEsperado = -3;
		Calculadora calculadora = new Calculadora();
		
		double resEjecucion = calculadora.resta(num1, num2);
		
		assertThat(resEsperado, is(resEjecucion));
	}
	@Test
	void multiplicacion2numerosTest() {
	/*	double num1 = 10;
		double num2 = 4;*/
		double resEsperado = 40;
		Calculadora calculadora = new Calculadora();
		
		double resEjecucion = calculadora.multiplica(num1, num2);
		
		assertThat(resEsperado, is(resEjecucion));
	}
	@Test
	void division2numerosTest() {
	/*	double num1 = 10;
		double num2 = 5;*/
		double resEsperado = 0.4;
		Calculadora calculadora = new Calculadora();
		
		double resEjecucion = calculadora.divide(num1, num2);
		
		assertThat(resEsperado, is(resEjecucion));
	}
	
	@AfterEach
	void print() {
		System.out.println("Esto se imprime despues de cada prueba");
	}

}











