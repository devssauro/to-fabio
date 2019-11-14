package com.fabio.models;

import java.util.List;

public class Obra {
    private String descricao;
    private List<EtapaObra> etapas;

    public float total() {
        float _total = 0;
        for (EtapaObra etapa: etapas) {
            _total += etapa.total();
        }
        return _total;
    }
}
