package br.com.jhonatapers.markovchains.evento;

public abstract class Evento {

    private final Float instanteEvento;

    private Float duracaoEvento;

    public Evento(Float instanteEvento) {
        this.instanteEvento = instanteEvento;
    }

    public Float getInstanteEvento() {
        return instanteEvento;
    }

    public Float getDuracaoEvento() {
        return duracaoEvento;
    }

    public void setDuracaoEvento(Float duracaoEvento) {
        this.duracaoEvento = duracaoEvento;
    }

}
