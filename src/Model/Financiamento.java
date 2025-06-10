package src.Model;

public abstract class Financiamento {

    private String id;
    private Venda venda;
    private double valorFinanciado;
    private int numeroParcelas;
    private double taxaJuros;
    private double valorParcela;
    private String banco;
    private String status;

    public Financiamento(String id, Venda venda, double valorFinanciado, int numeroParcelas, double taxaJuros, String banco) {
        this.id = id;
        this.venda = venda;
        this.valorFinanciado = valorFinanciado;
        this.numeroParcelas = numeroParcelas;
        this.taxaJuros = taxaJuros;
        this.banco = banco;
        this.status = "PENDENTE";
        calcularValorParcela();

    }

    private void calcularValorParcela() {
        double jurosDecimal = taxaJuros / 100;
        this.valorParcela = (valorFinanciado * jurosDecimal * Math.pow(1 + jurosDecimal, numeroParcelas)) /
                (Math.pow(1 + jurosDecimal, numeroParcelas) - 1);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }

    public double getValorFinanciado() { return valorFinanciado; }
    public void setValorFinanciado(double valorFinanciado) {
        this.valorFinanciado = valorFinanciado;
        calcularValorParcela();
    }

    public int getNumeroParcelas() { return numeroParcelas; }
    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
        calcularValorParcela();
    }

    public double getTaxaJuros() { return taxaJuros; }
    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
        calcularValorParcela();
    }

    public double getValorParcela() { return valorParcela; }

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Financiamento ID: " + id + " - Valor: R$ " + valorFinanciado +
                " - Parcelas: " + numeroParcelas + "x R$ " + String.format("%.2f", valorParcela) +
                " - Taxa: " + taxaJuros + "% - Status: " + status;
    }


}