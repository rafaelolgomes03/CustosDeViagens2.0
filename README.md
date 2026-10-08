# Custos de Viagens — Trabalho 2

Aplicativo Android desenvolvido em Kotlin + Jetpack Compose.

## Como executar
1. Abra a pasta `CustosDeViagens` no Android Studio.
2. Aguarde a sincronização do Gradle.
3. Execute o projeto em um emulador ou dispositivo Android.

## O que foi implementado
- 7 telas principais do roteiro do projeto:
  1. Boas-vindas
  2. Perfil do Veículo
  3. Dados da Rota
  4. Custos de Combustível e Pedágio
  5. Gastos Adicionais
  6. Divisão de Despesas
  7. Resumo / Resultado Final
- Navegação real com `NavHost`, `NavController` e objeto `Rotas`.
- `NavigationBar` inferior nas telas principais.
- Formulários com dados compartilhados entre as telas.
- Cálculo real de litros, combustível, pedágio, gastos adicionais, custo total e custo por pessoa.
- Duas listas reativas com `LazyColumn` + `Card`: veículos e gastos.
- Adição e remoção de itens nas duas listas.
- Duas telas de detalhes, recebendo o ID do item pela rota.
- As telas de detalhes apresentam informações adicionais calculadas/derivadas dos dados.
- Dados das listas ficam em memória com `mutableStateListOf`, conforme permitido pelo trabalho.

## Observação
As listas permanecem apenas em memória e não utilizam banco de dados ou persistência.
