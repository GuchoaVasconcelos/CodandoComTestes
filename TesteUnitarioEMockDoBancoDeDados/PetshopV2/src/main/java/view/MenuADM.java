/*
package view;

import controller.ClienteController;
import controller.PetshopController;
import controller.ServiçoController;
import model.Cliente;
import model.Pets;
import model.Serviços;

import java.util.List;
import java.util.Scanner;

public class MenuADM {

    public void mostrarMenu() {
        ClienteController clienteController = new ClienteController();
        PetshopController petshopController = new PetshopController();
        ServiçoController serviçoController = new ServiçoController();
        Scanner sc = new Scanner(System.in);
        int chave = 0;
        int opcao = 0;
        String nome, cpf, telefone;
        while(true){
            System.out.println("Digite a opção:");
            System.out.println("Digite 1 para adicionar Cliente");
            System.out.println("Digite 2 para remover Cliente");
            System.out.println("Digite 3 para listar os Clientes");
            System.out.println("Digite 4 para adicionar Serviço");
            System.out.println("Digite 5 para editar Serviço");
            System.out.println("Digite 6 para remover Serviço");
            System.out.println("Digite 7 para listar Serviço");
            System.out.println("Digite 8 para mostrar o valor no caixa");
            System.out.println("Digite -1 para sair");
            chave = sc.nextInt();
            switch(chave){
                case 1:
                    sc.nextLine();
                    System.out.println("Digite o nome do cliente");
                    nome = sc.nextLine();
                    System.out.println("Digite a CPF do cliente");
                    cpf = sc.nextLine();
                    System.out.println("Digite o telefone do cliente");
                    telefone = sc.nextLine();
                    if(clienteController.cadastrarCliente(new Cliente(nome,cpf,telefone))){
                        System.out.println("Cliente cadastrado com sucesso");
                    }else{
                        System.out.println("Falha ao cadastrar cliente");
                    }
                    break;
                case 2:
                    System.out.println("Selecione o cliente a ser removido");
                    for (Cliente cliente: clienteController.listarClientes()
                         ) {
                        System.out.println(cliente.toString());
                    }
                    opcao = sc.nextInt();
                    sc.nextLine();
                    if(clienteController.removerCliente(opcao)){
                        System.out.println("Cliente removido com sucesso");
                    }else{
                        System.out.println("Falha ao remover o cliente");
                    }

                    break;
                case 3:
                    for (Cliente cliente: clienteController.listarClientes()
                    ) {
                        System.out.println(cliente.toString());
                    }
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Digite o nome do serviço");
                    nome = sc.nextLine();
                    System.out.println("Digite o valor do serviço");
                    double valor = sc.nextDouble();
                    if(serviçoController.criarServiço(new Serviços(nome,valor))){
                        System.out.println("Serviço cadastrado com sucesso");
                    }else{
                        System.out.println("Falha ao cadastrar serviço");
                    }
                    break;
                case 5:
                    sc.nextLine();
                    System.out.println("Selecione o serviço a ser editado");
                    for (Serviços serviço: serviçoController.mostrarServiços()
                         ) {
                        System.out.println(serviço.toString());
                    }
                    opcao = sc.nextInt();
                    sc.nextLine();
                    Serviços serviçoEdit = serviçoController.pegarUmServiço(opcao);
                    System.out.println("Digite o novo nome do serviço");
                    nome = sc.nextLine();
                    System.out.println("Digite o novo preço do serviço");
                    double preço = sc.nextDouble();
                    serviçoController.editarServiço(new Serviços(nome,preço), opcao);
                    break;
                case 6:
                    System.out.println("Selecione o serviço a ser removido");
                    for (Serviços serviços: serviçoController.mostrarServiços()
                    ) {
                        System.out.println(serviços.toString());
                    }
                    opcao = sc.nextInt();
                    sc.nextLine();
                    if(serviçoController.removerServiço(opcao)){
                        System.out.println("Serviço removido com sucesso");
                    }else{
                        System.out.println("Falha ao remover o serviço");
                    }
                    break;
                case 7:
                    for (Serviços serviço: serviçoController.mostrarServiços()
                         ) {
                        System.out.println(serviço.toString());
                    }
                    break;
                case 8:
                    System.out.println(petshopController.getCaixa());
                    break;
                case -1:
                    return;

            }
        }
    }

}
*/
