package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class VistaPersonal extends JFrame {

	public VistaPersonal() {
		ImageIcon icono = new ImageIcon("miniLogoNoodle.png");

		this.setIconImage(icono.getImage());
		this.setTitle("Ñoodle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		PanelPersonal panelPersonal = new PanelPersonal();
		this.add(panelPersonal);

		this.setVisible(true);
	}

}

class PanelPersonal extends JPanel {

	Image logo, logoU;
	JButton botonSalir;
	ImageIcon iconoSalir;

	public PanelPersonal() {

		this.setLayout(null);
		// Fuente principal y Foreground
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 3));

		botonSalir = new JButton();
		botonSalir.setBounds(850, 50, 70, 70);
//		botonSalir.setFocusable(false);
		botonSalir.setBackground(new Color(0, 0, 0, 0));
		botonSalir.setBorderPainted(false);
		botonSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));

		iconoSalir = new ImageIcon("atras.png");
		Image imagen = iconoSalir.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		iconoSalir = new ImageIcon(imagenRedimensionada);
		botonSalir.setIcon(iconoSalir);

		this.add(botonSalir);

		this.setOpaque(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		this.setFont(new Font("Consolas", Font.BOLD, 25));
		logo = new ImageIcon("logoNoodle.png").getImage();
		this.setFont(new Font("Consolas", Font.BOLD, 28));
		g.drawImage(logo, 60, 50, 150, 50, null);
	}

}