package am.ik.servicebroker.elephantsql.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * https://docs.elephantsql.com/
 */
@Component
public class ElephantSqlClient {
    private final RestTemplate restTemplate;
    private final ParameterizedTypeReference<List<ElephantSqlInstance>> type = new ParameterizedTypeReference<>() {
    };

    public ElephantSqlClient(ElephantSql elephantSql, RestTemplateBuilder builder) {
        this.restTemplate = builder
                .basicAuthentication("", elephantSql.getApiKey())
                .rootUri(elephantSql.getUri() + "/api")
                .build();
    }

    public List<ElephantSqlInstance> listInstances() {
        final ElephantSqlInstance[] instances = this.restTemplate.getForObject("/instances", ElephantSqlInstance[].class);
        return instances == null ? List.of() : Arrays.asList(instances);
    }

    public ElephantSqlInstance getInstance(Integer id) {
        return this.restTemplate.getForObject("/instances/{id}", ElephantSqlInstance.class, id);
    }

    public ElephantSqlInstance createInstance(String name, ElephantSqlPlan plan, ElephantSqlRegion region) {
        final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("name", name);
        body.add("plan", plan.toString());
        body.add("region", region.toString());
        return this.restTemplate.postForObject("/instances", body, ElephantSqlInstance.class);
    }

    public Optional<Integer> findIdByName(String name) {
        return this.listInstances().stream()
                .filter(x -> Objects.equals(x.getName(), name))
                .findAny()
                .map(ElephantSqlInstance::getId);
    }

    public void deleteInstance(Integer id) {
        this.restTemplate.delete("/instances/{id}", id);
    }
}
