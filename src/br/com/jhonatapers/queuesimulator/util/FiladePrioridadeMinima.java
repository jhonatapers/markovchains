package br.com.jhonatapers.queuesimulator.util;

import java.util.Comparator;
import java.util.PriorityQueue;

import br.com.jhonatapers.queuesimulator.Evento;

public class FiladePrioridadeMinima {

    private PriorityQueue<Evento> minHeap = new PriorityQueue<>(new Comparator<Evento>() {
        @Override
        public int compare(Evento o1, Evento o2) {
            if (o1.getInstanteEvento() > (o2.getInstanteEvento()))
                return +1;
            if (o1.equals(o2))
                return 0;
            return -1;
        }
    });

    public Evento pool() {
        return minHeap.poll();
    }

    public void add(Evento evento) {
        minHeap.add(evento);
    }

}
