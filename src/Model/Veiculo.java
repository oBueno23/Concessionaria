package model;

public abstract class Veiculo {
    protected String chassi;
    protected String marca;
    protected String modelo;
    protected int ano;
    protected String cor;
    protected double preco;
    protected boolean disponivel;

    public Veiculo(String chassi, String marca, String modelo, int ano, String cor, double preco) {
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.disponivel = true;
    }

    // Getters e Setters
    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " - " + marca + " " + modelo + " (" + ano + ") - " + cor + " - R$ " + preco;
    }
}