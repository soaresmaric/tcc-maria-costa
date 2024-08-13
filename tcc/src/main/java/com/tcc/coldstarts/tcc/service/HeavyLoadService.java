package com.tcc.coldstarts.tcc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HeavyLoadService {

    private static final Logger logger = LoggerFactory.getLogger(HeavyLoadService.class);

    public String performHeavyLoad() {
        logger.info("Iniciando a execução da carga pesada.");

        try {
            // Simulating heavy load by sleeping for 10 seconds
            logger.debug("Simulando carga pesada. Dormindo por 10 segundos.");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error("A execução foi interrompida.", e);
            Thread.currentThread().interrupt();
            return "A execução foi interrompida.";
        }

        logger.info("Carga pesada concluída com sucesso!");
        return "Heavy load completed!";
    }
}
