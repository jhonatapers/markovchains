package br.com.jhonatapers.queuesimulator;

public class Simulador {

    private Fila fila;

    private Float tempoSimulacao;

    private Escalonador escalonador;

    private GeradorDeEventos geradorDeEventos;

    private Long qtdSimulacoes;

    public Simulador(Fila fila, Float tempoSimulacao, Escalonador escalonador, GeradorDeEventos geradorDeEventos,
            Long qtdSimulacoes) {
        this.fila = fila;
        this.tempoSimulacao = tempoSimulacao;
        this.escalonador = escalonador;
        this.geradorDeEventos = geradorDeEventos;
        this.qtdSimulacoes = qtdSimulacoes;
    }

    private void chegada(Evento proximo) {
        contabilizaTempo(proximo);
        if (fila.getEstadoAtual() < fila.getK()) {
            fila.setEstadoAtual(fila.getEstadoAtual() + 1);
            if (fila.getEstadoAtual() <= fila.getC()) {
                escalonador.agenda(geradorDeEventos.novoEvento(TipoEvento.SAIDA, tempoSimulacao));
            }
        } else {
            fila.setPerdas(fila.getPerdas() + 1);
        }

        escalonador.agenda(geradorDeEventos.novoEvento(TipoEvento.ENTRADA, tempoSimulacao));
    }

    private void saida(Evento proximo) {
        contabilizaTempo(proximo);
        fila.setEstadoAtual(fila.getEstadoAtual() - 1);
        if (fila.getEstadoAtual() >= fila.getC()) {
            escalonador.agenda(geradorDeEventos.novoEvento(TipoEvento.SAIDA, tempoSimulacao));
        }
    }

    private void contabilizaTempo(Evento evento) {
        fila.getEstadosFila()[fila.getEstadoAtual()] += evento.getInstanteEvento() - tempoSimulacao;
        tempoSimulacao = evento.getInstanteEvento();
    }

    public void run() {

        Long count = 0L;

        escalonador.agenda(geradorDeEventos.novoEvento(TipoEvento.ENTRADA, tempoSimulacao));

        while (count < qtdSimulacoes) {

            Evento proximo = escalonador.proximo();

            if (proximo.getTipo().equals(TipoEvento.ENTRADA))
                chegada(proximo);
            else
                saida(proximo);

            count++;
        }

    }

}
