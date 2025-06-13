package src.Controller;

import src.Interface.Gerenciavel;
import src.Model.Cliente;
import src.Util.Logger;

import java.util.ArrayList;
import java.util.List;

public class ClienteController implements Gerenciavel<Cliente> {
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        if (buscarPorId(cliente.getCPF()) != null) {
            return false; 
        }
        clientes.add(cliente);
        Logger.log("Cliente cadastrado" + cliente.getNome() + "(CPF: " + cliente.getCPF() + ")");
        return true;
    }

    @Override
    public Cliente buscarPorId(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCPF().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    @Override
    public boolean atualizar(String cpf, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(cpf);
        if (cliente != null) {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            cliente.setRendaMensal(clienteAtualizado.getRendaMensal());
            Logger.log("Cliente atualizado: " + cliente.getNome() + " (CPF: " + cpf + ")");
            return true;
        }
        return false;
    }

    @Override
    public boolean remover(String cpf) {
        boolean removed = clientes.removeIf(c -> c.getCPF().equals(cpf));
        Logger.log(removed ? "Cliente removido" + cpf : "Cliente não encontrado para remoção" + cpf);
        return removed;
    }
}
