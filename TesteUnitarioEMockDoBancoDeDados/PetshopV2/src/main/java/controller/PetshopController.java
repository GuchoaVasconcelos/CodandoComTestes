
package controller;

import JDBC.ConnectionFactory;
import model.Cliente;
import model.Pets;
import model.Petshop;
import model.Serviços;

import java.util.List;

public class PetshopController {
    private Petshop petshop = new Petshop(0);
    private PetsController petsController;
    private ServiçoController serviçoController;

    public PetshopController(ConnectionFactory connection_factory) {
        this.petsController = new PetsController(connection_factory);
        this.serviçoController = new ServiçoController(connection_factory);
    }

    public double getCaixa(){
        return petshop.getCaixa();
    }

    public boolean addDinheiroCaixa(double dinheiro){
        if(dinheiro > 0){
            petshop.setCaixa(petshop.getCaixa() + dinheiro);
            return true;
        }
        return false;
    }

    public boolean removerDinheiroCaixa(double dinheiro){
        if((petshop.getCaixa() - dinheiro) >= 0 ){
            petshop.setCaixa(petshop.getCaixa() - dinheiro);
            return true;
        }
        return false;
    }

    public boolean solicitarServiço(Pets pet, Serviços serviço) {
        Pets pett = petsController.pegarUmPet(pet.getId());
        if (pett != null) {
            List<Serviços> serviços = petsController.pegarServiçosDoPet(pet);
            serviços.add(serviço);
            addDinheiroCaixa(serviço.getPreço());
            pet.setServiços(serviços);
            petsController.editarPet(pet, pett.getId());
            return true;
        }
        return false;
    }
}


