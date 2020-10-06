package Mocks.ClienteRepositorio;

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

public class EditarClienteRepositorioMock {
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
    public void editarClienteComCamposValidos() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        Assert.assertTrue(clienteController.cadastrarCliente(clienteSetup));
        Cliente clienteEditado = new Cliente("Testado", "111111", "22222");
        clienteController.editarCliente(clienteEditado, clienteController.pegarUltimoIDCadastrado());
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void editarClienteComNomeInvalido() {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteEditado = new Cliente("123123", "111111", "22222");
        clienteController.editarCliente(clienteEditado, 1);
        Mockito.verifyZeroInteractions(stmt);

    }

    @Test
    public void editarClienteComNomeVazio() {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteEditado = new Cliente("", "111111", "22222");
        clienteController.editarCliente(clienteEditado, 1);
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void editarClienteComCPFInvalido() {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteEditado = new Cliente("ASDASDASD", "asdasdasd", "22222");
        clienteController.editarCliente(clienteEditado, 1);
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void editarClienteComCPFVazio() {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteEditado = new Cliente("ASDASDASD", "", "22222");
        clienteController.editarCliente(clienteEditado, 1);
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void editarClienteComTelefoneInvalido() {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteEditado = new Cliente("ASDASDASD", "111111", "ASDASDASD");
        clienteController.editarCliente(clienteEditado, 1);
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void editarClienteComTelefoneVazio() {
        ClienteController clienteController = new ClienteController(factory);
        Cliente clienteEditado = new Cliente("ASDASDASDASD", "111111", "");
        clienteController.editarCliente(clienteEditado, 1);
        Mockito.verifyZeroInteractions(stmt);
    }
}
