package br.com.jhonatapers.markovchains;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<Float, List<Transicao>> gruposDeProbabilidade = new HashMap<Float, List<Transicao>>();

        for (Transicao transicao : transicoes)
            if (gruposDeProbabilidade.containsKey(transicao.getProbabilidade()))
                gruposDeProbabilidade.get(transicao.getProbabilidade()).add(transicao);
            else
                gruposDeProbabilidade.put(transicao.getProbabilidade(), transicoes);

        // falta descobrir qual probabilidade vai acontecer
        Collections.sort(transicoes);

        // 0.2
        // 0.2
        // 0.6

        return Optional.empty();

    }

    public Float instante(IntervaloVO intervalo) {
        return converte(intervalo.getMinimo(), intervalo.getMaximo(), random.nextRandom());
    }

    // U(A, B) = (B – A) x U(0, 1) + A
    private Float converte(Float a, Float b, Float r) {
        return ((b - a) * r) + a;
    }

}
