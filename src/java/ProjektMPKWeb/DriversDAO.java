package ProjektMPKWeb;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class DriversDAO {    
    public int id;
    public String name;
    public String surname;
    public String sex;
    public boolean electric;
    
    private String url;
    private Connection conn;
    
    public DriversDAO() throws SQLException {
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433; databaseName=ProjektMPK; integratedSecurity=true;";
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Kierowcy_autobusow");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                electric = rs.getBoolean("Uprawnienia_do_autobusu_elektrycznego");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pracownicy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                name = rs2.getString("Imie");
                System.out.println(name);
                surname = rs2.getString("Nazwisko");
                sex = rs2.getString("Plec");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean Prev(int idOld) {
        boolean value = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Kierowcy_autobusow WHERE ID < ? ORDER BY id DESC");
            pstmt.setInt(1, idOld);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pracownicy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                name = rs2.getString("Imie");
                surname = rs2.getString("Nazwisko");
                sex = rs2.getString("Plec");
                electric = rs.getBoolean("Uprawnienia_do_autobusu_elektrycznego");
                value = true;
            }
        } catch (SQLException e) {
            
        }
        return value;
    }
    
    public boolean Next(int idOld) {
        boolean value = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Kierowcy_autobusow WHERE ID > ? ORDER BY id ASC");
            pstmt.setInt(1, idOld);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pracownicy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                name = rs2.getString("Imie");
                surname = rs2.getString("Nazwisko");
                sex = rs2.getString("Plec");
                electric = rs.getBoolean("Uprawnienia_do_autobusu_elektrycznego");
                value = true;
            }
        } catch (SQLException e) {
            
        }
        return value;
    }
    
    public void Create(
        String name,
        String surname,
        String sex,
        boolean electric
    ) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("EXEC Wstaw_kierowce ?, ?, ?, ?");
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, sex);
            pstmt.setBoolean(4, electric);
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Kierowcy_autobusow ORDER BY id DESC");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pracownicy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                this.name = rs2.getString("Imie");
                this.surname = rs2.getString("Nazwisko");
                this.sex = rs2.getString("Plec");
                this.electric = rs.getBoolean("Uprawnienia_do_autobusu_elektrycznego");
            }
        } catch (SQLException e) {
            
        }
    }
    
    public void Update(
        String name,
        String surname,
        String sex,
        boolean electric
    ) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Pracownicy \nSET Imie=?, Nazwisko=?, Plec=? \nWHERE id=?");
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, sex);
            pstmt.setInt(4, this.id);
            PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Kierowcy_autobusow \nSET Uprawnienia_do_autobusu_elektrycznego=?\nWHERE id=?");
            pstmt2.setBoolean(1, electric);
            pstmt2.setInt(2, this.id);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            this.name = name;
            this.surname = surname;
            this.sex = sex;
            this.electric = electric;
        } catch (SQLException e) {
            
        }   
    }
    
    public void Delete() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Pracownicy WHERE id = ?");
            pstmt.setInt(1, id);
            PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM Kierowcy_autobusow WHERE id = ?");
            pstmt2.setInt(1, id);
            pstmt2.executeUpdate();
            pstmt.executeUpdate();
            if (!Next(id)) Prev(id);
        } catch (SQLException e) {
            
        }
    }
}
