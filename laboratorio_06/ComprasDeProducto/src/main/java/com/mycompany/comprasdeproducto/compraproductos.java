
package com.mycompany.comprasdeproducto;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "compraproductos")
public class compraproductos {

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "comprasProductos")
    public String comprasProductos(@WebParam(name = "CantidadPan") int CantidadPan, @WebParam(name = "CantidadQueso") int CantidadQueso, @WebParam(name = "CantidadPlatanos") int CantidadPlatanos , @WebParam(name = "CantidadNaranjas") int CantidadNaranjas) {
        String mensaje="";
        double total=0;
        if(CantidadPan<1 || CantidadQueso<1 || CantidadPlatanos<1 || CantidadNaranjas<1)
        mensaje="Ingreso incorrecto de las catidades de productos";
        else{
            mensaje+="\n";
            total+=CantidadPan*0.50;
            mensaje+="Pan: "+CantidadPan+"Unidades --> Subtotal:" + CantidadPan*0.50;
            mensaje+="\n";
            total+=CantidadQueso*2.50;
            mensaje+="Queso: "+CantidadQueso+"Unidades --> Subtotal:" + CantidadQueso*2.50;
            mensaje+="\n";
            total+=CantidadPlatanos*0.40;
            mensaje+="Platanos: "+CantidadPlatanos+"Unidades --> Subtotal:" + CantidadPlatanos*0.40;
            mensaje+="\n";
            total+=CantidadNaranjas*0.40;
            mensaje+="Naranjas: "+CantidadNaranjas+"Unidades --> Subtotal:" + CantidadNaranjas*0.40;
        }
        return mensaje;
    }
}
