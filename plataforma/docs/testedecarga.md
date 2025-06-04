
# Testes de Carga – HPA e Métricas

> Testes de carga foram aplicados para simular múltiplos acessos simultâneos à aplicação.  
> O foco é observar o comportamento do autoscaler (HPA) e o impacto no uso de recursos.

---

!!! info "Execução do Teste"

    Veja no vídeo abaixo a execução do teste de carga junto com a visualização do `kubectl get hpa` e o escalonamento dos pods em tempo real.

    - O `HPA` começa com 1 pod e escala até 5 conforme o uso de CPU.
    - O Prometheus monitora as métricas de forma contínua.
    - O Grafana exibe visualmente o comportamento da aplicação.

    <iframe width="560" height="315" src="https://www.youtube.com/watch?v=ImXXUg53QGA&ab_channel=LuigiOrlandiQuinze" 
    title="Teste de Carga - HPA" frameborder="0" allowfullscreen></iframe>

---

link: https://www.youtube.com/watch?v=ImXXUg53QGA&ab

> _Última execução: 2025-06-03_
