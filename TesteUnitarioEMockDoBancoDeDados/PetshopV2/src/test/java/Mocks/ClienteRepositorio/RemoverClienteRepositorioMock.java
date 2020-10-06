package Mocks.ClienteRepositorio;

import DAO.ClienteRepositorio;
import JDBC.ConnectionFactory;
import controller.ClienteController;
import model.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoverClienteRepositorioMock {

    @Mock
    private ConnectionFactory factory = Mockito.mock(ConnectionFactory.class);

    @Mock
    private Connection conn = Mockito.mock(Connection.class);

    @Mock
    private PreparedStatement stmt = Mockito.mock(PreparedStatement.class);

    @Mock
    private ResultSet rs = Mockito.mock(ResultSet.class);

    private Cliente cliente = new Cliente("Roberto", "66666666655", "85990324354");
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

        Mockito.when(rs.getString("nome")).thenReturn(this.cliente.getNome());

        Mockito.when(rs.getString("CPF")).thenReturn(this.cliente.getCpf());

        Mockito.when(rs.getString("telefone")).thenReturn(this.cliente.getTelefone());

        Mockito.when(rs.getInt("idClientes")).thenReturn(this.cliente.getId());


    }

    @Test
    public void removerCliente() throws SQLException {
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(factory);
        Cliente cliente = new Cliente("Testandoo","60455544433","40028922");
        clienteRepositorio.addCliente(cliente);
        cliente.setId(clienteRepositorio.getUltimoIDCadastrado());

        Assert.assertTrue(clienteRepositorio.removerCliente(cliente.getId()));
    }
}
