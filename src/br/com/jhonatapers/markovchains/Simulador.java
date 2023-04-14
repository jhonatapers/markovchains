package br.com.jhonatapers.markovchains;

import java.util.Collection;

import br.com.jhonatapers.markovchains.estado.Fila;
import br.com.jhonatapers.markovchains.evento.Entrada;
import br.com.jhonatapers.markovchains.evento.Evento;
import br.com.jhonatapers.markovchains.evento.Passagem;
import br.com.jhonatapers.markovchains.evento.Saida;

public class Simulador {

    public final Collection<Fila> filas;
    private final Collection<Entrada> entradasIniciais;

    private final Escalonador escalonador;
    private final Sorteio sorteio;
    private final GeradorDeEventos geradorDeEventos;
    private final Long qtdSimulacoes;

    public Float tempoSimulacao;

    public Simulador(Collection<Fila> filas,
            Collection<Entrada> entradasIniciais,
            Escalonador escalonador,
            Sorteio sorteio,
            GeradorDeEventos geradorDeEventos,
            Long qtdSimulacoes) {

        this.filas = filas;
        this.entradasIniciais = entradasIniciais;
        this.escalonador = escalonador;
        this.sorteio = sorteio;
        this.geradorDeEventos = geradorDeEventos;
        this.qtdSimulacoes = qtdSimulacoes;

        this.tempoSimulacao = 0F;
    }

    private void entrada(Entrada entrada) {

        contabilizaTempo(filas, entrada.getInstanteEvento());

        if (entrada.getDestino().getEstadoAtual() < entrada.getDestino().getK()) {

            entrada.getDestino().estadoAtualMaisUm();

            if (entrada.getDestino().getEstadoAtual() <= entrada.getDestino().getC())
                agendaSaidaOuPassagem(entrada.getDestino());

        } else {
            entrada.getDestino().perdaMaisUm();
        }

        escalonador.agenda(geradorDeEventos.novaEntrada(tempoSimulacao, entrada.getDestino()));
    }

    private void passagem(Passagem passagem) {

        contabilizaTempo(filas, passagem.getInstanteEvento());

        passagem.getOrigem().estadoAtualMenosUm();
        if (passagem.getOrigem().getEstadoAtual() >= passagem.getOrigem().getK()) {
            agendaSaidaOuPassagem(passagem.getOrigem());
        }

        passagem.getDestino().estadoAtualMaisUm();
        if (passagem.getDestino().getEstadoAtual() <= passagem.getDestino().getK()) {
            passagem.getDestino().estadoAtualMaisUm();
            if (passagem.getDestino().getEstadoAtual() <= passagem.getDestino().getC())
                agendaSaidaOuPassagem(passagem.getDestino());
        } else {
            passagem.getDestino().perdaMaisUm();
        }

    }

    private void saida(Saida saida) {
        contabilizaTempo(filas, saida.getInstanteEvento());

        saida.getOrigem().estadoAtualMenosUm();

        if (saida.getOrigem().getEstadoAtual() >= saida.getOrigem().getC()) {
            agendaSaidaOuPassagem(saida.getOrigem());
            escalonador.agenda(geradorDeEventos.novaSaida(tempoSimulacao, saida.getOrigem()));
        }
    }

    private void agendaSaidaOuPassagem(Fila fila) {
        sorteio.proximaFila(fila.getTransicoes())
                .ifPresentOrElse(
                        (destino) -> {
                            geradorDeEventos.novaPassagem(tempoSimulacao, fila, destino);
                        },
                        () -> {
                            geradorDeEventos.novaSaida(tempoSimulacao, fila);
                        });
    }

    private void contabilizaTempo(Collection<Fila> filas, Float instanteEvento) {

        filas
                .stream()
                .forEach(fila -> contabilizaEstadosFila(fila, instanteEvento));

        this.tempoSimulacao = instanteEvento;
    }

    private void contabilizaEstadosFila(Fila fila, float instanteEvento) {
        fila.getEstadosFila()[fila.getEstadoAtual()] += instanteEvento - tempoSimulacao;
    }

    public void run() {

        Long count = 0L;

        entradasIniciais
                .forEach(entradaInicial -> escalonador.agenda(entradaInicial));

        while (count < qtdSimulacoes) {

            Evento proximo = escalonador.proximo();

            if (proximo.getClass().equals(Entrada.class))
                entrada((Entrada) proximo);
            if (proximo.getClass().equals(Saida.class))
                saida((Saida) proximo);
            if (proximo.getClass().equals(Passagem.class))
                passagem((Passagem) proximo);

            count++;
        }

    }

}
