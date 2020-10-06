package teste;

import controller.ClienteController;
import controller.PetsController;
import model.Cliente;
import model.Pets;

import java.util.List;

import static org.junit.Assert.*;

public class PetsControllerTest {
    private PetsController petsController = new PetsController();
    private ClienteController clienteController = new ClienteController();
    @org.junit.Test
    public void cadastrarPet() {
        Cliente cliente = new Cliente("Carlos", "15975325896", "88996358596");
        clienteController.cadastrarCliente(cliente);
        boolean resultado = petsController.cadastrarPet(new Pets("","Poodle", cliente));
        boolean resultado2 = petsController.cadastrarPet(new Pets("Bob","", cliente ));
        boolean resultado3 = petsController.cadastrarPet(new Pets("Bob","Poodle", null ));
        boolean resultado4 = petsController.cadastrarPet(new Pets("Bo1b","Poodle", cliente ));
        boolean resultado5 = petsController.cadastrarPet(new Pets("Bob","Poo1dle", cliente ));

        boolean resultado6 = petsController.cadastrarPet(new Pets("Bob","Poodle", cliente ));
        assertFalse(resultado);
        assertFalse(resultado2);
        assertFalse(resultado3);
        assertFalse(resultado4);
        assertFalse(resultado5);

        assertTrue(resultado6);
    }

    @org.junit.Test
    public void removerPet() {
        Cliente cliente = new Cliente("Carlos", "15975325896", "88996358596");
        clienteController.cadastrarCliente(cliente);
        petsController.cadastrarPet(new Pets("Bob","Poodle",cliente));
        petsController.cadastrarPet(new Pets("Bobo","Chihuahua",cliente));
        boolean resultado = petsController.removerPet(1);
        assertTrue(resultado);

    }

    @org.junit.Test
    public void editarPet() {
        Cliente cliente = new Cliente("Carlos", "15975325896", "88996358596");
        clienteController.cadastrarCliente(cliente);
        boolean resultado = petsController.cadastrarPet(new Pets("Bob","Poodle",cliente));
        assertTrue(resultado);

        Pets pet = new Pets("Bobo", "Poodle", cliente);
        boolean resultado2 = petsController.editarPet(pet,1);
        assertTrue(resultado2);

        Pets pet2 = new Pets("", "", cliente);
        boolean resultado3 = petsController.editarPet(pet2,1);
        assertFalse(resultado3);

    }

    @org.junit.Test
    public void listarPet() {
        Cliente clientee = new Cliente("Carloss", "15975325896", "88996358596");
        clienteController.cadastrarCliente(clientee);
        petsController.cadastrarPet(new Pets("Bob","Poodle", clientee ));
        petsController.cadastrarPet(new Pets("Bobe","Chitsu", clientee ));
        List<Pets> pets = petsController.listarPet();
        for (Pets pet: pets ) {
            System.out.println(pet.toString());
        }
    }

    @org.junit.Test
    public void validarPet() {
        Cliente cliente = new Cliente("Carlos", "15975325896", "88996358596");
        clienteController.cadastrarCliente(cliente);
        boolean resultado = petsController.validarPet(new Pets("","Poodle", cliente));
        boolean resultado2 = petsController.validarPet(new Pets("Bob","", cliente ));
        boolean resultado3 = petsController.validarPet(new Pets("Bob","Poodle", null ));
        boolean resultado4 = petsController.validarPet(new Pets("Bo1b","Poodle", cliente ));
        boolean resultado5 = petsController.validarPet(new Pets("Bob","Poo1dle", cliente ));

        boolean resultado6 = petsController.validarPet(new Pets("Bob","Poodle", cliente ));
        assertFalse(resultado);
        assertFalse(resultado2);
        assertFalse(resultado3);
        assertFalse(resultado4);
        assertFalse(resultado5);

        assertTrue(resultado6);

    }

    @org.junit.Test
    public void adicionarPetAoCliente(){
        Cliente cliente = new Cliente("Carlos", "15975325896", "88996358596");
        clienteController.cadastrarCliente(cliente);
        boolean resultado = petsController.adicionarPetAoCliente(new Pets("Vampeta","vampetaço", cliente));
        assertTrue(resultado);
    }
    @org.junit.Test
    public void removerPetDoCliente(){
        Cliente cliente = new Cliente("Carlos", "15975325896", "88996358596");
        clienteController.cadastrarCliente(cliente);
        boolean resultado = petsController.adicionarPetAoCliente(new Pets("Vampeta","vampetaço", cliente));
        assertTrue(resultado);
        boolean resultado2 = petsController.removerPetDoCliente(cliente.getPets().get(0));
        assertTrue(resultado2);

    }

}