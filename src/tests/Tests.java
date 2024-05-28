package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;

import bd.BaseQueries;
import gui.Vista;
import logica.Tarea;

class Tests {

	@Test
	void testCrearBoton() {
		// Se crea un botón correctamente y tiene el nombre adecuado
		JButton boton = Vista.crearBoton("Boton", 'a');
		assertAll(()-> assertNotNull(boton),()-> assertEquals("Boton", boton.getText()));
	}

	@Test
	void testHayNombreUsuario() {
		// El usuario Fran siempre debe aparecer (creado en la BBDD)
		boolean existe = BaseQueries.hayNombreUsuario("Fran");
		assertTrue(existe);
	}

	@Test
	void testBuscarMaxIdTarea() {
		// Devuelve el id máximo adecuado
		int pruebaId = BaseQueries.buscarMaxIdTarea("PROG");
		assertTrue(pruebaId >= 0);
	}

	@Test
	void testbuscarEntregas() {
		// No encuentra tareas al introducir valores inválidos
		ArrayList<Tarea> prueba = BaseQueries.buscarEntregas("", "");
		assertEquals(0, prueba.size());
	}

	@Test
	void testComprobarNombre() {
		// El nombre no cumple las condiciones
		boolean pruebaNom = Vista.comprobarNombre("Fr4nKo");
		assertFalse(pruebaNom);
	}

}
