package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logica.Usuario;

class PanelPrincipalEstudiante extends JPanel implements ActionListener {

	JButton asignatura1, asignatura2, asignatura3, asignatura4, botonSalir;
	private Programa programa;

	public PanelPrincipalEstudiante(Usuario u, Programa programa) {
		this.programa = programa;
		this.setSize(800, 500);
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel infoAsignaturaContenedor = new PanelConLogoEstu(u);
		this.add(infoAsignaturaContenedor, BorderLayout.CENTER);

		JPanel asignaturasContenedor = new JPanel();
		this.add(asignaturasContenedor, BorderLayout.SOUTH);
		asignaturasContenedor.setPreferredSize(new Dimension(200, 200));
		asignaturasContenedor.setLayout(new GridLayout(1, 4, 10, 0));
		asignaturasContenedor.setBackground(new Color(Vista.COLOR2));
		asignaturasContenedor.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		asignatura1 = crearBoton("PROG");
		asignaturasContenedor.add(asignatura1);

		asignatura2 = crearBoton("BBDD");
		asignaturasContenedor.add(asignatura2);

		asignatura3 = crearBoton("LGMS");
		asignaturasContenedor.add(asignatura3);

		asignatura4 = crearBoton("SISI");
		asignaturasContenedor.add(asignatura4);

	}

	public JButton crearBoton(String nombre) {
		JButton asignatura = new JButton(nombre);
		asignatura.setBackground(new Color(Vista.COLOR3));
		asignatura.setFont(new Font("Consolas", Font.BOLD, 18));
		asignatura.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2));
		asignatura.setCursor(new Cursor(Cursor.HAND_CURSOR));
		asignatura.setFocusable(false);
		asignatura.addActionListener(this);
		return asignatura;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == asignatura1) {
			System.out.println("1"); // Constructor Ventana Asig
		} else if (e.getSource() == asignatura2) {
			System.out.println("2"); // Constructor Ventana Asig
		} else if (e.getSource() == asignatura3) {
			System.out.println("3"); // Constructor Ventana Asig
		} else if (e.getSource() == asignatura4) {
			System.out.println("4"); // Constructor Ventana Asig
		}
	}

	class PanelConLogoEstu extends JPanel implements ActionListener {

		private Image logo;
		private ImageIcon iconoSalir;

		public PanelConLogoEstu(Usuario u) {
			this.setBackground(new Color(Vista.COLOR4));
			this.setPreferredSize(new Dimension(100, 130));
			this.setLayout(null);

			botonSalir = new JButton();
			botonSalir.setBounds(610, 25, 70, 70);
			botonSalir.setFocusable(false);
			botonSalir.setBackground(new Color(Vista.COLOR4));
			botonSalir.setBorderPainted(false);
			botonSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
			botonSalir.addActionListener(this);

			iconoSalir = new ImageIcon("images//atras.png");
			Image imagen = iconoSalir.getImage();
			Image imagenRedimensionada = imagen.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
			iconoSalir = new ImageIcon(imagenRedimensionada);
			botonSalir.setIcon(iconoSalir);
			this.add(botonSalir);

			JLabel textoNombre = new JLabel("Nombre: " + u.getNombre());
			textoNombre.setFont(new Font("Consolas", Font.BOLD, 16));
			textoNombre.setBounds(0, 125, 200, 25);
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
//			botonSalir = new ImageIcon("images//atras.png").getImage();
//			g.drawImage(botonSalir, 600, 42, 100, 100, null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botonSalir) {
				Vista ventana = new Vista();
				programa.dispose();
			}
		}
	}

}
