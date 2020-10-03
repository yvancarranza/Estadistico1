
package controlador;

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
import model.ModelUsuario;

/**
 *
 * @author Yvan
 */
@WebServlet(name = "usuario", urlPatterns = {"/usuario"})
public class usuario extends HttpServlet {

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
            out.println("<title>Servlet usuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet usuario at " + request.getContextPath() + "</h1>");
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
             //elimina(request,response);
         }
    }     
     protected void registra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         String idusuario = request.getParameter("idusuario");
         String nomusuario =  request.getParameter("nomusuario");
         String password = request.getParameter("password");  
         String tipodeusuario = request.getParameter("tipodeusuario");  
         
         Usuario a = new Usuario();
         a.setIdusuario(idusuario);
         a.setNomusuario(nomusuario);
         a.setPassword(password);
         a.setTipodeusuario(tipodeusuario);
         a.setTienecaducidad("NO");
         
         ModelUsuario model = new ModelUsuario();
         model.insertaUsuario(a);
         lista(request,response);
     }
    
     protected void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         ModelUsuario model = new ModelUsuario();
         List<Usuario> data =  model.listaUsuario();
         
         request.setAttribute("data", data);
         
         request.getRequestDispatcher("/listaUsuario.jsp").forward(request, response);
     }
    
     protected void actualiza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         String password = request.getParameter("password");
         String nomusuario = request.getParameter("nomusuario");
         String idusuario = request.getParameter("idusuario");
                
         Usuario a = new Usuario();
         a.setIdusuario(idusuario);
         a.setNomusuario(nomusuario);
         a.setPassword(password);
         
         ModelUsuario model = new ModelUsuario();         
         /*model.actualizausuario();
         lista(request,response);*/
     }
    
     protected void busca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String idusuario = request.getParameter("idusuario");
         String password  = request.getParameter("password");
         String tipodeusuario;
         int idpersona;
         ModelUsuario model = new ModelUsuario();
         Usuario a = model.buscaUsuario(idusuario);
         if (a == null)
         {
            String message = "Correo o password incorrecto";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);            
         }
         else if (a.getPassword().equalsIgnoreCase(password))
         {      
             tipodeusuario = a.getTipodeusuario();
             idpersona = a.getIdpersona();
            HttpSession session =  (HttpSession)request.getSession();
            session.setAttribute("usuario", idusuario);
            session.setAttribute("tipodeusuario", tipodeusuario);
            session.setAttribute("idpaciente",0);
            // Obtener el ID del Paciente
            //P = Paciente
            //A = Administrador
            //F = Profesional
            String url;
            url = "servicios.jsp";
            if(tipodeusuario.equals("U"))
            {                
                String hola = Integer.toString(idpersona);
                session.setAttribute("idpaciente",hola );
                url = "serviciosPaciente.jsp";
            }else if(tipodeusuario.equals("F"))
                {
                    String hola = Integer.toString(idpersona);
                    session.setAttribute("idprofesional",hola);
                    url = "serviciosProfesional.jsp";   
                    url = "solicitud?metodo=lista";
                }
            else
            {
                String hola = Integer.toString(idpersona);
                session.setAttribute("idprofesional",hola);
               url = "servicios.jsp";                    
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response); 
            
         }
         else
         {
             String message = "Contraseña Incorrecta";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);            
         }
     }

     /*protected void elimina(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String id = request.getParameter("id");
         
        
         ModelAlumno model = new ModelAlumno();
         model.eliminaAlumno(Integer.parseInt(id));
         lista(request,response);
     }
*/
     
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
