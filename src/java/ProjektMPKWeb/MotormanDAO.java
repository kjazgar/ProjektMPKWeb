package ProjektMPKWeb;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class MotormanDAO {    
    public int id;
    public String name;
    public String surname;
    public String sex;
    public int level;
    
    private String url;
    private Connection conn;
    
    public MotormanDAO() throws SQLException {
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433; databaseName=ProjektMPK; integratedSecurity=true;";
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Motorniczy");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                level = rs.getInt("Poziom_uprawnien");
                System.out.println(level);
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Motorniczy WHERE ID < ? ORDER BY id DESC");
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
                level = rs.getInt("Poziom_uprawnien");
                value = true;
            }
        } catch (SQLException e) {
            
        }
        return value;
    }
    
    public boolean Next(int idOld) {
        boolean value = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Motorniczy WHERE ID > ? ORDER BY id ASC");
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
                level = rs.getInt("Poziom_uprawnien");
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
        int level
    ) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("EXEC Wstaw_motorniczego ?, ?, ?, ?");
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, sex);
            pstmt.setInt(4, level);
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Motorniczy ORDER BY id DESC");
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
                this.level = rs.getInt("Poziom_uprawnien");
            }
        } catch (SQLException e) {
            
        }
    }
    
    public void Update(
        String name,
        String surname,
        String sex,
        int level
    ) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Pracownicy \nSET Imie=?, Nazwisko=?, Plec=? \nWHERE id=?");
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, sex);
            pstmt.setInt(4, this.id);
            PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Motorniczy \nSET Poziom_uprawnien=?\nWHERE id=?");
            pstmt2.setInt(1, level);
            pstmt2.setInt(2, this.id);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            this.name = name;
            this.surname = surname;
            this.sex = sex;
            this.level = level;
        } catch (SQLException e) {
            
        }   
    }
    
    public void Delete() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Pracownicy WHERE id = ?");
            pstmt.setInt(1, id);
            PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM Motorniczy WHERE id = ?");
            pstmt2.setInt(1, id);
            pstmt2.executeUpdate();
            pstmt.executeUpdate();
            if (!Next(id)) Prev(id);
        } catch (SQLException e) {
            
        }
    }
}
