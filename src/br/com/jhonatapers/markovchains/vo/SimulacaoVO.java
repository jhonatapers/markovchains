package br.com.jhonatapers.markovchains.vo;

import java.util.Collection;

public record SimulacaoVO(Integer qtdSimulacoes,
                Long execucoes,
                Collection<FilaVO> filas) {

}
