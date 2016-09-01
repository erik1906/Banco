import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

//import modelBanco.ClienteAD;
//import modelBanco.ClienteADjdbc;

public class ClienteGUI extends JFrame implements ActionListener
{
	private JTextField tfNocuenta, tfNombre, tfTipo, tfSaldo;
	private JButton bCapturar, bConsultar, bConsultarTipo, bConsultarNocta, bSalir;
	private JTextArea taDatos;
	private JPanel panel1, panel2;
	
	private ClienteAD cliente = new ClienteAD();
	//private ClienteADjdbc cliente = new ClienteADjdbc();
	
	public ClienteGUI()
	{
		super("Banco OOP BCH");
		
		// 1. Crear objetos
		tfNocuenta = new JTextField();
		tfNombre   = new JTextField();
		tfTipo     = new JTextField();
		tfSaldo    = new JTextField();
		
		bCapturar  = new JButton("Capturar Cliente");
		bConsultar = new JButton("Consultar Clientes");
		bConsultarTipo = new JButton("Consultar Tipo de Cuenta");
		bConsultarNocta = new JButton("Consultar No. Cuenta");
		bSalir     = new JButton("Salir"); 
		
		// Adicionar deteccion de eventos a los botones
		bCapturar.addActionListener(this);
		bConsultar.addActionListener(this);
		bConsultarTipo.addActionListener(this);
		bConsultarNocta.addActionListener(this);
		bSalir.addActionListener(this);
		
		taDatos    = new JTextArea(10,30);
		panel1     = new JPanel();
		panel2     = new JPanel();
		
		// 2. Adicionar los objetos al panel1
		panel1.setLayout(new GridLayout(7,2));
		panel2.setLayout(new FlowLayout());
		
		panel1.add(new JLabel("NO. DE CUENTA"));
		panel1.add(tfNocuenta);
		
		panel1.add(new JLabel("NOMBRE DEL CLIENTE"));
		panel1.add(tfNombre);
		
		panel1.add(new JLabel("TIPO DE CUENTA"));
		panel1.add(tfTipo);
		
		panel1.add(new JLabel("SALDO"));
		panel1.add(tfSaldo);
		
		panel1.add(bCapturar);
		panel1.add(bConsultar);
		panel1.add(bConsultarTipo);
		panel1.add(bConsultarNocta);
		panel1.add(bSalir);
		
		panel2.add(panel1);
		panel2.add(new JScrollPane(taDatos));
		
		// 3. Adicionar panel2 al JFrame
		add(panel2);
		setSize(400,400);
		setVisible(true);
	}
	
	public void desplegar(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		tfNocuenta.setText(st.nextToken());
		tfNombre.setText(st.nextToken());
		tfTipo.setText(st.nextToken());
		tfSaldo.setText(st.nextToken());
	}
	
	private String obtenerDatos()
	{
		String datos="";
		
		String cuenta = tfNocuenta.getText();
		String nombre = tfNombre.getText();
		String tipo   = tfTipo.getText();
		String saldo  = tfSaldo.getText();
		
		if(cuenta.equals("") || nombre.equals("") || tipo.isEmpty() || saldo.isEmpty())
			datos = "VACIO";
		else
		{
			try
			{
				int s = Integer.parseInt(saldo);
				datos = cuenta+"_"+nombre+"_"+tipo+"_"+saldo;
			}
			catch(NumberFormatException nfe)
			{
				datos = "NO_NUMERICO";
			}
		}
		
		return datos;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == bCapturar)
		{
			String datos="";
			String resultado="";
			
			// 1. Obtner dato de los JTextFields
			datos = obtenerDatos();
			
			// 2. Checar si algun campo es vacio o saldo no numerico
			if(datos.equals("VACIO"))
				taDatos.setText("Algun campo esta vacío...");
			else
			{
				if(datos.equals("NO_NUMERICO"))
					taDatos.setText("Saldo debe ser numerico...");
				else
				{
					// 3. Capturar los datos del cliente
					resultado = cliente.capturar(datos);
				
					// 4. Desplegar resultado de la transaccion
					taDatos.setText(resultado);
				}
			}
		}
		
		if(e.getSource() == bConsultar)
		{
			String datos = cliente.consultarClientes();
			taDatos.setText(datos);
		}

		if(e.getSource() == bConsultarTipo)
		{
			String tipo = tfTipo.getText();
			String datos = cliente.consultarTipo(tipo);
			taDatos.setText(datos);
		}
		
		if(e.getSource() == bConsultarNocta)
		{
			String cta = tfNocuenta.getText();
			String datos = cliente.consultarNoCuenta(cta);
			
			if(datos.equals("ERROR"))
				taDatos.setText("Error al abrir el archivo de datos...");
			else
				if(datos.equals("NO_LOCALIZADO"))
					taDatos.setText("No se localizo el No. de Cuenta..."+cta);
				else
				{
					taDatos.setText(datos);
					desplegar(datos);
				}
		}
		
		if(e.getSource() == bSalir)
			System.exit(0);
	}
	
	public static void main(String args[])
	{
		new ClienteGUI();
	}
}