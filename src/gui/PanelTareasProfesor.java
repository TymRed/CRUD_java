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
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bd.BaseQueries;
import logica.Asignatura;
import logica.Tarea;
import logica.Usuario;

class PanelTareasProfesor extends JPanel implements ItemListener, ActionListener {
	
	JComboBox<String> eligirTarea;
	JPanel tareasContenedor;
	JButton addTarea, removeTarea;
	Asignatura asig;
	Usuario u;
	ArrayList<String> nombresDeTareas;
	
	public PanelTareasProfesor(Usuario u, Asignatura asig) {

		this.u = u;
		this.asig = asig;
		this.setSize(800, 500);
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel infoAsignaturaContenedor = new PanelConLogoProf(u);
		this.add(infoAsignaturaContenedor, BorderLayout.NORTH);
		
		JPanel contenedorBotones = crearPanelContenedorBotones();
		this.add(contenedorBotones);
		
		tareasContenedor = new JPanel();
		tareasContenedor.setBackground(new Color(Vista.COLOR2));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		ArrayList<Tarea> tareas = BaseQueries.buscarEntregas("Tarea1", asig.getNombre());
		bucleTareas(tareas);

		JScrollPane tareasScroll = new JScrollPane(tareasContenedor);
		tareasScroll.setPreferredSize(new Dimension(200, 250));
		this.add(tareasScroll, BorderLayout.SOUTH);

	}

	public JPanel crearPanelContenedorBotones(){
		JPanel contenedorBotones = new JPanel();
		contenedorBotones.setBackground(new Color(Vista.COLOR4));
		contenedorBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		nombresDeTareas = BaseQueries.crearListaNombreTareas(asig.getNombre());
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nombresDeTareas.toArray(new String[0]));
		eligirTarea = new JComboBox<String>();
		eligirTarea.setModel(model);
		eligirTarea.addItemListener(this);
		contenedorBotones.add(eligirTarea, BorderLayout.WEST);

		addTarea = crearBoton("+");
		addTarea.addActionListener(this);
		contenedorBotones.add(addTarea, BorderLayout.CENTER);

