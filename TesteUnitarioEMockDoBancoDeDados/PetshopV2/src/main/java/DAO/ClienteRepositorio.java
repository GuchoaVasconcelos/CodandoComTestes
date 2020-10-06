package DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import JDBC.ConnectionFactory;
import com.mysql.jdbc.Driver;

import model.Cliente;
import model.Serviços;

import javax.sql.DataSource;
import javax.swing.*;

public class ClienteRepositorio {
    private ConnectionFactory connectionFactory = null;
    private int LastID;


    public ClienteRepositorio(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }


    public boolean addCliente(Cliente cliente) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO clientes(nome,CPF,telefone) VALUES (?,?,?)";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public boolean removerCliente(int clienteID) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM Clientes WHERE idClientes = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clienteID);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public Cliente verificarCliente(int clienteID) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM clientes WHERE idClientes = ?";
        Cliente clienteVerificado = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clienteID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String CPF = rs.getString("CPF");
                String telefone = rs.getString("telefone");
                int ID = rs.getInt("idClientes");

                clienteVerificado = new Cliente(nome,CPF,telefone);
                clienteVerificado.setId(ID);

                return clienteVerificado;
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return null;
    }

    public boolean atualizarCliente(Cliente cliente) {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE clientes SET nome = ?, CPF = ?, telefone=?  WHERE idClientes=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public List<Cliente> getClientes() {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String CPF = rs.getString("CPF");
                String telefone = rs.getString("telefone");
                clientes.add(new Cliente(nome, CPF, telefone));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return clientes;
    }

    public int getUltimoIDCadastrado(){
        Connection conn = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "select max(idClientes) from clientes";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int ID = rs.getInt("max(idClientes)");

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
