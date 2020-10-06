package Mocks.ClienteRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.ConnectionFactory;
import controller.ClienteController;
import model.Cliente;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class AddClienteRepositorioMock {
    @Mock
    private ConnectionFactory factory = Mockito.mock(ConnectionFactory.class);

    @Mock
    private Connection conn = Mockito.mock(Connection.class);

    @Mock
    private PreparedStatement stmt = Mockito.mock(PreparedStatement.class);

    @Mock
    private ResultSet rs = Mockito.mock(ResultSet.class);

    private Cliente clienteSetup = new Cliente("Roberto", "66666666655", "85990324354");
    private ClienteController clienteController = new ClienteController(factory);


    @Before
    public void setUp() throws SQLException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(factory.getConnection()).thenReturn(conn);

        Mockito.when(conn.prepareStatement(Mockito.any(String.class))).thenReturn(stmt);

        Mockito.when(stmt.executeUpdate()).thenReturn(1);

        Mockito.when(stmt.executeQuery()).thenReturn(rs);

        Mockito.doNothing().when(stmt).close();

        Mockito.when(conn.prepareStatement(Mockito.startsWith("Select"))).thenReturn(stmt);

        Mockito.when(rs.getString("nome")).thenReturn(this.clienteSetup.getNome());

        Mockito.when(rs.getString("CPF")).thenReturn(this.clienteSetup.getCpf());

        Mockito.when(rs.getString("telefone")).thenReturn(this.clienteSetup.getTelefone());

        Mockito.when(rs.getInt("idClientes")).thenReturn(this.clienteSetup.getId());


    }

    @Test
    public void addCliente() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        Cliente cliente = new Cliente(clienteSetup.getNome(), clienteSetup.getCpf(), clienteSetup.getTelefone());
        clienteController.cadastrarCliente(cliente);
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void addClienteComNomeInvalido() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteTestado = new Cliente("123123", clienteSetup.getCpf(), clienteSetup.getTelefone());
        assertFalse(clienteController.cadastrarCliente(clienteTestado));
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void addClienteComNomeVazio() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteTestado = new Cliente("", clienteSetup.getCpf(), clienteSetup.getTelefone());
        assertFalse(clienteController.cadastrarCliente(clienteTestado));
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void addClienteComCPFInvalido() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteTestado = new Cliente(clienteSetup.getNome(), "asdasdasdasd", clienteSetup.getTelefone());
        assertFalse(clienteController.cadastrarCliente(clienteTestado));
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void addClienteComCPFVazio() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteTestado = new Cliente(clienteSetup.getNome(), "", clienteSetup.getTelefone());
        assertFalse(clienteController.cadastrarCliente(clienteTestado));
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void addClienteComTelefoneInvalido() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteTestado = new Cliente(clienteSetup.getNome(), clienteSetup.getCpf(), "asdasdasdasds");
        assertFalse(clienteController.cadastrarCliente(clienteTestado));
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void addClienteComTelefoneVazio() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteTestado = new Cliente(clienteSetup.getNome(), clienteSetup.getCpf(), "");
        assertFalse(clienteController.cadastrarCliente(clienteTestado));
        Mockito.verifyZeroInteractions(stmt);
    }

}
