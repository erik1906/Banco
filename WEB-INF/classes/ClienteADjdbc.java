
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteADjdbc
{	
	private Connection conexion;
	private Statement statement;
	
	ClienteDP clientedp;
	
	
	public ClienteADjdbc()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/bancoremoto?user=root");
			System.out.println("Conexion exitosa a la BD en MySQL...");
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Error: "+cnfe);
		}
		catch(InstantiationException ie)
		{
			System.out.println("Error: "+ie);
		}
		catch(IllegalAccessException iae)
		{
			System.out.println("Error: "+iae);
		}
		catch(SQLException sqle)
		{
			System.out.println("Error: "+sqle);
		}
	}
	
	
	public String capturar(String datos)
	{
		String respuesta="";
		String insert;
		
		clientedp = new ClienteDP(datos);
		
		insert = "INSERT INTO Cliente VALUES("+clientedp.toStringSql()+")";
		
		try
		{
			// 1. Abrir BD para manipular alguna tabla definida, tabla Cliente
			//archivoSalida = new PrintWriter(new FileWriter("Clientes.txt",true));
			statement = conexion.createStatement();
			
			// 2. capturar o guardar los datos en el archivo
			//archivoSalida.println(datos);
			statement.executeUpdate(insert);
			
			// 3. Cerrar el archivo
			//archivoSalida.close();
			statement.close();
			
			respuesta = "Captura correcta...";
			System.out.println(conexion.nativeSQL(insert));
		}
		catch(SQLException sqle)
		{
			respuesta = "Error en Captura ...\n"+sqle;
			System.out.println("Error: "+sqle);
		}
		
		return respuesta;
	}
	
	public String consultarClientes()
	{
		String datos="";
		String query;
		ResultSet tr;
		
		query = "SELECT * FROM Cliente";
		
		try
		{
			// 1. Abrir archivo
			//archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
			statement = conexion.createStatement();
			
			// 2. Procesar datos
			//while(archivoEntrada.ready())
			//	datos = datos + archivoEntrada.readLine() + "\n";
			tr = statement.executeQuery(query);
			clientedp = new ClienteDP();
			
			while(tr.next())
			{
				clientedp.setNocta(tr.getString("nocta"));
				clientedp.setNombre(tr.getString("nombre"));
				clientedp.setTipo(tr.getString(3));
				clientedp.setSaldo(tr.getInt(4));
				
				//datos = datos + clientedp.toString()+ "\n";
				datos = datos + clientedp.toStringHtml()+ "\n";
			}
			
			// 3. Cerrar archivo
			//archivoEntrada.close();
			statement.close();
			
			System.out.println(conexion.nativeSQL(query));
			System.out.println(datos);
		}
		catch(SQLException sqle)
		{
			datos = "ERROR";
			System.out.println("Error: "+sqle);
		}
		
		return datos;
	}
	
	public String consultarTipo(String tipoCta)
	{
		String datos="";
		String query;
		ResultSet tr;
		boolean encontrado=false;
		
		query = "SELECT * FROM Cliente WHERE tipo='"+tipoCta+"'";
		
		try
		{
			// 1. Abrir archivo
			//archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
			statement = conexion.createStatement();
			
			// 2. Procesar datos
			//while(archivoEntrada.ready())
			//	datos = datos + archivoEntrada.readLine() + "\n";
			tr = statement.executeQuery(query);
			clientedp = new ClienteDP();
			while(tr.next())
			{
				clientedp.setNocta(tr.getString("nocta"));
				clientedp.setNombre(tr.getString("nombre"));
				clientedp.setTipo(tr.getString(3));
				clientedp.setSaldo(tr.getInt(4));
				
				//datos = datos + clientedp.toString()+ "\n";
				datos = datos + clientedp.toStringHtml()+ "\n";
				encontrado = true;
			}
			
			// 3. Cerrar archivo
			//archivoEntrada.close();
			statement.close();
			
			System.out.println(conexion.nativeSQL(query));
		}
		catch(SQLException sqle)
		{
			datos = "Error en Consular Tipo de Cuenta ...\n"+sqle;
			System.out.println("Error: "+sqle);
		}
		
		if(!encontrado)
			datos = "No selocalizo el Tipo de Cuenta: "+tipoCta;
		
		return datos;
	}
	
	public String consultarNoCuenta(String cta)
	{
		String datos="";
		String query;
		ResultSet tr;
		
		query = "SELECT * FROM Cliente WHERE nocta='"+cta+"'";
		
		try
		{
			// 1. Abrir archivo
			//archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
			statement = conexion.createStatement();
			
			// 2. Procesar datos
			//while(archivoEntrada.ready())
			//	datos = datos + archivoEntrada.readLine() + "\n";
			tr = statement.executeQuery(query);
			clientedp = new ClienteDP();
			if(tr.next())
			{
				clientedp.setNocta(tr.getString("nocta"));
				clientedp.setNombre(tr.getString("nombre"));
				clientedp.setTipo(tr.getString(3));
				clientedp.setSaldo(tr.getInt(4));
				
				//datos = datos + clientedp.toString()+ "\n

				datos = datos + clientedp.toStringHtml()+ "\n";			}
			else
				datos = "NO_LOCALIZADO";
			
			// 3. Cerrar archivo
			//archivoEntrada.close();
			statement.close();
			
			System.out.println(conexion.nativeSQL(query));
		}
		catch(SQLException sqle)
		{
			datos = "ERROR";
			System.out.println("Error: "+sqle);
		}
				
		return datos;		
	}
}