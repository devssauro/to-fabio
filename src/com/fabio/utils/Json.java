package com.fabio.utils;

import com.fabio.models.Composicao;
import com.fabio.models.Elemento;
import com.fabio.models.Insumo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Json {
    private List<ComposicaoBase> composicoesBase = new ArrayList<>();
    private List<Composicao> composicoes = new ArrayList<>();
    public List<ComposicaoBase> toObject(JSONArray jArray) {
        List<Object> _jArray = jArray.toList();
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jObj = jArray.getJSONObject(i);
            composicoesBase.add(
                new ComposicaoBase(
                    jObj.getInt("codigoComposicao"),
                    jObj.getString("descricaoComposicao")
                )
            );
        }

        return composicoesBase;
    }

    public List<Composicao> getElementos(JSONArray jArray) {
        List<Elemento> elementos = new ArrayList<>();
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jObj = jArray.getJSONObject(i);
            int compIdx = verificaComposicoes(jObj.getInt("codigoComposicao"));
            if (compIdx < 0) {
                composicoes.add(new Composicao(
                        jObj.getInt("codigoComposicao"),
                        jObj.getString("descricaoComposicao"),
                        parseBRLFloat(jObj.getString("quantidadeComposicao")),
                        jObj.getString("unidadeItem"),
                        new ArrayList<>()
                ));
                compIdx = composicoes.size()-1;
            }
            composicoes.get(compIdx).addElemento(getElemento(jObj));
        }
        return composicoes;
    }

    private int verificaComposicoes(int id) {
        for (Composicao composicao : composicoes)
            if (id == composicao.getId()) return composicoes.indexOf(composicao);
        return -1;
    }

    private Elemento getElemento(JSONObject jObj) {
        if (jObj.getString("tipoItem").equals("INSUMO")) {
            return new Insumo(
                jObj.getInt("codigoItem"),
                jObj.getString("descricaoItemComposicao"),
                parseBRLFloat(jObj.getString("quantidadeComposicao")),
                jObj.getString("unidadeItem"),
                parseBRLFloat(jObj.getString("valorUnitario"))
            );
        }
        if (jObj.getString("tipoItem").equals("COMPOSICAO")) {
            return new Composicao(
                    jObj.getInt("codigoComposicao"),
                    jObj.getString("descricaoComposicao"),
                    parseBRLFloat(jObj.getString("quantidadeComposicao")),
                    jObj.getString("unidadeItem"),
                    new ArrayList<>()
            );
        }
        return null;
    }

    private float parseBRLFloat(String preco) {
        String[] partes = preco.split(",");
        return  partes[0] == null ? 0 : Float.parseFloat(String.format("%s.%s", partes));
//        return Float.parseFloat(String.format("%s.%s"));
    }

    private class ComposicaoBase {
        public int id;
        public String descricao;

        public ComposicaoBase(int _id, String _descricao) {
            this.id = _id;
            this.descricao = _descricao;
        }

        @Override
        public String toString() {
            return String.format("%s, %s\n", id, descricao);
        }
    }
}
