package src.Model;

public class Moto extends Veiculo{

    private int cilindrada;
    private String tipoPartida;
    private boolean bagageiro;

    public Moto(String chassi, String marca, String modelo, int ano, String cor, double preco, int cilindrada, String tipoPartida,boolean bagageiro){
        super(chassi, marca, modelo, ano, cor, preco);
        this.cilindrada = cilindrada;
        this.tipoPartida = tipoPartida;
        this.bagageiro = bagageiro;

    }

    public int getCilindrada(){return cilindrada;}
    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getTipoPartida(){return tipoPartida;}
    public void setTipoPartida(String tipoPartida) {
        this.tipoPartida = tipoPartida;
    }

    public boolean isBagageiro(){return bagageiro;}
    public void setBagageiro(boolean bagageiro){
        this.bagageiro = bagageiro;
    }

    @Override
    public String getTipo(){
        return "Moto";
    }

    @Override
    public String toString() {
        return super.toString() + " - " + cilindrada + "cc - " + tipoPartida +
                (bagageiro ? " - Com Bagageiro" : " - Sem Bagageiro");

    }
}