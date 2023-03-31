package etu1879.framework.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.util.HashMap;
import etu1879.framework.Mapping;
import utilities.Utilitaire;
import java.util.Vector;

public class FrontServlet extends HttpServlet {

    HashMap<String , Mapping> MappingUrls = new HashMap<String , Mapping>();
    Vector<Class<?>> classes;

    @Override
    public void init()throws ServletException{ 
        super.init();
        Utilitaire utile = new Utilitaire();
        this.classes = new Vector<Class<?>>();
        this.MappingUrls = new HashMap<String,Mapping>();

        String pathToClasses = this.getInitParameter("pathClass");
        String classesPath = this.getServletContext().getRealPath(pathToClasses);

            try{
              this.classes = utile.getAllClasses(classesPath + "\\", classesPath, new Vector<Class<?>>());
              utile.setMappingUrls(this.MappingUrls,this.classes);
          }catch (Exception e) {
              //erreur
          }



  }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        try {
            String clientString = processRequest(res, req);
            out.println(clientString);
        } catch (Exception e) {
            out.println("Exception: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        try {
            String clientString = processRequest(res, req);
            out.println(clientString);
        } catch (Exception e) {
            out.println("Exception: " + e.getMessage());
        }
    }

    public String processRequest(HttpServletResponse res, HttpServletRequest req) throws Exception {
         // PrintWriter out = res.getWriter();
        StringBuffer url = req.getRequestURL(); //url iray manonontolo

        String context = req.getContextPath(); //anarana dossier
         // out.println(context);
        int index = url.indexOf(req.getContextPath());
        String otherArgs = "";
        // +1 for not taking the "/"
        for (int i = index + (context.length()) + 1; i < url.length(); i++) {
            otherArgs += url.toString().charAt(i);
        }
        return otherArgs;
    }

}
