package br.com.jhonatapers.queuesimulator.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RandomGL {

    private final Long a = 104729L;

    private final Long c = 11L;

    private final Long M = 2147483647L;

    private Long seed;

    private Queue<Float> preDefinidos;

    public RandomGL() {
        this.seed = System.nanoTime();
        this.preDefinidos = new LinkedList<Float>(Arrays.asList(
            0.0176F, 0.1103F, 0.2109F, 0.3456F, 0.4910F, 0.5323F, 0.6211F, 0.7322F, 0.8211F, 0.9131F, 0.1208F, 0.2172F,
            0.3922F, 0.4324F, 0.5011F, 0.6931F));
    }

    public RandomGL(Long seed) {
        this.seed = seed;
    }

    public void novaSeed(Long seed) {
        this.preDefinidos = new LinkedList<Float>(Arrays.asList(
            0.0176F, 0.1103F, 0.2109F, 0.3456F, 0.4910F, 0.5323F, 0.6211F, 0.7322F, 0.8211F, 0.9131F, 0.1208F, 0.2172F,
            0.3922F, 0.4324F, 0.5011F, 0.6931F));
        this.seed = seed;
    }

    public Float nextRandom() {
        return preDefinidos.poll();
        //return Float.parseFloat(random(seed).toString()) / Float.parseFloat(M.toString());
    }

    private Long random(Long seed) {

        this.seed = (a * seed + c) % M;
        return this.seed;
    }

}
