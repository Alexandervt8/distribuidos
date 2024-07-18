
package com.mycompany.comprobarusuario;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "ComprobarUsuario")
public class ComprobarUsuario {

    private String usuario = "melvin";
    private String contrasenia = "aprobado";

    @WebMethod(operationName = "Comprobar")
    public boolean Comprobar(@WebParam(name = "usuario") String user1, @WebParam(name = "contrasenia") String contra) {
        try {
            if (user1.equals(usuario)) {
                if (contra.equals(contrasenia)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
