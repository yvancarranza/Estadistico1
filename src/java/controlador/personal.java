/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidad.Personal;
import entidad.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ModelPersonal;
import model.ModelUsuario;

/**
 *
 * @author Yvan
 */
@WebServlet(name = "personal", urlPatterns = {"/personal"})
public class personal extends HttpServlet {

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
            out.println("<title>Servlet personal</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet personal at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     response.setContentType("text/html;charset=UTF-8");
        
     String metodo = request.getParameter("metodo");
     if(metodo.equals("registra"))
        {
            registra(request,response);
        }else if(metodo.equals("lista"))
        {
            lista(request,response);
        }else if(metodo.equals("actualiza"))
        {
            actualiza(request,response);
        }else if(metodo.equals("busca"))
        {
           busca(request,response);
        }else if (metodo.equals("elimina"))
         {
             elimina(request,response);
         }
    }     
    
     protected void registra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         String nombres = request.getParameter("nombres");
         String apellidos =  request.getParameter("apellidos");
         String correo = request.getParameter("correo");  
         String password = request.getParameter("password");  
         String dni = request.getParameter("dni");  
         String genero = request.getParameter("genero");  
         String telefono = request.getParameter("telefono");  
         String ubigeo = request.getParameter("ubigeo");  
         String tipousuario = request.getParameter("tipousuario");  
         
         //Procedemos a validar si ya existe un usuario con el correo
          ModelUsuario model = new ModelUsuario();
         Usuario a = model.buscaUsuario(correo);
         if (a != null)
         {
             // El usuario no existe
            String message = "Ya existe una cuenta con el correo ingresado";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("registroUsuario.jsp");
            dispatcher.forward(request, response);            
         }
         else
         {
             //Verificar si existe un Personal con el mismo DNI
             ModelPersonal modelp = new ModelPersonal();
             Personal paci1 = modelp.buscaPersonal(dni);
             if(paci1 != null)                     
             {
                 // Ya existe personal con el mismo DNI
                String message = "Ya existe una Personal con el mismo No de DNI";
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher("registroUsuario.jsp");
                dispatcher.forward(request, response);                             
             }
             else
             {
                Personal objp = new Personal();                      
                objp.setNombres(nombres);
                objp.setApellidos(apellidos);
                objp.setGenero(genero);
                objp.setCorreo(correo);
                objp.setDni(dni);
                objp.setTelefono(telefono);
                objp.setUbigeo(ubigeo);
                objp.setDireccion("");
               


                modelp.insertaPersonal(objp);                             
                Personal paci2 = modelp.buscaPersonal(dni);
             
                // Registrar al personal como usuario
                 Usuario obju = new Usuario();
                   obju.setIdusuario(correo);
                   obju.setNomusuario(nombres + " " + apellidos);
                   obju.setPassword(password);
                   obju.setTipodeusuario(tipousuario);
                   obju.setTienecaducidad("NO");
                   obju.setEstado("ACTIVO");
                   obju.setIdpersona(paci2.getIdpersonal());
                 ModelUsuario modelu = new ModelUsuario();
                 modelu.insertaUsuario(obju);         
                 //RequestDispatcher dispatcher = request.getRequestDispatcher("listadoUsuarios.jsp");
                 RequestDispatcher dispatcher = request.getRequestDispatcher("personal?metodo=lista");
                 
               dispatcher.forward(request, response);        
             }
         }   
                 
     }
      protected void elimina(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String id = request.getParameter("id");           
          ModelPersonal model = new ModelPersonal();
           Personal p = model.buscaPersonal(Integer.parseInt(id));           
           model.eliminaPersonal(Integer.parseInt(id));           
           
           ModelUsuario mus = new ModelUsuario();
           mus.eliminaUsuario(p.getCorreo());
           lista(request, response);          
       }

     protected void busca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //Obtener el ID del Usuario que está logueado         
           String id = request.getParameter("id");           
           if(id == null)
                   {
                        HttpSession session = (HttpSession)request.getSession();
                         id = session.getAttribute("idpersonal").toString();
                   }
           ModelPersonal model = new ModelPersonal();
           Personal p = model.buscaPersonal(Integer.parseInt(id));           
           request.setAttribute("data", p);           
           request.getRequestDispatcher("/modificaUsuario.jsp").forward(request, response);     
       }
     
     protected void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         ModelPersonal model = new ModelPersonal();
         List<Personal> data =  model.listaPersonal();         
         request.setAttribute("data", data);         
         request.getRequestDispatcher("/listadoUsuarios.jsp").forward(request, response);
     }
     
     protected void actualiza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         String nombres = request.getParameter("nombres");
         String apellidos =  request.getParameter("apellidos");
         String correo = request.getParameter("correo");  
         String password = request.getParameter("password");  
         String direccion = request.getParameter("direccion");
         String dni = request.getParameter("dni");  
         String genero = request.getParameter("genero");  
         String telefono = request.getParameter("telefono");  
         String ubigeo = request.getParameter("ubigeo");
         String tipousuario = request.getParameter("tipousuario");
         int idpersonal = Integer.parseInt(request.getParameter("idpersonal").toString()) ;
                
         Personal a = new Personal();
         Personal objp = new Personal();                      
        objp.setNombres(nombres);
        objp.setApellidos(apellidos);
        objp.setGenero(genero);
        objp.setCorreo(correo);
        objp.setDni(dni);
        objp.setTelefono(telefono);
        objp.setUbigeo(ubigeo);
        objp.setDireccion(direccion);
        objp.setIdpersonal(idpersonal);
         
        ModelPersonal model = new ModelPersonal();         
        model.actualizaPersonal(objp);
        
        if(!password.isEmpty())
        {
            // Solo si se ha cambiado la contraseña, se procede a actualizar la credencial del usuario
            Usuario u = new Usuario();
            u.setIdusuario(objp.getCorreo());
            u.setPassword(password);
            
            ModelUsuario mus = new ModelUsuario();
            mus.actualizaUsuario(u);
        }
        
        //RequestDispatcher dispatcher = request.getRequestDispatcher("personal?metodo=lista");
         request.getRequestDispatcher("personal?metodo=lista").forward(request, response);
                
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
     service(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
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
