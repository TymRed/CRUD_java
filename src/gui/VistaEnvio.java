package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaEnvio extends JFrame {

	public VistaEnvio(ArrayList<String> errores) {

		ImageIcon icono = new ImageIcon("images//miniLogoNoodle.png");

		this.setTitle(errores.isEmpty() ? "Éxito" : "Errores");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(460, 150);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		PanelEnvio panelEnvio = new PanelEnvio(errores);
		this.add(panelEnvio);

		this.setVisible(true);

	}

	public class PanelEnvio extends JPanel implements ActionListener {

		private static boolean exito;
		JButton botonEnviar;
		
		public PanelEnvio(ArrayList<String> errores) {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setBackground(new Color(Vista.COLOR4));
			this.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 3));

			botonEnviar = new JButton("Enviar");
			botonEnviar.setSize(100, 100);
			botonEnviar.addActionListener(this);
			this.add(botonEnviar);
			
			this.add(Box.createVerticalGlue());

			JLabel textoLabel;
			if (errores.isEmpty()) {
				exito = true;
				textoLabel = crearLabel("Usuario creado con éxito.", new Color(Vista.COLOR1));
				this.add(textoLabel);
			} else {
				exito = false;
				for (String error : errores) {
					textoLabel = crearLabel(error, Color.RED);
					this.add(textoLabel);
				}
			}

			this.add(Box.createVerticalGlue());

			errores.clear();

			this.setOpaque(true);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image iconoExito = new ImageIcon("images//exito.png").getImage();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==botonEnviar) {
				JFileChooser enviador = new JFileChooser();
				enviador.showOpenDialog(null);
			}
		}

	}

	private JLabel crearLabel(String textoLabel, Color color) {
		JLabel label = new JLabel(textoLabel);
		label.setForeground(color);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setMaximumSize(new Dimension(450, Integer.MAX_VALUE));
		label.setFont(textoLabel.length() <= 35 ? new Font("Consolas", Font.PLAIN, 16)
				: new Font("Consolas", Font.PLAIN, 14));
		return label;
	}

}
