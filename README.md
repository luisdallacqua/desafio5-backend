# Processo Seletivo Java

## Descrição 

- Resolução do teste proposto para avaliação no processo seletivo supera java-react, desenvolvendo uma camada de serviço para emissão de extrato bancário


### Features

-   [x] Fornecer os dados de todas as transferências vinculadas ao número da conta bancária - (GET) http://localhost:8080/{idDaConta}
-   [x] Retornar todas as transferências relacionadas **àquele período de tempo** - (GET) http://localhost:8080/{idDaConta}?{dataInicio=dataInicio}&{dataFim=dataFim}
-   [x] Retornar todas as transferências relacionadas **àquele operador** - (GET) http://localhost:8080/{idDaConta}?{operador=operador}
-   [x] Retornar todas as transferências relacionadas **àquele operador no respectivo período de tempo** - (GET) http://localhost:8080/{idDaConta}?{operador=operador}&{dataInicio=dataInicio}&{dataFim=dataFim}
-   [x] Testes unitários implementados
-   [x] Todos os requisitos do teste estão satisfeitos


### Algumas observações:

- Utilizei as validações fornecidas nos dados iniciais do sql.data como base, mas acrescentei algumas linhas para demonstrar melhor a paginação e a filtragem;
- Optei por retornar um Pageable no lugar de uma Lista por ser uma implementação mais próxima de uma aplicação em produção;
- Optei por ter um endpoint que retorne a Conta para poder verificar se a conta pesquisada existe para só então retornar as tranferẽncias. E caso não existir, retornar um código 404 (entidade não encontrada) para a requisição.
- Optei a utilização do MapStruct como biblioteca para facilitar o mapeamento da entedide para DTO;
- Optei a utilização do OpenAPI como biblioteca por facilitar a documentação do projeto;

## Requisitos de sistema

- Possuir a JDK 11
- Uma IDE ou editor da sua preferência

## Como executar a aplicação 

- Pode executar a aplicação da maneira que quiser e utilizando a IDE de sua preferência. 
- Caso queira executar a aplicação via linha de comando, execute primeiramente o comando:

                   ./mvnw clean package  para linux.

                   .\mvnw clean package  para windows.
- Após isso execute o comando: 

                             java -jar <...caminhoParaSeuJar>
