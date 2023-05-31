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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nico_
 */
@WebServlet(name = "PostGet", urlPatterns = {"/PostGet"})
public class PostGet extends HttpServlet {

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
            out.println("<title>Servlet PostGet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostGet at " + request.getContextPath() + "</h1>");
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
		
                String tabla = request.getParameter("tablas4");
                String nombre = request.getParameter("nombre4");
                String correo = request.getParameter("correo4");
                
                boolean cambio=false;
		try {
			// conectar DB
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");
			
                        PreparedStatement stmt=null;
                        
                        if (tabla.equals("usuarios")) {
                            stmt = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND correo = ?");
                            stmt.setString(1, nombre);
			    stmt.setString(2, correo);
                            cambio=true;
                       } else if(tabla.equals("productos")){
                            stmt = conexion.prepareStatement("SELECT * FROM productos WHERE nombre = ?");
                            stmt.setString(1, nombre);
                       }
                        
			//SENTENCIA SQL
                        
                        
			ResultSet rs = stmt.executeQuery();
			
                        
                        
                        if (cambio) {
                            if (rs.next()) {
                            
			    response.setContentType("text/plain");
                            response.setCharacterEncoding("UTF-8");
                            
                            String nombre2=rs.getString("nombre");
                            String correo2=rs.getString("correo");
                            String contra2=rs.getString("contra");
                            String id=rs.getString("idUsuario");
                            
                            out.println("<p>ID:"+id+ "</p>"+"<p>Nombre: "+nombre2+"</p>"+"<p>Correo: "+correo2+"</p>"+"<p>Contraseña: "+contra2+"</p>");
			    } else {
                              response.setContentType("text/plain");
                              response.setCharacterEncoding("UTF-8");
                              out.println("<h3>El usuario no existe</h3>");
			    }
                        } else {
                            if (rs.next()) {
                                    do  {
                                        response.setContentType("text/plain");
                                        response.setCharacterEncoding("UTF-8");
                            
                                        String id=rs.getString("idProducto");
                                        String nombre2=rs.getString("nombre");
                                        String descri2=rs.getString("descripcion");
                                        int precio2=rs.getInt("precio");
                                        String imagen=rs.getString("imagen");
                            
                                        out.println("<p>ID:"+id+ "</p>"+"<p>Nombre: "+nombre2+"</p>"+"<p>Descripcion: "+descri2+"</p>"+"<p>Precio: "+precio2+"</p>"+"<p>Imagen: "+imagen+"</p>");
                                    }while (rs.next());
                            } else {
                                        response.setContentType("text/plain");
                              response.setCharacterEncoding("UTF-8");
                              out.println("<h3>El usuario no existe</h3>");
                                    }
                            
                            
                        }
			rs.close();
			stmt.close();
			conexion.close();
			
		} catch (SQLException e) {
      
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
        
        // Obtener los datos del formulario
          String tabla = request.getParameter("tablas1");
          String nombre = request.getParameter("nombre1");
          String correo = request.getParameter("correo1");
          String contrasena = request.getParameter("contra1");
          String precio = request.getParameter("precio1");
          
          
          try {
        // Conectarse a la base de datos
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "coffeco", "coffeco");
      
         // Preparar la sentencia SQL
         PreparedStatement sentencia = null ;
         
              if (tabla.equals("usuarios")) {
                  sentencia = conexion.prepareStatement("INSERT INTO usuarios (IDUSUARIO, NOMBRE, CORREO, CONTRA) VALUES (IDPLUS.NEXTVAL, ?, ?, ?)");
                  sentencia.setString(1, nombre);
                  sentencia.setString(2, correo);
                  sentencia.setString(3, contrasena);
              } else if(tabla.equals("productos")){
                  sentencia = conexion.prepareStatement("INSERT INTO productos (idProducto, NOMBRE, descripcion, precio,imagen) VALUES (IDPLUS.NEXTVAL, ?, ?, ?, ?)");
                  sentencia.setString(1, nombre);
                  sentencia.setString(2, correo);
                  sentencia.setString(3, precio);
                  sentencia.setString(4, contrasena);
              }
         
         // Ejecutar la sentencia SQL
         int filasInsertadas = sentencia.executeUpdate();
        
         if (filasInsertadas > 0) {
          
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
           PrintWriter out = response.getWriter();
           out.println("<p>Datos Ingresados</p>");
         } else {
           
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
          PrintWriter out = response.getWriter();
          out.println("<p>No se pudo ingresar los datos</p>");
        }

        // Cerrar la conexión a la base de datos
          sentencia.close();
          conexion.close();
  
        } catch (SQLException e) {
      
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<h3>No se pudo ingresar los datos</h3>"+e.getMessage());
        } catch (ClassNotFoundException e) {
     
         response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<h3>Problemas con el servidor</h3>");
        }
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    /*
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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
*/
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
