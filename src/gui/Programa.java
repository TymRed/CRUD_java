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

import javax.swing.BorderFactory;
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
		
		JPanel panelEstudiante = new PanelPrincipalEstudiante(u); // 3
		
		JPanel tareaProfesor = new PanelTareasProfesor(u); // 5

		JPanel panelCardLayout = new JPanel();
		panelCardLayout.setSize(800, 500);
		panelCardLayout.setLayout(new CardLayout(0, 0));
		panelCardLayout.add(tareaProfesor, "Tarea Profesor");
		panelCardLayout.add(panelEstudiante, "Panel Estudiante");

		this.add(panelCardLayout, BorderLayout.CENTER);

		this.setVisible(true);
	}

}

class PanelPrincipalEstudiante extends JPanel {

	public PanelPrincipalEstudiante(Usuario u) {

		this.setSize(800, 500);
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel infoAsignaturaContenedor = new PanelConLogo(u);
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

class PanelTareasProfesor extends JPanel {

	public PanelTareasProfesor(Usuario u) {
		
		this.setSize(800,500);
		this.setBackground(new Color(0xe1e5f2));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x022b3a), 2),BorderFactory.createEmptyBorder(20,50,20,50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel paneCardLayout = new JPanel();
		paneCardLayout.setSize(800,500);
		paneCardLayout.setLayout(new CardLayout(0, 0));
		paneCardLayout.add(this, "Tareas Profe");
		
		JPanel infoAsignaturaContenedor = new JPanel();
		infoAsignaturaContenedor.setBackground(new Color(0xe1e5f2));
		infoAsignaturaContenedor.setPreferredSize(new Dimension(100,130));
		this.add(infoAsignaturaContenedor, BorderLayout.NORTH);
		infoAsignaturaContenedor.setLayout(null);
		
		JLabel nombAsig = new JLabel("Prog");
		nombAsig.setBounds(0, 10, 71, 13);
		infoAsignaturaContenedor.add(nombAsig);
		
		JButton atras = new JButton("Atras");
		atras.setFocusPainted(false);
		atras.setBounds(582, 0, 100, 100);
		atras.setBackground(Color.red);
		infoAsignaturaContenedor.add(atras);

		
		
		String[] array = {"Tarea1", "tarea2"};
		JPanel contenedorBotones = new JPanel();
		contenedorBotones.setBackground(new Color(0xe1e5f2));
		contenedorBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		this.add(contenedorBotones);
		
		JComboBox elegirTarea = new JComboBox(array);
		contenedorBotones.add(elegirTarea, BorderLayout.WEST);
		
		JButton addTarea = new JButton("+");
		contenedorBotones.add(addTarea, BorderLayout.CENTER);
		
		JButton removeTarea = new JButton("-");
		contenedorBotones.add(removeTarea, BorderLayout.EAST);
		
		
		
		JPanel tareasContenedor = new JPanel();
//		tareasContenedor.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		GridBagLayout gbl_tarea1 = new GridBagLayout();
		gbl_tarea1.columnWeights = new double[]{1.0, 1.0, 0.5, 0.0};
		JPanel tarea1 = new JPanel(gbl_tarea1);
		tarea1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x1f7a8c), 3),BorderFactory.createEmptyBorder(5,5,5,5)));
		tarea1.setBackground(new Color(0xbfdbf7));
		tarea1.setPreferredSize(new Dimension(1,40));
		tareasContenedor.add(tarea1);
		
		JLabel nomAl1 = new JLabel("Tymur Kulivar");
		nomAl1.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_nomAl1 = new GridBagConstraints();
		gbc_nomAl1.insets = new Insets(0, 0, 0, 5);
		gbc_nomAl1.gridy = 0;
		gbc_nomAl1.gridx = 0;
//		gbc_nomAl1.ipadx = 15;
		gbc_nomAl1.anchor = GridBagConstraints.WEST;
		tarea1.add(nomAl1, gbc_nomAl1);
		
		JLabel time = new JLabel("10/05/2023");
		time.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_time = new GridBagConstraints();
		gbc_time.insets = new Insets(0, 0, 0, 5);
		gbc_time.anchor = GridBagConstraints.WEST;
		gbc_time.gridx = 1;
		gbc_time.gridy = 0;
//		gbc_lblNewLabel_2.ipadx = 15;
		tarea1.add(time, gbc_time);
		
		JTextField nota = new JTextField();
		GridBagConstraints gbc_nota = new GridBagConstraints();
		gbc_nota.fill = GridBagConstraints.HORIZONTAL;
		gbc_nota.insets = new Insets(0, 0, 0, 5);
		gbc_nota.gridx = 2;
		gbc_nota.gridy = 0;
		tarea1.add(nota, gbc_nota);
		nota.setColumns(1);
		
		JButton submitNota = new JButton("Poner");
		GridBagConstraints gbc_submitNota = new GridBagConstraints();
		gbc_submitNota.gridx = 3;
		gbc_submitNota.gridy = 0;
		gbc_submitNota.anchor = GridBagConstraints.EAST;
		tarea1.add(submitNota, gbc_submitNota);
		
		JPanel tarea2 = new JPanel();
		tarea2.setBackground(new Color(0, 255, 0));
		tarea2.setPreferredSize(new Dimension(1,40));
		tareasContenedor.add(tarea2);
		GridBagLayout gbl_tarea2 = new GridBagLayout();
		gbl_tarea2.columnWidths = new int[]{102, 96, 0};
		gbl_tarea2.rowHeights = new int[]{19, 0};
		gbl_tarea2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_tarea2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tarea2.setLayout(gbl_tarea2);
		
		JPanel tarea4 = new JPanel();
		tarea4.setBackground(new Color(128, 255, 0));
		tarea4.setPreferredSize(new Dimension(1,40));
		tareasContenedor.add(tarea4);
		
		JPanel tarea5 = new JPanel();
		tarea5.setBackground(new Color(128, 255, 128));
		tarea5.setPreferredSize(new Dimension(1,40));
		tareasContenedor.add(tarea5);
		
		JPanel tarea6 = new JPanel();
		tarea6.setBackground(new Color(0, 255, 128));
		tarea6.setPreferredSize(new Dimension(1,40));
		tareasContenedor.add(tarea6);
		
		JPanel tarea7 = new JPanel();
		tarea7.setBackground(new Color(0, 128, 64));
		tarea7.setPreferredSize(new Dimension(1,40));
		tareasContenedor.add(tarea7);
		
		JScrollPane tareasScroll = new JScrollPane(tareasContenedor);
		tareasScroll.setPreferredSize(new Dimension(200,250));
		this.add(tareasScroll, BorderLayout.SOUTH);
		
		
//		frame.getContentPane().add(panel, BorderLayout.CENTER);

	}

}

class PanelConLogo extends JPanel {

	private Image logo;
	private JButton botonSalir;
	private ImageIcon iconoSalir;

	public PanelConLogo(Usuario u) {
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
}
