package br.com.jhonatapers.queuesimulator;

public class Evento {

    private TipoEvento tipo;

    private Float instanteEvento;

    private Float duracaoEvento;

    public Evento(TipoEvento tipo, Float instanteEvento) {
        this.tipo = tipo;
        this.instanteEvento = instanteEvento;
    }

    public TipoEvento getTipo() {
        return tipo;
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
