package am.ik.servicebroker.elephantsql.servicebroker;

import am.ik.servicebroker.elephantsql.client.ElephantSqlClient;
import am.ik.servicebroker.elephantsql.client.ElephantSqlPlan;
import am.ik.servicebroker.elephantsql.client.ElephantSqlRegion;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/v2/service_instances/{instanceId}")
public class ServiceInstanceController {
    private static final Logger log = LoggerFactory
            .getLogger(ServiceInstanceController.class);
    private final ElephantSqlClient elephantSqlClient;

    public ServiceInstanceController(ElephantSqlClient elephantSqlClient) {
        this.elephantSqlClient = elephantSqlClient;
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> provisioning(
            @PathVariable("instanceId") String instanceId, @RequestBody JsonNode body) {
        log.info("Provisioning instanceId={}", instanceId);
        ElephantSqlRegion region;
        if (body.has("parameters") && body.get("parameters").has("region")) {
            final String s = body.get("parameters").get("region").asText();
            region = ElephantSqlRegion.of(s);
            if (region == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given region is not supported. :" + s);
            }
        } else {
            region = ElephantSqlRegion.GOOGLE_COMPUTE_ENGINE_ASIA_EAST2;
        }
        final String name = ServiceInstances.findNameByInstanceId(instanceId);
        if (this.elephantSqlClient.findIdByName(name).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of());
        }
        ElephantSqlPlan plan = ElephantSqlPlan.TURTLE; // TODO
        this.elephantSqlClient.createInstance(name, plan, region);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of());
    }

    @PatchMapping
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable("instanceId") String instanceId) {
        return ResponseEntity.ok(Map.of());
    }

    @DeleteMapping
    public ResponseEntity<?> deprovisioning(
            @PathVariable("instanceId") String instanceId) {
        log.info("Deprovisioning instanceId={}", instanceId);
        String name = ServiceInstances.findNameByInstanceId(instanceId);
        return this.elephantSqlClient.findIdByName(name) //
                .map(id -> {
                    this.elephantSqlClient.deleteInstance(id);
                    return ResponseEntity.ok(Map.of());
                }) //
                .orElseGet(() -> ResponseEntity.status(HttpStatus.GONE).body(Map.of()));
    }

}