package com.acme.iot.platform.dashboard.interfaces.rest;

import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities.*;
import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.repositories.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * CRUD endpoints consumed by the Ventix Angular frontend (monitoring, analytics
 * and device-management modules). Data is persisted in MySQL through JPA, so
 * records added or removed survive restarts. JSON keys match the exact resource
 * shapes the frontend assemblers expect.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Tag(name = "Dashboard", description = "Frontend integration endpoints (telemetry, alerts, kpis, reports, devices, thresholds)")
public class DashboardController {

    private final VentixSensorReadingRepository readings;
    private final VentixAlertRepository alerts;
    private final VentixMetricRepository metrics;
    private final VentixHistoryRecordRepository history;
    private final VentixDeviceRepository devices;
    private final VentixThresholdConfigRepository thresholds;

    public DashboardController(VentixSensorReadingRepository readings, VentixAlertRepository alerts,
                              VentixMetricRepository metrics, VentixHistoryRecordRepository history,
                              VentixDeviceRepository devices, VentixThresholdConfigRepository thresholds) {
        this.readings = readings;
        this.alerts = alerts;
        this.metrics = metrics;
        this.history = history;
        this.devices = devices;
        this.thresholds = thresholds;
    }

    // ====== Monitoring: telemetry (sensor readings) ======
    @GetMapping("/telemetry")
    @Operation(summary = "List sensor readings")
    public List<Map<String, Object>> getTelemetry() {
        return readings.findAll().stream().map(DashboardController::readingJson).toList();
    }

    @PostMapping("/telemetry")
    @Operation(summary = "Create a sensor reading")
    public ResponseEntity<Map<String, Object>> createReading(@RequestBody Map<String, Object> b) {
        VentixSensorReading e = new VentixSensorReading(str(b, "zone"), dbl(b, "temperature"),
                intg(b, "co2"), intg(b, "humidity"), str(b, "timestamp"));
        return ResponseEntity.status(HttpStatus.CREATED).body(readingJson(readings.save(e)));
    }

