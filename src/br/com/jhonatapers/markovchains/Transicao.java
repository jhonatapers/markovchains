package br.com.jhonatapers.markovchains;

import java.util.Optional;

import br.com.jhonatapers.markovchains.estado.Fila;

public class Transicao implements Comparable<Transicao> {

    private final Float probabilidade;

    private final Optional<Fila> destino;

    public Transicao(Float probabilidade, Optional<Fila> destino) {
        this.probabilidade = probabilidade;
        this.destino = destino;
    }

    public Float getProbabilidade() {
        return probabilidade;
    }

    public Optional<Fila> getDestino() {
        return destino;
    }

    @Override
    public int compareTo(Transicao t) {
        return this.probabilidade.compareTo(t.probabilidade);
    }

}
