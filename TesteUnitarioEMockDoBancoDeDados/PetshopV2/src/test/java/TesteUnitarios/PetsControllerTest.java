package TesteUnitarios;

import JDBC.ConnectionFactory;
import controller.ClienteController;
import controller.PetsController;
import model.Cliente;
import model.Pets;

import java.util.List;

import static org.junit.Assert.*;

public class PetsControllerTest {
    private ClienteController clienteController = new ClienteController(new ConnectionFactory());
    private PetsController petsController = new PetsController(new ConnectionFactory());


    @org.junit.Test
    public void cadastrarPet() {
        boolean resultado = petsController.cadastrarPet(new Pets("","Poodle", 5));
        boolean resultado2 = petsController.cadastrarPet(new Pets("Bob","", 5 ));
        boolean resultado4 = petsController.cadastrarPet(new Pets("Bo1b","Poodle", 5 ));
        boolean resultado5 = petsController.cadastrarPet(new Pets("Bob","Poo1dle", 5 ));

        boolean resultado6 = petsController.cadastrarPet(new Pets("Bob","Poodle", 5 ));
        assertFalse(resultado);
        assertFalse(resultado2);
        assertFalse(resultado4);
        assertFalse(resultado5);

        assertTrue(resultado6);
    }

    @org.junit.Test
    public void removerPet() {
        petsController.cadastrarPet(new Pets("Bob","Poodle",5));
        petsController.cadastrarPet(new Pets("Bobo","Chihuahua",5));
        boolean resultado = petsController.removerPet(petsController.pegarUmPet(1));
        assertTrue(resultado);

    }

    @org.junit.Test
    public void editarPet() {
        boolean resultado = petsController.cadastrarPet(new Pets("Bob","Poodle",5));
        assertTrue(resultado);

        Pets pet = new Pets("Bobo", "Poodle", 5);
        boolean resultado2 = petsController.editarPet(pet, petsController.pegarUltimoIDCadastrado());
        assertTrue(resultado2);

        Pets pet2 = new Pets("", "", 5);
        boolean resultado3 = petsController.editarPet(pet2,petsController.pegarUltimoIDCadastrado());
        assertFalse(resultado3);

    }

    @org.junit.Test
    public void listarPet() {

        petsController.cadastrarPet(new Pets("Bob","Poodle", 5 ));
        petsController.cadastrarPet(new Pets("Bobe","Chitsu", 5 ));
        List<Pets> pets = petsController.listarPet();
        for (Pets pet: pets ) {
            System.out.println(pet.toString());
        }
    }

    @org.junit.Test
    public void validarPet() {
        boolean resultado = petsController.validarPet(new Pets("","Poodle", 5));
        boolean resultado2 = petsController.validarPet(new Pets("Bob","", 5 ));
        boolean resultado4 = petsController.validarPet(new Pets("Bo1b","Poodle", 5));
        boolean resultado5 = petsController.validarPet(new Pets("Bob","Poo1dle", 5));

        boolean resultado6 = petsController.validarPet(new Pets("Bob","Poodle", 5));
        assertFalse(resultado);
        assertFalse(resultado2);
        assertFalse(resultado4);
        assertFalse(resultado5);

        assertTrue(resultado6);

    }

    @org.junit.Test
    public void adicionarPetAoCliente(){
        boolean resultado = petsController.adicionarPetAoCliente(new Pets("Vampeta","vampetaço", 5));
        assertTrue(resultado);
    }
    @org.junit.Test
    public void removerPetDoCliente(){
        ClienteController clienteController = new ClienteController(new ConnectionFactory());
        boolean resultado = petsController.adicionarPetAoCliente(new Pets("Vampeta","vampetaço", 5));
        assertTrue(resultado);
        boolean resultado2 = petsController.removerPetDoCliente(clienteController.pegarCliente(5).getPets().get(1));
        assertTrue(resultado2);

    }

}
