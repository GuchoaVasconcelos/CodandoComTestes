package utils;

import controller.ServiçoController;
import model.Serviços;

public class GeradorServiço {
    private ServiçoController serviçoController = new ServiçoController();

    public void geradorServiço(){
        serviçoController.criarServiço(new Serviços("Banho", (float) 20));
        serviçoController.criarServiço(new Serviços("Cortar Unhas", (float) 30));
        serviçoController.criarServiço(new Serviços("Tosar", (float) 15.5));
        serviçoController.criarServiço(new Serviços("Aplicar Injeção", (float) 11.5));
    }
}
