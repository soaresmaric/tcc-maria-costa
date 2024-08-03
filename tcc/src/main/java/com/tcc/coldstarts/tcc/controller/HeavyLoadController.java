package com.tcc.coldstarts.tcc.controller;

import com.tcc.coldstarts.tcc.model.DataEntity;
import com.tcc.coldstarts.tcc.repository.DataRepository;
import com.tcc.coldstarts.tcc.service.HeavyLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
public class HeavyLoadController {

    @Autowired
    private DataRepository dataRepository;


    @GetMapping("/compute")
    public String compute() {
        long result = IntStream.range(1, 1_000_000)
                .mapToLong(i -> i)
                .reduce(0, Long::sum);
        return "Computation Result: " + result;
    }

    @GetMapping("/process")
    public String process() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < 1_000; i++) {
            data.append("Processing data line ").append(i).append("\n");
        }
        return "Processed data size: " + data.length();
    }

    @GetMapping("/db")
    public String interactWithDb() {
        // Simular interação com banco de dados
        DataEntity dataEntity = new DataEntity("Sample data");
        dataRepository.save(dataEntity);

        Iterable<DataEntity> entities = dataRepository.findAll();
        long count = entities.spliterator().getExactSizeIfKnown();

        return "Database interaction simulated, number of records: " + count;
    }
}
