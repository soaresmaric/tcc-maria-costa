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
import java.util.ArrayList;
import java.util.List;

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

        // Aumentando o uso de CPU com uma operação matemática mais complexa
        long result = IntStream.range(1, 10_000_000)
                .parallel()
                .mapToLong(i -> i * (i - 1) / (i + 1)) // Operação mais pesada
                .reduce(0, Long::sum);

        logger.info("Resultado da computação: {}", result);
        return "Computation Result: " + result;
    }

    @GetMapping("/process")
    public String process() {
        logger.info("Endpoint /process chamado.");

        // Aumentando o uso de memória com uma lista grande
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            data.add("Processing data line " + i + "\n");
        }

        logger.info("Tamanho dos dados processados: {}", data.size());
        return "Processed data size: " + data.size();
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

        // Realizando uma operação de carga pesada, que usa mais CPU
        String result = heavyLoadService.performHeavyLoad();

        // Adicionando uma operação de consumo de memória
        List<double[]> memoryConsumingList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            memoryConsumingList.add(new double[1_000_000]); // Aloca um array grande
        }

        logger.info("Resultado da carga pesada: {}", result);
        return result;
    }
    @GetMapping("/memory-leak")
    public String memoryLeak() {
        List<int[]> memoryHog = new ArrayList<>();
        while (true) {
            memoryHog.add(new int[1000000]); // Aloca 1 milhão de inteiros a cada iteração
        }
    }

}
