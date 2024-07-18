package com.mycompany.comprobarusuariojsp;

import jakarta.annotation.Resource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import java.io.IOException;

@WebService(serviceName = "ComprobarUsuarioJSP")
public class ComprobarUsuarioJSP {

    private String usuario = "alexander";
    private String contrasenia = "123456";    

    @Resource
    private WebServiceContext wsContext;

    @WebMethod(operationName = "Comprobar")
    public boolean Comprobar(@WebParam(name = "usuario") String user1, @WebParam(name = "contrasenia") String contra) throws IOException {
        if (user1.equals(usuario)) {
            if (contra.equals(contrasenia)) {
                return true; // Usuario y contraseña correctos
            } else {
                setMessages(new String[]{"Contraseña incorrecta"});
                return false; // Contraseña incorrecta
            }
        } else {
            setMessages(new String[]{"Usuario no encontrado"});
            return false; // Usuario no encontrado
        }
    }

    private void setMessages(String[] messages) {
        try {
            MessageContext mc = wsContext.getMessageContext();
            HttpServletRequest request = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            HttpServletResponse response = (HttpServletResponse) mc.get(MessageContext.SERVLET_RESPONSE);
            request.setAttribute("messages", messages);

            // Redirigir a error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}



