package br.com.jhonatapers.markovchains;

import java.util.List;
import java.util.Optional;

import br.com.jhonatapers.markovchains.estado.Fila;
import br.com.jhonatapers.markovchains.util.RandomGL;
import br.com.jhonatapers.markovchains.vo.IntervaloVO;

public class Sorteio {

    private RandomGL random;

    public Sorteio(RandomGL random) {
        this.random = random;
    }

    public Optional<Fila> proximaFila(List<Transicao> transicoes) {

        Float valorRandom = random.nextRandom();
        Float rangePorcentagem = 0F;

        for (Transicao transicao : transicoes) {
            rangePorcentagem += transicao.getProbabilidade();
            if (valorRandom <= rangePorcentagem)
                return transicao.getDestino();
        }

        return Optional.empty();
    }

    public Float instante(IntervaloVO intervalo) {
        return converte(intervalo.minimo(), intervalo.maximo(), random.nextRandom());
    }

    // U(A, B) = (B – A) x U(0, 1) + A
    private Float converte(Float a, Float b, Float r) {
        return ((b - a) * r) + a;
    }

}
