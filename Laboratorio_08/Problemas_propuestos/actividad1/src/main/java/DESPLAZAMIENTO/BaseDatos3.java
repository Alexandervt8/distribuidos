
package DESPLAZAMIENTO;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaseDatos3 extends javax.swing.JFrame {

    JLabel LblId;
    JLabel LblNombre;
    JLabel LblDescripcion;
    JTextField TxtId;
    JTextField TxtNombre;
    JTextField TxtDescripcion;
    JButton BtnPrimero;
    JButton BtnSiguiente;
    JButton BtnAnterior;
    JButton BtnUltimo;
    JButton BtnInsertar;
    JButton BtnModificar;
    JButton BtnActualizar;
    JButton BtnEliminar;

    ResultSet resultado;
    Statement sentencia;
    
    public BaseDatos3() {
        initComponents2();
        Conexion();
    }


    private void initComponents2() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Inicialización de componentes y botones
        LblId = new JLabel("Id Categoria");
        LblNombre = new JLabel("Nombre");
        LblDescripcion = new JLabel("Descripcion");
        TxtId = new JTextField();
        TxtNombre = new JTextField();
        TxtDescripcion = new JTextField();
        BtnPrimero = new JButton("Primero");
        BtnAnterior = new JButton("Anterior");
        BtnSiguiente = new JButton("Siguiente");
        BtnUltimo = new JButton("Ultimo");
        BtnInsertar = new JButton("Insertar");
        BtnModificar = new JButton("Modificar");
        BtnActualizar = new JButton("Actualizar");
        BtnEliminar = new JButton("Eliminar");

        // Asignación de ActionListener a los botones
        BtnPrimero.addActionListener(new EventoBoton(this));
        BtnAnterior.addActionListener(new EventoBoton(this));
        BtnSiguiente.addActionListener(new EventoBoton(this));
        BtnUltimo.addActionListener(new EventoBoton(this));
        BtnInsertar.addActionListener(new EventoBoton(this));
        BtnModificar.addActionListener(new EventoBoton(this));
        BtnActualizar.addActionListener(new EventoBoton(this));
        BtnEliminar.addActionListener(new EventoBoton(this));

        // Paneles para organizar los componentes
        JPanel Panel1 = new JPanel(new GridLayout(3, 2, 30, 30)); // GridLayout con 3 filas y 2 columnas, con espacio de 20px entre componentes
        Panel1.add(LblId);
        Panel1.add(TxtId);
        Panel1.add(LblNombre);
        Panel1.add(TxtNombre);
        Panel1.add(LblDescripcion);
        Panel1.add(TxtDescripcion);

        JPanel Panel2 = new JPanel();
        Panel2.add(BtnPrimero);
        Panel2.add(BtnAnterior);
        Panel2.add(BtnSiguiente);
        Panel2.add(BtnUltimo);

        Panel2.add(BtnInsertar);
        Panel2.add(BtnModificar);
        Panel2.add(BtnActualizar);
        Panel2.add(BtnEliminar);

            // Panel principal que organiza los paneles secundarios
        JPanel Panel = new JPanel();
        Panel.setLayout(new BorderLayout());
        Panel.add(Panel1, BorderLayout.CENTER);
        Panel.add(Panel2, BorderLayout.SOUTH);
        //Panel.add(Panel3, BorderLayout.PAGE_END);
        getContentPane().add(Panel, BorderLayout.CENTER);

    // Ajustar el tamaño del JFrame según los componentes
    pack();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaseDatos3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

 private void Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/EmpresaMSQL", "root", "123456");
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            boolean tieneResultados = sentencia.execute("SELECT * FROM Categorias");
            if (tieneResultados) {
                resultado = sentencia.getResultSet();
                if (resultado != null) {
                    IrPrimero(); // Mostrar el primer registro al iniciar
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("Error de Conexión: " + e);
        }
    }

    public void IrPrimero() {
        try {
            if (resultado != null && resultado.first()) {
                String var = resultado.getString("IdCategoria");
                TxtId.setText(var);
                var = resultado.getString("Nombre");
                TxtNombre.setText(var);
                var = resultado.getString("Descripcion");
                TxtDescripcion.setText(var);
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexión: " + e);
        }
    }

    public void IrAnterior() {
        try {
            if (resultado != null && !resultado.isFirst()) {
                resultado.previous();
                String var = resultado.getString("IdCategoria");
                TxtId.setText(var);
                var = resultado.getString("Nombre");
                TxtNombre.setText(var);
                var = resultado.getString("Descripcion");
                TxtDescripcion.setText(var);
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexión: " + e);
        }
    }

    public void IrSiguiente() {
        try {
            if (resultado != null && !resultado.isLast()) {
                resultado.next();
                String var = resultado.getString("IdCategoria");
                TxtId.setText(var);
                var = resultado.getString("Nombre");
                TxtNombre.setText(var);
                var = resultado.getString("Descripcion");
                TxtDescripcion.setText(var);
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexión: " + e);
        }
    }

    public void IrUltimo() {
        try {
            if (resultado != null && resultado.last()) {
                String var = resultado.getString("IdCategoria");
                TxtId.setText(var);
                var = resultado.getString("Nombre");
                TxtNombre.setText(var);
                var = resultado.getString("Descripcion");
                TxtDescripcion.setText(var);
            }
        } catch (SQLException e) {
            System.out.println("Error de Conexión: " + e);
        }
    }

    public void InsertarRegistro() {
    System.out.println("Insertando...");

        try {
            String var2 = TxtNombre.getText();
            String var3 = TxtDescripcion.getText();

            // Preparar la sentencia SQL para la inserción
            String query = "INSERT INTO Categorias(Nombre, Descripcion) VALUES ('" + var2 + "', '" + var3 + "')";

            // Ejecutar la sentencia SQL
            int cantidad = sentencia.executeUpdate(query);
            if (cantidad > 0) {
                System.out.println("Inserción exitosa.");
                // Actualizar la interfaz o realizar otras acciones necesarias después de la inserción
            } else {
                System.out.println("No se pudo insertar el registro.");
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
}

public void ModificarRegistro() {
    System.out.println("Modificando...");

    try {
        String var1 = TxtId.getText();
        String var2 = TxtNombre.getText();
        String var3 = TxtDescripcion.getText();

        // Preparar la sentencia SQL para la modificación
        String query = "UPDATE Categorias SET Nombre = '" + var2 + "', Descripcion = '" + var3 + "' WHERE IDCategoria = " + var1;

        // Ejecutar la sentencia SQL
        int cantidad = sentencia.executeUpdate(query);
        if (cantidad > 0) {
            System.out.println("Modificación exitosa.");
            // Actualizar la interfaz o realizar otras acciones necesarias después de la modificación
        } else {
            System.out.println("No se encontró el registro a modificar.");
        }
    } catch (SQLException e) {
        System.out.println("Error de SQL: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

public void ActualizarRegistro() {
    System.out.println("Actualizando...");

    try {
        // Volver a ejecutar la consulta SELECT para refrescar el ResultSet
        boolean tieneResultados = sentencia.execute("SELECT * FROM Categorias");
        if (tieneResultados) {
            resultado = sentencia.getResultSet();
            if (resultado != null) {
                resultado.first(); // Mover al primer registro
                String var1 = resultado.getString("IdCategoria");
                String var2 = resultado.getString("Nombre");
                String var3 = resultado.getString("Descripcion");
                TxtId.setText(var1);
                TxtNombre.setText(var2);
                TxtDescripcion.setText(var3);
                System.out.println("Actualización exitosa.");
                // Actualizar la interfaz o realizar otras acciones necesarias después de la actualización
            }
        }
    } catch (SQLException e) {
        System.out.println("Error de SQL: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

public void EliminarRegistro() {
    System.out.println("Eliminando...");

    try {
        String var1 = TxtId.getText();

        // Preparar la sentencia SQL para la eliminación
        String query = "DELETE FROM Categorias WHERE IDCategoria = " + var1;

        // Ejecutar la sentencia SQL
        int cantidad = sentencia.executeUpdate(query);
        if (cantidad > 0) {
            System.out.println("Eliminación exitosa.");
            // Actualizar la interfaz o realizar otras acciones necesarias después de la eliminación
        } else {
            System.out.println("No se encontró el registro a eliminar.");
        }
    } catch (SQLException e) {
        System.out.println("Error de SQL: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}



    class EventoBoton implements ActionListener {

        BaseDatos3 fuente;

        public EventoBoton(BaseDatos3 pWnd) {
            fuente = pWnd;
        }

        @Override
        public void actionPerformed(ActionEvent evento) {
            String texto = evento.getActionCommand();
            switch (texto) {
                case "Primero":
                    fuente.IrPrimero();
                    break;
                case "Anterior":
                    fuente.IrAnterior();
                    break;
                case "Siguiente":
                    fuente.IrSiguiente();
                    break;
                case "Ultimo":
                    fuente.IrUltimo();
                    break;
                case "Insertar":
                    fuente.InsertarRegistro();
                    break;
                case "Modificar":
                    fuente.ModificarRegistro();
                    break;
                case "Actualizar":
                    fuente.ActualizarRegistro();
                    break;
                case "Eliminar":
                    fuente.EliminarRegistro();
                    break;
                default:
                    break;
            }
        }
    }

}
