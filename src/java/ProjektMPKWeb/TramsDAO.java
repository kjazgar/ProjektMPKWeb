package ProjektMPKWeb;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class TramsDAO {    
    public int id;
    public String name;
    public int level;
    
    private String url;
    private Connection conn;
    
    public TramsDAO() throws SQLException {
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433; databaseName=ProjektMPK; integratedSecurity=true;";
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Tramwaje");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                level = rs.getInt("Wymagane_uprawnienia");
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Tramwaje WHERE ID < ? ORDER BY id DESC");
            pstmt.setInt(1, idOld);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pojazdy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                name = rs2.getString("Nazwa_pojazdu");
                level = rs.getInt("Wymagane_uprawnienia");
                value = true;
            }
        } catch (SQLException e) {
            
        }
        return value;
    }
    
    public boolean Next(int idOld) {
        boolean value = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Tramwaje WHERE ID > ? ORDER BY id ASC");
            pstmt.setInt(1, idOld);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pojazdy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                name = rs2.getString("Nazwa_pojazdu");
                level = rs.getInt("Wymagane_uprawnienia");
                value = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return value;
    }
    
    public void Create(
        String name,
        int level
    ) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("EXEC Wstaw_tramwaj ?, ?");
            pstmt.setString(1, name);
            pstmt.setInt(2, level);
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement("SELECT TOP 1 * FROM Tramwaje ORDER BY id DESC");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("ID");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT TOP 1 * FROM Pojazdy WHERE ID = ?");
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                rs2.next();
                this.name = rs2.getString("Nazwa_pojazdu");
                this.level = rs.getInt("Wymagane_uprawnienia");
            }
        } catch (SQLException e) {
            
        }
    }
    
    public void Update(
        String name,
        int level
    ) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Pojazdy \nSET Nazwa_pojazdu=? \nWHERE id=?");
            pstmt.setString(1, name);
            pstmt.setInt(2, this.id);
            PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Tramwaje \nSET Wymagane_uprawnienia=?\nWHERE id=?");
            pstmt2.setInt(1, level);
            pstmt2.setInt(2, this.id);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            this.name = name;
            this.level = level;
        } catch (SQLException e) {
            
        }   
    }
    
    public void Delete() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Pojazdy WHERE id = ?");
            pstmt.setInt(1, id);
            PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM Tramwaje WHERE id = ?");
            pstmt2.setInt(1, id);
            pstmt2.executeUpdate();
            pstmt.executeUpdate();
            if (!Next(id)) Prev(id);
        } catch (SQLException e) {
            
        }
    }
}
