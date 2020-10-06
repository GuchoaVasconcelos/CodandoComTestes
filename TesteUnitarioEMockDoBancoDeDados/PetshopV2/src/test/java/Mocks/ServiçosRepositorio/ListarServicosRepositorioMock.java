package Mocks.ServiçosRepositorio;

import DAO.ServiçosRepositorio;
import JDBC.ConnectionFactory;
import controller.ClienteController;
import controller.ServiçoController;
import model.Cliente;
import model.Serviços;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ListarServicosRepositorioMock {

    @Mock
    private ConnectionFactory factory = Mockito.mock(ConnectionFactory.class);

    @Mock
    private Connection conn = Mockito.mock(Connection.class);

    @Mock
    private PreparedStatement stmt = Mockito.mock(PreparedStatement.class);

    @Mock
    private ResultSet rs = Mockito.mock(ResultSet.class);

    private Serviços serviçosSetup = new Serviços("Roberto", 10.1);
    private ClienteController clienteController = new ClienteController(factory);
    private List<Serviços> serviços = new ArrayList<>();


    @Before
    public void setUp() throws SQLException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(factory.getConnection()).thenReturn(conn);

        Mockito.when(conn.prepareStatement(Mockito.any(String.class))).thenReturn(stmt);

        Mockito.when(stmt.executeUpdate()).thenReturn(1);

        Mockito.when(stmt.executeQuery()).thenReturn(rs);

        Mockito.doNothing().when(stmt).close();

        Mockito.when(conn.prepareStatement(Mockito.startsWith("Select"))).thenReturn(stmt);

        Mockito.when(rs.getString("nomeServicos")).thenReturn(serviçosSetup.getNomeServiço());

        Mockito.when(rs.getDouble("precoServico")).thenReturn(serviçosSetup.getPreço());


    }
    @Test
    public void listarServicos() throws SQLException {
        ServiçoController serviçoController = new ServiçoController(factory);
        ServiçosRepositorio serviçosRepositorio = new ServiçosRepositorio(factory);
        Serviços serviço = new Serviços(serviçosSetup.getNomeServiço(), serviçosSetup.getPreço());
        serviços.add(serviço);
        serviçoController.criarServiço(serviço);
        serviços = serviçoController.mostrarServiços();
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate();
    }

}