    @DeleteMapping("/telemetry/{id}")
    @Operation(summary = "Delete a sensor reading")
    public ResponseEntity<Void> deleteReading(@PathVariable Long id) {
        if (!readings.existsById(id)) return ResponseEntity.notFound().build();
        readings.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ====== Monitoring: alerts (notifications) ======
    @GetMapping("/alerts")
    @Operation(summary = "List alerts / notifications")
    public List<Map<String, Object>> getAlerts() {
        return alerts.findAll().stream().map(DashboardController::alertJson).toList();
    }

    @PostMapping("/alerts")
    @Operation(summary = "Create an alert")
    public ResponseEntity<Map<String, Object>> createAlert(@RequestBody Map<String, Object> b) {
        VentixAlert e = new VentixAlert(str(b, "title"), str(b, "description"), str(b, "action"),
                str(b, "severity"), str(b, "time"), str(b, "icon"));
        return ResponseEntity.status(HttpStatus.CREATED).body(alertJson(alerts.save(e)));
    }

    @DeleteMapping("/alerts/{id}")
    @Operation(summary = "Delete an alert")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        if (!alerts.existsById(id)) return ResponseEntity.notFound().build();
        alerts.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ====== Analytics: kpis (metrics) ======
    @GetMapping("/kpis")
    @Operation(summary = "List KPI metrics")
    public List<Map<String, Object>> getKpis() {
        return metrics.findAll().stream().map(DashboardController::metricJson).toList();
    }

    @PostMapping("/kpis")
    @Operation(summary = "Create a KPI metric")
    public ResponseEntity<Map<String, Object>> createMetric(@RequestBody Map<String, Object> b) {
        VentixMetric e = new VentixMetric(str(b, "title"), str(b, "value"), str(b, "status"));
        return ResponseEntity.status(HttpStatus.CREATED).body(metricJson(metrics.save(e)));
    }

    @DeleteMapping("/kpis/{id}")
    @Operation(summary = "Delete a KPI metric")
    public ResponseEntity<Void> deleteMetric(@PathVariable Long id) {
        if (!metrics.existsById(id)) return ResponseEntity.notFound().build();
        metrics.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ====== Analytics: reports (history records) ======
    @GetMapping("/reports")
    @Operation(summary = "List historical records")
    public List<Map<String, Object>> getReports() {
        return history.findAll().stream().map(DashboardController::historyJson).toList();
    }

    @PostMapping("/reports")
    @Operation(summary = "Create a historical record")
    public ResponseEntity<Map<String, Object>> createHistory(@RequestBody Map<String, Object> b) {
        VentixHistoryRecord e = new VentixHistoryRecord(str(b, "date"), str(b, "zone"), str(b, "humidity"),
                str(b, "co2"), str(b, "temperature"), str(b, "action"));
        return ResponseEntity.status(HttpStatus.CREATED).body(historyJson(history.save(e)));
    }

    @DeleteMapping("/reports/{id}")
    @Operation(summary = "Delete a historical record")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        if (!history.existsById(id)) return ResponseEntity.notFound().build();
        history.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ====== Device management: devices ======
    @GetMapping("/devices")
    @Operation(summary = "List devices (sensor nodes)")
    public List<Map<String, Object>> getDevices() {
        return devices.findAll().stream().map(DashboardController::deviceJson).toList();
    }

    @GetMapping("/devices/{id}")
    @Operation(summary = "Get a device by id")
    public ResponseEntity<Map<String, Object>> getDevice(@PathVariable Long id) {
        return devices.findById(id).map(d -> ResponseEntity.ok(deviceJson(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/devices")
    @Operation(summary = "Register a device")
    public ResponseEntity<Map<String, Object>> createDevice(@RequestBody Map<String, Object> b) {
        VentixDevice e = new VentixDevice(str(b, "nodeUuid"), str(b, "zone"), intg(b, "batteryLevel"),
                strOr(b, "status", "ACTIVE"), strOr(b, "firmwareVersion", "1.0.0"));
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceJson(devices.save(e)));
    }

    @PutMapping("/devices/{id}")
    @Operation(summary = "Update a device")
    public ResponseEntity<Map<String, Object>> updateDevice(@PathVariable Long id, @RequestBody Map<String, Object> b) {
        return devices.findById(id).map(e -> {
            e.setNodeUuid(str(b, "nodeUuid"));
            e.setZone(str(b, "zone"));
            e.setBatteryLevel(intg(b, "batteryLevel"));
            e.setStatus(str(b, "status"));
            e.setFirmwareVersion(str(b, "firmwareVersion"));
            return ResponseEntity.ok(deviceJson(devices.save(e)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/devices/{id}")
    @Operation(summary = "Delete a device")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        if (!devices.existsById(id)) return ResponseEntity.notFound().build();
        devices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ====== Device management: threshold-configs ======
    @GetMapping("/threshold-configs")
    @Operation(summary = "List threshold configurations")
    public List<Map<String, Object>> getThresholds() {
        return thresholds.findAll().stream().map(DashboardController::thresholdJson).toList();
    }

    @GetMapping("/threshold-configs/{id}")
    @Operation(summary = "Get a threshold configuration by id")
    public ResponseEntity<Map<String, Object>> getThreshold(@PathVariable Long id) {
        return thresholds.findById(id).map(t -> ResponseEntity.ok(thresholdJson(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/threshold-configs")
    @Operation(summary = "Create a threshold configuration")
    public ResponseEntity<Map<String, Object>> createThreshold(@RequestBody Map<String, Object> b) {
        VentixThresholdConfig e = new VentixThresholdConfig(lng(b, "nodeId"), str(b, "zone"), intg(b, "ventilationPct"),
                intg(b, "temperatureLimit"), intg(b, "co2Limit"), bool(b, "optimizedMode"), bool(b, "savingMode"));
        return ResponseEntity.status(HttpStatus.CREATED).body(thresholdJson(thresholds.save(e)));
    }

    @PutMapping("/threshold-configs/{id}")
    @Operation(summary = "Update a threshold configuration")
    public ResponseEntity<Map<String, Object>> updateThreshold(@PathVariable Long id, @RequestBody Map<String, Object> b) {
        return thresholds.findById(id).map(e -> {
            e.setNodeRef(lng(b, "nodeId"));
            e.setZone(str(b, "zone"));
            e.setVentilationPct(intg(b, "ventilationPct"));
            e.setTemperatureLimit(intg(b, "temperatureLimit"));
            e.setCo2Limit(intg(b, "co2Limit"));
            e.setOptimizedMode(bool(b, "optimizedMode"));
            e.setSavingMode(bool(b, "savingMode"));
            return ResponseEntity.ok(thresholdJson(thresholds.save(e)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/threshold-configs/{id}")
    @Operation(summary = "Delete a threshold configuration")
    public ResponseEntity<Void> deleteThreshold(@PathVariable Long id) {
        if (!thresholds.existsById(id)) return ResponseEntity.notFound().build();
        thresholds.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ====== JSON mappers (entity -> frontend resource) ======
    private static Map<String, Object> readingJson(VentixSensorReading e) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", e.getId());
        m.put("zone", e.getZone());
        m.put("temperature", e.getTemperature());
        m.put("co2", e.getCo2());
        m.put("humidity", e.getHumidity());
        m.put("timestamp", e.getRecordedAt());
        return m;
    }

    private static Map<String, Object> alertJson(VentixAlert e) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", e.getId());
        m.put("title", e.getTitle());
        m.put("description", e.getDescription());
        m.put("action", e.getActionText());
        m.put("severity", e.getSeverity());
        m.put("time", e.getTimeLabel());
        m.put("icon", e.getIcon());
        return m;
    }

    private static Map<String, Object> metricJson(VentixMetric e) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", e.getId());
        m.put("title", e.getTitle());
        m.put("value", e.getMetricValue());
        m.put("status", e.getStatus());
        return m;
    }

    private static Map<String, Object> historyJson(VentixHistoryRecord e) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", e.getId());
        m.put("date", e.getRecordDate());
        m.put("zone", e.getZone());
        m.put("humidity", e.getHumidity());
        m.put("co2", e.getCo2());
        m.put("temperature", e.getTemperature());
        m.put("action", e.getActionText());
        return m;
    }

    private static Map<String, Object> deviceJson(VentixDevice e) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", e.getId());
        m.put("nodeUuid", e.getNodeUuid());
        m.put("zone", e.getZone());
        m.put("batteryLevel", e.getBatteryLevel());
        m.put("status", e.getStatus());
        m.put("firmwareVersion", e.getFirmwareVersion());
        return m;
    }

    private static Map<String, Object> thresholdJson(VentixThresholdConfig e) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", e.getId());
        m.put("nodeId", e.getNodeRef());
        m.put("zone", e.getZone());
        m.put("ventilationPct", e.getVentilationPct());
        m.put("temperatureLimit", e.getTemperatureLimit());
        m.put("co2Limit", e.getCo2Limit());
        m.put("optimizedMode", e.getOptimizedMode());
        m.put("savingMode", e.getSavingMode());
        return m;
    }

    // ====== body coercion helpers (frontend may send strings or numbers) ======
    private static String str(Map<String, Object> b, String k) {
        Object v = b.get(k);
        return v == null ? null : v.toString();
    }

    private static String strOr(Map<String, Object> b, String k, String def) {
        String v = str(b, k);
        return (v == null || v.isBlank()) ? def : v;
    }

    private static Integer intg(Map<String, Object> b, String k) {
        Object v = b.get(k);
        if (v == null) return null;
        if (v instanceof Number n) return n.intValue();
        try { return (int) Double.parseDouble(v.toString()); } catch (NumberFormatException ex) { return null; }
    }

    private static Long lng(Map<String, Object> b, String k) {
        Object v = b.get(k);
        if (v == null) return null;
        if (v instanceof Number n) return n.longValue();
        try { return (long) Double.parseDouble(v.toString()); } catch (NumberFormatException ex) { return null; }
    }

    private static Double dbl(Map<String, Object> b, String k) {
        Object v = b.get(k);
        if (v == null) return null;
        if (v instanceof Number n) return n.doubleValue();
        try { return Double.parseDouble(v.toString()); } catch (NumberFormatException ex) { return null; }
    }

    private static Boolean bool(Map<String, Object> b, String k) {
        Object v = b.get(k);
        if (v == null) return null;
        if (v instanceof Boolean bb) return bb;
        return Boolean.parseBoolean(v.toString());
    }
}
