package src.Model;

public class Funcionario extends Pessoa{

    protected String matricula;
    protected double salario;
    protected String cargo;

    public Funcionario(String nome, String CPF, String telefone, String email, String matricula, double salario,String cargo ){
        super(nome,CPF,telefone,email );
        this.matricula = matricula;
        this.salario = salario;
        this.cargo = cargo;
    }

    public  String getMatricula(){return matricula; }
    public void setMatricula(String matricula){ this.matricula = matricula;}

    public  double getSalario(){return salario; }
    public void setSalario(double salario){ this.salario = salario;}

    public  String getCargo(){return cargo; }
    public void setCargo(String cargo){ this.cargo = cargo;}

}
