package am.ik.servicebroker.elephantsql.client;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.TestConstructor;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ElephantSqlInstanceTest {
    private final JacksonTester<ElephantSqlInstance> json;

    ElephantSqlInstanceTest(JacksonTester<ElephantSqlInstance> json) {
        this.json = json;
    }

    @Test
    void testDeserialize_info() throws Exception {
        String content = "{\n" +
                "  \"id\": 1234,\n" +
                "  \"plan\": \"turtle\",\n" +
                "  \"region\": \"amazon-web-services::eu-west-1\",\n" +
                "  \"name\": \"My test\",\n" +
                "  \"url\": \"postgres://iuyhjiu:iyhjiuyhjmhuhjk@test-speedcar-01.db.elephantsql.com:5432/iuyhjiu\",\n" +
                "  \"apikey\": \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxxx\",\n" +
                "  \"ready\": true\n" +
                "}";
        final ElephantSqlInstance instance = json.parseObject(content);
        assertThat(instance.getId()).isEqualTo(1234);
        assertThat(instance.getPlan()).isEqualTo(ElephantSqlPlan.TURTLE);
        assertThat(instance.getRegion()).isEqualTo(ElephantSqlRegion.AMAZON_WEB_SERVICES_EU_WEST_1);
        assertThat(instance.getName()).isEqualTo("My test");
        assertThat(instance.getUrl()).isEqualTo(URI.create("postgres://iuyhjiu:iyhjiuyhjmhuhjk@test-speedcar-01.db.elephantsql.com:5432/iuyhjiu"));
        assertThat(instance.isReady()).isTrue();
    }

    @Test
    void testDeserialize_list() throws Exception {
        String content = "{\n" +
                "  \"id\": 1234,\n" +
                "  \"plan\": \"turtle\",\n" +
                "  \"region\": \"amazon-web-services::eu-west-1\",\n" +
                "  \"name\": \"My test\",\n" +
                "  \"url\": \"postgres://iuyhjiu:iyhjiuyhjmhuhjk@test-speedcar-01.db.elephantsql.com:5432/iuyhjiu\"" +
                "}";
        final ElephantSqlInstance instance = json.parseObject(content);
        assertThat(instance.getId()).isEqualTo(1234);
        assertThat(instance.getPlan()).isEqualTo(ElephantSqlPlan.TURTLE);
        assertThat(instance.getRegion()).isEqualTo(ElephantSqlRegion.AMAZON_WEB_SERVICES_EU_WEST_1);
        assertThat(instance.getName()).isEqualTo("My test");
        assertThat(instance.getUrl()).isEqualTo(URI.create("postgres://iuyhjiu:iyhjiuyhjmhuhjk@test-speedcar-01.db.elephantsql.com:5432/iuyhjiu"));
        assertThat(instance.isReady()).isFalse();
    }


    @Test
    void testDeserialize_create() throws Exception {
        String content = "{\n" +
                "  \"id\": 2234,\n" +
                "  \"url\": \"postgres://iuyhjiu:iyhjiuyhjmhuhjk@test-speedcar-01.db.elephantsql.com:5432/iuyhjiu\",\n" +
                "  \"apikey\": \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxxx\"\n" +
                "}";
        final ElephantSqlInstance instance = json.parseObject(content);
        assertThat(instance.getId()).isEqualTo(2234);
        assertThat(instance.getUrl()).isEqualTo(URI.create("postgres://iuyhjiu:iyhjiuyhjmhuhjk@test-speedcar-01.db.elephantsql.com:5432/iuyhjiu"));
    }
}