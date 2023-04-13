package br.com.jhonatapers.markovchains;

import br.com.jhonatapers.markovchains.util.RandomGL;

public class Sorteio {

    private RandomGL random;

    private Float tempoChegadaMinima;

    private Float tempoChegadaMaxima;

    private Float tempoSaidaMinima;

    private Float tempoSaidaMaxima;

    public Sorteio(RandomGL random, Float tempoChegadaMinima, Float tempoChegadaMaxima, Float tempoSaidaMinima,
            Float tempoSaidaMaxima) {
        this.random = random;
        this.tempoChegadaMinima = tempoChegadaMinima;
        this.tempoChegadaMaxima = tempoChegadaMaxima;
        this.tempoSaidaMinima = tempoSaidaMinima;
        this.tempoSaidaMaxima = tempoSaidaMaxima;
    }

    public Float sorteia(TipoEvento tipo) {
        if (tipo.equals(TipoEvento.ENTRADA))
            return converte(tempoChegadaMinima, tempoChegadaMaxima);
        else
            return converte(tempoSaidaMinima, tempoSaidaMaxima);
    }

    // U(A, B) = (B – A) x U(0, 1) + A
    private Float converte(Float a, Float b) {
        return ((b - a) * random.nextRandom()) + a;
    }

    public RandomGL getRandom() {
        return random;
    }
}
