# Getting Started

Projeto de busca de CEP com cache local em Java para colocar em prática os estudos sobre o tema Arquitetura Hexagonal


## Estratégia
- Logando em formato JSON com Logback;
- Actuator + Micrometer para healthCheck e métricas(http://localhost:8080/cep-api/actuator/prometheus e http://localhost:8080/cep-api/actuator/health);
- Para documentação da API está sendo utilizado springfox em (http://localhost:8080/cep-api/swagger-ui/);
- Solução de cache do Spring com (@Cacheable e @CacheEvict) para gerar caches de 10 minutos dos CEPs pesquisados.

## Arquitetura
- Para a organização do projeto, foi utilizada a Arquitetura Hexagonal, onde as regras de negócio ficam isoladas dentro do pacote "core" e toda comunicação com o mundo externo (tanto inbound como o controller, quanto outbound que é a camada de persistência e o serviço de busca de cep com o VIACEP via http) é feito via interface (adapters) para gerar um baixo acoplamento.
- Esse tipo de arquitetura permite a troca de todo o mundo externo preservando a regra de negócio. (https://alistair.cockburn.us/hexagonal-architecture/)
