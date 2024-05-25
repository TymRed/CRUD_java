package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


import bd.BaseQueries;
import gui.VistaError.PanelError;
import logica.Estudiante;
import logica.Profesor;
import logica.Usuario;

public class Programa extends JFrame {

	static JPanel panelCardLayout;
	
	public Programa(Usuario u) {
		ImageIcon icono = new ImageIcon("images//miniLogoNoodle.png");

		this.setIconImage(icono.getImage());
		this.setTitle("Ñoodle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		panelCardLayout = new JPanel();
		panelCardLayout.setSize(800, 500);
		panelCardLayout.setLayout(new CardLayout(0, 0));
//		panelCardLayout.add(tareaEstudiante, "Panel Tareas Estudiante");
//		panelCardLayout.add(tareaProfesor, "Panel Tarea Profesor");


		CardLayout cl = (CardLayout) (panelCardLayout.getLayout());
//		Asi se cambia de un panel a otro (esas 2 lineas) 

		if (u instanceof Estudiante) {
			JPanel panelEstudiante = new PanelPrincipalEstudiante(u); // 3
			panelCardLayout.add(panelEstudiante, "Panel Estudiante");
			cl.show(panelCardLayout, "Panel Estudiante");						
		}
		else if (u instanceof Profesor) {
			JPanel panelProfesor = new PanelPrincipalProfesor(u); // 2
			panelCardLayout.add(panelProfesor, "Panel Profesor");
			cl.show(panelCardLayout, "Panel Profesor");			
		}

		this.add(panelCardLayout, BorderLayout.CENTER);

		this.setVisible(true);
	}

}


