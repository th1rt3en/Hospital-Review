/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hai06
 */
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
import org.junit.Test;
import junit.framework.*;
import org.mockito.Mockito;

public class Register extends Mockito{

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("type")).thenReturn("1");
        when(request.getParameter("Email")).thenReturn("hai06780@gmail.com");
        when(request.getParameter("Password")).thenReturn("123456");
        when(request.getParameter("Email")).thenReturn("hai06780@gmail.com");
        when(request.getParameter("Email")).thenReturn("hai06780@gmail.com");
        

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Controller.Register().doPost(request, response);

        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("My expected string"));
    }
}