package src.Model;

import src.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente>clientes;

    public ClienteController(){
        this.clientes = new ArrayList<>();
    }

    public boolean cadastrarCliente (String nome, String CPF, String telefone, String email, String endereco, double rendaMensal){
        if (buscarPorCpf(CPF) !=null){
            return false;
        }
        Cliente cliente = new Cliente(nome, CPF, telefone, email, endereco, rendaMensal);
        clientes.add(cliente);
        return true;
    }

    public Cliente buscarPorCpf(String CPF){
        return clientes.stream()
                .filter(c -> c.getCPF().equals(CPF))
                .findFirst()
                .orElse(null);
    }


    public List<Cliente> listarTodos(){
        return new ArrayList<>(clientes);
    }

    public boolean atualizarCliente(String CPF, String nome, String telefone, String email, String endereco, double rendaMensal){
        Cliente cliente = buscarPorCpf(CPF);
        if (cliente != null){
            cliente.setNome(nome);
            cliente.setTelefone(telefone);
            cliente.setEmail(email);
            cliente.setEndereco(endereco);
            cliente.setRendaMensal(rendaMensal);
            return true;
        }
        return false;
    }

    public boolean removerCliente(String CPF){
        return clientes.removeIf(c -> c.getCPF().equals(CPF));
    }

}