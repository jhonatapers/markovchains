package br.com.jhonatapers.queuesimulator;

public class Fila {

    private int c; // servidores
    private int k; // capacidade

    private int estadoAtual = 0;

    private Long perdas = 0L;

    private Float[] estadosFila;

    public Fila(int c, int k) {
        this.c = c;
        this.k = k;
        this.estadosFila = new Float[k + 1];
        for (int i = 0; i < k + 1; i++)
            this.estadosFila[i] = 0F;
    }

    public int getC() {
        return c;
    }

    public int getK() {
        return k;
    }

    public int getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(int estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public Float[] getEstadosFila() {
        return estadosFila;
    }

    public void setPerdas(Long perdas) {
        this.perdas = perdas;
    }

    public Long getPerdas() {
        return perdas;
    }

}
