package src.Controller;

import src.Model.Financiamento;
import src.Model.Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FinanceiroController {
    private List<Financiamento> financiamentos;

    public FinanceiroController() {
        this.financiamentos = new ArrayList<>();
    }

    public String solicitarFinanciamento(Venda venda, double valorFinanciado, int numeroParcelas, double taxaJuros, String banco) {
        String id = UUID.randomUUID().toString();
        Financiamento financiamento = new Financiamento(id, venda, valorFinanciado,numeroParcelas,taxaJuros,banco) {
        };
        financiamentos.add(financiamento);
        venda.setFinanciamento(financiamento);

        return id;
    }

    public boolean aprovarFinanciamento(String id) {
        Financiamento financiamento = buscarPorId(id);
        if (financiamento != null && financiamento.getStatus().equals("PENDENTE")) {
            financiamento.setStatus("APROVADO");
            return true;
        }
        return false;
    }

    public boolean reprovarFinanciamento(String id) {
        Financiamento financiamento = buscarPorId(id);
        if (financiamento != null && financiamento.getStatus().equals("PENDENTE")) {
            financiamento.setStatus("REPROVADO");
            return true;
        }
        return false;
    }

    public Financiamento buscarPorId(String id) {
        return financiamentos.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Financiamento> listarTodos() {
        return new ArrayList<>(financiamentos);
    }

    public List<Financiamento> buscarPorStatus(String status) {
        return financiamentos.stream()
                .filter(f -> f.getStatus().equals(status))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public double calcularTotalFinanciado() {
        return financiamentos.stream()
                .filter(f -> f.getStatus().equals("APROVADO"))
                .mapToDouble(Financiamento::getValorFinanciado)
                .sum();
    }
}
