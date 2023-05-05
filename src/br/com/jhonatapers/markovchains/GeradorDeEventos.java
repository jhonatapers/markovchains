package br.com.jhonatapers.markovchains;

import br.com.jhonatapers.markovchains.estado.Fila;
import br.com.jhonatapers.markovchains.evento.Entrada;
import br.com.jhonatapers.markovchains.evento.Passagem;
import br.com.jhonatapers.markovchains.evento.Saida;

public class GeradorDeEventos {

    private final Sorteio sorteio;

    public GeradorDeEventos(Sorteio sorteio) {
        this.sorteio = sorteio;
    }

    public Entrada novaEntrada(Float tempoAtual, Fila destino) throws Exception {
        return new Entrada(tempoAtual + sorteio.instante(destino.getIntervaloEntrada()), destino);
    }

    public Passagem novaPassagem(Float tempoAtual, Fila origem, Fila destino) throws Exception {
        return new Passagem(tempoAtual + sorteio.instante(origem.getIntervaloSaida()), origem, destino);
    }

    public Saida novaSaida(Float tempoAtual, Fila origem) throws Exception {
        return new Saida(tempoAtual + sorteio.instante(origem.getIntervaloSaida()), origem);
    }

}
