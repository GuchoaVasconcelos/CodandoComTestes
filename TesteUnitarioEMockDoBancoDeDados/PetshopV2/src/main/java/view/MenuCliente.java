/*
package view;

import controller.ClienteController;
import controller.PetsController;
import controller.PetshopController;
import controller.ServiçoController;
import model.Cliente;
import model.Pets;
import model.Petshop;
import model.Serviços;

import java.util.List;
import java.util.Scanner;


public class MenuCliente {

    public void mostrarClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            System.out.println("ID do cliente:" + cliente.getId() + " \n" +
                    "Nome do cliente:" + cliente.getNome() + " \n" +
                    "CPF do cliente:" + cliente.getCpf() + " \n" +
                    "Telefone do cliente:" + cliente.getTelefone() + " \n");
        }

    }

    public void mostrarPets(List<Pets> pets) {
        for (Pets pet : pets) {
            System.out.println("ID do pet:" + pet.getId() + " \n" +
                    "Nome do pet:" + pet.getNome() + " \n" +
                    "Raça do pet:" + pet.getRaça() + " \n" +
                    "Nome do dono:" + pet.getDono().getNome() + " \n" +
                    "ID do dono: " + pet.getDono().getId());
        }

    }

    public void mostrarServiços(List<Serviços> serviços) {
        for (Serviços serviço: serviços) {
            serviço.toString();
        }

    }
    public void mostrarMenu() {
        PetsController petsController = new PetsController();
        ClienteController clienteController = new ClienteController();
        ServiçoController serviçoController = new ServiçoController();
        PetshopController petshopController = new PetshopController();
        Scanner sc = new Scanner(System.in);
        int chave = 0;
        int opcao = 0;
        String nome, raça;
        while (true) {
            System.out.println("Digite a opção:");
            System.out.println("Digite 1 para adicionar Pet");
            System.out.println("Digite 2 para editar Pet");
            System.out.println("Digite 3 para remover Pet");
            System.out.println("Digite 4 para listar os Pets");
            System.out.println("Digite 5 para solicitar um Serviço");
            System.out.println("Digite 6 para listar serviços solicitados");
            System.out.println("Digite 7 para editar nome");
            System.out.println("Digite 8 para editar CPF");
            System.out.println("Digite 9 para editar telefone");
            System.out.println("Digite -1 para sair");
            chave = sc.nextInt();
            switch (chave) {
                case 1:
                    sc.nextLine();
                    System.out.println("Digite o nome do pet");
                    nome = sc.nextLine();
                    System.out.println("Digite a raça do pet");
                    raça = sc.nextLine();
                    System.out.println("Selecione o id do dono do pet");
                    clienteController.listarClientes();
                    opcao = sc.nextInt();
                    Cliente cliente = clienteController.pegarCliente(opcao);
                    if (petsController.cadastrarPet(new Pets(nome, raça, cliente))) {
                        System.out.println("Pet cadastrado com sucessso");
                    } else {
                        System.out.println("Falha ao cadastrar o pet. Por favor, tente novamente.");
                    }
                    break;
                case 2:
                    System.out.println("Digite o ID do pet que será editado");
                    List<Pets> pets = petsController.listarPet();
                    mostrarPets(pets);
                    opcao = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Digite novo nome do pet");
                    nome = sc.nextLine();
                    System.out.println("Digite a nova raça do pet");
                    raça = sc.nextLine();
                    Pets atualizadoPet = petsController.pegarUmPet(opcao);
                    atualizadoPet.setNome(nome);
                    atualizadoPet.setRaça(raça);
                    atualizadoPet.toString();
                    break;
                case 3:
                    System.out.println("Digite o ID do pet que será removido");
                    List<Pets> petss = petsController.listarPet();
                    mostrarPets(petss);
                    opcao = sc.nextInt();
                    petsController.removerPet(opcao);
                    break;
                case 4:
                    List<Pets> todosPets = petsController.listarPet();
                    for (Pets pet: todosPets) {
                        System.out.println(pet.toString());
                    }

                    break;
                case 5:
                    System.out.println("Selecione o serviço desejado");
                    List<Serviços> serviços = serviçoController.mostrarServiços();
                    for (Serviços serviço: serviços) {
                        System.out.println(serviço.toString());
                    }
                    int serviço = sc.nextInt();
                    System.out.println("Selecione o pet desejado");
                    todosPets = petsController.listarPet();
                    for (Pets petArray: todosPets) {
                        System.out.println(petArray.toString());
                    }
                    int pet = sc.nextInt();
                    petsController.pegarUmPet(pet);
                    if(petshopController.solicitarServiço(petsController.pegarUmPet(pet), serviçoController.pegarUmServiço(serviço))){
                        System.out.println("Serviço feito com sucesso");
                    }else{
                        System.out.println("Falha ao solicitar serviço");
                    }
                    break;
                case 6:
                    System.out.println("Selecione o pet o qual você quer visualizar o histórico de serviços");
                    todosPets = petsController.listarPet();
                    for (Pets petArray: todosPets) {
                        System.out.println(petArray.toString());
                    }
                    pet = sc.nextInt();
                    Pets petListar = petsController.pegarUmPet(pet);
                    List<Serviços> servicosPet = petListar.getServiços();
                    for (Serviços serviçoPet: servicosPet
                         ) {
                        System.out.println(serviçoPet.toString());
                    }
                    break;
                case 7:
                    System.out.println("Selecione o nome do cliente que será editado");
                    List<Cliente> clients = clienteController.listarClientes();
                    for (Cliente clienteArray: clients
                         ) {
                        System.out.println(clienteArray.toString());
                    }
                    opcao = sc.nextInt();
                    cliente = clienteController.pegarCliente(opcao);
                    System.out.println("Digite o novo nome do cliente");
                    sc.nextLine();
                    nome = sc.nextLine();
                    cliente.setNome(nome);
                    break;
                case 8:
                    System.out.println("Selecione o nome do CPF que será editado");
                    clients = clienteController.listarClientes();
                    for (Cliente clienteArray: clients
                    ) {
                        System.out.println(clienteArray.toString());
                    }
                    opcao = sc.nextInt();
                    cliente = clienteController.pegarCliente(opcao);
                    System.out.println("Digite o CPF nome do cliente");
                    sc.nextLine();
                    String cpf = sc.nextLine();
                    cliente.setCpf(cpf);
                    break;
                case 9:
                    System.out.println("Selecione o nome do telefone que será editado");
                    clients = clienteController.listarClientes();
                    for (Cliente clienteArray: clients
                    ) {
                        System.out.println(clienteArray.toString());
                    }
                    opcao = sc.nextInt();
                    cliente = clienteController.pegarCliente(opcao);
                    System.out.println("Digite o novo telefone do cliente");
                    sc.nextLine();
                    String telefone = sc.nextLine();
                    cliente.setTelefone(telefone);
                    break;
                case -1:
                    return;

            }
        }
    }
}
*/
