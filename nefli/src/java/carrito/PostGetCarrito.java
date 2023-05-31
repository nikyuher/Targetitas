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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nico_
 */
@WebServlet(name = "carrito", urlPatterns = {"/carrito"})
public class PostGetCarrito extends HttpServlet {

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
            out.println("<title>Servlet carrito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet carrito at " + request.getContextPath() + "</h1>");
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
          String idUsuario = request.getParameter("obtID");
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");
                        
            PreparedStatement stmt = conexion.prepareStatement("select * from cesta where idUsuario=?");
	    stmt.setString(1, idUsuario);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            String idProducto=rs.getString("idProducto");
            
            PreparedStatement stmt2 = conexion.prepareStatement("select * from productos where idProducto=?");
            stmt2.setString(1, idProducto);
            
            ResultSet rs2 = stmt2.executeQuery();
            
            rs2.next();
            String imagen=rs2.getString("imagen");
            String nombre=rs2.getString("nombre");
            String descrip=rs2.getString("descripcion");
            String precio=rs2.getString("precio");
            
            
                
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
                
            out.println("<div class=\"caja\">"+
                        "<img src=\"imagenes/cafes/"+imagen+"\">"+
                        "<p>"+nombre+"</p>"+
                        "<p>"+descrip+"</p>"+
                        "<p>"+precio+"$</p>"+
                        "</div>");
            }
            
            rs.close();
	    stmt.close();
	    conexion.close();
            
        }catch (SQLException e) {
      
                   response.setContentType("text/plain");
                   response.setCharacterEncoding("UTF-8");
                   out.println("<h3>No se pudede mostrar los datos </h3>"+e.getMessage());
                } catch (ClassNotFoundException e) {
     
                   response.setContentType("text/plain");
                   response.setCharacterEncoding("UTF-8");
                   out.println("<h3>Problemas con el servidor</h3>"+e.getMessage());
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
        // Obtener los datos 
          String idUsuario = request.getParameter("obtID");
          String idProducto = request.getParameter("idProducto");
          String precio = request.getParameter("precioP");
              
          
          try {
        // Conectarse a la base de datos
        Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");
      
         // Preparar la sentencia SQL
        PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO CESTA (IDCESTA,IDUSUARIO,IDPRODUCTO,CANTIDAD) VALUES (IDPLUS.NEXTVAL, ?, ?, ?)");

         sentencia.setString(1, idUsuario);
         sentencia.setString(2, idProducto);
         sentencia.setString(3, precio);
         
         // Ejecutar la sentencia SQL
         int filasInsertadas = sentencia.executeUpdate();
        
         if (filasInsertadas > 0) {
          
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
           PrintWriter out = response.getWriter();
           out.println("Datos ingresados");
         } else {
           
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
          PrintWriter out = response.getWriter();
          out.println("Datos no se pudieron ingresar");
        }

        
          sentencia.close();
          conexion.close();
  
        } catch (SQLException e) {
     
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("El correo ya esta en uso"+e.getMessage());
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
