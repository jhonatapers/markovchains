package br.com.jhonatapers.queuesimulator;

import java.util.concurrent.Semaphore;

import br.com.jhonatapers.queuesimulator.util.FiladePrioridadeMinima;
import br.com.jhonatapers.queuesimulator.util.RandomGL;

public class App {
    public static void main(String[] args) throws Exception {

        RandomGL randomGL = new RandomGL();

        Sorteio sorteio = new Sorteio(randomGL,
                1F,
                3F,
                4F,
                5F);

        GeradorDeEventos geradorDeEventos = new GeradorDeEventos(sorteio);

        Escalonador escalonador;

        Fila fila0_0 = new Fila(1, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador0_0 = new Simulador(fila0_0, 3F, escalonador, geradorDeEventos, 100000L);
        simulador0_0.run();
        Fila fila0_1 = new Fila(1, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador0_1 = new Simulador(fila0_1, 3F, escalonador, geradorDeEventos, 100000L);
        simulador0_1.run();
        Fila fila0_2 = new Fila(1, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador0_2 = new Simulador(fila0_2, 3F, escalonador, geradorDeEventos, 100000L);
        simulador0_2.run();
        Fila fila0_3 = new Fila(1, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador0_3 = new Simulador(fila0_3, 3F, escalonador, geradorDeEventos, 100000L);
        simulador0_3.run();
        Fila fila0_4 = new Fila(1, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador0_4 = new Simulador(fila0_4, 3F, escalonador, geradorDeEventos, 100000L);
        simulador0_4.run();

        Fila fila1_0 = new Fila(2, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador1_0 = new Simulador(fila1_0, 3F, escalonador, geradorDeEventos, 100000L);
        simulador1_0.run();
        Fila fila1_1 = new Fila(2, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador1_1 = new Simulador(fila1_1, 3F, escalonador, geradorDeEventos, 100000L);
        simulador1_1.run();
        Fila fila1_2 = new Fila(2, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador1_2 = new Simulador(fila1_2, 3F, escalonador, geradorDeEventos, 100000L);
        simulador1_2.run();
        Fila fila1_3 = new Fila(2, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador1_3 = new Simulador(fila1_3, 3F, escalonador, geradorDeEventos, 100000L);
        simulador1_3.run();
        Fila fila1_4 = new Fila(2, 5);
        sorteio.getRandom().novaSeed(System.nanoTime());
        escalonador = new Escalonador(new FiladePrioridadeMinima());
        Simulador simulador1_4 = new Simulador(fila1_4, 3F, escalonador, geradorDeEventos, 100000L);
        simulador1_4.run();

        System.out.println("aham");
    }
}
