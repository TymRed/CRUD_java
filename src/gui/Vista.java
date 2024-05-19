package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

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
			String nombreUsusario = nombre.getText();
			String contrasenaUsuario = String.valueOf(contrasena.getPassword());
			boolean datosCorrectos = Prueba.hayUsuario(nombreUsusario, contrasenaUsuario);
//			System.out.println(nombreUsusario);
//			System.out.println(contrasenaUsuario);
			if (datosCorrectos) {
				System.out.println("Loged In");
			} else {
				System.out.println("Error, usuario no está registrado");
			}
		} else if (e.getSource() == signIn) {
			String nombreUsuario = nombre.getText();
			String contrasenaUsuario = String.valueOf(contrasena.getPassword());
			System.out.println(nombreUsuario);
			System.out.println(contrasenaUsuario);
			boolean nombreOcupado = Prueba.hayNombreUsuario(nombreUsuario);
			if (nombreOcupado) {
				System.out.println("Nombre ocupado");
				errorUsuario.setVisible(true); //no debe estar aqui, es solo un ejemplo
				return;
			}
			boolean nombreAdecuado = comprobarNombre(nombreUsuario);
			boolean contrasenaAdecuada = comprobarContrasena(contrasenaUsuario);
			
			if (nombreAdecuado && contrasenaAdecuada) {
				Prueba.signIn(nombreUsuario, contrasenaUsuario);
				System.out.println("Singed In");				
			}
			else {
				System.out.println("Error");
			}

		}
	}

	// Creo q hay q mover este metodo a otra clase, es solo prueba
	// Se pueden hacer comentarios personalizados para los errores
	private boolean comprobarNombre(String nombreUsuario) {
		if (nombreUsuario.isEmpty()) return false;
		
		boolean longitudAdecuada = nombreUsuario.length() >= 2 && nombreUsuario.length() <= 16;
		if (!longitudAdecuada) System.out.println("long con inad");
		boolean may1 = nombreUsuario.charAt(0) >= 'A' && nombreUsuario.charAt(0) <= 'Z';
		if (!may1) System.out.println("1 lt no may");
		boolean adecuado = nombreUsuario.substring(1).matches("[a-z].*");
		if (!adecuado) System.out.println("no todo min");
		
		if (longitudAdecuada && may1 && adecuado) return true;
		return false;
	}
	private boolean comprobarContrasena(String contrasena) {
		if (contrasena.isEmpty()) return false;
		
		boolean longitudAdecuada = contrasena.length() >= 2 && contrasena.length() <= 16;
		if (!longitudAdecuada) System.out.println("long con inad");
		boolean hayMin = contrasena.matches(".*[a-z].*");
		if (!hayMin) System.out.println("No hay min");
		boolean hayMay = contrasena.matches(".*[A-Z].*");
		if (!hayMay) System.out.println("No hay may");
		boolean hayNum = contrasena.matches(".*[1-9].*");
		if (!hayNum) System.out.println("No hay num");
		
		if (longitudAdecuada && hayMin && hayMay && hayNum) return true;
		return false;
	}

}
