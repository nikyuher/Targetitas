package LoginRegister;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nico_
 */
@WebServlet(name = "RegistrarseServlet", urlPatterns = {"/registrarse"})
public class RegistrarseServerlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrarseServerlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarseServerlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
          
        PrintWriter out = response.getWriter();
		
		
		String correo2 = request.getParameter("correoL");
		String contra2 = request.getParameter("contrasenaL");
		
		try {
			// conectar BD
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");
			
			// MANDAR SENTENCIA SQL
			PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM usuarios WHERE correo = ? AND contra = ?");
                        
			stmt.setString(1, correo2);
			stmt.setString(2, contra2);
                        
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
                            String nombreUsuario = rs.getString("nombre");
                            int idUsuario = rs.getInt("idUsuario");
                            
			    response.setContentType("text/plain");
                            response.setCharacterEncoding("UTF-8");
                            
                            if (correo2.equals("admin@gmail.com")) {
                                out.println(nombreUsuario);
                            out.println("<p id=\"obtID\" class=\"idUsuarios\">"+idUsuario+"</p>");
                            out.println("<p class=\"confirmacion\">admin</p>");
                            
                            HttpSession session = request.getSession();
                            session.setAttribute("nombreUsuario", nombreUsuario);
                            } else {
                                out.println(nombreUsuario);
                            out.println("<p id=\"obtID\" class=\"idUsuarios\">"+idUsuario+"</p>");
                            out.println("<p class=\"confirmacion\">usuario</p>");
                            
                            HttpSession session = request.getSession();
                            session.setAttribute("nombreUsuario", nombreUsuario);
                            }
                            
			} else {
                         response.setContentType("text/plain");
                            response.setCharacterEncoding("UTF-8");
                            out.println("<h3>Email o Contraseña Incorrecto</h3>");
			}
			
			rs.close();
			stmt.close();
			conexion.close();
			
		} catch (SQLException e) {
      
                   response.setContentType("text/plain");
                   response.setCharacterEncoding("UTF-8");
                   out.println("<h3>Email o Contraseña Incorrecto BD</h3>");
                } catch (ClassNotFoundException e) {
     
                   response.setContentType("text/plain");
                   response.setCharacterEncoding("UTF-8");
                   out.println("<h3>Problemas con el servidor</h3>");
                }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        // Obtener los datos del formulario
          String nombre = request.getParameter("nombre");
          String correo = request.getParameter("correo");
          String contrasena = request.getParameter("contra");
              
          
          try {
        // Conectarse a la base de datos
        Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");
      
         // Preparar la sentencia SQL
        PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO USUARIOS (IDUSUARIO, NOMBRE, CORREO, CONTRA) VALUES (IDPLUS.NEXTVAL, ?, ?, ?)");

         sentencia.setString(1, nombre);
         sentencia.setString(2, correo);
         sentencia.setString(3, contrasena);
         
         // Ejecutar la sentencia SQL
         int filasInsertadas = sentencia.executeUpdate();
        
         if (filasInsertadas > 0) {
          
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
           PrintWriter out = response.getWriter();
           out.println("<p>Gracias por Registrarte</p>");
         } else {
           
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
          PrintWriter out = response.getWriter();
          out.println("<p>El correo ya esta en uso</p>");
        }

        
          sentencia.close();
          conexion.close();
  
        } catch (SQLException e) {
     
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<h3>El correo ya esta en uso</h3>");
        } catch (ClassNotFoundException e) {
    
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<h3>Problemas con el servidor</h3>");
        }
    }
    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request,HttpServletResponse response)throws ServletException,java.io.IOException{
        
    // Obtener los datos del formulario
     String nombre = request.getParameter("nombre");
     String correo = request.getParameter("correo");
     String contra = request.getParameter("contra");
     
     if (nombre == null || correo == null || contra == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<h3>Error al eliminar</h3><p>Los parámetros correo2 y contrasena2 son requeridos.</p>");
            return;
        }
    try {
 // Conectarse a la base de datos
     Class.forName("oracle.jdbc.driver.OracleDriver");
     Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");

 // Preparar la sentencia SQL
     PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM usuarios WHERE nombre = ? and  correo = ? and contra= ?" );

     sentencia.setString(1, nombre);
     sentencia.setString(2, correo);
     sentencia.setString(3, contra);
  
  // Ejecutar la sentencia SQL
    int filasEliminadas = sentencia.executeUpdate();
  
    if (filasEliminadas > 0) {
  // Responder con un mensaje de éxito
       response.setContentType("text/plain");
       response.setCharacterEncoding("UTF-8");
       PrintWriter out = response.getWriter();
       out.println("<p>Se elimino correctamente</p>");
    } else {
  // Responder con un mensaje de error
       response.setContentType("text/plain");
       response.setCharacterEncoding("UTF-8");
       PrintWriter out = response.getWriter();
       out.println("<h3>No se pudo eliminar</h3>");
    }

  // Cerrar la conexión a la base de datos
    sentencia.close();
    conexion.close();
  
    } catch (ClassNotFoundException | SQLException ex) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h3>Error al registrarse</h3>");
    }
    }
    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request,HttpServletResponse response)throws ServletException,java.io.IOException{
        
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
