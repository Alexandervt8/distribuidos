package tabla;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ventana extends javax.swing.JFrame {

    JTextField txtSql;
    JTextArea areaResultados;
    JButton btnConsulta;

    public Ventana() {
        initComponents2();
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

    private void initComponents2() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta SQL");

        txtSql = new JTextField(20);
        areaResultados = new JTextArea(10, 20);
        btnConsulta = new JButton("Ejecutar SQL");

        JScrollPane scrollPanel = new JScrollPane(areaResultados);

        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ejecutarConsulta();
            }
        });

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(Box.createRigidArea(new Dimension(30, 10)));
        panelPrincipal.add(txtSql);
        panelPrincipal.add(Box.createRigidArea(new Dimension(30, 10)));
        panelPrincipal.add(scrollPanel);
        panelPrincipal.add(Box.createRigidArea(new Dimension(30, 10)));
        panelPrincipal.add(btnConsulta);
        panelPrincipal.add(Box.createRigidArea(new Dimension(30, 10)));

        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        pack();
    }

    private void ejecutarConsulta() {
        String sql = txtSql.getText().trim();

        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/EmpresaMSQL", "root", "123456");

            // Crear el statement y ejecutar la consulta
            Statement sentencia = conexion.createStatement();
            boolean tieneResultados = sentencia.execute(sql);

            if (tieneResultados) {
                ResultSet resultado = sentencia.getResultSet();
                if (resultado != null) {
                    mostrarResultados(resultado);
                } else {
                    areaResultados.setText("");
                }
            }

            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            areaResultados.setText("Error: " + e.getMessage());
        }
    }

    private void mostrarResultados(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();

        // Obtener metadatos de resultado
        int numColumnas = rs.getMetaData().getColumnCount();

        // Construir encabezados de columna
        for (int i = 1; i <= numColumnas; i++) {
            sb.append(rs.getMetaData().getColumnName(i)).append("\t");
        }
        sb.append("\n");

        // Construir filas de datos
        while (rs.next()) {
            for (int i = 1; i <= numColumnas; i++) {
                sb.append(rs.getString(i)).append("\t");
            }
            sb.append("\n");
        }

        areaResultados.setText(sb.toString());
    }
    
    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
