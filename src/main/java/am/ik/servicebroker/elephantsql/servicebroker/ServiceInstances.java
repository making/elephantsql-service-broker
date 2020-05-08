package am.ik.servicebroker.elephantsql.servicebroker;

import java.net.URI;

public class ServiceInstances {
    static String findNameByInstanceId(String instanceId) {
        return ("si_" + instanceId).replace("-", "");
    }

    static Credentials credentials(URI url) {
        final Credentials credentials = new Credentials();
        credentials.setHostname(url.getHost());
        credentials.setPort(url.getPort());
        credentials.setName(url.getRawPath().replaceFirst("/", ""));
        final String[] userInfo = url.getUserInfo().split(":");
        final String username = userInfo[0];
        credentials.setUsername(username);
        final String password = userInfo[1];
        credentials.setPassword(password);
        credentials.setUri(url.toString());
        credentials.setJdbcUrl("jdbc:" + url.toString()
                .replace(url.getUserInfo() + "@", "")
                .replace("postgres:", "postgresql:"));
        return credentials;
    }

    public static class Credentials {
        public String hostname;
        public int port;
        public String name;
        public String username;
        public String password;
        public String uri;
        public String jdbcUrl;

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getJdbcUrl() {
            return jdbcUrl;
        }

        public void setJdbcUrl(String jdbcUrl) {
            this.jdbcUrl = jdbcUrl;
        }

        @Override
        public String toString() {
            return "Credentials{" +
                    "hostname='" + hostname + '\'' +
                    ", port=" + port +
                    ", name='" + name + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", uri='" + uri + '\'' +
                    ", jdbcUrl='" + jdbcUrl + '\'' +
                    '}';
        }
    }
}
