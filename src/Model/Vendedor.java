package src.Model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Funcionario{

    private List<Venda> vendas;
    private double percentualComissao;

    public Vendedor(String nome, String CPF, String telefone, String email, String matricula, double salario,double percentualComissao ){
        super(nome, CPF, telefone, email, matricula, salario, "Vendedor");
        this.vendas = new ArrayList<>();
        this.percentualComissao = percentualComissao;
    }

    public List<Venda> getVendas(){return vendas; }
    public void adicionarVenda(Venda venda){this.vendas.add(venda);}

    public double getPercentualComissao(){return getPercentualComissao();}
    public void setPercentualComissao(double percentualComissao) {this.percentualComissao = percentualComissao; }

}
