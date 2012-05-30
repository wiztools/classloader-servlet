package org.wiztools.classloaderservlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author subwiz
 */
@WebListener()
public class ClassloaderServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("####################################");
        System.out.println("## Classloader Servlet Is Enabled ##");
        System.out.println("## NOT RECOMMENDED FOR PRODUCTION ##");
        System.out.println("##================================##");
        System.out.println("## Usage:                         ##");
        System.out.println("##    /ctx/class-source?class=[]  ##");
        System.out.println("##    /ctx/list-classpath         ##");
        System.out.println("####################################");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // do nothing!
    }
}
