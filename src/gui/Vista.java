package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Vista extends JFrame {

	// LOS COLORES VAN DE OSCURO A CLARO
	final static int COLOR1 = 0x022b3a;
	final static int COLOR2 = 0x1f7a8c;
	final static int COLOR3 = 0xbfdbf7;
	final static int COLOR4 = 0xe1e5f2;
	final static int COLOR5 = 0xffffff;

	public Vista() {

		this.setTitle("Ñoodle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		MiPanel logIn = new MiPanel();
		this.add(logIn);

		this.setVisible(true);

	}

}

class MiPanel extends JPanel {

	JButton signIn, logIn;
	JTextArea nombre, contrasena;

	public MiPanel() {

		this.setLayout(null);
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 3));

		nombre = new JTextArea();
		nombre.setBounds(130, 120, 200, 25);
		nombre.setForeground(new Color(Vista.COLOR1));
		nombre.setFont(new Font("Consolas", Font.PLAIN, 18));
		nombre.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1).brighter().brighter(), 2));
		contrasena = new JTextArea();
		contrasena.setBounds(130, 190, 200, 25);
		contrasena.setForeground(new Color(Vista.COLOR1));
		contrasena.setFont(new Font("Consolas", Font.PLAIN, 18));
		contrasena.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1).brighter().brighter(), 2));
		this.add(nombre);
		this.add(contrasena);

		signIn = new JButton("Sign in");
		signIn.setBounds(100, 300, 80, 30);
		signIn.setFocusable(false);
		signIn.setFont(new Font("Consolas", Font.PLAIN, 14));
		signIn.setMnemonic('s');
		signIn.setBackground(new Color(Vista.COLOR3));
		signIn.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2, true));
		logIn = new JButton("Log in");
		logIn.setBounds(300, 300, 80, 30);
		logIn.setFocusable(false);
		logIn.setFont(new Font("Consolas", Font.PLAIN, 14));
		logIn.setMnemonic('l');
		logIn.setBackground(new Color(Vista.COLOR3));
		logIn.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2, true));

		this.add(signIn);
		this.add(logIn);
		this.setOpaque(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setFont(new Font("Consolas", Font.BOLD, 25));
		g.drawString("Ñoodle", 200, 100);
	}

}
