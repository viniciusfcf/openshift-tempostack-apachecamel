# openshift-tempostack-apachecamel

## Tempo (https://docs.redhat.com/en/documentation/openshift_container_platform/4.18/html-single/distributed_tracing/index#installing-a-tempostack-instance_distr-tracing-tempo-installing)
- Tempo Operator
- TempoMonolithic
- Cluster Observability Operator

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
