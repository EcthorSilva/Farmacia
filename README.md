# Sistema de Vendas (Farmácia)

###### Um software de gerenciamento de vendas e controle de estoque em java (swing) e mySQL

O objetivo do projeto é produzir um sistema de vendas completo para a disciplina PROJETO INTEGRADOR: DESENVOLVIMENTO ESTRUTURADO DE SISTEMAS, presente no 2° Semestre do curso superior em Tecnologia em Analise e Desenvolvimento de Sistemas, utilizando os paradigmas da Programação Orientada a Objetos (POO) em Java, com a interface gráfica construída utilizando Java Swing, e armazenamento de dados em um banco de dados MySQL.

## Principais Funcionalidades 
O sistema possui quatro principais funcionalidades:

- Cadastro de Clientes: Permite ao usuário inserir, excluir, alterar e consultar informações de clientes. Os campos obrigatórios como nome, sexo, CPF, e-mail e endereço passam por validações antes de serem inseridos no banco de dados. Não é permitido cadastrar dois clientes com o mesmo CPF.

- Cadastro de Produtos: Permite ao usuário inserir, excluir, alterar e consultar informações de produtos. Inclui um controle de estoque simples, onde o usuário pode atualizar os indicadores de estoque. Vendas realizadas reduzem o estoque, e não é possível vender produtos sem estoque.

- Venda de Produtos: A atividade central do sistema. O usuário seleciona produtos, quantidades e cliente para efetuar uma venda. O valor final da venda é mostrado e a transação só é concluída após seleção de cliente, produtos e quantidade. Validadores são aplicados.

- Relatório de Vendas: Oferece dois tipos de visualização. O relatório sintético resume as vendas em um período, indicando o valor total, data da compra e cliente. O relatório analítico fornece detalhes de cada venda, incluindo produtos vendidos e suas quantidades.

## Tecnologias Utilizadas
Linguagem de Programação: Java  
Interface Gráfica: Java Swing  
Banco de Dados: MySQL  

## Updates

> Nota: Estamos atualizando o README.md e movendo as informações mais detalhadas para a [Wiki](https://github.com/EcthorSilva/Farmacia/wiki). Dessa forma, apenas as últimas atualizações em que estamos trabalhando serão mantidas aqui para facilitar a leitura.

- Atualização do README.md e adição dos colaboradores do projeto.

## Contribuição
Este projeto foi desenvolvido para fins educacionais, portanto, não aceitamos contribuições para o mesmo. No entanto, sugestões e feedbacks são bem-vindos.

## Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para obter mais informações.
