package br.com.jhonatapers.markovchains.evento;

import br.com.jhonatapers.markovchains.estado.Fila;

public class Passagem extends Evento {

    private final Fila origem;
    private final Fila destino;

    public Passagem(Float instanteEvento, Fila origem, Fila destino) {
        super(instanteEvento);
        this.origem = origem;
        this.destino = destino;
    }

    public Fila getOrigem() {
        return origem;
    }

    public Fila getDestino() {
        return destino;
    }

}
