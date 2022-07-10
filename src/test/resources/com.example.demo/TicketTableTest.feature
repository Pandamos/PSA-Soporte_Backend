Feature: Interacción con tickets
  Scenario: Cliente hace POST a /soporte/ticket
    Given soy un cliente
    When el cliente crea un ticket
    Then el ticket se crea
    And el cliente recibe un status code de 201


  Scenario: Cliente hace PUT a /soporte/ticket/{idTicket}
    Given hay tickets cargados
    When el cliente modifica un ticket con exito
    Then se actuliza el ticket


  Scenario: Cliente hace GET a /soporte/ticket/{idTicket}
    Given hay tickets cargados
    When el cliente pide un ticket
    Then se devuelve el ticket pedido
