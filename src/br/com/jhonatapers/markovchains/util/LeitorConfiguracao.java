package br.com.jhonatapers.markovchains.util;

import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhonatapers.markovchains.vo.ConfigVO;

public class LeitorConfiguracao {

    private final ObjectMapper mapper;

    public LeitorConfiguracao() {
        this.mapper = new ObjectMapper();
    }

    public ConfigVO le(String fileName) throws Exception {
        try {

            return mapper.readValue(new String(Files.readAllBytes(Path.of(fileName))), ConfigVO.class);

        } catch (Exception e) {
            throw e;
        }
    }

}
