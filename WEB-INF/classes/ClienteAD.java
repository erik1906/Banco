
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

public class ClienteAD
{
	private PrintWriter    archivoSalida;
	private BufferedReader archivoEntrada;
	private String pathFile = "C:\\Apache-tomcat-8.5.4\\webapps\\banco\\WEB-INF\\classes\\Clientes.txt";
	
	
	public String capturar(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		String cta = st.nextToken();
		
		String respuesta = consultarNoCuenta(cta);
		
		if(respuesta.equals("NO_LOCALIZADO"))
			respuesta = captura(datos);
		else
			respuesta = "Ya existe el No. de Cuenta "+cta;
		
		return respuesta;
	}
	
	public String captura(String datos)
	{
		String respuesta="";
		
		try
		{
			// 1. Abrir archivo de clientes
			archivoSalida = new PrintWriter(new FileWriter("Clientes.txt",true));
			
			// 2. capturar o guardar los datos en el archivo
			archivoSalida.println(datos);
			
			// 3. Cerrar el archivo
			archivoSalida.close();
			
			respuesta = "Captura correcta...";
		}
		catch(IOException ioe)
		{
			respuesta = "Error en Captura ...\n"+ioe;
			System.out.println("Error: "+ioe);
		}
		
		return respuesta;
	}
	
	public String consultarClientes()
	{
		String datos="";
		
		try
		{
			// 1. Abrir archivo
			archivoEntrada = new BufferedReader(new FileReader(pathFile));
			
			// 2. Procesar datos
			while(archivoEntrada.ready())
				datos = datos + archivoEntrada.readLine() + "\n";
			
			// 3. Cerrar archivo
			archivoEntrada.close();
		}
		catch(FileNotFoundException fnfe)
		{
			datos = "Error en Consular Clientes ...\n"+fnfe;
			System.out.println("Error: "+fnfe);
		}
		
		catch(IOException ioe)
		{
			datos = "Error al cerrar archivo ...\n"+ioe;
			System.out.println("Error: "+ioe);
		}
		
		return datos;
	}
	
	public String consultarTipo(String tipo)
	{
		String datos="";
		String str="";
		StringTokenizer st;
		String tipoCuenta;
		boolean encontrado=false;
		
		try
		{
			// 1. Abrir archivo
			archivoEntrada = new BufferedReader(new FileReader(pathFile));
			
			// 2. Procesar datos y localizar clientes con el tipo de cuenta buscado
			while(archivoEntrada.ready())
			{
				str = archivoEntrada.readLine();
				st = new StringTokenizer(str,"_");
				
				tipoCuenta = st.nextToken();
				tipoCuenta = st.nextToken();
				tipoCuenta = st.nextToken();
				
				if(tipoCuenta.equals(tipo))
				{
					datos = datos + str + "\n";
					encontrado = true;
				}
			}
			
			// 3. Cerrar archivo
			archivoEntrada.close();
			
			if(!encontrado)
				datos = "No se localizo el tipo de cuenta buscado... "+tipo;
		}
		catch(FileNotFoundException fnfe)
		{
			datos = "Error en Consular Clientes ...\n"+fnfe;
			System.out.println("Error: "+fnfe);
		}
		
		catch(IOException ioe)
		{
			datos = "Error al cerrar archivo ...\n"+ioe;
			System.out.println("Error: "+ioe);
		}
		
		return datos;
	}
	
	public String consultarNoCuenta(String cta)
	{
		String datos="";
		String str="";
		StringTokenizer st;
		String cuenta;
		boolean encontrado=false;
		
		try
		{
			// 1. Abrir archivo
			archivoEntrada = new BufferedReader(new FileReader(pathFile));
			
			// 2. Procesar datos y localizar al cliente con no. de cuenta buscado
			while(archivoEntrada.ready() && !encontrado)
			{
				str = archivoEntrada.readLine();
				st = new StringTokenizer(str,"_");
				
				cuenta = st.nextToken();
				
				if(cuenta.equals(cta))
				{
					datos = str;
					encontrado = true;
				}
			}
			
			// 3. Cerrar archivo
			archivoEntrada.close();
			
			if(!encontrado)
				datos = "NO_LOCALIZADO";
		}
		catch(FileNotFoundException fnfe)
		{
			datos = "ERROR";
			System.out.println("Error: "+fnfe);
		}
		
		catch(IOException ioe)
		{
			datos = "ERROR";
			System.out.println("Error: "+ioe);
		}
		
		return datos;		
	}
}