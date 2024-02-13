package listeners;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


public class Listener1 implements HttpSessionListener {

	private static int activeSessions = 0;
	
	 public static int getActiveSessions() {
	        return activeSessions;
	    }


	
    public void sessionCreated(HttpSessionEvent se)  { 
    	 activeSessions++;   
    	 System.out.println(activeSessions);
    	 }
    
    

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
         if (activeSessions >0) {
        	 activeSessions--;
        	 System.out.println(activeSessions);
         }
    }
	
}
