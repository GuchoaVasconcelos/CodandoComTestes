package DAO;

import model.Serviços;
import java.util.ArrayList;
import java.util.List;


public class ServiçosRepositorio {
    private static ServiçosRepositorio serviçosRepositorio;
    private List<Serviços> serviços = new ArrayList<>();

    public static ServiçosRepositorio getInstance() {
        if (serviçosRepositorio == null) {
            serviçosRepositorio = new ServiçosRepositorio();
        }
        return serviçosRepositorio;
    }

    public void addServiço(Serviços serviço){
        int ultimoID = 1;
        for(int i=0; i<serviços.size(); i++){
            ultimoID++;
        }
        serviço.setId(ultimoID);
        serviços.add(serviço);
    }

    public boolean removerServiço(int id){
        Serviços removerServiço = VerificarServiço(id);
        if(removerServiço != null){
            serviços.remove(removerServiço);
            return true;
        }
        return false;
    }

    public void atualizarServiço(Serviços serviço, int id){
        Serviços atualizarServiço = VerificarServiço(id);
        if(atualizarServiço != null){
            atualizarServiço.setNomeServiço(serviço.getNomeServiço());
            atualizarServiço.setPreço(serviço.getPreço());
        }
    }

    public List<Serviços> mostrarServiços(){
        return serviços;
    }

    public Serviços VerificarServiço(int id){
        for (Serviços serviço: serviços ) {
            if(serviço.getId() == id){
                return serviço;
            }
        }
        return null;
    }


    public List<Serviços> getServiços() {
        return serviços;
    }

    public void setServiços(List<Serviços> serviços) {
        this.serviços = serviços;
    }

}
