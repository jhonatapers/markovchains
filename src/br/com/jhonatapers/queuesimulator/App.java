package br.com.jhonatapers.queuesimulator;

import java.util.concurrent.Semaphore;

import br.com.jhonatapers.queuesimulator.util.FiladePrioridadeMinima;
import br.com.jhonatapers.queuesimulator.util.RandomGL;

public class App {
    public static void main(String[] args) throws Exception {

        Semaphore mutex = new Semaphore(1);

        RandomGL randomGL = new RandomGL();

        Sorteio sorteio = new Sorteio(randomGL,
                1F,
                3F,
                4F,
                5F);

        GeradorDeEventos geradorDeEventos = new GeradorDeEventos(sorteio);

        Escalonador escalonador = new Escalonador(new FiladePrioridadeMinima());

        Fila fila = new Fila(1, 3);

        Simulador simulador = new Simulador(fila, 0F, escalonador, geradorDeEventos, mutex, 999L);

        simulador.run();

        mutex.acquire();

        Float check = 0F;

        for (Float estadoFila : fila.getEstadosFila()) {
            check += estadoFila;
        }

        System.out.println(check);
    }
}
