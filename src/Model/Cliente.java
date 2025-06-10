package src.Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private String endereco;
    private double rendaMensal;
    private List<Venda>historicoCompras;

    public Cliente(String nome, String CPF,String telefone, String email, String endereco, double rendaMensal){
        super(nome, CPF, telefone, email);
        this.endereco = endereco;
        this.rendaMensal = rendaMensal;
        this.historicoCompras = new ArrayList<>();
    }

    public String getEndereco(){return endereco;}
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getRendaMensal(){return rendaMensal;}
    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public List<Venda> getHistoricoCompras(){return historicoCompras;}
    public void adicionarCompra(Venda venda) {this.historicoCompras.add(venda);}

    @Override
    public String toString(){
        return super.toString() + ", Endereço: " + endereco + ", Renda: R$ " + rendaMensal;
    }
}