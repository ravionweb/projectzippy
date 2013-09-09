package com.schooltrix.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class Log4JServlet
 */

public class Log4JServlet extends HttpServlet {
	private final long serialVersionUID = 1L;
       
	 private org.apache.log4j.Logger log = Logger.getLogger(Log4JServlet.class);

    /**
	 * Initialization of the servlet. 
	 * @throws ServletException if an error occure
	 */
    public void init() throws ServletException {
    	try{
    		// gets the log4j.properties file path from web.xml
    		String log4jfile = getInitParameter("log4j-init-file");
    		System.out.println("in Log4JServlet");
	        if (log4jfile != null) {
	            String propfile = getServletContext().getRealPath(log4jfile);
	            System.out.println("propfile:::"+propfile);
	            PropertyConfigurator.configure(propfile);
	        }
    	}catch(Exception _ex){_ex.printStackTrace();}
    }


}
