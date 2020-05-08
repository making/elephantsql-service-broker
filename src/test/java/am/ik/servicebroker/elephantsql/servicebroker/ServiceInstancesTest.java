package am.ik.servicebroker.elephantsql.servicebroker;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceInstancesTest {

    @Test
    void credentials() {
        final URI url = URI.create("postgres://iuyhjiu:iyhjiuyhjmhuhjk@test-speedcar-01.db.client.com:5432/iuyhjiu");
        final ServiceInstances.Credentials credentials = ServiceInstances.credentials(url);
        assertThat(credentials.getHostname()).isEqualTo("test-speedcar-01.db.client.com");
        assertThat(credentials.getPort()).isEqualTo(5432);
        assertThat(credentials.getName()).isEqualTo("iuyhjiu");
        assertThat(credentials.getUsername()).isEqualTo("iuyhjiu");
        assertThat(credentials.getPassword()).isEqualTo("iyhjiuyhjmhuhjk");
        assertThat(credentials.getUri()).isEqualTo("postgres://iuyhjiu:iyhjiuyhjmhuhjk@test-speedcar-01.db.client.com:5432/iuyhjiu");
        assertThat(credentials.getJdbcUrl()).isEqualTo("jdbc:postgresql://test-speedcar-01.db.client.com:5432/iuyhjiu?user=iuyhjiu&password=iyhjiuyhjmhuhjk");
    }
}