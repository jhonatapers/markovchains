package br.com.jhonatapers.queuesimulator;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.jhonatapers.queuesimulator.util.FiladePrioridadeMinima;
import br.com.jhonatapers.queuesimulator.util.RandomGL;

public class App {
    public static void main(String[] args) throws Exception {

        //CASO 1
        int EXECUCOES = 5;
        int CAPACIDADE = 5;
        int SERVIDORES = 1;
        float PRIMEIRO_EVENTO = 3;
        long QUANTIDADE_EXECUCOES = 15L;
        float TEMPO_MIN_CHEGADA = 2F;
        float TEMPO_MAX_CHEGADA = 4F;
        float TEMPO_MIN_SAIDA = 3F;
        float TEMPO_MAX_SAIDA = 5F;

        simularMedia(EXECUCOES, CAPACIDADE, SERVIDORES, PRIMEIRO_EVENTO, QUANTIDADE_EXECUCOES, TEMPO_MIN_CHEGADA, TEMPO_MAX_CHEGADA, TEMPO_MIN_SAIDA, TEMPO_MAX_SAIDA);

        //CASO 2
        EXECUCOES = 5;
        CAPACIDADE = 5;
        SERVIDORES = 2;
        PRIMEIRO_EVENTO = 3;
        QUANTIDADE_EXECUCOES = 15L;
        TEMPO_MIN_CHEGADA = 2F;
        TEMPO_MAX_CHEGADA = 4F;
        TEMPO_MIN_SAIDA = 3F;
        TEMPO_MAX_SAIDA = 5F;

        simularMedia(EXECUCOES, CAPACIDADE, SERVIDORES, PRIMEIRO_EVENTO, QUANTIDADE_EXECUCOES, TEMPO_MIN_CHEGADA, TEMPO_MAX_CHEGADA, TEMPO_MIN_SAIDA, TEMPO_MAX_SAIDA);

    }

    private static void simularMedia(int EXECUCOES, int CAPACIDADE, int SERVIDORES, float PRIMEIRO_EVENTO, long QUANTIDADE_EXECUCOES, float TEMPO_MIN_CHEGADA, float TEMPO_MAX_CHEGADA, float TEMPO_MIN_SAIDA, float TEMPO_MAX_SAIDA){

        RandomGL randomGL = new RandomGL();

        Sorteio sorteio = new Sorteio(randomGL, TEMPO_MIN_CHEGADA, TEMPO_MAX_CHEGADA, TEMPO_MIN_SAIDA, TEMPO_MAX_SAIDA);

        GeradorDeEventos geradorDeEventos = new GeradorDeEventos(sorteio);

        Escalonador escalonador;

        ArrayList<Simulador> simuladores = new ArrayList<Simulador>();

        for (int i = 0; i < EXECUCOES; i++) {
            Fila fila = new Fila(SERVIDORES, CAPACIDADE);
            sorteio.getRandom().novaSeed(System.nanoTime());
            escalonador = new Escalonador(new FiladePrioridadeMinima());
            Simulador simulador = new Simulador(fila, PRIMEIRO_EVENTO, escalonador, geradorDeEventos, QUANTIDADE_EXECUCOES);
            simulador.run();
            
            simuladores.add(simulador);
        }
        

        Float[] mediaEstados = new Float[CAPACIDADE+1];
        Float[] mediaPorcentagem = new Float[CAPACIDADE+1];
        Float mediaTempo = 0F;
        Long perdas = 0L;

        for (Simulador simulador : simuladores) {
            for (int i = 0; i < CAPACIDADE+1; i++) {
               mediaEstados[i] = mediaEstados[i] != null ? mediaEstados[i] : 0 + simulador.fila.getEstadosFila()[i]; 
            }
            perdas += simulador.fila.getPerdas();
            mediaTempo += simulador.tempoSimulacao;
        }

        mediaTempo = mediaTempo / simuladores.size();

        for (int i = 0; i < CAPACIDADE+1; i++) mediaPorcentagem[i] = (mediaEstados[i] / mediaTempo) * 100;

        System.out.println(String.format("/// ------------ [ G/G/%s/%s ] ------------ ///\n", SERVIDORES, CAPACIDADE));

        System.out.println(" ESTADO  |      TEMPO      |      PORCENTAGEM ");
        for (int i = 0; i < CAPACIDADE+1; i++) System.out.println(String.format("%s | %s | %s", alinharString(Integer.toString(i),8), alinharString(Float.toString(mediaEstados[i]),15), alinharString(Float.toString(mediaPorcentagem[i])+"%",20)));

        System.out.println(String.format("\nPerdas: %s\n", perdas));
    }

    public static String alinharString(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(inputString);
        while (sb.length() < length) {
            sb.append(' ');
        }
        
    
        return sb.toString();
    }

}
