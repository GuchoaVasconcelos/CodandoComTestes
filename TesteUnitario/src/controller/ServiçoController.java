package controller;

import DAO.ServiçosRepositorio;
import model.Cliente;
import model.Serviços;

import java.util.List;

public class ServiçoController {
    ServiçosRepositorio serviçosRepositorio = ServiçosRepositorio.getInstance();

    public boolean criarServiço(Serviços serviço) {
        if(validarServiço(serviço)){
            serviçosRepositorio.addServiço(serviço);
            return true;
        }
        return false;

    }

    public boolean removerServiço(int id) {
       Serviços removerServiço = serviçosRepositorio.VerificarServiço(id);
       if(removerServiço != null){
           serviçosRepositorio.removerServiço(removerServiço.getId());
           return true;
       }
       return false;
    }

    public boolean editarServiço(Serviços serviço, int id) {
        if(validarServiço(serviço)){
            Serviços editarServiço = serviçosRepositorio.VerificarServiço(id);
            if(editarServiço != null){
                editarServiço.setPreço(serviço.getPreço());
                editarServiço.setNomeServiço(serviço.getNomeServiço());
                return true;
            }
        }

        return false;
    }

    public List<Serviços> mostrarServiços() {
        return serviçosRepositorio.getServiços();
    }

    public boolean validarServiço(Serviços serviço) {
        if (serviço.getNomeServiço().equals("")|| serviço.getNomeServiço().matches(".*[0-9].*")){
            return false;
        }
        if (serviço.getPreço() <= 0) {
            return false;
        }
        return true;
    }
    public Serviços pegarUmServiço(int id){
        return serviçosRepositorio.VerificarServiço(id);
    }
}
