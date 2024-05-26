package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	JButton atras, addTarea, removeTarea;
	Asignatura asig;
	Usuario u;
	ArrayList<String> nombresDeTareas;
	
	public PanelTareasProfesor(Usuario u, Asignatura asig) {

		this.u = u;
		this.asig = asig;
		this.setSize(800, 500);
		this.setBackground(new Color(0xe1e5f2));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x022b3a), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel infoAsignaturaContenedor = new JPanel();
		infoAsignaturaContenedor.setBackground(new Color(Vista.COLOR4));
		infoAsignaturaContenedor.setPreferredSize(new Dimension(100, 130));
		this.add(infoAsignaturaContenedor, BorderLayout.NORTH);
		infoAsignaturaContenedor.setLayout(null);
		JLabel nombAsig = new JLabel("Tareas " + asig.getNombre());
		nombAsig.setBounds(0, 100, 71, 13);
		infoAsignaturaContenedor.add(nombAsig);

		atras = new JButton("Atras");
		atras.setFocusPainted(false);
		atras.setBounds(582, 0, 100, 100);
		atras.setBackground(Color.red);
		atras.setCursor(new Cursor(Cursor.HAND_CURSOR));
		atras.addActionListener(this);
		infoAsignaturaContenedor.add(atras);

		
		
		JPanel contenedorBotones = crearPanelContenedorBotones();
		this.add(contenedorBotones);

		
		
		tareasContenedor = new JPanel();
		tareasContenedor.setBackground(new Color(Vista.COLOR2));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		ArrayList<Tarea> tareas = BaseQueries.buscarEntregas("Tarea1", asig.getNombre()); //mejorable
		bucleTareas(tareas);

		JScrollPane tareasScroll = new JScrollPane(tareasContenedor);
		tareasScroll.setPreferredSize(new Dimension(200, 250));
		this.add(tareasScroll, BorderLayout.SOUTH);

	}

	public JPanel crearPanelContenedorBotones(){
		JPanel contenedorBotones = new JPanel();
		contenedorBotones.setBackground(new Color(Vista.COLOR4));
		contenedorBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		nombresDeTareas = BaseQueries.crearListaNombreTareas(asig.getNombre()); //se puede cambiar. da pereza
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nombresDeTareas.toArray(new String[0]));
		eligirTarea = new JComboBox<String>();
		eligirTarea.setModel(model);
		eligirTarea.addItemListener(this);
		contenedorBotones.add(eligirTarea, BorderLayout.WEST);

		addTarea = new JButton("+");
		addTarea.addActionListener(this);
		contenedorBotones.add(addTarea, BorderLayout.CENTER);

		removeTarea = new JButton("-");
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
	    gbl_tarea1.columnWidths = new int[] { 0, 100, 50, 50 }; // Ajusta estos valores según sea necesario

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
	    
	    if (tareaInfo.getNota() != null) {
	    	Color verde = new Color(20,200,20);
	    	Color rojo = new Color(200,20,20);
	    	nota.setText(tareaInfo.getNota() + "");
	    	nota.setBackground(tareaInfo.getNota() >= 5 ? verde : rojo);
	    	nota.setEnabled(false);
	    }

	    JButton submitNota = new JButton("Poner");
	    submitNota.setBackground(new Color(Vista.COLOR4));
	    submitNota.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR1), 1),
	            BorderFactory.createEmptyBorder(3, 17, 3, 17)));
	    submitNota.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    submitNota.setFocusable(false);
	    submitNota.addActionListener(new ActionListener() { //Para poder hacerlo con varios botones
	        public void actionPerformed(ActionEvent e) {
	        	Double notaD = Double.parseDouble(nota.getText());
	            BaseQueries.ponerNotaEstudiante(notaD, tareaInfo.getNombre(), nombreEstud.getText(), asig.getNombre());
	            
	            if (notaD != null) {
	            	Color verde = new Color(20,200,20);
	    	    	Color rojo = new Color(200,20,20);
	    	    	nota.setText(notaD + "");
	    	    	nota.setBackground(notaD >= 5 ? verde : rojo);
	    	    	nota.setEnabled(false);
	    	    }
	            repaint();
	        }
	    });
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
			
			ArrayList<Tarea> tareas = BaseQueries.buscarEntregas(selectedValue, asig.getNombre());
			bucleTareas(tareas);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == atras) {
			JPanel panelProfesor = new PanelPrincipalProfesor(u); // 3
			Programa.panelCardLayout.add(panelProfesor, "Panel Profesor");
			CardLayout cl = (CardLayout) (Programa.panelCardLayout.getLayout());
			cl.show(Programa.panelCardLayout, "Panel Profesor");
		}
		else if (e.getSource() == addTarea) { 
			BaseQueries.crearTarea(asig.getNombre());
			
			nombresDeTareas = BaseQueries.crearListaNombreTareas(asig.getNombre());

			// Para actualizar el modelo del JComboBox existente
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nombresDeTareas.toArray(new String[0]));
			eligirTarea.setModel(model);

			eligirTarea.revalidate();
			eligirTarea.repaint();
			
			//Para mostrar otra vez entregas de Tarea1
			ArrayList<Tarea> tareas = BaseQueries.buscarEntregas("Tarea1", asig.getNombre()); //mejorable
			bucleTareas(tareas);
		}
//		else if (e.getSource() == atras) {
//			JPanel panelProfesor = new PanelPrincipalProfesor(u); // 3
//			Programa.panelCardLayout.add(panelProfesor, "Panel Profesor");
//			CardLayout cl = (CardLayout) (Programa.panelCardLayout.getLayout());
//			cl.show(Programa.panelCardLayout, "Panel Profesor");
//		}
	}

}
