/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ComprobarUsuario;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;



@WebService(serviceName = "ComprobarUsuario")
public class ComprobarUsuario {

 private String usuario = "nombreDeUsuario"; 
 private String contrasenia = "contraseniaDeUsuario";    
    
    @WebMethod(operationName = "Comprobar")
    public boolean Comprobar(@WebParam(name = "usuario") String user1, @WebParam(name = "contrasenia") String contra) {
        try{
            if (user1.equals(usuario)) {
                if (contra.equals(contrasenia)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
