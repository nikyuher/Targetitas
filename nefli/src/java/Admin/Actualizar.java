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
@WebServlet(name = "Actualizar", urlPatterns = {"/Actualizar"})
public class Actualizar extends HttpServlet {

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
            out.println("<title>Servlet Actualizar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Actualizar at " + request.getContextPath() + "</h1>");
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
        
        // Obtener los datos del formulario
     String tabla = request.getParameter("tablas3");   
     String idusuario = request.getParameter("idusuario3");
     String nombre = request.getParameter("nombre3");
     String correo = request.getParameter("correo3");
     String contra = request.getParameter("contra3");
     String precio = request.getParameter("precio3");
     
    try {
 // Conectarse a la base de datos
     Class.forName("oracle.jdbc.driver.OracleDriver");
     Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");

 // Sentencia SQL
     PreparedStatement sentencia=null;
     
        if (tabla.equals("usuarios")) {
            sentencia = conexion.prepareStatement("UPDATE usuarios SET nombre = ?, correo = ?, contra = ? WHERE idusuario = ?" );
            sentencia.setString(1, nombre);
            sentencia.setString(2, correo);
            sentencia.setString(3, contra);
            sentencia.setString(4, idusuario);
            
        } else if(tabla.equals("productos")){
            sentencia = conexion.prepareStatement("UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, imagen = ? WHERE idproducto = ?" );
            sentencia.setString(1, nombre);
            sentencia.setString(2, correo);
            sentencia.setString(3, precio);
            sentencia.setString(4, contra);
            sentencia.setString(5, idusuario);
            
        }
     
  // Ejecutar la sentencia SQL
    int filasActualizadas = sentencia.executeUpdate();
  
    if (filasActualizadas > 0) {
  
       response.setContentType("text/plain");
       response.setCharacterEncoding("UTF-8");
       PrintWriter out = response.getWriter();
       out.println("<p>Se actualizo los datos Correctamente</p>");
    } else {
  // Responder con un mensaje de error
       response.setContentType("text/plain");
       response.setCharacterEncoding("UTF-8");
       PrintWriter out = response.getWriter();
       out.println("<h3>El ID es incorrecto</h3>");
    }

  // Cerrar la conexión a la base de datos
    sentencia.close();
    conexion.close();
  
    } catch (ClassNotFoundException | SQLException ex) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h3>Error al Actualizar</h3>"+ex.getMessage());
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
