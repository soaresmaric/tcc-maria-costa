package com.tcc.coldstarts.tcc.controller;

import com.tcc.coldstarts.tcc.model.DataEntity;
import com.tcc.coldstarts.tcc.repository.DataRepository;
import com.tcc.coldstarts.tcc.service.HeavyLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

@RestController("/basepath")
public class HeavyLoadController {

    private static final Logger logger = LoggerFactory.getLogger(HeavyLoadController.class);

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private HeavyLoadService heavyLoadService;

    @GetMapping("/compute")
    public String compute() {
        logger.info("Endpoint /compute chamado.");
        long result = IntStream.range(1, 1_000_000)
                .mapToLong(i -> i)
                .reduce(0, Long::sum);
        logger.info("Resultado da computação: {}", result);
        return "Computation Result: " + result;
    }

    @GetMapping("/process")
    public String process() {
        logger.info("Endpoint /process chamado.");
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < 1_000; i++) {
            data.append("Processing data line ").append(i).append("\n");
        }
        logger.info("Tamanho dos dados processados: {}", data.length());
        return "Processed data size: " + data.length();
    }

    @GetMapping("/db")
    public String interactWithDb() {
        logger.info("Endpoint /db chamado. Interagindo com o banco de dados.");
        // Simular interação com banco de dados
        DataEntity dataEntity = new DataEntity("Sample data");
        dataRepository.save(dataEntity);

        Iterable<DataEntity> entities = dataRepository.findAll();
        long count = entities.spliterator().getExactSizeIfKnown();

        logger.info("Interação com banco de dados simulada. Número de registros: {}", count);
        return "Database interaction simulated, number of records: " + count;
    }

    @GetMapping("/heavyload")
    public String performHeavyLoad() {
        logger.info("Endpoint /heavyload chamado.");
        String result = heavyLoadService.performHeavyLoad();
        logger.info("Resultado da carga pesada: {}", result);
        return result;
    }
}
