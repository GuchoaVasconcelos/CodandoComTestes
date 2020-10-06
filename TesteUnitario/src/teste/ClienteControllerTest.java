package teste;

import controller.ClienteController;
import controller.PetsController;
import model.Cliente;
import model.Pets;
import model.Serviços;

import java.util.List;

import static org.junit.Assert.*;

public class ClienteControllerTest {
    private ClienteController clienteController = new ClienteController();
    private PetsController petsController= new PetsController();

    @org.junit.Test
    public void cadastrarCliente() {
        boolean resultadoReal = clienteController.cadastrarCliente(new Cliente("", "15975325896", "88996358596"));
        boolean resultadoReal2 = clienteController.cadastrarCliente(new Cliente("Carlos", "", "88996358596"));
        boolean resultadoReal3 = clienteController.cadastrarCliente(new Cliente("Carlos", "15975325896", ""));
        boolean resultadoReal4 = clienteController.cadastrarCliente(new Cliente("123", "15975325896", "88996358596"));
        boolean resultadoReal5 = clienteController.cadastrarCliente(new Cliente("Carlos", "a15975325896a", "88996358596"));
        boolean resultadoReal6 = clienteController.cadastrarCliente(new Cliente("Carlos", "15975325896", "a88996358596a"));

        boolean resultadoReal7 = clienteController.cadastrarCliente(new Cliente("Carlos", "15975325896", "88996358596"));

        assertFalse(resultadoReal);
        assertFalse(resultadoReal2);
        assertFalse(resultadoReal3);
        assertFalse(resultadoReal4);
        assertFalse(resultadoReal5);
        assertFalse(resultadoReal6);
        assertTrue(resultadoReal7);


    }


    @org.junit.Test
    public void removerCliente() {
        clienteController.cadastrarCliente(new Cliente("Carlos", "15975325896", "88996358596"));
        assertTrue(clienteController.removerCliente(1));

    }

    @org.junit.Test
    public void editarCliente() {
        boolean resultado = clienteController.cadastrarCliente(new Cliente("Carlos", "15975325896", "88996358596"));
        assertTrue(resultado);

        Cliente cliente = new Cliente("Uchoa", "85789665412", "85996385868");
        resultado = clienteController.editarCliente(cliente, 1);
        assertTrue(resultado);

        Cliente cliente2 = new Cliente("", "85789665412", "85996385868");
        resultado = clienteController.editarCliente(cliente2, 1);
        assertFalse(resultado);
    }

    @org.junit.Test
    public void listarClientes() {
        clienteController.cadastrarCliente(new Cliente("Carlos", "15975325896", "88996358596"));
        clienteController.cadastrarCliente(new Cliente("Uchoa", "85789665412", "85996385868"));
        List<Cliente> clientes = clienteController.listarClientes();
        for (Cliente cliente: clientes
             ) {
            System.out.println(cliente.toString());
        }

    }

    @org.junit.Test
    public void validarCliente() {
            boolean resultadoReal = clienteController.validarCliente(new Cliente("", "15975325896", "88996358596"));
            boolean resultadoReal2 = clienteController.validarCliente(new Cliente("Carlos", "", "88996358596"));
            boolean resultadoReal3 = clienteController.validarCliente(new Cliente("Carlos", "15975325896", ""));
            boolean resultadoReal4 = clienteController.validarCliente(new Cliente("123", "15975325896", "88996358596"));
            boolean resultadoReal5 = clienteController.validarCliente(new Cliente("Carlos", "a15975325896a", "88996358596"));
            boolean resultadoReal6 = clienteController.validarCliente(new Cliente("Carlos", "15975325896", "a88996358596a"));
            boolean resultadoReal7 = clienteController.validarCliente(new Cliente("Carlos", "15975325896", "88996358596"));

        assertFalse(resultadoReal);
        assertFalse(resultadoReal2);
        assertFalse(resultadoReal3);
        assertFalse(resultadoReal4);
        assertFalse(resultadoReal5);
        assertFalse(resultadoReal6);
        assertTrue(resultadoReal7);

    }
    @org.junit.Test
    public void removerTodosOsPets(){
        clienteController.cadastrarCliente(new Cliente("Carlos", "15975325896", "88996358596"));
        Cliente cliente = clienteController.pegarCliente(1);
        petsController.cadastrarPet(new Pets("Bob","poodle", cliente));
        petsController.cadastrarPet(new Pets("Bobo","poodlee", cliente));
        petsController.cadastrarPet(new Pets("Boboo","poodlee", cliente));
        clienteController.removerTodosOsPets(cliente.getId());
    }

}