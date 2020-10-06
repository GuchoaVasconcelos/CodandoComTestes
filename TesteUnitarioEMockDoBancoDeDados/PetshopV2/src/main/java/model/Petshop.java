package model;

import java.util.ArrayList;
import java.util.List;

public class Petshop {
    private double caixa;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Serviços> serviços = new ArrayList<>();

    public Petshop(double caixa) {
        this.caixa = caixa;
    }

    public double getCaixa() {
        return caixa;
    }

    public void setCaixa(double caixa) {
        this.caixa = caixa;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setServiços(List<Serviços> serviços) {
        this.serviços = serviços;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public boolean addCliente(Cliente cliente){
        return clientes.add(cliente);
    }

    public boolean removerCliente(Cliente cliente){
        return clientes.remove(cliente);
    }

    public List<Serviços> getServiços() {
        return serviços;
    }

    public boolean addServiço(Serviços serviço){
        return serviços.add(serviço);
    }

    public boolean removerServiço(Serviços serviço){
        return serviços.remove(serviço);
    }
}
