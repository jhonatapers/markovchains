package br.com.jhonatapers.markovchains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.jhonatapers.markovchains.estado.Fila;
import br.com.jhonatapers.markovchains.evento.Entrada;
import br.com.jhonatapers.markovchains.util.FiladePrioridadeMinima;
import br.com.jhonatapers.markovchains.util.LeitorConfiguracao;
import br.com.jhonatapers.markovchains.util.RandomGL;
import br.com.jhonatapers.markovchains.vo.ConfigVO;

public class App {
    public static void main(String[] args) throws Exception {

        LeitorConfiguracao leitor = new LeitorConfiguracao();

        String fileName = "D:\\Local Workspace\\PUCRS\\Simulacao de metodos analiticos\\markovchains\\exemplo.json";
        ConfigVO config = leitor.le(fileName);

        config.simulacoes()
                .stream()
                .forEach(simulacao -> {

                    Float tempoSimulacao = 0f;
                    Collection<Collection<Fila>> listaDeListasDeFilas = new ArrayList<Collection<Fila>>();

                    for (int i = 0; i < simulacao.media_de(); i++) {

                        Set<Fila> filas = simulacao.filas()
                                .stream()
                                .map(fila -> new Fila(fila.identificador(),
                                        fila.servidores(),
                                        fila.capacidade(),
                                        fila.saida(),
                                        fila.entrada()))
                                .collect(Collectors.toSet());

                        filas.stream()
                                .forEach(fila -> {

                                    List<Transicao> transicoes = simulacao.filas()
                                            .stream()
                                            .filter(f -> f.identificador().equals(fila.getIdentificador()))
                                            .findFirst()
                                            .get()
                                            .transicoes()
                                            .stream()
                                            .map(transicao -> new Transicao(transicao.probabilidade(), filas
                                                    .stream()
                                                    .filter(f -> f.getIdentificador().equals(transicao.destino()))
                                                    .findFirst()))
                                            .collect(Collectors.toList());

                                    fila.setTransicoes(transicoes);
                                });

                        Collection<Entrada> entradas = simulacao.filas()
                                .stream()
                                .filter(fila -> fila.entrada() != null)
                                .map(entrada -> new Entrada(entrada.primeira_entrada(),
                                        filas.stream().filter(
                                                fila -> fila.getIdentificador().equals(entrada.identificador()))
                                                .findFirst().get()))
                                .collect(Collectors.toList());

                        Sorteio sorteio = new Sorteio(new RandomGL());

                        Simulador simulador = new Simulador(filas,
                                entradas,
                                new Escalonador(new FiladePrioridadeMinima()),
                                sorteio,
                                new GeradorDeEventos(sorteio),
                                simulacao.execucoes());

                        simulador.run();

                        listaDeListasDeFilas.add(filas);
                        tempoSimulacao += simulador.getTempoSimulacao();
                    }

                    tempoSimulacao = 0f;
                    listaDeListasDeFilas
                            .stream()
                            .forEach(listaDeFilas -> {
                                listaDeFilas
                                        .stream()
                                        .forEach(fila -> {
                                            System.out.println("--------------");
                                            System.out.println(fila.getIdentificador());
                                            System.out.println("--------------");

                                            System.out.println(
                                                    String.format("Perdas: %s", String.valueOf(fila.getPerdas())));

                                            for (int i = 0; i < fila.getEstadosFila().length; i++) {
                                                System.out.println(String.format("Estado %s -> %s min.", i,
                                                        String.valueOf(fila.getEstadosFila()[i])));
                                            }

                                        });
                            });

                });

    }
}