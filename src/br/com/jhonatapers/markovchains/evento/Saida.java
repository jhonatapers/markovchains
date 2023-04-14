package br.com.jhonatapers.markovchains.evento;

import br.com.jhonatapers.markovchains.estado.Fila;

public class Saida extends Evento {

    private final Fila origem;

    public Saida(Float instante, Fila origem) {
        super(instante);
        this.origem = origem;
    }

    public Fila getOrigem() {
        return origem;
    }

}
