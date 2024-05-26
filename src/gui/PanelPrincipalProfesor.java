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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bd.BaseQueries;
import logica.Asignatura;
import logica.Usuario;

class PanelPrincipalProfesor extends JPanel implements ActionListener {


	JButton asignaturaBoton, boletinBoton;

	Usuario prof;
	Asignatura asig;
	
	public PanelPrincipalProfesor(Usuario u) {

		this.prof = u;
		this.asig = BaseQueries.buscarAsignaturaProfesor(u.getNombre());
		
		this.setSize(800, 500);
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel infoAsignaturaContenedor = new PanelConLogoProf(u);
		this.add(infoAsignaturaContenedor, BorderLayout.CENTER);

		JPanel asignaturasContenedor = new JPanel();
		this.add(asignaturasContenedor, BorderLayout.SOUTH);
		asignaturasContenedor.setPreferredSize(new Dimension(200, 200));
		asignaturasContenedor.setLayout(new GridLayout(1, 2, 80, 0));
		asignaturasContenedor.setBackground(new Color(Vista.COLOR2));
		asignaturasContenedor.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		asignaturaBoton = crearBoton(asig.getNombre());
		asignaturasContenedor.add(asignaturaBoton);

		boletinBoton = crearBoton("Imprimir boletín");
		asignaturasContenedor.add(boletinBoton);

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
		if (e.getSource() == asignaturaBoton) {
			JPanel tareaProfesor = new PanelTareasProfesor(prof, asig);
			Programa.panelCardLayout.add(tareaProfesor, "Panel Tarea Profesor");
			CardLayout cl = (CardLayout) (Programa.panelCardLayout.getLayout());
			cl.show(Programa.panelCardLayout, "Panel Tarea Profesor");
		} else if (e.getSource() == boletinBoton) {
			System.out.println("2"); // Sacar .txt
		}
	}

	class PanelConLogoProf extends JPanel implements ActionListener {

		private Image logo;
		private JButton botonSalir;
		private ImageIcon iconoSalir;

		public PanelConLogoProf(Usuario u) {
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
			botonSalir.addActionListener(this);
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
//		botonSalir = new ImageIcon("images//atras.png").getImage();
//		g.drawImage(botonSalir, 600, 42, 100, 100, null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botonSalir) {
				Vista ventana = new Vista();
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // para acceder al frame
				topFrame.dispose();
			}
		}
	}
}
