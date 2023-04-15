package br.com.jhonatapers.markovchains.util;

public class RandomGL {

    private final Long a = 104729L;

    private final Long c = 11L;

    private final Long M = 2147483647L;

    private Long seed;

    public RandomGL() {
        this.seed = System.nanoTime();
    }

    public RandomGL(Long seed) {
        this.seed = seed;
    }

    public void novaSeed(Long seed) {
        this.seed = seed;
    }

    public Float nextRandom() {
        return Float.parseFloat(random(seed).toString()) / Float.parseFloat(M.toString()) * 1;
    }

    private Long random(Long seed) {
        this.seed = (a * seed + c) % M;
        return this.seed;
    }

}
