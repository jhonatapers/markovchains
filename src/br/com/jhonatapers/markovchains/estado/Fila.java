package br.com.jhonatapers.markovchains.estado;

import java.util.List;

import br.com.jhonatapers.markovchains.Transicao;
import br.com.jhonatapers.markovchains.vo.IntervaloVO;

public class Fila {

    private final String identificador;
    private final int c; // servidores
    private final int k; // capacidade
    private final IntervaloVO intervaloEntrada;
    private final IntervaloVO intervaloSaida;
    private List<Transicao> transicoes;

    private int estadoAtual = 0;
    private Long perdas = 0L;
    private Float[] estadosFila;

    public Fila(String identificador,
            int c,
            int k,
            IntervaloVO saida,
            IntervaloVO entrada) {

        this.identificador = identificador;
        this.c = c;
        this.k = k;
        this.estadosFila = new Float[k + 1];
        for (int i = 0; i < k + 1; i++)
            this.estadosFila[i] = 0F;

        this.intervaloEntrada = entrada;
        this.intervaloSaida = saida;
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getC() {
        return c;
    }

    public int getK() {
        return k;
    }

    public IntervaloVO getIntervaloEntrada() {
        return intervaloEntrada;
    }

    public IntervaloVO getIntervaloSaida() {
        return intervaloSaida;
    }

    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(List<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public int getEstadoAtual() {
        return estadoAtual;
    }

    public void estadoAtualMenosUm() {
        this.estadoAtual--;
    }

    public void estadoAtualMaisUm() {
        this.estadoAtual++;
    }

    public Float[] getEstadosFila() {
        return estadosFila;
    }

    public void setEstadosFila(Float[] estadosFila) {
        this.estadosFila = estadosFila;
    }

    public void perdaMaisUm() {
        this.perdas++;
    }

    public Long getPerdas() {
        return perdas;
    }

    public void setPerdas(Long perdas){
        this.perdas = perdas;
    }

}
