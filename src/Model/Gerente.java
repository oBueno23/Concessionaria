package src.Model;

public class Gerente extends Funcionario {

    private double bonificacao;
    private String setor;

    public Gerente (String nome, String CPF, String telefone, String email, String matricula, double salario, double bonificacao, String setor){
        super(nome,CPF,telefone,email,matricula,salario,"Gerente");
        this.bonificacao=bonificacao;
        this.setor=setor;

    }

    public double getBonificacao(){return bonificacao;}
    public void setBonificacao(double bonificacao){this.bonificacao = bonificacao;}

    public String getSetor(){return setor;}
    public void setSetor(String setor){this.setor = setor;}



}