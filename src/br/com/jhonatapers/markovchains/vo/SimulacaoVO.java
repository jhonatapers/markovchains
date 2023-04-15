package br.com.jhonatapers.markovchains.vo;

import java.util.Collection;

public record SimulacaoVO(Integer media_de,
                Long execucoes,
                Collection<FilaVO> filas) {

}
