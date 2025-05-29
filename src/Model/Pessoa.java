package src.Model;

public abstract class Pessoa {

    protected String nome;
    protected String CPF;
    protected String telefone;
    protected String email;

    public Pessoa(String nome,String CPF,String telefone,String email){
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome(){return nome; }
    public void setNome(String nome){this.nome=nome;}

    public String getCPF(){return CPF; }
    public void setCPF(String CPF){this.CPF=CPF;}

    public String getTelefone(){return telefone; }
    public void setTelefone(String telefone){this.telefone=telefone;}

    public String getEmail(){return email; }
    public void setEmail(String email){this.email=email;}


}
