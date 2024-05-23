package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


import bd.Prueba;
import gui.VistaError.PanelError;
import logica.Estudiante;
import logica.Profesor;
import logica.Usuario;

public class Programa extends JFrame {

	public Programa(Usuario u) {
		ImageIcon icono = new ImageIcon("images//miniLogoNoodle.png");

		this.setIconImage(icono.getImage());
		this.setTitle("Ñoodle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		JPanel panelProfesor = new PanelPrincipalProfesor(u); // 2
		
		JPanel panelEstudiante = new PanelPrincipalEstudiante(u); // 3

		JPanel tareaProfesor = new PanelTareasProfesor(u); // 4

		JPanel tareaEstudiante = new PanelTareasEstudiante(u); // 5

		JPanel panelCardLayout = new JPanel();
		panelCardLayout.setSize(800, 500);
		panelCardLayout.setLayout(new CardLayout(0, 0));
		panelCardLayout.add(tareaProfesor, "Panel Tarea Profesor");
		panelCardLayout.add(panelEstudiante, "Panel Estudiante");
		panelCardLayout.add(tareaEstudiante, "Panel Tareas Estudiante");
		panelCardLayout.add(panelProfesor, "Panel Profesor");


		CardLayout cl = (CardLayout) (panelCardLayout.getLayout());
//		Asi se cambia de un panel a otro (esas 2 lineas) 

		if (u instanceof Estudiante) {
			cl.show(panelCardLayout, "Panel Estudiante");						
		}
		else if (u instanceof Profesor) {
			cl.show(panelCardLayout, "Panel Profesor");			
		}

		this.add(panelCardLayout, BorderLayout.CENTER);

		this.setVisible(true);
	}

}


