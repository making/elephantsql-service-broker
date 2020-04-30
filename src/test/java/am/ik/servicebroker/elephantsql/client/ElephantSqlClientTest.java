package am.ik.servicebroker.elephantsql.client;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import static am.ik.servicebroker.elephantsql.client.ElephantSqlPlan.TURTLE;
import static am.ik.servicebroker.elephantsql.client.ElephantSqlRegion.GOOGLE_COMPUTE_ENGINE_ASIA_EAST2;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ElephantSqlClientTest {
    private final ElephantSqlClient elephantSqlClient;
    private final String name = "elephantsqlclienttest";

    ElephantSqlClientTest(ElephantSqlClient elephantSqlClient) {
        this.elephantSqlClient = elephantSqlClient;
    }

    @Order(0)
    @Test
    void createInstance() {
        final ElephantSqlInstance instance = this.elephantSqlClient.createInstance(this.name, TURTLE, GOOGLE_COMPUTE_ENGINE_ASIA_EAST2);
        System.out.println(instance);
        assertThat(instance).isNotNull();
        assertThat(instance.getId()).isNotNull();
        assertThat(instance.getUrl()).isNotNull();
    }

    @Order(1)
    @Test
    void getInstance() {
        final ElephantSqlInstance instance = this.elephantSqlClient.getInstance(this.createdId());
        System.out.println(instance);
        assertThat(instance).isNotNull();
        assertThat(instance.getId()).isNotNull();
        assertThat(instance.getPlan()).isEqualTo(TURTLE);
        assertThat(instance.getRegion()).isEqualTo(GOOGLE_COMPUTE_ENGINE_ASIA_EAST2);
        assertThat(instance.getName()).isEqualTo(this.name);
        assertThat(instance.getUrl()).isNotNull();
        assertThat(instance.isReady()).isTrue();
    }

    @Order(2)
    @Test
    void deleteInstance() {
        this.elephantSqlClient.deleteInstance(this.createdId());
    }

    Integer createdId() {
        return this.elephantSqlClient.findIdByName(this.name)
                .get();
    }
}