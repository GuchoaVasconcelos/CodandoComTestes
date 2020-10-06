package Mocks.ClienteRepositorio;

import DAO.ClienteRepositorio;
import JDBC.ConnectionFactory;
import controller.ClienteController;
import model.Cliente;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ListarClienteRepositorioMock {

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
    private List<Cliente> clientes = new ArrayList<>();


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
    public void listarClientes() throws SQLException {
        ClienteController clienteController = new ClienteController(factory);
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(factory);
        Cliente cliente = new Cliente("asdasd", "123123123123", "123123123123");
        clientes.add(cliente);
        clienteRepositorio.addCliente(cliente);
        clientes = clienteController.listarClientes();

        Mockito.verify(stmt, Mockito.times(1)).executeQuery();

    }
}
