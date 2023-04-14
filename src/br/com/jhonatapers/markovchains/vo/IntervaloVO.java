package br.com.jhonatapers.markovchains.vo;

public class IntervaloVO {

    private final Float minimo;

    private final Float maximo;

    public IntervaloVO(Float minimo, Float maximo) {
        this.minimo = minimo;
        this.maximo = maximo;
    }

    public Float getMinimo() {
        return minimo;
    }

    public Float getMaximo() {
        return maximo;
    }

}
