package br.com.jhonatapers.markovchains;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        String fileName = "config.json";
        ConfigVO config = leitor.le(fileName);

        config.simulacoes()
                .stream()
                .forEach(simulacao -> {

                    Float tempoSimulacao = 0f;
                    Collection<Collection<Fila>> simulacoes = new ArrayList<Collection<Fila>>();

                    for (int i = 0; i < simulacao.qtdSimulacoes(); i++) {


                        Set<Fila> filas = simulacao.filas()
                                .stream()
                                .map(fila -> new Fila(fila.identificador(),
                                        fila.servidores(),
                                        (fila.capacidade() != null ? fila.capacidade() : Integer.MAX_VALUE),
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

                        Sorteio sorteio = new Sorteio(new RandomGL(simulacao.execucoes()));

                        Simulador simulador = new Simulador(filas,
                                entradas,
                                new Escalonador(new FiladePrioridadeMinima()),
                                sorteio,
                                new GeradorDeEventos(sorteio),
                                simulacao.execucoes());

                        try {
                            simulador.run();
                        } catch (Exception e) {
                        }

                        simulacoes.add(filas);
                        tempoSimulacao += simulador.getTempoSimulacao();
                    }

                    Map<String,Fila> mediaFilasSimulacoes = new HashMap<>();
                    for (Collection<Fila> listaDeFilas : simulacoes) {
                        for (Fila fila : listaDeFilas) {
                            if(mediaFilasSimulacoes.containsKey(fila.getIdentificador())){

                                Fila novaFila = mediaFilasSimulacoes.get(fila.getIdentificador());

                                Map<Integer,Float> novosEstadosFila = new HashMap<Integer,Float>();

                                for (int i = 0; i < novaFila.getEstadosFila().size(); i++) {
                                    novosEstadosFila.put(i, novaFila.getEstadosFila().get(i) + fila.getEstadosFila().get(i));
                                }

                                novaFila.setEstadosFila(novosEstadosFila);
                                novaFila.setPerdas(novaFila.getPerdas() + fila.getPerdas());

                                mediaFilasSimulacoes.put(novaFila.getIdentificador(), novaFila);
                            }
                            else {
                                    mediaFilasSimulacoes.put(fila.getIdentificador(), fila);
                            }
                                
                        }
                    }

                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.DOWN); 
                    for (Fila fila : mediaFilasSimulacoes.values()) {

                        System.out.println(String.format("\n/// ------------ [ %s ] ------------ ///\n", fila.getIdentificador()));

                        System.out.println(" ESTADO  |      TEMPO      |     PORCENTAGEM ");

                        for (int i = 0; i < fila.getEstadosFila().size(); i++){
                            System.out.println(String.format("%s | %s | %s",
                                    alinharString(Integer.toString(i),8),
                                    alinharString(Float.toString(fila.getEstadosFila().get(i)/simulacao.qtdSimulacoes()),15),
                                    alinharString(df.format((fila.getEstadosFila().get(i)/simulacao.qtdSimulacoes()) / (tempoSimulacao / simulacao.qtdSimulacoes()) * 100)+"%",20)
                            ));
                        }
                        System.out.println(String.format("\nPERDAS: %s", fila.getPerdas()/simulacao.qtdSimulacoes()));
                    }

                        System.out.println(String.format("\nTEMPO DE SIMULAÇÃO: %s\n", tempoSimulacao / simulacao.qtdSimulacoes()));
                }
        );

    }

    private static String alinharString(String s, int size) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(' ');
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(' ');
        }
        return sb.toString();
    }
}