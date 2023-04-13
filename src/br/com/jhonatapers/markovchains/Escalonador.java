package br.com.jhonatapers.markovchains;

import br.com.jhonatapers.markovchains.util.FiladePrioridadeMinima;

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
