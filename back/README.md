# JooJLog
#### Api Requisições

**Get**: */api/jogos*
-Lista todos os jogos

**Get**: */api/jogos/search={nome do jogo}*
-Lista jogos quem tem o parâmetro no nome.

**Post**: */api/jogos*
-Adiciona um novo jogo

**Put**: */api/jogos/{id}*
-Atualiza o jogo

**Delete**: */api/jogos/{id}*
-Apaga o jogo

#### Instalação
- Crie uma database chamada joojlog.
>mvnw spring-boot:run
