package utils;

import controller.ClienteController;
import controller.PetsController;
import model.Pets;
import org.junit.Test;
import utils.GeradorClients;

public class GeradorPets {
    PetsController petsController = new PetsController();
    GeradorClients geradorClients = new GeradorClients();
    ClienteController clienteController = new ClienteController();

    @Test
    public void gerarPets() {
        geradorClients.criarClientes();
        petsController.cadastrarPet(new Pets("Vampeta", "Vampetaço", clienteController.pegarCliente(1)));
        petsController.cadastrarPet(new Pets("Vampetaa", "Vampetaço", clienteController.pegarCliente(2)));
        petsController.cadastrarPet(new Pets("Vampete", "Vampetaço", clienteController.pegarCliente(3)));
        petsController.cadastrarPet(new Pets("Vampeti", "Vampetaço", clienteController.pegarCliente(4)));
        petsController.cadastrarPet(new Pets("Vampeto", "Vampetaço", clienteController.pegarCliente(5)));
        petsController.cadastrarPet(new Pets("Vampetu", "Vampetaço", clienteController.pegarCliente(6)));

    }
}
