
import java.util.StringTokenizer;

public class ClienteDP
{
	// Atributos
	private String nocta, nombre, tipo;
	private int saldo;
	
	// Constructores
	public ClienteDP()
	{
		this.nocta  = "";
		this.nombre = "";
		this.tipo   = "";
		this.saldo  = 0;
	}
	
	public ClienteDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		this.nocta  = st.nextToken();
		this.nombre = st.nextToken();
		this.tipo   = st.nextToken();
		this.saldo  = Integer.parseInt(st.nextToken());
	}
	
	// Metodos: Accesors (geter's)
	public String getNocta()
	{
		return this.nocta;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getTipo()
	{
		return this.tipo;
	}
	
	public int getSaldo()
	{
		return this.saldo;
	}
	
	// Metodos: Mutators (seter's)
	public void setNocta(String ncta)
	{
		this.nocta = ncta;
	}
	
	public void setNombre(String name)
	{
		this.nombre = name;
	}
	
	public void setTipo(String tcta)
	{
		this.tipo = tcta;
	}
	
	public void setSaldo(int cantidad)
	{
		this.saldo = cantidad;
	}
	
	public String toString()
	{
		return this.nocta+"_"+this.nombre+"_"+this.tipo+"_"+this.saldo;
	}
	
	public String toStringHtml()
	{
		return "<tr><td>"+this.nocta+"</td><td>"+this.nombre+"</td><td>"+this.tipo+"</td><td>"+this.saldo+"</td></tr>";
	}

	public String toStringSql()
	{
		return "'"+this.nocta+"','"+this.nombre+"','"+this.tipo+"',"+this.saldo;
	}
}