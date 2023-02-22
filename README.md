# OrcamentoMensalChanllengeAluraBackEnd
cadastro e pesquisa de orçamento mensal para controle financeiros, podendo ver as despesas, e ganhos de entrada de cada mês  separado por catergoria,
necessario usuario autenticado para usar algumas requisições do sistema.


Documentação Completa
http://localhost:8080/swagger-ui/#/


http://localhost:8080 - Generated server url

urls : 
receita-controller


GET
/receitas/{id}

PUT
/receitas/{id}

DELETE
/receitas/{id}

GET
/receitas

POST
/receitas

GET
/receitas/{mes}/{ano}
despesas-controlller


GET
/despesas/{id}

PUT
/despesas/{id}

DELETE
/despesas/{id}

GET
/despesas

POST
/despesas

GET
/despesas/{mes}/{ano}
usuario-controller


POST
/usuario
resumo-controller


GET
/resumo/{ano}/{mes}
