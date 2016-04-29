package br.edu.ite.financeiroandroid.adapter.dto;

import java.util.HashMap;
import java.util.Map;

public class ItemAdapterDTO {

    private Map<String, String> valores = new HashMap<>();

    public Map<String, String> getValores() {
        return valores;
    }

    public void setValores(Map<String, String> valores) {
        this.valores = valores;
    }
}
