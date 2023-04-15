package br.com.jhonatapers.markovchains.vo;

import java.util.Collection;

public record FilaVO(String identificador,
        Integer servidores,
        Integer capacidade,
        IntervaloVO saida,
        IntervaloVO entrada,
        Collection<TransicaoVO> transicoes) {

}
