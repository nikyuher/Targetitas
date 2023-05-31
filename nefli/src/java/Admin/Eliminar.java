/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nico_
 */
@WebServlet(name = "Eliminar", urlPatterns = {"/Eliminar"})
public class Eliminar extends HttpServlet {

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
            out.println("<title>Servlet Eliminar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Eliminar at " + request.getContextPath() + "</h1>");
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
          
          String tabla = request.getParameter("tablas2");
          int idusuario = Integer.parseInt(request.getParameter("idusuario2"));
          String nombre = request.getParameter("nombre2");
          String correo = request.getParameter("correo2");
          
          
          // Insertar los datos en la base de datos
          try {
        // Conectarse a la base de datos
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");
      
         // Preparar la sentencia SQL
        PreparedStatement sentencia = null ;
        
          if (tabla.equals("usuarios")) {
              
                  sentencia = conexion.prepareStatement("DELETE FROM usuarios WHERE idusuario = ? and correo = ?");
                  sentencia.setInt(1, idusuario);
                  sentencia.setString(2, correo);
                  
              } else if(tabla.equals("productos")){
                  
                  sentencia = conexion.prepareStatement("DELETE FROM productos WHERE idproducto = ? and  nombre = ?");
                  sentencia.setInt(1, idusuario);
                  sentencia.setString(2, nombre);
              }

         // Ejecutar la sentencia SQL
         int filasInsertadas = sentencia.executeUpdate();
        
         if (filasInsertadas > 0) {
          
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
           PrintWriter out = response.getWriter();
           out.println("<p>Eliminado correctamente</p>");
         } else {
           
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
          PrintWriter out = response.getWriter();
          out.println("<p>No se puedo eliminar</p>");
        }

      
          sentencia.close();
          conexion.close();
  
        } catch (SQLException e) {
     
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<h3>Error con Oracle</h3>"+e.getMessage());
        } catch (ClassNotFoundException e) {
  
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<h3>Problemas con el servidor</h3>");
        }
    
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
