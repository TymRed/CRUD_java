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
		cl.show(panelCardLayout, "Panel Tarea Profesor");


		this.add(panelCardLayout, BorderLayout.CENTER);

		this.setVisible(true);
	}

}

class PanelPrincipalEstudiante extends JPanel implements ActionListener {

	JButton asignatura1, asignatura2, asignatura3, asignatura4;

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

}

class PanelTareasEstudiante extends JPanel implements ItemListener {
	JComboBox<String> eligirTarea;

	public PanelTareasEstudiante(Usuario u) {

		this.setSize(800, 500);
		this.setBackground(new Color(0xe1e5f2));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x022b3a), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel paneCardLayout = new JPanel();
		paneCardLayout.setSize(800, 500);
		paneCardLayout.setLayout(new CardLayout(0, 0));
		paneCardLayout.add(this, "Tareas Profe");

		JPanel infoAsignaturaContenedor = new JPanel();
		infoAsignaturaContenedor.setBackground(new Color(0xe1e5f2));
		infoAsignaturaContenedor.setPreferredSize(new Dimension(100, 160));
		this.add(infoAsignaturaContenedor, BorderLayout.NORTH);
		infoAsignaturaContenedor.setLayout(null);

		JLabel nombAsig = new JLabel("Tareas Prog");// Cambiar por Ñoodle
		nombAsig.setBounds(0, 10, 71, 13);
		infoAsignaturaContenedor.add(nombAsig);

		JLabel notaMedia = new JLabel("Nota media: ");
		notaMedia.setBounds(0, 80, 71, 13);
		infoAsignaturaContenedor.add(notaMedia);

		JLabel textoAsig = new JLabel("Tareas");
		textoAsig.setFont(new Font("Consolas", Font.BOLD, 20));
		textoAsig.setBounds(0, 145, 180, 20);
		infoAsignaturaContenedor.add(textoAsig);

		JButton atras = new JButton("Atras");
		atras.setFocusPainted(false);
		atras.setBounds(582, 0, 100, 100);
		atras.setBackground(Color.red);
		infoAsignaturaContenedor.add(atras);

		JPanel tareasContenedor = new JPanel();
//		tareasContenedor.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		ArrayList<ArrayList<String>> tareas = new ArrayList<ArrayList<String>>(); // Hay que mejorar
		Prueba.buscarTareas(tareas, "Prog");

		for (ArrayList<String> tareaInfo : tareas) {
			String nombreAlumno = tareaInfo.get(0);
			String fecha = tareaInfo.get(1); // cambiar a date

			JPanel tarea = crearTarea(nombreAlumno, fecha);
			tareasContenedor.add(tarea);

		}

		JScrollPane tareasScroll = new JScrollPane(tareasContenedor);
		tareasScroll.setPreferredSize(new Dimension(200, 250));
		this.add(tareasScroll, BorderLayout.SOUTH);

	}
	
	

	public JPanel crearTarea(String nombre, String fecha) {
		GridBagLayout gbl_tarea1 = new GridBagLayout();
		gbl_tarea1.columnWeights = new double[] { 2.0, 3.0, 0.5 };

		JPanel tarea1 = new JPanel(gbl_tarea1);
		tarea1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x1f7a8c), 3),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		tarea1.setBackground(new Color(0xbfdbf7));
		tarea1.setPreferredSize(new Dimension(1, 40));
		tarea1.setMaximumSize(new Dimension(4000, 40)); // 4000 es limite al cual nunca llegaremos

		JLabel nomAl1 = new JLabel(nombre);
		nomAl1.setHorizontalAlignment(SwingConstants.LEFT);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		tarea1.add(nomAl1, gbc);

		JLabel time = new JLabel(fecha);
		time.setHorizontalAlignment(SwingConstants.LEFT);
		gbc.gridx = 1;
		tarea1.add(time, gbc);

		JButton submitNota = new JButton("Enviar");
		submitNota.setBackground(new Color(Vista.COLOR3));
		submitNota.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x1f7a8c), 1),
				BorderFactory.createEmptyBorder(7, 17, 7, 17)));
		submitNota.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitNota.setFocusable(false);
//		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.EAST;
		tarea1.add(submitNota, gbc);
		return tarea1;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			String selectedValue = eligirTarea.getSelectedItem().toString();
			System.out.println(selectedValue);
		}
	}

}

class PanelPrincipalProfesor extends JPanel implements ActionListener {

	JButton asignatura1, asignatura2, asignatura3, asignatura4;

	public PanelPrincipalProfesor(Usuario u) {

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
		asignaturasContenedor.setLayout(new GridLayout(1, 2, 80, 0));
		asignaturasContenedor.setBackground(new Color(Vista.COLOR2));
		asignaturasContenedor.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		asignatura1 = crearBoton("PROG");
		asignaturasContenedor.add(asignatura1);

		asignatura2 = crearBoton("Imprimir boletín");
		asignaturasContenedor.add(asignatura2);

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
			System.out.println("2"); // Sacar .txt
		}
	}

}

