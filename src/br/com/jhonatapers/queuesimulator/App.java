package br.com.jhonatapers.queuesimulator;

import br.com.jhonatapers.queuesimulator.util.FiladePrioridadeMinima;
import br.com.jhonatapers.queuesimulator.util.RandomGL;

public class App {
    public static void main(String[] args) throws Exception {

        RandomGL randomGL = new RandomGL();

        Sorteio sorteio = new Sorteio(randomGL,
                2F,
                4F,
                3F,
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

        Float[] mediaEstados0 = new Float[6];

        for (int i = 0; i < mediaEstados0.length; i++)
            mediaEstados0[i] = (fila0_0.getEstadosFila()[i] + fila0_1.getEstadosFila()[i] + fila0_2.getEstadosFila()[i]
                    + fila0_3.getEstadosFila()[i] + fila0_4.getEstadosFila()[i]) / 5;

        Float tempoSimulacao0 = 0F;

        for (Float media : mediaEstados0)
            tempoSimulacao0 += media;

        Float[] porcentagemMediaEstados0 = new Float[6];

        for (int i = 0; i < mediaEstados0.length; i++)
            porcentagemMediaEstados0[i] = mediaEstados0[i] / tempoSimulacao0;

        System.out.println("/// *Q(G/G/1/5)* ///");

        for (int i = 0; i < mediaEstados0.length; i++)
            System.out.println(String.format("Estado: %s;Tempo: %s;Porcentagem: %s%s", i, mediaEstados0[i],
                    porcentagemMediaEstados0[i] * 100, "%"));

        Long perdas0 = (fila0_0.getPerdas() + fila0_1.getPerdas() + fila0_2.getPerdas()
                + fila0_3.getPerdas() + fila0_4.getPerdas()) / 5;

        System.out.println(String.format("Perdas: %s", perdas0));

        System.out.println("/// **************************************** ///");
        System.out.println("/// **************************************** ///");
        System.out.println("/// **************************************** ///");
        System.out.println("/// **************************************** ///");
        System.out.println("/// **************************************** ///");
        System.out.println("/// **************************************** ///");
        System.out.println("/// **************************************** ///");

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

        Float[] mediaEstados1 = new Float[6];

        for (int i = 0; i < mediaEstados1.length; i++)
            mediaEstados1[i] = (fila1_0.getEstadosFila()[i] + fila1_1.getEstadosFila()[i] + fila1_2.getEstadosFila()[i]
                    + fila1_3.getEstadosFila()[i] + fila1_4.getEstadosFila()[i]) / 5;

        Float tempoSimulacao1 = 0F;

        for (Float media : mediaEstados1)
            tempoSimulacao1 += media;

        Float[] porcentagemmediaEstados1 = new Float[6];

        for (int i = 0; i < mediaEstados1.length; i++)
            porcentagemmediaEstados1[i] = mediaEstados1[i] / tempoSimulacao1;

        System.out.println("/// *Q(G/G/2/5)* ///");

        for (int i = 0; i < mediaEstados1.length; i++)
            System.out.println(String.format("Estado: %s;Tempo: %s;Porcentagem: %s%s", i, mediaEstados1[i],
                    porcentagemmediaEstados1[i] * 100, "%"));

        Long perdas1 = (fila1_0.getPerdas() + fila1_1.getPerdas() + fila1_2.getPerdas()
                + fila1_3.getPerdas() + fila1_4.getPerdas()) / 5;

        System.out.println(String.format("Perdas: %s", perdas1));

    }
}
