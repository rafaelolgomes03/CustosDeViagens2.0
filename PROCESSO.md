# PROCESSO — Trabalho 2

## 1\. Como estava o projeto no Trabalho 1

O projeto original possuía três telas estáticas: Boas-vindas, dados da viagem e resumo do cálculo. Os botões não faziam uma navegação completa e vários dados estavam concentrados em uma única tela.

## 2\. O que mudou

O aplicativo foi reorganizado para usar navegação real com `NavHost`, `NavController` e o objeto `Rotas`. As informações da viagem agora são mantidas em estados compartilhados e passam pelas etapas do cálculo.

## 3\. Novas telas

Foram implementadas as sete telas descritas no roteiro:

* Boas-vindas;
* Perfil do Veículo;
* Dados da Rota;
* Custos de Combustível e Pedágio;
* Gastos Adicionais;
* Divisão de Despesas;
* Resumo / Resultado Final.

Também foram acrescentadas telas auxiliares para atender ao requisito de duas listas e duas telas de detalhes:

* Lista de Veículos;
* Detalhe do Veículo;
* Lista de Gastos;
* Detalhe do Gasto.

## 4\. Organização

As rotas ficam centralizadas no objeto `Rotas`. A navegação principal utiliza `NavigationBar`. As listas utilizam `mutableStateListOf`, `LazyColumn` e `Card`.

## 5\. Complexidade extra das telas de detalhes

O detalhe do veículo explica como o combustível selecionado se relaciona ao cálculo da viagem. O detalhe do gasto mostra o impacto daquele valor no orçamento, além de apresentar o valor formatado.

## 6\. Dificuldades

A principal mudança foi transformar as telas do Trabalho 1 em um fluxo funcional, mantendo os dados entre as etapas e permitindo adicionar, remover e consultar itens das listas.

