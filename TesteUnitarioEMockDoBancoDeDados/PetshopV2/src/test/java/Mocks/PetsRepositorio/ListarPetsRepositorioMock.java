package Mocks.PetsRepositorio;

import DAO.ClienteRepositorio;
import DAO.PetsRepositorio;
import JDBC.ConnectionFactory;
import controller.ClienteController;
import controller.PetsController;
import model.Cliente;
import model.Pets;
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

public class ListarPetsRepositorioMock {

    @Mock
    private ConnectionFactory factory = Mockito.mock(ConnectionFactory.class);

    @Mock
    private Connection conn = Mockito.mock(Connection.class);

    @Mock
    private PreparedStatement stmt = Mockito.mock(PreparedStatement.class);

    @Mock
    private ResultSet rs = Mockito.mock(ResultSet.class);

    private Cliente cliente = new Cliente("Roberto", "66666666655", "85990324354");
    private Pets pet = new Pets("testinha", "teste", 5);
    private ClienteController clienteController = new ClienteController(factory);
    private List<Pets> petsList = new ArrayList<>();


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

        Mockito.when(rs.getString("nomePet")).thenReturn(this.pet.getNome());

        Mockito.when(rs.getString("racaPet")).thenReturn(this.pet.getRaça());

        Mockito.when(rs.getInt("dono_id")).thenReturn(this.pet.getDono());


    }

    @Test
    public void listarPets() throws SQLException {
        PetsController petsController = new PetsController(factory);
        PetsRepositorio petsRepositorio = new PetsRepositorio(factory);
        Pets pet = new Pets("asdasd", "asdasd", 5);
        petsList.add(pet);
        petsRepositorio.addPet(pet);
        petsList = petsController.listarPet();

        Mockito.verify(stmt, Mockito.times(1)).executeQuery();

    }

}
