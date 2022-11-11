import javax.swing.*;
import java.sql.*;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InventarioConeccion {
    String db = "jdbc:postgresql://localhost:5432/FinalProjectJava";

    public void addProduct (String name, String code) {
        try {
            String time = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(db, "postgres", "secret");
            Statement createStatement = connection.createStatement();
            PreparedStatement newStatement = connection.prepareStatement("insert into producto values (?, ?, ?)");
            newStatement.setString(1, name);
            newStatement.setString(2, code);
            newStatement.setString(3, time);
            newStatement.executeQuery();
            JOptionPane.showMessageDialog(null, "Se guardó el producto");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
        }
    }

    public void export () {
        try {
            FileWriter file = new FileWriter("C:/Users/deadm/Desktop/Proyecto_Final/Inventario.txt");
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(db, "postgres", "secret");

            Statement createStatement = connection.createStatement();

            ResultSet results = createStatement.executeQuery ("select * from producto");
            int i=0;

            while (results.next())
            {
                file.write(i+1 + results.getString(1) + " " + results.getString(2) + results.getString(3)+ "\n");
                i++;
            }
            file.close();

            connection.close();
            JOptionPane.showMessageDialog(null, "Exportando en un archivo txt");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
        }
    }
}

