package br.com.jhonatapers.markovchains.evento;

public abstract class Evento {

    private final Float instanteEvento;

    public Evento(Float instanteEvento) {
        this.instanteEvento = instanteEvento;
    }

    public Float getInstanteEvento() {
        return instanteEvento;
    }

}
