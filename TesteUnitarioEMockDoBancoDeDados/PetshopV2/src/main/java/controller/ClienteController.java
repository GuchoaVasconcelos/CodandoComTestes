
package controller;

import DAO.ClienteRepositorio;
import JDBC.ConnectionFactory;
import model.Cliente;
import model.Pets;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;


public class ClienteController {
    private ClienteRepositorio clienteRepositorio;

    public ClienteController(ConnectionFactory connection_factory) {
        clienteRepositorio = new ClienteRepositorio(connection_factory);
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (validarCliente(cliente)) {
            clienteRepositorio.addCliente(cliente);
            return true;
        }
        return false;
    }

    public boolean removerCliente(int clienteID) {
        Cliente cliente = clienteRepositorio.verificarCliente(clienteID);
        removerTodosOsPets(cliente);
        if(clienteRepositorio.removerCliente(cliente.getId())){
            return true;
        }
        return false;

    }

    public boolean editarCliente(Cliente cliente, int id) {
        if (validarCliente(cliente)) {
            Cliente clienteverificar = clienteRepositorio.verificarCliente(id);
            if (clienteverificar != null) {
                cliente.setId(clienteverificar.getId());
                clienteRepositorio.atualizarCliente(cliente);
                return true;
            }
        }
        return false;
    }

    public List<Cliente> listarClientes() {
        return clienteRepositorio.getClientes();
    }

    public boolean validarCliente(Cliente cliente) {
        if (cliente.getNome().equals("") || cliente.getNome().matches(".*[[0-9]].*")) {
            return false;
        }
        if (cliente.getCpf().equals("") || cliente.getCpf().matches(".*[[A-Z][a-z]].*")) {
            return false;
        }
        if (cliente.getTelefone().equals("") || cliente.getTelefone().matches(".*[[A-Z][a-z]].*")) {
            return false;
        }
        return true;
    }

    public void removerTodosOsPets(Cliente clienteRemove) {
        Cliente cliente = clienteRepositorio.verificarCliente(clienteRemove.getId());
        List<Pets> pets = cliente.getPets();
        int aux = pets.size();
        for (int i = 0; i < aux; i++) {
            pets.remove(pets.get(0));
        }

        cliente.setPets(pets);
        clienteRepositorio.atualizarCliente(cliente);
    }

    public Cliente pegarCliente(int clienteID) {

        return clienteRepositorio.verificarCliente(clienteID);

    }

    public int pegarUltimoIDCadastrado(){
        return clienteRepositorio.getUltimoIDCadastrado();
    }

}