		removeTarea = crearBoton("-");
		removeTarea.addActionListener(this);
		contenedorBotones.add(removeTarea, BorderLayout.EAST);
		return contenedorBotones;
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
	    GridBagLayout gbl_tarea1 = new GridBagLayout();
	    gbl_tarea1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0 };
	    gbl_tarea1.columnWidths = new int[] { 0, 100, 50, 50 };

	    JPanel tarea1 = new JPanel(gbl_tarea1);
	    tarea1.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR2), 3),
	            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	    tarea1.setBackground(new Color(Vista.COLOR3));
	    tarea1.setMaximumSize(new Dimension(800, 40));

	    GridBagConstraints gbc = new GridBagConstraints();
	    JLabel nombreEstud = new JLabel(tareaInfo.getNombreEstudiante());
	    nombreEstud.setHorizontalAlignment(SwingConstants.LEFT);
	    gbc.insets = new Insets(0, 0, 0, 5);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    tarea1.add(nombreEstud, gbc);

	    JLabel time = new JLabel(tareaInfo.getFechaEntrega());
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
	    

	    JButton botonPuntuar = new JButton("Puntuar");
	    botonPuntuar.setBackground(new Color(Vista.COLOR4));
	    botonPuntuar.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR1), 1),
	            BorderFactory.createEmptyBorder(3, 17, 3, 17)));
	    botonPuntuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    botonPuntuar.setFocusable(false);
	    botonPuntuar.addActionListener(new ActionListener() { //Para poder hacerlo con varios botones
	        public void actionPerformed(ActionEvent e) {
	        	Double notaD;
	        	try {
	        		notaD = Double.parseDouble(nota.getText());
	        		BaseQueries.ponerNotaEstudiante(notaD, tareaInfo.getNombre(), nombreEstud.getText(), asig.getNombre());
	        		
	        		if (notaD != null && notaD >=0 && notaD <=10) {
	        			Color verde = new Color(144, 238, 144);
	        			Color rojo = new Color(240,128,128);
	        			nota.setText(notaD + "");
	        			nota.setBackground(notaD >= 5 ? verde : rojo);
	        			nota.setEnabled(false);
	        			nota.setDisabledTextColor(new Color(Vista.COLOR1));
	        			botonPuntuar.setEnabled(false);
	        		}
	        		else {
						throw new Exception("error");
					}
	        		repaint();
				} catch (Exception e2) {
					System.out.println("Nota invalida");
				}
	        	
	        }
	    });
	    if (tareaInfo.getNota() != null) {
	    	Color verde = new Color(144, 238, 144);
	    	Color rojo = new Color(240,128,128);
	    	nota.setText(tareaInfo.getNota() + "");
	    	nota.setBackground(tareaInfo.getNota() >= 5 ? verde : rojo);
	    	nota.setEnabled(false);
	    	nota.setDisabledTextColor(new Color(Vista.COLOR1));
	    	botonPuntuar.setEnabled(false);
	    }
	    
	    gbc.insets = new Insets(0, 0, 0, 0);
	    gbc.gridx = 3;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.fill = GridBagConstraints.NONE;
	    tarea1.add(botonPuntuar, gbc);

	    return tarea1;
	}
	public JButton crearBoton(String nombre) {
		JButton asignatura = new JButton(nombre);
		asignatura.setBackground(new Color(Vista.COLOR3));
		asignatura.setFont(new Font("Consolas", Font.BOLD, 18));
		asignatura.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR2), 1),
	            BorderFactory.createEmptyBorder(2, 10, -1, 10)));
		
		asignatura.setCursor(new Cursor(Cursor.HAND_CURSOR));
		asignatura.setFocusable(false);
		return asignatura;
	}
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			String valorSeleccionado = eligirTarea.getSelectedItem().toString();
			System.out.println(valorSeleccionado);
			
			ArrayList<Tarea> tareas = BaseQueries.buscarEntregas(valorSeleccionado, asig.getNombre());
			bucleTareas(tareas);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addTarea) { 
			BaseQueries.crearTarea(asig.getNombre());
			
			nombresDeTareas = BaseQueries.crearListaNombreTareas(asig.getNombre());

			// Para actualizar el modelo del JComboBox
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nombresDeTareas.toArray(new String[0]));
			eligirTarea.setModel(model);

			eligirTarea.revalidate();
			eligirTarea.repaint();
			
			ArrayList<Tarea> tareas = BaseQueries.buscarEntregas("Tarea1", asig.getNombre()); 
			bucleTareas(tareas);
		}
		if (e.getSource() == removeTarea) { 
			String valorSeleccionado = eligirTarea.getSelectedItem().toString();
			BaseQueries.borrarTarea(valorSeleccionado, asig.getNombre());
			
			nombresDeTareas = BaseQueries.crearListaNombreTareas(asig.getNombre());

			// Para actualizar el modelo del JComboBox existente
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nombresDeTareas.toArray(new String[0]));
			eligirTarea.setModel(model);

			eligirTarea.revalidate();
			eligirTarea.repaint();
			
			ArrayList<Tarea> tareas = BaseQueries.buscarEntregas("Tarea1", asig.getNombre()); 
			bucleTareas(tareas);
		}
	}
	
	class PanelConLogoProf extends JPanel implements ActionListener {

		private Image logo;
		private JButton atras;
		private ImageIcon iconoSalir;

		public PanelConLogoProf(Usuario u) {
			
			this.setBackground(new Color(Vista.COLOR4));
			this.setPreferredSize(new Dimension(100, 130));
			this.setLayout(null);
			JLabel nombAsig = new JLabel("Tareas " + asig.getNombre());
			nombAsig.setBounds(0, 100, 120, 20);
			this.add(nombAsig);

			atras = new JButton();
			atras.setBounds(610, 10, 70, 70);
			atras.setFocusable(false);
			atras.setBackground(new Color(Vista.COLOR4));
			atras.setBorderPainted(false);
			atras.setCursor(new Cursor(Cursor.HAND_CURSOR));
						
			iconoSalir = new ImageIcon("images//atras.png");
			Image imagen = iconoSalir.getImage();
			Image imagenRedimensionada = imagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			iconoSalir = new ImageIcon(imagenRedimensionada);
			atras.setIcon(iconoSalir);
			atras.addActionListener(this);

			this.add(atras);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			logo = new ImageIcon("images//logoNoodle.png").getImage();
			g.drawImage(logo, -10, 10, 150, 50, null);
//		botonSalir = new ImageIcon("images//atras.png").getImage();
//		g.drawImage(botonSalir, 600, 42, 100, 100, null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == atras) {
				JPanel panelProfesor = new PanelPrincipalProfesor(u); // 3
				Programa.panelCardLayout.add(panelProfesor, "Panel Profesor");
				CardLayout cl = (CardLayout) (Programa.panelCardLayout.getLayout());
				cl.show(Programa.panelCardLayout, "Panel Profesor");
			}
		}
	}

}
