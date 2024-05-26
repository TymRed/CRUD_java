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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import bd.BaseQueries;
import logica.Asignatura;
import logica.Tarea;
import logica.Usuario;

class PanelTareasEstudiante extends JPanel implements ActionListener {
	private JButton botonAtras;
	Usuario u;
	JPanel tareasContenedor;
	Asignatura asig;
	public PanelTareasEstudiante(Usuario u, Asignatura a) {
		this.u = u;
		this.asig = a;
		
		this.setSize(800, 500);
		this.setBackground(new Color(0xe1e5f2));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x022b3a), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

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

		tareasContenedor = new JPanel();
		tareasContenedor.setBackground(new Color(Vista.COLOR2));
//		tareasContenedor.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		BaseQueries.buscarTareasEstudiante(tareas, asig.getNombre());
		bucleTareas(tareas);

		JScrollPane tareasScroll = new JScrollPane(tareasContenedor);
		tareasScroll.setPreferredSize(new Dimension(200, 250));
		this.add(tareasScroll, BorderLayout.SOUTH);

	}

	public void bucleTareas(ArrayList<Tarea> tareas) {
		tareasContenedor.removeAll();
		for (Tarea tareaInfo : tareas) {
			JPanel tarea = crearTarea(tareaInfo);
			tareasContenedor.add(tarea);
		}
		tareasContenedor.revalidate();
		tareasContenedor.repaint();
	}
	
	public JPanel crearTarea(Tarea tareaInfo) {
		boolean entregado = BaseQueries.buscarSiEntregado(tareaInfo.getNombre(), u.getNombre(), asig.getNombre());
		
	    GridBagLayout gbl_tarea1 = new GridBagLayout();
	    gbl_tarea1.columnWeights = new double[] { 1.0, 0.0, 0.0 };
	    gbl_tarea1.columnWidths = new int[] { 0, 450, 50 }; // Ajusta estos valores según sea necesario

	    JPanel tarea = new JPanel(gbl_tarea1);
	    tarea.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR2), 3),
	            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	    tarea.setBackground(new Color(Vista.COLOR3));
	    tarea.setMaximumSize(new Dimension(800, 40));

	    GridBagConstraints gbc = new GridBagConstraints();
	    JLabel nombreTarea = new JLabel(tareaInfo.getNombre());
	    nombreTarea.setHorizontalAlignment(SwingConstants.LEFT);
	    gbc.insets = new Insets(0, 0, 0, 5);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    tarea.add(nombreTarea, gbc);


	    
	    JLabel descrip = new JLabel(tareaInfo.getDescripcion());
	    nombreTarea.setHorizontalAlignment(SwingConstants.LEFT);
	    gbc.insets = new Insets(0, 0, 0, 20);
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 1;
	    tarea.add(descrip, gbc);
	    
//	    if (tareaInfo.getNota() != null) {
//	    	Color verde = new Color(20,200,20);
//	    	Color rojo = new Color(200,20,20);
//	    	nota.setText(tareaInfo.getNota() + "");
//	    	nota.setBackground(tareaInfo.getNota() >= 5 ? verde : rojo);
//	    	nota.setEnabled(false);
//	    }

	    JButton botonEnviar = new JButton("Poner");
	    botonEnviar.setBackground(new Color(Vista.COLOR4));
	    botonEnviar.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR1), 1),
	            BorderFactory.createEmptyBorder(3, 17, 3, 17)));
	    botonEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    botonEnviar.setFocusable(false);
	    botonEnviar.addActionListener(new ActionListener() { //Para poder hacerlo con varios botones
	        public void actionPerformed(ActionEvent e) {
	        	JFileChooser enviador = new JFileChooser();
				enviador.showOpenDialog(null);
				BaseQueries.entregarTarea(tareaInfo.getNombre(), u.getNombre(), asig.getNombre());
				botonEnviar.setEnabled(false);//Por ahora, para evitar problemas
	        }
	    });
	    if (entregado) { //Por ahora, para evitar problemas
	    	botonEnviar.setEnabled(false);
	    }
	    gbc.insets = new Insets(0, 0, 0, 0);
	    gbc.gridx = 2;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.fill = GridBagConstraints.NONE;
	    tarea.add(botonEnviar, gbc);

	    return tarea;
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
