package org.wiztools.classloaderservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.CodeSource;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author subwiz
 */
@WebServlet(name = "DisplayClassSourceServlet", urlPatterns = {"/class-source"})
public class DisplayClassSourceServlet extends HttpServlet {
    
    private static final String NOT_AVAILABLE = "[NOT AVAILABLE]";

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        final String className = request.getParameter("class");
        if(className == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Please pass `class' request parameter!");
            return;
        }
        
        response.setContentType("text/plain;charset=UTF-8");
        response.addHeader("Content-Disposition", "inline;filename=class-source.txt");
        PrintWriter out = response.getWriter();
        try {
            final ClassLoader classLoader = this.getClass().getClassLoader();
            
            
            out.println("## Protection Domain Path Extraction ##");
            out.println();
            Class myClass = classLoader.loadClass(className);
            CodeSource source = myClass.getProtectionDomain().getCodeSource();
            if(source != null) {
                out.println("* " + source.getLocation());
            }
            else {
                out.println(NOT_AVAILABLE);
            }
            out.println();
            
            // Use resource query method:
            
            out.println("## Resource Query Path Extraction ##");
            out.println();
            
            final String classPath = className.replace('.', '/') + ".class";
            Enumeration<URL> urls = classLoader.getResources(classPath);
            if(urls.hasMoreElements()) {
                while(urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    out.println("* " + url);
                }
            }
            else {
                out.println(NOT_AVAILABLE);
            }
            out.println();
        }
        catch(ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
        finally {            
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Display Class Source Servlet";
    }
}
