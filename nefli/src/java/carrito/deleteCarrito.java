/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrito;

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
@WebServlet(name = "deleteCarrito", urlPatterns = {"/deleteCarrito"})
public class deleteCarrito extends HttpServlet {

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
            out.println("<title>Servlet deleteCarrito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteCarrito at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        // Obtener los datos 
          String idUsuario = request.getParameter("obtID");
              
          
          try {
        // Conectarse a la base de datos
        Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");
      
         // Preparar la sentencia SQL
        PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM CESTA WHERE idUsuario=?");

         sentencia.setString(1, idUsuario);
         
         // Ejecutar la sentencia SQL
         int filasInsertadas = sentencia.executeUpdate();
        
         if (filasInsertadas > 0) {
          
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
           PrintWriter out = response.getWriter();
           out.println("<div class=\"caja\">Productos Comprados</div>");
         } else {
           
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
          PrintWriter out = response.getWriter();
          out.println("Datos no se pudieron Eliminar");
        }

        
          sentencia.close();
          conexion.close();
  
        } catch (SQLException e) {
     
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("Error en el SQL"+e.getMessage());
        } catch (ClassNotFoundException e) {
    
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("Problemas con el servidor");
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
