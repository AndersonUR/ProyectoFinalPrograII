import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Productos extends JFrame {
    public Productos() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        components();
    }

    private void components() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

        JLabel nombre = new JLabel("Ingrese el nombre del producto:");
        nombre.setBounds(100, 50, 700, 30);
        panel.add(nombre);
        JLabel codigo = new JLabel("Ingrese el codigo del producto:");
        codigo.setBounds(100, 120, 700, 30);
        panel.add(codigo);
        JButton guardar = new JButton("Guardar Producto");
        guardar.setBounds(100, 200, 200, 30);
        panel.add(guardar);
        JButton exportar = new JButton("Crear archivo txt");
        exportar.setBounds(100, 240, 200, 30);
        panel.add(exportar);
        JTextField escribirNombre = new JTextField();
        escribirNombre.setBounds(100, 85, 200, 30);
        panel.add(escribirNombre);
        JTextField escribirCodigo = new JTextField();
        escribirCodigo.setBounds(100, 155, 200, 30);
        panel.add(escribirCodigo);

        ActionListener nuevoProducto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventarioConeccion conexion = new InventarioConeccion();
                conexion.addProduct(escribirNombre.getText(), escribirCodigo.getText());
                JOptionPane.showMessageDialog(null, "Se guardó en el inventario");
            }
        };
        guardar.addActionListener(nuevoProducto);

        ActionListener crearArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventarioConeccion conexion = new InventarioConeccion();
                conexion.export();
                JOptionPane.showMessageDialog(null, "Se exportó el archivo");
            }
        };

        exportar.addActionListener(crearArchivo);
    }
}


