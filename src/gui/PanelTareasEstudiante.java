package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import bd.Prueba;
import logica.Asignatura;
import logica.Tarea;
import logica.Usuario;

class PanelTareasEstudiante extends JPanel implements ItemListener, ActionListener {
	JComboBox<String> eligirTarea;
	private JButton botonAtras;
	Usuario u;

	public PanelTareasEstudiante(Usuario u, Asignatura a) {
		this.u = u;
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

		botonAtras = new JButton("Atras");
		botonAtras.setFocusPainted(false);
		botonAtras.setBounds(582, 0, 100, 100);
		botonAtras.setBackground(Color.red);
		botonAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botonAtras.addActionListener(this);
		infoAsignaturaContenedor.add(botonAtras);

		JPanel tareasContenedor = new JPanel();
		tareasContenedor.setBackground(new Color(Vista.COLOR2));
//		tareasContenedor.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		ArrayList<Tarea> tareas = new ArrayList<Tarea>(); // Hay que mejorar
//		Prueba.buscarTareasEstudiante(tareas, "Prog");

		for (Tarea tareaInfo : tareas) {
			String nombreAlumno = tareaInfo.getNombreEstudiante();
			String fecha = tareaInfo.getFechaEntrega(); // cambiar a date

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
		submitNota.setBackground(new Color(Vista.COLOR4));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonAtras) {
			JPanel panelEstudiante = new PanelPrincipalEstudiante(u); // 3
			Programa.panelCardLayout.add(panelEstudiante, "Panel Estudiante");
			CardLayout cl = (CardLayout) (Programa.panelCardLayout.getLayout());
			cl.show(Programa.panelCardLayout, "Panel Estudiante");
		}
	}

}
