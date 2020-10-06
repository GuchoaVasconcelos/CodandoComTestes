
package controller;

import DAO.ClienteRepositorio;
import DAO.PetsRepositorio;
import JDBC.ConnectionFactory;
import model.Cliente;
import model.Pets;
import model.Serviços;

import java.sql.SQLException;
import java.util.List;


public class PetsController {
    PetsRepositorio petsRepositorio;
    ClienteRepositorio clienteRepositorio;

    public PetsController(ConnectionFactory connection_factory) {
        clienteRepositorio = new ClienteRepositorio(connection_factory);
        petsRepositorio = new PetsRepositorio(connection_factory);
    }


    public boolean cadastrarPet(Pets pet) {
        if (validarPet(pet)) {
            petsRepositorio.addPet(pet);
            adicionarPetAoCliente(pet);
            return true;
        }
        return false;
    }

    public boolean removerPet(Pets pet) {
        for (int i = 0; i < petsRepositorio.getPets().size(); i++) {
            if (pet.getId() == petsRepositorio.getPets().get(i).getId()) {
                Cliente cliente = clienteRepositorio.verificarCliente(pet.getDono());
                removerPetDoCliente(pet);
                petsRepositorio.removerPet(pet);
                return true;
            }
        }
        return false;
    }

    public boolean editarPet(Pets pet, int id) {
        if (validarPet(pet)) {
            Pets editarPet = petsRepositorio.verificarPet(id);
            if (editarPet != null) {
                editarPet.setNome(pet.getNome());
                editarPet.setDono(pet.getDono());
                editarPet.setRaça(pet.getRaça());
                editarPet.setServiços(pet.getServiços());
                petsRepositorio.editarPet(editarPet);
                return true;
            }
        }
        return false;
    }

    public List<Pets> listarPet() {
        return petsRepositorio.getPets();

    }

    public boolean validarPet(Pets pet) {
        if (pet.getNome().equals("") || pet.getNome().matches(".*[[0-9]].*")) {
            return false;

        }
        if (pet.getRaça().equals("") || pet.getRaça().matches(".*[[0-9]].*")) {
            return false;
        }
        if (pet.getDono() <= 0) {
            return false;
        }

        return true;
    }

    public boolean adicionarPetAoCliente(Pets pet) {
        Cliente cliente = clienteRepositorio.verificarCliente(pet.getDono());
        if (cliente != null) {
            List<Pets> petsList = cliente.getPets();
            petsList.add(pet);
            cliente.setPets(petsList);
            clienteRepositorio.atualizarCliente(cliente);
            return true;
        }
        return false;
    }

    public boolean removerPetDoCliente(Pets pet) {
        Cliente cliente = clienteRepositorio.verificarCliente(pet.getDono());
        if (cliente != null) {
            List<Pets> petsList = cliente.getPets();
            petsList.remove(pet);
            cliente.setPets(petsList);
            clienteRepositorio.atualizarCliente(cliente);
            return true;
        }
        return false;
    }
    public List<Serviços> pegarServiçosDoPet(Pets pet){
        Pets pett = petsRepositorio.verificarPet(pet.getId());
        if(pett != null){
            return pett.getServiços();
        }
        return null;
    }

    public Pets pegarUmPet(int pet1){
        Pets pet = petsRepositorio.verificarPet(pet1);
        if(pet != null){
            return pet;
        }
        return null;
    }

    public int pegarUltimoIDCadastrado(){
        return petsRepositorio.getUltimoIDCadastrado();
    }
}


