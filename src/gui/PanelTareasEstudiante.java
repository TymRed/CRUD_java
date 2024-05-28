package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import bd.BaseQueries;
import logica.Asignatura;
import logica.Tarea;
import logica.Usuario;

class PanelTareasEstudiante extends JPanel {
	
	Usuario u;
	JPanel tareasContenedor;
	Asignatura asig;
	public PanelTareasEstudiante(Usuario u, Asignatura a) {
		this.u = u;
		this.asig = a;
		
		this.setSize(800, 500);
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2),
				BorderFactory.createEmptyBorder(20, 50, 20, 50)));
		this.setLayout(new BorderLayout(0, 0));

		JPanel infoAsignaturaContenedor = new PanelConLogoEst(u);
		this.add(infoAsignaturaContenedor, BorderLayout.NORTH);


		tareasContenedor = new JPanel();
		tareasContenedor.setBackground(new Color(Vista.COLOR2));
		tareasContenedor.setLayout(new BoxLayout(tareasContenedor, BoxLayout.Y_AXIS));

		ArrayList<Tarea> tareas = BaseQueries.buscarTareasEstudiante(asig.getNombre());
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
		String entregado = BaseQueries.buscarSiEntregado(tareaInfo.getNombre(), u.getNombre(), asig.getNombre());
		
	    GridBagLayout gbl_tarea1 = new GridBagLayout();
	    gbl_tarea1.columnWeights = new double[] { 1.0, 0.0, 0.0 };
	    gbl_tarea1.columnWidths = new int[] { 0, 450, 50 };

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

	    JLabel descrip = new JLabel(entregado == null ? "No entregado" : entregado);
	    nombreTarea.setHorizontalAlignment(SwingConstants.LEFT);
	    gbc.insets = new Insets(0, 0, 0, 20);
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 1;
	    tarea.add(descrip, gbc);

	    JButton botonEnviar = new JButton("Entregar");
	    botonEnviar.setBackground(new Color(Vista.COLOR4));
	    botonEnviar.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(Vista.COLOR1), 1),
	            BorderFactory.createEmptyBorder(3, 17, 3, 17)));
	    botonEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    botonEnviar.setFocusable(false);
	    botonEnviar.setEnabled(entregado == null);//Por ahora, para evitar problemas
	    botonEnviar.addActionListener(new ActionListener() { //Para poder hacerlo con varios botones
	        public void actionPerformed(ActionEvent e) {
	        	JFileChooser enviador = new JFileChooser();
				enviador.showOpenDialog(null);
				BaseQueries.entregarTarea(tareaInfo.getNombre(), u.getNombre(), asig.getNombre());
				botonEnviar.setEnabled(false);//Por ahora, para evitar problemas
				
				descrip.setText(LocalDate.now().toString());;
	        }
	    });

	    gbc.insets = new Insets(0, 0, 0, 0);
	    gbc.gridx = 2;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.fill = GridBagConstraints.NONE;
	    tarea.add(botonEnviar, gbc);

	    return tarea;
	}

	class PanelConLogoEst extends JPanel implements ActionListener {

		private Image logo;
		private JButton atras;
		private ImageIcon iconoSalir;

		public PanelConLogoEst(Usuario u) {
			
			this.setBackground(new Color(Vista.COLOR4));
			this.setPreferredSize(new Dimension(100, 160));
			this.setLayout(null);
			JLabel nombAsig = new JLabel("Tareas " + asig.getNombre());
			nombAsig.setBounds(0, 100, 120, 20);
			this.add(nombAsig);

			
			JLabel notaMedia = new JLabel("Nota media: ");
			notaMedia.setBounds(0, 80, 71, 13);
			this.add(notaMedia);

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
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == atras) {
				JPanel panelEstudiante = new PanelPrincipalEstudiante(u); // 3
				Programa.panelCardLayout.add(panelEstudiante, "Panel Estudiante");
				CardLayout cl = (CardLayout) (Programa.panelCardLayout.getLayout());
				cl.show(Programa.panelCardLayout, "Panel Estudiante");;
			}
		}
	}

}
