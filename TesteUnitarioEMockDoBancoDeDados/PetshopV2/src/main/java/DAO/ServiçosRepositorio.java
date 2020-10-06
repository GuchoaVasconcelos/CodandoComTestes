package DAO;

import JDBC.ConnectionFactory;
import model.Cliente;
import model.Pets;
import model.Serviços;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiçosRepositorio {
    private ConnectionFactory connectionFactory = null;


    public ServiçosRepositorio(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public boolean addServiço(Serviços serviço) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO servicos(nomeServico,precoServico) values (?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, serviço.getNomeServiço());
            stmt.setDouble(2, serviço.getPreço());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn,stmt);
        }

    }

    public boolean removerServiço(int serviçoID) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM servicos WHERE idServicos = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, serviçoID);
            stmt.executeUpdate();
                return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn,stmt);
        }


    }

    public boolean atualizarServiço(Serviços serviço) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE servicos SET nomeServico = ?, precoServico = ? WHERE idServicos=?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, serviço.getNomeServiço());
            stmt.setDouble(2, serviço.getPreço());
            stmt.setInt(3, serviço.getId());
            stmt.executeUpdate();
                return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn,stmt);
        }

    }

    public Serviços VerificarServiço(int serviçoID) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM servicos WHERE idServicos = ?";
        Serviços servicoVerificado;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, serviçoID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nomeServico");
                Double preco = rs.getDouble("precoServico");

                servicoVerificado = new Serviços(nome, preco);
                servicoVerificado.setId(serviçoID);
                return servicoVerificado;
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn,stmt,rs);
        }
        return null;

    }


    public List<Serviços> getServiços() {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM servicos";
        List<Serviços> serviços = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nomeServico");
                Double preco = rs.getDouble("precoServico");

                serviços.add(new Serviços(nome, preco));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn,stmt,rs);
        }
        return serviços;
    }

    public int getUltimoIDCadastrado(){
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "select max(idServicos) from servicos";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int ID = rs.getInt("max(idServicos)");

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
