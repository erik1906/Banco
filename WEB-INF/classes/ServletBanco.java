/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import modelBanco.ClienteAD;
import java.io.PrintWriter;
import java.io.IOException;
/**
 *
 * @author Raul
 */
public class ServletBanco extends HttpServlet {

    /**
     * @param args the command line arguments
     */


    String nocta, datos, tipo;
    //private ClienteAD cliente = new ClienteAD();
    private ClienteADjdbc cliente = new ClienteADjdbc();

    private void obtenerDatos(HttpServletRequest request){
        nocta = request.getParameter("nocta");
        tipo = request.getParameter("tipo");
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        obtenerDatos(request);

        if(request.getParameter("bConsultar") != null){
                datos = cliente.consultarClientes();
                //respuestaServer(request,response,datos);
                //USAR UN JSP
                response.sendRedirect("RespuestaServer.jsp?datos="+datos);
        }
        
        if(request.getParameter("bConsultarCuenta") != null){
                
                datos = cliente.consultarNoCuenta(nocta);
                response.sendRedirect("RespuestaServer.jsp?datos="+datos);
              //  respuestaServer(request,response,datos);
        }

        if(request.getParameter("bConsultarTipo") != null){
                
                datos = cliente.consultarTipo(tipo);
                response.sendRedirect("RespuestaServer.jsp?datos="+datos);
              //  respuestaServer(request,response,datos);
        }

        

    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }

    private void respuestaServer(HttpServletRequest request, HttpServletResponse response,String datos) throws IOException{
            PrintWriter salida = response.getWriter();
            response.setContentType("text/html");

            salida.println("<html>");
            salida.println("<head>");
            salida.println("<title> Banco En Linea </title>");
            salida.println("</head>");
            salida.println("<body>");
            salida.println("<h1> Datos de clientes</h1>");
            //salida.println("<h2> Datos</h2>"+datos);
            salida.println("<table border=1>");
            salida.println(datos);
            salida.println("</table>");


            salida.println("</body>");
            salida.println("</html>");
    }
    
}
