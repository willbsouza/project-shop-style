<h1> Projeto Shop Style 🛒</h1>

[MS Customer](#id1)  |  [MS Catalog](#id2)  |  [MS Payment](#id3)  |  [MS Order](#id4)  |  [MS Audit](#id5)  |  [MS BFF-Shop](#id6)

<p> O Shop Style é uma loja física que vende roupas de todos os tipos e estilos. Os fundadores do Shop Style desejam agora abrir uma loja virtual e contrataram uma equipe para implementar. Os arquitetos já desenharam a solução e agora cabe a você implementar essa solução. O projeto usará uma arquitetura de micro-serviços. Foi definindo a criação de seis micro-serviços: customer, catalog, payment, order, audit e o bff-shop. Todos os micro-serviços devem ter testes unitários com cobertura de pelo menos 80% e também um swagger correspondente.</p>

<div align="center">
  <img src="https://user-images.githubusercontent.com/76399078/180453933-4d169a01-0a4f-448c-9cd8-a26dba6a765b.PNG" width="600px"/>
</div>

<details>
  <summary><a id="id1"><h2><strong>MS Customer</strong></h2></summary>

<p> O MS customer tem a responsabilidade de armazenar e gerenciar os dados de usuário e seus endereços. O MS customer possui os seguintes endpoints:</p>

```
POST - /v1/login

POST - /v1/customers
GET - /v1/customers/:id
PUT - /v1/customers/:id
PUT - /v1/customers/:id/password

POST - /v1/address
PUT - /v1/address/:id
DELETE - /v1/address/:id
```

<p><strong>Campos da tabela customer:</strong></p>

```
ID, CPF, FIRST_NAME, LAST_NAME, SEX, BIRTHDATE, EMAIL, PASSWORD, ACTIVE
```

<p><strong>Campos da tabela address:</strong></p>

```
ID, STATE, CITY, DISTRICT, STREET, NUMBER, CEP, COMPLEMENT, CUSTOMER_ID
```

<p><strong>Observação:</strong></p>

<li>O campo ID de todas as tabelas deve ser gerado por auto incremento.</li>
<li>Exemplo de um payload para cadastrar cliente:</li>

```
{
  "firstName": "Maria",
  "lastName": "Oliveira",
  "sex": "Feminino",
  "cpf": "000.000.000-00",
  "birthdate": "0000-00-00",
  "email": "maria@email.com",
  "password": "12345678",
  "active": true
}
```

<p><strong>Validações necessárias:</strong></p>
<li> Os campos firstName e lastName precisam ter no mínimo 3 caracteres.</li>
<li> O campo sex só pode ter duas opções disponíveis Masculino e Feminino, caso contrário informar um erro ao usuário.
<li> O campo email precisa estar no formato de um email válido e não deve permitir emails duplicados.
<li> O campo cpf precisa seguir o seguinte padrão (xxx-xxx-xxx.xx).
<li> O campo password precisa ter no mínimo 6 caracteres e tem que ser salva criptografada no banco.
<li> O campo birthdate precisa ser salvo no banco como o tipo date e tem que estar no formato ISO-8601, entretanto na hora de serializar o objeto e enviar no payload do response esse campo precisa estar no formato dd/mm/aaaa.
<li> O campo active deve aceitar somente valores booleanos.

<p><strong>Exemplo de um payload para cadastrar endereço:</strong></p>

```
{
  "state": "Ceará",
  "city": "Fortaleza",
  "district": "Conjunto Ceará",
  "street": "Rua 202B",
  "number": "902",
  "cep": "60530-280",
  "complement": "",
  "customerId": 1
}
```

<p><strong>Validações necessárias:</strong></p>

<li>Todos os campos são obrigatórios exceto o campo complement.</li>
<li>Todos os campos são textos.</li>
<li>O campo state só deve aceitar valores de um dos 27 estados brasileiros, qualquer outro valor deve retornar um erro.</li>
<li>O body do endpoint POST - /v1/login:</li>

```
{
  "email": "maria@email.com",
  "password": "12345678"
}
```

<p><strong>Observação:</strong></p>

<li>No endpoint /v1/customers/:id além de retornar os dados do cliente deve trazer todos os seus endereços.</li>
<li>Usar o PostgreSQL.</li>

</details>

<details>
  <summary><a id="id2"><h2><strong>MS Catalog</strong></h2></summary>

<p>O MS catalog é o responsável por armazenar os produtos, skus e categorias que vão estar disponíveis na aplicação. Um produto tem um ou mais skus e está vinculado a uma categoria e uma categoria pode ter zero ou mais produtos. Uma sku tem uma ou mais medias e uma media é de uma sku. O MS catalog possui os seguintes endpoints:</p>

```
POST - /v1/products
GET - /v1/products
GET - /v1/products/:id
PUT - /v1/products/:id
DELETE - /v1/products/:id

POST - /v1/skus
PUT - /v1/skus/:id
DELETE - /v1/skus/:id

POST - /v1/categories
GET - /v1/categories
GET - /v1/categories/:id/products
PUT - /v1/categories/:id
DELETE - /v1/categories/:id
```

<p><strong>Campos da tabela product:</strong></p>

```
ID, NAME, DESCRIPTION, BRAND, MATERIAL, ACTIVE, CATEGORY_ID
```

<p><strong>Campos da tabela sku:</strong></p>

```
ID, PRICE, QUANTITY, COLOR, SIZE, HEIGHT, WIDTH, PRODUCT_ID
```

<p><strong>Campos da tabela media:</strong></p>

```
ID, IMAGE_URL, SKU_ID
```

<p><strong>Campos da tabela category:</strong></p>

```
ID, NAME, ACTIVE, PARENT_ID
```

<p><strong>Observação:</strong></p>

<li>O campo ID de todas as tabelas deve ser gerado por auto incremento.</li>

<p><strong>Exemplo de um payload para cadastrar um produto:</strong></p>

```
{
  "name": "Camisa Oficial do Fluminense",
  "description": "A camisa pra você que é tricolor de coração",
  "brand": "Umbro",
  "material": "Algodão"
  "active": true,
  "categoryId": 1
}
```

<p><strong>Exemplo de um payload para cadastrar uma sku:</strong></p>

```
{
  "price": 249.99,
  "quantity": 10,
  "color": "tricolor",
  "size": "M",
  "height": 100
  "width": 80
  "images": ["http://example.com/image-1.png", "http://example.com/image-2.png", "http://example.com/image-3.png", "http://example.com/image
  "productId": 1
}
```

<p><strong>Exemplo de um payload para cadastrar uma categoria principal:</strong></p>

```
{
  "name": "Camisas",
  "active": true
}
```

<p><strong>Exemplo de um payload para cadastrar uma categoria filha:</strong></p>

```
{
  "name": "Camisas de Futebol",
  "active": true
  "parentId": 1
}
```

<p><strong>Validações necessárias:</strong></p>

<li>Os campos name, description, brand, active e categoryId são obrigatórios para salvar um produto.</li>
<li>As categorias têm que estar ativa para um produto ser salvo.</li>
<li>Produtos só podem ser salvos em categorias ativas e que não tem nenhum filho.</li>
<li>Todos os campos mostrados acima são obrigatórios para cadastrar uma sku.</li>
<li>Os campos height e width da sku tem que ser enviado em centímetros.</li>
<li>Os campos name e active são obrigatórios para salvar uma categoria.</li>
<li>No endpoint GET - /v1/categories o retorno deve ser em formato de árvore, segue um exemplo logo abaixo:</li>

```
[
  {
    "id": 1,
    "name": "Masculino",
    "active": true,
    "children": [
      {
        "id": 2,
        "name": "Roupas",
        "active": true,
        "children": [
          {
            "id": 3,
            "name": "Futebol",
            "active": true
          },
          {
            "id": 4,
            "name": "Elegante",
            "active": true
          }
        ]
      }
    ]
  },
  {
    "id": 5,
    "name": "Feminino",
    "active": true,
    "children": [
      {
        "id": 6,
        "name": "Roupas",
        "active": true,
        "children": [
          {
            "id": 7,
            "name": "Usual",
            "active": true
          },
          {
            "id": 8,
            "name": "Elegante",
            "active": true
          }
        ]
      }
    ]
  },
]
```

<li>O ms-catalog deve escutar as mensagens enviadas via RabbitMQ pelo ms-order para diminuir o estoque das skus, a mensagemenviada pelo ms-order possui o seguinte formato:</li>

```
{
  "orderId": "6294d4b66f71221237b4d211",
  "skus": [
    {
      "id": 1,
      "quantity": 1
    },
    {
      "id": 2,
      "quantity": 5
    }
  ]
}
```

<p><strong>Observações:</strong></p>

<li>Usar PostgreSQL e RabbitMQ.</li>
<li>O endpoint GET - /v1/products/:id além de retornar as informações do produto, tem que listar todas as suas skus.</li>
<li>Ao desativar uma categoria todas as suas categorias filhas serão desativadas.</li>

</details>

<details>
  <summary><a id="id3"><h2><strong>MS Payment</strong></h2></summary>

<p>O MS Payment é o responsável por gerenciar todos os métodos de pagamentos disponíveis. O MS Payment possui os seguintes endpoints:</p>

```
POST - /v1/payments
GET - /v1/payments
PUT - /v1/payments/:id
DELETE - /v1/payments/:id

POST - /v1/installments
PUT - /v1/installments/:id
DELETE - /v1/installments/:id
```

<p><strong>Campos da tabela payments:</strong></p>

```
ID, TYPE, INSTALLMENTS, ACTIVE
```

<p><strong>Campos da tabela installments:</strong></p>

```
ID, AMOUNT, BRAND, PAYMENT_ID
```

<p><strong>Observação:</strong></p>
<li>O campo ID de todas as tabelas deve ser gerado por auto incremento.</li>
<li>Exemplo de um payload para cadastrar um método de pagamento:</li>

```
{
  "type": "credit card",
  "installments": true,
  "active": true
}
```

<p><strong>Validações necessárias:</strong></p>
<li>Todos os campos são obrigatórios.</li>
<li>Exemplo de um payload para cadastrar a quantidade de parcelas disponíveis naquele método de pagamento:</li>

```
{
  "amount": 5,
  "brand": "mastercard"
  "paymentId": 1
}
```

<p><strong>Validações necessárias:</strong></p>
<li>O campo brand não é obrigatório.</li>
<li>Tem que validar se o installments do paymentId informado é true.</li>
<li>O ms-payment deve escutar as mensagens enviadas via RabbitMQ pelo ms-order com relação ao processamento de pagamento de um pedido. Os ms-payment deve processar essa mensagem que possui o seguinte formato:</li>

```
{
  "orderId": "6294d4b66f71221237b4d211",
  "payment": {
    "id": 1,
    "installments": 0
  }
}
```

<li>Depois de realizar o processamento da mensagem o ms-payment deve retornar o resultado, que possui o seguinte formato:</li>

```
{
  "orderId": "6294d4b66f71221237b4d211",
  "status": "PAYMENT_SUCCESSFUL"
}
```

<p><strong>Os possíveis status que o ms-payment pode enviar para o ms-order são os seguintes:</strong></p>
<li>Pagamento realizado com sucesso - PAYMENT_SUCCESSFUL</li>
<li>Pagamento não existe no banco - PAYMENT_NOT_FOUND</li>
<li>Pagamento está inativado - PAYMENT_INACTIVE</li>
<li>Pagamento não aceita parcelamento - PAYMENT_NOT_INSTALLMENT</li>
<li>As parcelas informadas não estão dentro do limite definido - PAYMENT_AMOUNT_NOT_AVAILABLE</li>

<p><strong>Observações:</strong></p>
<li>Usar PostgreSQL e RabbitMQ.</li>

</details>

<details>
  <summary><a id="id4"><h2><strong>MS Order</strong></h2></summary>

<p>O MS Order é o responsável por gerenciar todos os pedidos de compra realizadas na aplicação. O MS Order possui os seguintes endpoints:</p>

```
POST - /v1/orders
GET - /v1/orders
GET - /v1/orders/customers/:customerId
```

<p><strong>Campos da coleção orders:</strong></p>

```
ID, CUSTOMER, PAYMENT, CART, DATE, STATUS, TOTAL
```

<p><strong>Exemplo de um payload para criar um pedido:</strong></p>

```
{
  "customer": {
    "id": 1,
    "addressId": 1
  },
  "payment": {
    "id": 1,
    "installments": 0
  },
  "cart": [
    {
      "skuId": 1,
      "quantity": 1
    },
    {
    "skuId": 2,
    "quantity": 5
    }
  ]
}
```

<p><strong>Validações necessárias:</strong></p>
<li>Todos os campos são obrigatórios.</li>
<li>Dado o valor id e addressId que está dentro do objeto customer, o ms-order deve se comunicar com o ms-customer para saber se esse usuário existe, se está ativo e se o endereço informado realmente existe, caso não deve retornar um erro.</li>
<li>Dado o valor skuId e quantity que estão dentro do objeto cart, os ms-order deve se comunicar com o ms-catalog para saber se existe essa sku e se tem disponível no estoque a quantidade solicitada, caso não atenda algum dos dois critérios deve retornar um erro.</li>
<li>Após realizar a inserção do documento, deve ser feito uma comunicação com o ms-payment para processar o pagamento desse pedido e o mesmo deve escutar o resultado enviado pelo ms-payment para atualizar o status do pedido no banco. Se o pagamento foi processado com sucesso, o ms-order deve enviar uma mensagem para o ms-catalog diminuir o estoque das skus do pedido.</li>

<p><strong>Observações:</strong></p>
<li>Usar o MongoDB</li>
<li>Na hora da inserção do documento na coleção deve ser calculado o total da compra, a partir do objeto cart é possível fazer esse calculo, assim como inserir a data e a hora que ocorreu a compra. O campo status deve ser salvo com o valor inicial de PROCESSING_PAYMENT.</li>
<li>O endpoint GET - /v1/orders necessita de três query param, sendo que um é obrigatório. O query param obrigatório é o startDate que informa a partir de qual data que deseja filtrar os pedidos realizados, o segundo query param é o endDate que usado em conjunto com o startDate define um intervalo de tempo dos pedidos realizados. O ultimo query param é o status para filtrar os pedidos a partir do seu status.</li>
<li>O endpoint GET - /v1/orders/customers/:customerId necessita de três query param, mas nenhum é obrigatório. O query param startDate informa a partir de qual data que deseja filtrar os pedidos realizados, o segundo query param é o endDate que usado em conjunto com o startDate define um intervalo de tempo dos pedidos realizados. O ultimo query param é o status para filtrar os pedidos a partir do seu status.</li>

</details>

<details>
  <summary><a id="id5"><h2><strong>MS Audit</strong></h2></summary>

<p>O MS audit é o micro-serviço responsável pela auditoria de todos os eventos que ocorreu no processamento de um pedido. O MS audit possui os seguinte endpoint:</p>

```
GET - /v1/audit/orders/:orderId
```

<p>Todos os eventos que transita entre o ms-order, ms-payment e ms-catalog devem ser salvas na base do ms-audit. Com essas informações salvas teremos uma visão ampla de todos os dados que foi transitado entre os micro-serviços que processa um pedido.</p>

<p><strong>Observações:</strong></p>
<li>Usar o MongoDB.</li>

</details>

<details>
  <summary><a id="id6"><h2><strong>MS BFF-Shop</strong></h2></summary>

<p>Todos os micro-serviços serão de uso interno para os funcionários da empresa. Então precisa ser disponibilizado um ponto de entrada para que os clientes possam se comunicar com as funcionalidades. O MS bff-shop tem os seguintes endpoints:</p>

```
POST - /v1/login

POST - /v1/customers
GET - /v1/customers/:id
PUT - /v1/customers/:id
PUT - /v1/customers/:id/password

POST - /v1/address
PUT - /v1/address/:id
DELETE - /v1/address/:id

GET - /v1/products
GET - /v1/products/:id

GET - /v1/categories
GET - /v1/categories/:id/products

GET - /v1/payments

POST - /v1/orders
GET - /v1/orders/customers/:customerId
```

<p><strong>Observação:</strong></p>

<li>Todos os endpoints precisam ser autenticados e autorizados via token JWT, exceto os endpoints POST - /v1/login e POST - /v1/customers.</li>

<h4><strong>ATENÇÃO: Esse projeto é para fins didáticos então algumas decisões de arquitetura foi tomada para ajudar no aprendizado de alguns conceitos, então algumas decisões técnicas usadas nesse projeto didático não serão arquitetados igualmente em projetos reais.</strong></h4>
