# Concessionaria

**Informações Gerais**

Este projeto tem o objetivo de gerenciar uma concessionária de veículos. O objetivo principal é facilitar o gerenciamento de Clientes, Veiculos, Venda, Financiamento de Carros e Motos, e de gerenciar uma agenda de Test Drives.

**Relações entre as Classes**

**Agregação:** A classe Cliente possui um histórico de Venda, indicando que um cliente pode ter múltiplas vendas, mas as vendas não existem sem um cliente.

**Composição:** A classe Venda é composta por um Cliente, um Vendedor e um Veiculo, significando que uma venda não faz sentido sem esses elementos.

**Associação:** A classe Financiamento está associada a uma Venda, indicando que um financiamento é sempre relacionado a uma venda específica.

**Como Executar o Projeto**

Para executar o projeto, siga as instruções abaixo:

1- Clone o Repositorio

2- Navegue ao diretorio do Projeto

3-Compile o Projeto

4-Execute o Projeto por meio da classe Menu Principal(main)

5-Siga as instruções no console para interagir com o sistema.

**Chat GPT / IAs**

As IA's foram utilizadas para implementar alguns metodos como o gerador de IDs aleatorios (UUID) e tambem a implementação do Log, além obviamente de parte desse README.

**Referências**

Durante o desenvolvimento deste projeto, foram utilizados os seguintes recursos e referências:

-Curso Professor Guanabara de Java (https://www.youtube.com/watch?v=sTX0UEplF54&list=PLHz_AreHm4dkI2ZdjTwZA4mPMxWTfNSpR)

