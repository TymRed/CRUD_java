package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bd.Prueba;
import logica.Asignatura;
import logica.Tarea;
import logica.Usuario;

class PanelTareasProfesor extends JPanel implements ItemListener {
	JComboBox<String> eligirTarea;
	JPanel tareasContenedor;
	Asignatura asig;
	Usuario u;
	public PanelTareasProfesor(Usuario u, Asignatura asig) {

		this.u = u;
		this.asig = asig;
		
		this.setSize(800, 500);
		this.setBackground(new Color(0xe1e5f2));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x022b3a), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

//		JPanel paneCardLayout = new JPanel();
//		paneCardLayout.setSize(800, 500);
//		paneCardLayout.setLayout(new CardLayout(0, 0));
//		paneCardLayout.add(this, "Tareas Profe");

		JPanel infoAsignaturaContenedor = new JPanel();
		infoAsignaturaContenedor.setBackground(new Color(Vista.COLOR4));
		infoAsignaturaContenedor.setPreferredSize(new Dimension(100, 130));
		this.add(infoAsignaturaContenedor, BorderLayout.NORTH);
		infoAsignaturaContenedor.setLayout(null);

		JLabel nombAsig = new JLabel(asig.getNombre());
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
		Prueba.crearListaNombreTareas(nombresDeTareas, asig.getNombre()); //se puede cambiar. da pereza
		eligirTarea = new JComboBox<String>(nombresDeTareas.stream().toArray(String[]::new));
		eligirTarea.addItemListener(this);
		contenedorBotones.add(eligirTarea, BorderLayout.WEST);

		JButton addTarea = new JButton("+");
		contenedorBotones.add(addTarea, BorderLayout.CENTER);

		JButton removeTarea = new JButton("-");
		contenedorBotones.add(removeTarea, BorderLayout.EAST);

		tareasContenedor = new JPanel();
		tareasContenedor.setBackground(new Color(Vista.COLOR2));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		Prueba.buscarEntregas(tareas, "Tarea1", asig.getNombre()); //mejorable

		bucleTareas(tareas);

		JScrollPane tareasScroll = new JScrollPane(tareasContenedor);
		tareasScroll.setPreferredSize(new Dimension(200, 250));
		this.add(tareasScroll, BorderLayout.SOUTH);

	}

	public void bucleTareas(ArrayList<Tarea> tareas) {
		tareasContenedor.removeAll();
		for (Tarea tareaInfo : tareas) {
			String nombreAlumno = tareaInfo.getNombreEstudiante();
			String fecha = tareaInfo.getFechaEntrega(); // cambiar a date

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
	    
//	    System.out.println(tareaInfo.getNota());
	    if (tareaInfo.getNota() != null) {
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
	        	double notaD = Double.parseDouble(nota.getText());
	            Prueba.ponerNotaEstudiante(notaD, tareaInfo.getNombre(), nombreEstud.getText(), asig.getNombre());
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
			
			ArrayList<Tarea> tareas = new ArrayList<Tarea>();
			Prueba.buscarEntregas(tareas, selectedValue, asig.getNombre());

			bucleTareas(tareas);
		}
	}

}
