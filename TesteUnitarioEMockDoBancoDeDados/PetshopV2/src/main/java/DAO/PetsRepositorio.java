package DAO;

import JDBC.ConnectionFactory;
import model.Cliente;
import model.Pets;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetsRepositorio {
    private ConnectionFactory connectionFactory = null;


    public PetsRepositorio(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }


    public boolean addPet(Pets pet) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO pets(nomePet,racaPet,dono_id) values (?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getRaça());
            stmt.setInt(3, pet.getDono());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

    }

    public boolean removerPet(Pets pet) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM Pets WHERE idPets = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pet.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public Pets verificarPet(int petID) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM pets WHERE idPets = ?";
        Pets petVerificado;
        Cliente dono;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, petID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nomePet");
                String raca = rs.getString("racaPet");
                int donoID = rs.getInt("dono_id");

                petVerificado = new Pets(nome, raca, donoID);
                petVerificado.setId(petID);
                return petVerificado;

            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return null;
    }

    public boolean editarPet(Pets pet) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE pets SET nomePet = ?, racaPet = ?, dono_id=?  WHERE idPets=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getRaça());
            stmt.setInt(3, pet.getDono());
            stmt.setInt(4, pet.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public List<Pets> getPets() {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM Pets";
        List<Pets> pets = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nomePet");
                String raca = rs.getString("racaPet");
                int dono_id = rs.getInt("dono_id");

                pets.add(new Pets(nome, raca, dono_id));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn,stmt,rs);
        }
        return pets;
    }

    public int getUltimoIDCadastrado(){
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "select max(idPets) from pets";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int ID = rs.getInt("max(idPets)");

                return ID;
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return 0;
    }
}
