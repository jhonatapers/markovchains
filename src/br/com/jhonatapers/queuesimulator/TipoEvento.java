package br.com.jhonatapers.queuesimulator;

public enum TipoEvento {
    ENTRADA(0),
    SAIDA(1);

    private int tipo;

    private TipoEvento(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
}
