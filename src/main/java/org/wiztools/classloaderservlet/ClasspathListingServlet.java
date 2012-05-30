package org.wiztools.classloaderservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author subwiz
 */
@WebServlet(name = "ClasspathListingServlet", urlPatterns = {"/list-classpath"})
public class ClasspathListingServlet extends HttpServlet {
    
    private void displayClasspath(PrintWriter out, ClassLoader classLoader) {
        out.println("### Classloader Class: "
                + classLoader.getClass().getName() + " ###");
        out.println();
        
        if(classLoader instanceof URLClassLoader) {
            final URL[] urls = ((URLClassLoader) classLoader).getURLs();
            for(URL url: urls) {
                out.println("* " + url);
            }
        }
        else {
            out.println("[Not Using URLClassLoader]");
        }
        out.println();
        ClassLoader parent = classLoader.getParent();
        if(parent != null) {
            displayClasspath(out, parent);
        }
    }

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
        // Set the headers:
        response.setContentType("text/plain;charset=UTF-8");
        response.addHeader("Content-Disposition", "inline;filename=list-classpath.txt");
        
        // Display classpath:
        PrintWriter out = response.getWriter();
        try {
            out.println("## ClassPath Listing ##");
            out.println();
            
            final ClassLoader classLoader = this.getClass().getClassLoader();
            displayClasspath(out, classLoader);
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
        return "Classpath Listing Servlet";
    }
}
