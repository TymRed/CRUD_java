package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logica.Usuario;

public class Programa extends JFrame {

	public Programa(Usuario u) {
		ImageIcon icono = new ImageIcon("images//miniLogoNoodle.png");

		this.setIconImage(icono.getImage());
		this.setTitle("Ñoodle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 500);
//		this.setResizable(false);
		this.setLocationRelativeTo(null);

		JPanel panelEstudiante = new PanelPrincipalEstudiante();

		JPanel panelCardLayout = new JPanel();
		panelCardLayout.setSize(800, 500);
		panelCardLayout.setLayout(new CardLayout(0, 0));
		panelCardLayout.add(panelEstudiante, "Panel Estudiante");

		this.add(panelCardLayout, BorderLayout.CENTER);

		this.setVisible(true);
	}

}

class PanelPrincipalEstudiante extends JPanel {

	public PanelPrincipalEstudiante() {

		this.setSize(800, 500);
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel infoAsignaturaContenedor = new PanelConLogo();
		this.add(infoAsignaturaContenedor, BorderLayout.CENTER);

		JPanel asignaturasContenedor = new JPanel();
		this.add(asignaturasContenedor, BorderLayout.SOUTH);
		asignaturasContenedor.setPreferredSize(new Dimension(200, 200));
		asignaturasContenedor.setLayout(new GridLayout(1, 4, 10, 0));
		asignaturasContenedor.setBackground(new Color(Vista.COLOR2));
		asignaturasContenedor.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		JButton asignatura1 = new JButton("PROG");
		asignatura1.setBackground(new Color(Vista.COLOR3));
		asignatura1.setFont(new Font("Consolas", Font.BOLD, 18));
		asignatura1.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2));
		asignatura1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		asignatura1.setFocusable(false);
		asignaturasContenedor.add(asignatura1);

		JButton asignatura2 = new JButton("BBDD");
		asignatura2.setBackground(new Color(Vista.COLOR3));
		asignatura2.setFont(new Font("Consolas", Font.BOLD, 18));
		asignatura2.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2));
		asignatura2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		asignatura2.setFocusable(false);
		asignaturasContenedor.add(asignatura2);

		JButton asignatura3 = new JButton("LGMS");
		asignatura3.setBackground(new Color(Vista.COLOR3));
		asignatura3.setFont(new Font("Consolas", Font.BOLD, 18));
		asignatura3.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2));
		asignatura3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		asignatura3.setFocusable(false);
		asignaturasContenedor.add(asignatura3);

		JButton asignatura4 = new JButton("SISI");
		asignatura4.setBackground(new Color(Vista.COLOR3));
		asignatura4.setFont(new Font("Consolas", Font.BOLD, 18));
		asignatura4.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2));
		asignatura4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		asignatura4.setFocusable(false);
		asignaturasContenedor.add(asignatura4);

	}

}

class PanelConLogo extends JPanel {

	private Image logo;
	private JButton botonSalir;
	private ImageIcon iconoSalir;

	public PanelConLogo() {
		this.setBackground(new Color(Vista.COLOR4));
		this.setPreferredSize(new Dimension(100, 130));
		this.setLayout(null);

		botonSalir = new JButton();
		botonSalir.setBounds(610, 25, 70, 70);
		botonSalir.setFocusable(false);
		botonSalir.setBackground(new Color(Vista.COLOR4));
		botonSalir.setBorderPainted(false);
		botonSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));

		iconoSalir = new ImageIcon("images//atras.png");
		Image imagen = iconoSalir.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		iconoSalir = new ImageIcon(imagenRedimensionada);
		botonSalir.setIcon(iconoSalir);
		this.add(botonSalir);

		JLabel textoNombre = new JLabel("Nombre: ");
		textoNombre.setBounds(0, 140, 80, 13);
		this.add(textoNombre);

		JLabel textoAsig = new JLabel("Asignaturas");
		textoAsig.setFont(new Font("Consolas", Font.BOLD, 20));
		textoAsig.setBounds(0, 195, 180, 20);
		this.add(textoAsig);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		logo = new ImageIcon("images//logoNoodle.png").getImage();
		g.drawImage(logo, -10, 30, 150, 50, null);
//		botonSalir = new ImageIcon("images//atras.png").getImage();
//		g.drawImage(botonSalir, 600, 42, 100, 100, null);
	}
}
