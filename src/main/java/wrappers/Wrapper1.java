package wrappers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class Wrapper1  extends HttpServletRequestWrapper {
       
    
    public Wrapper1(HttpServletRequest request) {
        super(request);
        // TODO Auto-generated constructor stub
    }

	
    @Override
    public String getParameter(String name) {
        if ("ilyess".equals(name)) {
        	 System.out.println("esmek raw tmodifa");
           
            return "ilyessmodif";
        }
        // Si le paramètre n'est pas adéquat alors on retourne la valeur par défaut
        return super.getParameter(name);
    }

	
	
}