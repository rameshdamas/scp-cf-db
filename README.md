# SAP Cloud Platform, CloudFoundry

## Create hanatrial service
Run the below command for manage schemas and HDI containers on a shared SAP HANA database.
```
> cf create-service hanatrial hdi-shared my-db
```

## manifest.yml
```
---
applications:

- name: scpcfdb
  memory: 2048M
  random-route: true
  path: application/target/application.war
  buildpack: sap_java_buildpack
  env:
    TARGET_RUNTIME: tomee
    JBP_CONFIG_SAPJVM_MEMORY_SIZES: 'metaspace:96m..'
    SET_LOGGING_LEVEL: '{ROOT: INFO, com.sap.cloud.sdk: INFO}'
  services:
   - my-db
```

## Deploy to CloudFoundry
Run the below command to compile the code
```
> cd scp-cf-db
> mvn clean install
```
Run the below cf CLI command to deploy the application
```
> cf push
```
Save data into database: [/saveUser]
## Redeploy to CloudFoundry
Make your java code changes and run the below command to compile the code
```
> cd scp-cf-db
> mvn clean install
```
Run the below cf CLI command to deploy the application
```
> cf push
```

## REFERENCE
https://blogs.sap.com/2017/05/10/first-steps-with-sap-s4hana-cloud-sdk/
