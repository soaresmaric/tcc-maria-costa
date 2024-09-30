package com.tcc.coldstarts.tcc;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupMetrics implements ApplicationListener<ApplicationReadyEvent> {

    private final MeterRegistry meterRegistry;

    public StartupMetrics(MeterRegistry meterRegistry) {

        this.meterRegistry = new SimpleMeterRegistry();

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Simular operações pesadas na inicialização
        simulateSlowInitialization();

        // Captura o valor que o Spring Boot já calcula
        long startupTimeMillis = event.getApplicationContext().getStartupDate();
        long currentTimeMillis = System.currentTimeMillis();

        // Calcula o tempo total de inicialização
        long startupDuration = currentTimeMillis - startupTimeMillis;

        // Converte o tempo de inicialização para segundos
        double startupTimeInSeconds = startupDuration / 1000.0;

        // Registra o tempo de inicialização na métrica Prometheus
        meterRegistry.gauge("application_startup_time_seconds", startupTimeInSeconds);

        // Log para verificar o tempo de inicialização
        System.out.println("Tempo de inicialização da aplicação (capturado): " + startupTimeInSeconds + " segundos");
    }

    private void simulateSlowInitialization() {
        System.out.println("Executando operações de inicialização lenta...");

        // Simular cálculos ou operações demoradas
        try {
            Thread.sleep(15000); // Simula 15 segundos de operação pesada
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Operações de inicialização lenta concluídas.");
    }
}
