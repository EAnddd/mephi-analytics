**This is a simple aggregator which aggregates information about 10 Russian cities using public api and stores result in Kafka topic **

To init aggregation make request 
```curl -X POST http://localhost:8080/start --data "token=your-token-from-api.openweathermap.org"```

**Build**

```./gradlew fatJar```

**Test**

```./gradlew test```

