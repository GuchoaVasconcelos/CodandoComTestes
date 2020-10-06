/*
package view;


import utils.GeradorClients;
import utils.GeradorPets;
import utils.GeradorServiço;

import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        int chave = 0;
        MenuADM menuADM = new MenuADM();
        MenuCliente menuCliente = new MenuCliente();
        Scanner sc = new Scanner(System.in);
        GeradorClients geradorClients = new GeradorClients();
        GeradorPets geradorPets = new GeradorPets();
        GeradorServiço geradorServiço = new GeradorServiço();
        geradorClients.criarClientes();
        geradorPets.gerarPets();
        geradorServiço.geradorServiço();

        System.out.println("Digite 1 se você for administrador, digite 2 se você for cliente");
        chave = sc.nextInt();
        while (true) {

            switch (chave) {
                case 1:
                    menuADM.mostrarMenu();
                    break;
                case 2:
                    menuCliente.mostrarMenu();
                    break;
                case -1:
                    return;
                default:
                    break;
            }
        }
    }


}
*/
