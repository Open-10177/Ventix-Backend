package com.acme.iot.platform.dashboard.application;

import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities.*;
import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Seeds sample dashboard data on startup when the tables are empty, so the
 * Ventix frontend has content to show on first run.
 */
@Component
public class DashboardDataSeeder implements CommandLineRunner {

    private final VentixSensorReadingRepository readings;
    private final VentixAlertRepository alerts;
    private final VentixMetricRepository metrics;
    private final VentixHistoryRecordRepository history;
    private final VentixDeviceRepository devices;
    private final VentixThresholdConfigRepository thresholds;

    public DashboardDataSeeder(VentixSensorReadingRepository readings, VentixAlertRepository alerts,
                               VentixMetricRepository metrics, VentixHistoryRecordRepository history,
                               VentixDeviceRepository devices, VentixThresholdConfigRepository thresholds) {
        this.readings = readings;
        this.alerts = alerts;
        this.metrics = metrics;
        this.history = history;
        this.devices = devices;
        this.thresholds = thresholds;
    }

    @Override
    public void run(String... args) {
        if (readings.count() == 0) {
            readings.saveAll(List.of(
                    new VentixSensorReading("Sala", 24.5, 620, 45, "2026-06-17T08:00:00.000Z"),
                    new VentixSensorReading("Cocina", 27.8, 980, 52, "2026-06-17T08:00:00.000Z"),
                    new VentixSensorReading("Cuarto", 22.1, 540, 48, "2026-06-17T08:00:00.000Z"),
                    new VentixSensorReading("Oficina", 25.0, 710, 40, "2026-06-17T08:00:00.000Z")
            ));
        }
        if (alerts.count() == 0) {
            alerts.saveAll(List.of(
                    new VentixAlert("CO2 elevado en Cocina", "El nivel de CO2 superó 900 ppm", "Ventilar ahora", "critical", "Hace 5 min", "warning"),
                    new VentixAlert("Temperatura alta en Sala", "La temperatura alcanzó 28°C", "Revisar", "warning", "Hace 20 min", "thermostat"),
                    new VentixAlert("Nodo Cuarto reconectado", "El sensor volvió a estar en línea", "Ver detalle", "info", "Hace 1 h", "info")
            ));
        }
        if (metrics.count() == 0) {
            metrics.saveAll(List.of(
                    new VentixMetric("Calidad del aire promedio", "Buena", "ok"),
                    new VentixMetric("CO2 promedio", "680 ppm", "ok"),
                    new VentixMetric("Alertas activas", "3", "warning"),
                    new VentixMetric("Nodos en línea", "4 / 4", "ok")
            ));
        }
        if (history.count() == 0) {
            history.saveAll(List.of(
                    new VentixHistoryRecord("2026-06-16", "Sala", "45%", "620 ppm", "24.5°C", "Ventilación automática"),
                    new VentixHistoryRecord("2026-06-16", "Cocina", "52%", "980 ppm", "27.8°C", "Alerta CO2"),
                    new VentixHistoryRecord("2026-06-15", "Cuarto", "48%", "540 ppm", "22.1°C", "Sin acciones"),
                    new VentixHistoryRecord("2026-06-15", "Oficina", "40%", "710 ppm", "25.0°C", "Modo ahorro")
            ));
        }
        if (devices.count() == 0) {
            devices.saveAll(List.of(
                    new VentixDevice("uuid-sala-01", "Sala", 80, "ACTIVE", "1.2.0"),
                    new VentixDevice("uuid-cocina-01", "Cocina", 40, "ACTIVE", "1.2.0"),
                    new VentixDevice("uuid-cuarto-01", "Cuarto", 60, "ACTIVE", "1.1.0"),
                    new VentixDevice("uuid-oficina-01", "Oficina", 95, "ACTIVE", "1.2.0")
            ));
        }
        if (thresholds.count() == 0) {
            thresholds.saveAll(List.of(
                    new VentixThresholdConfig(1L, "Sala", 60, 28, 900, true, false),
                    new VentixThresholdConfig(2L, "Cocina", 80, 30, 1200, true, false),
                    new VentixThresholdConfig(3L, "Cuarto", 50, 26, 800, false, true),
                    new VentixThresholdConfig(4L, "Oficina", 70, 27, 1000, true, false)
            ));
        }
    }
}
