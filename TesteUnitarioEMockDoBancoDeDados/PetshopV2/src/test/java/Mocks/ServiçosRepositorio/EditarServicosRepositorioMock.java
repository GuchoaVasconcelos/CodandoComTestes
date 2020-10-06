package Mocks.ServiçosRepositorio;

import JDBC.ConnectionFactory;
import controller.ClienteController;
import controller.ServiçoController;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EditarServicosRepositorioMock {
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
    public void EditarServico() throws SQLException {
        ServiçoController serviçoController = new ServiçoController(factory);
        Serviços serviços = new Serviços(serviçosSetup.getNomeServiço(), serviçosSetup.getPreço());
        assertTrue(serviçoController.criarServiço(serviços));

        Serviços serviços2 = new Serviços("SERVICOS", 20.0);
        serviçoController.editarServiço(serviços2, 1);
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void EditarServicoNomeInvalido() throws SQLException {
        ServiçoController serviçoController = new ServiçoController(factory);
        Serviços serviços = new Serviços(serviçosSetup.getNomeServiço(), serviçosSetup.getPreço());
        assertTrue(serviçoController.criarServiço(serviços));

        Serviços serviços2 = new Serviços("123123123", 30.0);
        serviçoController.editarServiço(serviços2, 1);
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void EditarServicoNomeVazio() throws SQLException {
        ServiçoController serviçoController = new ServiçoController(factory);
        Serviços serviços = new Serviços(serviçosSetup.getNomeServiço(), serviçosSetup.getPreço());
        assertTrue(serviçoController.criarServiço(serviços));

        Serviços serviços2 = new Serviços("", 20.0);
        serviçoController.editarServiço(serviços2, 1);
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate();
    }


}
