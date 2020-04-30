# Open Service Broker API for ElephantSQL

An Open Service Broker for [ElephantSQL](https://www.elephantsql.com/).

## How to deploy on Cloud Foundry

Get an [API Key](https://customer.elephantsql.com/apikeys).

Make sure `diego_docker` feature flag is enabled.

```
$ cf feature-flags | grep diego_docker
diego_docker                                  enabled
```

If not, run `cf enable-feature-flag diego_docker` as admin.

```
wget https://github.com/making/elephantsql-service-broker/raw/master/manifest.yml
cf push --no-start
cf set-env elephantsql-service-broker ELEPHANTSQL_API_KEY *******************
cf set-env elephantsql-service-broker SPRING_SECURITY_USER_PASSWORD xxxxxxxxxxxx
cf start elephantsql-service-broker
```

```
cf create-service-broker elephantsql admin xxxxxxxxxxxx https://elephantsql-service-broker.<apps domain>
cf enable-service-access elephantsql
```

or (if you don't have the admin role)

```
cf create-service-broker elephantsql admin xxxxxxxxxxxx https://elephantsql-service-broker.<apps domain> --space-scoped
```

```
$ cf marketplace -s elephantsql
Getting service plan information for service elephantsql as ****...
OK

service plan   description                                       free or paid
turtle         Free shared server (DO NOT USE IN PRODUCTION!!)   free
```

Only `turtle` plan is supported.

![image](https://user-images.githubusercontent.com/106908/80739197-01fa1580-8b51-11ea-897f-dbb6c830e1d9.png)

```
cf create-service elephantsql turtle my-db
```

By default, `google-compute-engine::asia-east2` region is used.

You can specify a region as follows:

```
cf create-service elephantsql turtle my-db -c '{"region": "amazon-web-services::ap-northeast-1"}'
```

The following regions are supported.

* `google-compute-engine::us-central1`
* `google-compute-engine::southamerica-east1`
* `google-compute-engine::europe-west2`
* `google-compute-engine::asia-east2`
* `google-compute-engine::australia-southeast1`
* `amazon-web-services::us-east-1`
* `amazon-web-services::eu-north-1`
* `amazon-web-services::eu-west-1`
* `amazon-web-services::ap-southeast-2`
* `amazon-web-services::ap-northeast-1`
* `amazon-web-services::ap-east-1`
* `amazon-web-services::sa-east-1`
* `azure-arm::centralus`
* `azure-arm::westeurope`

## Testing Service Broker functionalities locally

```
./mvnw clean package -DskipTests
java -jar target/elephantsql-service-broker-*.jar --elephantsql.api-key=*******************
```

Install a CLI for OSBAPI

```
brew install starkandwayne/cf/eden
```

### Show a catalog

```
eden catalog \
     --url http://localhost:8080 \
     --client admin \
     --client-secret password
```

### Provision a service instance

```
eden provision \
     --url http://localhost:8080 \
     --client admin \
     --client-secret password \
     -s elephantsql \
     -p turtle \
     -i demo-db
```

### Bind a service instance

```
eden bind \
     --url http://localhost:8080 \
     --client admin \
     --client-secret password \
     -i demo-db \
     -b demo-db-bind
```

### Show credentials

```
eden credentials \
     --url http://localhost:8080 \
     --client admin \
     --client-secret password \
     -i demo-db \
     -b demo-db-bind
```

### Unbind a service instance

```
eden unbind \
     --url http://localhost:8080 \
     --client admin \
     --client-secret password \
     -i demo-db \
     -b demo-db-bind
```

### Deprovision a service instance

```
eden deprovision \
     --url http://localhost:8080 \
     --client admin \
     --client-secret password \
     -i demo-db
```