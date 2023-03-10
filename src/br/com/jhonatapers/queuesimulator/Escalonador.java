package br.com.jhonatapers.queuesimulator;

import br.com.jhonatapers.queuesimulator.util.FiladePrioridadeMinima;

public class Escalonador {

    private FiladePrioridadeMinima filadePrioridadeMinima;

    public Escalonador(FiladePrioridadeMinima filadePrioridadeMinima) {
        this.filadePrioridadeMinima = filadePrioridadeMinima;
    }

    public Evento proximo() {
        return filadePrioridadeMinima.pool();
    }

    public void agenda(Evento evento) {
        filadePrioridadeMinima.add(evento);
    }

}
