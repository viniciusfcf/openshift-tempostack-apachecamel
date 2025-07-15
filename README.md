# openshift-tempostack-apachecamel

## Tempo (https://docs.redhat.com/en/documentation/openshift_container_platform/4.18/html-single/distributed_tracing/index#installing-a-tempostack-instance_distr-tracing-tempo-installing)
- Tempo Operator
- TempoMonolithic
- Streams for Apache Kafka

```
apiVersion: tempo.grafana.com/v1alpha1
kind: TempoMonolithic
metadata:
  name: tempo
  namespace: tempo
spec:
  ingestion:
    otlp:
      grpc:
        enabled: true
      http:
        enabled: true
  resources:
    limits:
      cpu: '2'
      memory: 2Gi
  jaegerui:
    resources:
      limits:
        cpu: '2'
        memory: 2Gi
    route:
      enabled: true
    enabled: true
  storage:
    traces:
      backend: memory

```

```
apiVersion: kafka.strimzi.io/v1beta2
kind: Kafka
metadata:
  name: my-cluster
  namespace: my-kafka
spec:
  kafka:
    config:
      offsets.topic.replication.factor: 3
      transaction.state.log.replication.factor: 3
      transaction.state.log.min.isr: 2
      default.replication.factor: 3
      min.insync.replicas: 2
      inter.broker.protocol.version: '3.9'
    storage:
      type: ephemeral
    listeners:
      - name: plain
        port: 9092
        type: internal
        tls: false
      - name: tls
        port: 9093
        type: internal
        tls: true
    version: 3.9.0
    replicas: 3
  entityOperator:
    topicOperator: {}
    userOperator: {}
  zookeeper:
    storage:
      type: ephemeral
    replicas: 3 
```

````
kind: KafkaTopic
apiVersion: kafka.strimzi.io/v1beta2
metadata:
  name: pagamentos
  labels:
    strimzi.io/cluster: my-cluster
  namespace: my-kafka
spec:
  partitions: 10
  replicas: 3
  config:
    retention.ms: 604800000
    segment.bytes: 1073741824
```