package ProjektMPKWeb;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class BusesDAO {    
    public int id;
    public String name;
    public boolean electric;
    
    private String url;
    private Connection conn;
    
    public BusesDAO() throws SQLException {
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433; databaseName=ProjektMPK; integratedSecurity=true;";
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Autobusy");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                electric = rs.getBoolean("Czy_elektryczny");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pojazdy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                name = rs2.getString("Nazwa_pojazdu");
                System.out.println(name);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean Prev(int idOld) {
        boolean value = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Autobusy WHERE ID < ? ORDER BY id DESC");
            pstmt.setInt(1, idOld);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pojazdy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                name = rs2.getString("Nazwa_pojazdu");
                electric = rs.getBoolean("Czy_elektryczny");
                value = true;
            }
        } catch (SQLException e) {
            
        }
        return value;
    }
    
    public boolean Next(int idOld) {
        boolean value = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Autobusy WHERE ID > ? ORDER BY id ASC");
            pstmt.setInt(1, idOld);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pojazdy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                name = rs2.getString("Nazwa_pojazdu");
                electric = rs.getBoolean("Czy_elektryczny");
                value = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return value;
    }
    
    public void Create(
        String name,
        boolean electric
    ) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("EXEC Wstaw_autobus ?, ?");
            pstmt.setString(1, name);
            pstmt.setBoolean(2, electric);
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Autobusy ORDER BY id DESC");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pojazdy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                this.name = rs2.getString("Nazwa_pojazdu");
                this.electric = rs.getBoolean("Czy_elektryczny");
            }
        } catch (SQLException e) {
            
        }
    }
    
    public void Update(
        String name,
        boolean electric
    ) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Pojazdy \nSET Nazwa_pojazdu=? \nWHERE id=?");
            pstmt.setString(1, name);
            pstmt.setInt(2, this.id);
            PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Autobusy \nSET Czy_elektryczny=?\nWHERE id=?");
            pstmt2.setBoolean(1, electric);
            pstmt2.setInt(2, this.id);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            this.name = name;
            this.electric = electric;
        } catch (SQLException e) {
            
        }   
    }
    
    public void Delete() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Pojazdy WHERE id = ?");
            pstmt.setInt(1, id);
            PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM Audobusy WHERE id = ?");
            pstmt2.setInt(1, id);
            pstmt2.executeUpdate();
            pstmt.executeUpdate();
            if (!Next(id)) Prev(id);
        } catch (SQLException e) {
            
        }
    }
}
