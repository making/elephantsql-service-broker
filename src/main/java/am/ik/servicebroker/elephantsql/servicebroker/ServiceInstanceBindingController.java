package am.ik.servicebroker.elephantsql.servicebroker;

import am.ik.servicebroker.elephantsql.client.ElephantSqlClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v2/service_instances/{instanceId}/service_bindings/{bindingId}")
public class ServiceInstanceBindingController {
    private static final Logger log = LoggerFactory
            .getLogger(ServiceInstanceBindingController.class);

    private final ElephantSqlClient elephantSqlClient;

    public ServiceInstanceBindingController(ElephantSqlClient elephantSqlClient) {
        this.elephantSqlClient = elephantSqlClient;
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> bind(
            @PathVariable("instanceId") String instanceId,
            @PathVariable("bindingId") String bindingId) {
        log.info("bind instanceId={}, bindingId={}", instanceId, bindingId);
        String name = ServiceInstances.findNameByInstanceId(instanceId);
        Map<String, Object> body = new HashMap<>();
        this.elephantSqlClient.findIdByName(name)
                .map(this.elephantSqlClient::getInstance)
                .ifPresent(i -> body.put("credentials",
                        ServiceInstances.credentials(i.getUrl())));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> unbind(
            @PathVariable("instanceId") String instanceId,
            @PathVariable("bindingId") String bindingId) {
        log.info("unbind instanceId={}, bindingId={}", instanceId, bindingId);
        return ResponseEntity.ok(Map.of());
    }
}
