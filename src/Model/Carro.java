package src.Model;

public class Carro extends Veiculo {
    private int numeroPortas;
    private String tipoCombustivel;
    private boolean arCondicionado;

    public Carro(String chassi, String marca, String modelo, int ano, String cor, double preco, int numeroPortas, String tipoCombustivel, boolean arCondicionado) {
        super(chassi, marca, modelo, ano, cor, preco);
        this.numeroPortas = numeroPortas;
        this.tipoCombustivel = tipoCombustivel;
        this.arCondicionado = arCondicionado;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    @Override
    public String toString() {
        return super.toString() + " - " + numeroPortas + " portas - " + tipoCombustivel +
                (arCondicionado ? " - Com Ar Condicionado" : " - Sem Ar Condicionado");
    }
}