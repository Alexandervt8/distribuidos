
package DESPLAZAMIENTO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaseDatos2 extends javax.swing.JFrame {

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

    ResultSet resultado;
    
    public BaseDatos2() {
        initComponents2();
        Conexion(); 
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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaseDatos2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void initComponents2() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        BtnPrimero.addActionListener(new EventoBoton(this));
        BtnAnterior.addActionListener(new EventoBoton(this));
        BtnSiguiente.addActionListener(new EventoBoton(this));
        BtnUltimo.addActionListener(new EventoBoton(this));

        JPanel Panel1 = new JPanel(new GridLayout(3, 2, 10, 10)); // GridLayout con 3 filas y 2 columnas, con espacio de 10px entre componentes
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

        JPanel Panel = new JPanel();
        Panel.setLayout(new BorderLayout());
        Panel.add(Panel1, BorderLayout.CENTER);
        Panel.add(Panel2, BorderLayout.SOUTH);

        getContentPane().add(Panel, BorderLayout.CENTER);
        pack();
    }

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

    class EventoBoton implements ActionListener {

        BaseDatos2 fuente;

        public EventoBoton(BaseDatos2 pWnd) {
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
                default:
                    break;
            }
        }
    }
}

