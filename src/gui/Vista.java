package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bd.Prueba;

public class Vista extends JFrame {

	// LOS COLORES VAN DE OSCURO A CLARO
	final static int COLOR1 = 0x022b3a;
	final static int COLOR2 = 0x1f7a8c;
	final static int COLOR3 = 0xbfdbf7;
	final static int COLOR4 = 0xe1e5f2;
	final static int COLOR5 = 0xffffff;

	public Vista() {

		ImageIcon icono = new ImageIcon("miniLogoNoodle.png");

		this.setIconImage(icono.getImage());
		this.setTitle("Ñoodle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(350, 350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		MiPanel logIn = new MiPanel();
		this.add(logIn);

		this.setVisible(true);

	}

}

class MiPanel extends JPanel implements ActionListener {

	JButton signIn, logIn;
	JTextField nombre;
	JPasswordField contrasena;
	Image logo, logoU, logoC;
	JLabel errorUsuario, errorContrasena;

	public MiPanel() {

		this.setLayout(null);
		this.setBackground(new Color(Vista.COLOR4));
		this.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 3));

		nombre = crearCampoTexto("Usuario");
		nombre.setBounds(55, 130, 225, 25);
		contrasena = crearCampoContrasena();
		contrasena.setBounds(55, 180, 225, 25);

		this.add(nombre);
		this.add(contrasena);

		signIn = crearBoton("Sign in", 's');
		signIn.setBounds(55, 230, 80, 30);
		logIn = crearBoton("Log in", 'l');
		logIn.setBounds(200, 230, 80, 30);
		this.add(signIn);
		this.add(logIn);

		errorUsuario = crearTextoError();
		errorUsuario.setBounds(55, 112, 250, 100);
		errorUsuario.setVisible(false);

		this.add(errorUsuario);

		this.setOpaque(true);

	}

	private JTextField crearCampoTexto(String texto) {
		JTextField t = new JTextField(texto);
		t.setForeground(Color.GRAY);
		t.setFont(new Font("Consolas", Font.PLAIN, 18));
		t.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1).brighter().brighter(), 2));
		return t;
	}

	private JPasswordField crearCampoContrasena() {
		JPasswordField contrasena = new JPasswordField();
		contrasena.setForeground(Color.GRAY);
		contrasena.setFont(new Font("Consolas", Font.PLAIN, 18));
		contrasena.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1).brighter().brighter(), 2));
		return contrasena;
	}

	private JButton crearBoton(String texto, char mnemonic) {
		JButton b = new JButton(texto);
		b.setFocusable(false);
		b.setForeground((new Color(Vista.COLOR1)));
		b.setFont(new Font("Consolas", Font.PLAIN, 14));
		b.setMnemonic('s');
		b.setBackground(new Color(Vista.COLOR3));
		b.setBorder(BorderFactory.createLineBorder(new Color(Vista.COLOR1), 2, true));
		b.addActionListener(this);
		return b;
	}

	private JLabel crearTextoError() {
		JLabel t = new JLabel();
		t.setText("Debe tener entre 4 y 16 caracteres");
		t.setForeground(Color.RED);
		t.setFont(new Font("Consolas", Font.ITALIC, 13));
		return t;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		this.setFont(new Font("Consolas", Font.BOLD, 25));
		logo = new ImageIcon("logoNoodle.png").getImage();
		logoU = new ImageIcon("usuario.png").getImage();
		logoC = new ImageIcon("contrasena.png").getImage();
		g.drawImage(logo, 87, 45, 150, 50, null);
		g.drawImage(logoU, 30, 133, 18, 18, null);
		g.drawImage(logoC, 30, 183, 18, 18, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logIn) {
			boolean comprobado = Prueba.hayUsuario(nombre.getText(), String.valueOf(contrasena.getPassword()));
			String nombreUsusario = nombre.getText();
			String contrasenaUsuario = String.valueOf(contrasena.getPassword());
			System.out.println(String.valueOf(contrasena.getPassword()));
			if (!comprobado) {
				errorUsuario.setVisible(true);
			}
		} else if (e.getSource() == signIn) {
			System.out.println("Loged In");
		}
	}

}
