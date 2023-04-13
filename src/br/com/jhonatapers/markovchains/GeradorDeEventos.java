package br.com.jhonatapers.markovchains;

public class GeradorDeEventos {

    private Sorteio sorteio;

    public GeradorDeEventos(Sorteio sorteio) {
        this.sorteio = sorteio;
    }

    public Evento novoEvento(TipoEvento tipo, Float tempoAtual) {
        return new Evento(tipo, tempoAtual + sorteio.sorteia(tipo));
    }

}
