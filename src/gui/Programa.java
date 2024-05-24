package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bd.Prueba;
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