class PanelTareasProfesor extends JPanel implements ItemListener {
	JComboBox<String> eligirTarea;
	JPanel tareasContenedor;
	public PanelTareasProfesor(Usuario u) {

		this.setSize(800, 500);
		this.setBackground(new Color(0xe1e5f2));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x022b3a), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel paneCardLayout = new JPanel();
		paneCardLayout.setSize(800, 500);
		paneCardLayout.setLayout(new CardLayout(0, 0));
		paneCardLayout.add(this, "Tareas Profe");

		JPanel infoAsignaturaContenedor = new JPanel();
		infoAsignaturaContenedor.setBackground(new Color(Vista.COLOR4));
		infoAsignaturaContenedor.setPreferredSize(new Dimension(100, 130));
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

		JPanel contenedorBotones = new JPanel();
		contenedorBotones.setBackground(new Color(Vista.COLOR4));
		contenedorBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		this.add(contenedorBotones);

		ArrayList<String> nombresDeTareas = new ArrayList<String>();
		Prueba.crearListaNombreTareas(nombresDeTareas);
		eligirTarea = new JComboBox<String>(nombresDeTareas.stream().toArray(String[]::new));
		eligirTarea.addItemListener(this);
		contenedorBotones.add(eligirTarea, BorderLayout.WEST);

		JButton addTarea = new JButton("+");
		contenedorBotones.add(addTarea, BorderLayout.CENTER);

		JButton removeTarea = new JButton("-");
		contenedorBotones.add(removeTarea, BorderLayout.EAST);

		tareasContenedor = new JPanel();
		tareasContenedor.setBackground(new Color(Vista.COLOR2));
//		tareasContenedor.setBord er(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		ArrayList<ArrayList<String>> tareas = new ArrayList<ArrayList<String>>(); // Hay que mejorar
		Prueba.buscarTareas(tareas, "Tarea1"); //mejorable

		bucleTareas(tareas);

		JScrollPane tareasScroll = new JScrollPane(tareasContenedor);
		tareasScroll.setPreferredSize(new Dimension(200, 250));
		this.add(tareasScroll, BorderLayout.SOUTH);

	}

	public void bucleTareas(ArrayList<ArrayList<String>> tareas) {
		tareasContenedor.removeAll();
		for (ArrayList<String> tareaInfo : tareas) {
			String nombreAlumno = tareaInfo.get(0);
			String fecha = tareaInfo.get(1); // cambiar a date

			JPanel tarea = crearTarea(nombreAlumno, fecha);
			tareasContenedor.add(tarea);

		}
		tareasContenedor.revalidate();
		tareasContenedor.repaint();
	}
	public JPanel crearTarea(String nombre, String fecha) {
	    GridBagLayout gbl_tarea1 = new GridBagLayout();
	    gbl_tarea1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0 };
	    gbl_tarea1.columnWidths = new int[] { 0, 100, 50, 50 }; // Ajusta estos valores según sea necesario

	    JPanel tarea1 = new JPanel(gbl_tarea1);
	    tarea1.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR2), 3),
	            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	    tarea1.setBackground(new Color(Vista.COLOR3));
	    tarea1.setMaximumSize(new Dimension(800, 40));

	    GridBagConstraints gbc = new GridBagConstraints();
	    JLabel nomAl1 = new JLabel(nombre);
	    nomAl1.setHorizontalAlignment(SwingConstants.LEFT);
	    gbc.insets = new Insets(0, 0, 0, 5);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    tarea1.add(nomAl1, gbc);

	    JLabel time = new JLabel(fecha);
	    time.setHorizontalAlignment(SwingConstants.LEFT);
	    gbc.insets = new Insets(0, 0, 0, 200);
	    gbc.gridx = 1;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.fill = GridBagConstraints.NONE;
	    tarea1.add(time, gbc);

	    JTextField nota = new JTextField();
	    gbc.insets = new Insets(0, 0, 0, 20);
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 2;
	    tarea1.add(nota, gbc);

	    JButton submitNota = new JButton("Poner");
	    submitNota.setBackground(new Color(Vista.COLOR4));
	    submitNota.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR1), 1),
	            BorderFactory.createEmptyBorder(3, 17, 3, 17)));
	    submitNota.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    submitNota.setFocusable(false);
	    gbc.insets = new Insets(0, 0, 0, 0);
	    gbc.gridx = 3;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.fill = GridBagConstraints.NONE;
	    tarea1.add(submitNota, gbc);

	    return tarea1;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			String selectedValue = eligirTarea.getSelectedItem().toString();
			System.out.println(selectedValue);
			
			ArrayList<ArrayList<String>> tareas = new ArrayList<ArrayList<String>>(); // Hay que mejorar
			Prueba.buscarTareas(tareas, selectedValue);

			bucleTareas(tareas);
		}
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
