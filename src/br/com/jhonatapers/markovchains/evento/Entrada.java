package br.com.jhonatapers.markovchains.evento;

import br.com.jhonatapers.markovchains.estado.Fila;

public class Entrada extends Evento {

    private final Fila destino;

    public Entrada(Float instante, Fila destino) {
        super(instante);
        this.destino = destino;
    }

    public Fila getDestino() {
        return destino;
    }

}
