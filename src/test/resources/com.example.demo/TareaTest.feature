
Feature: Interacción con tareas
  Scenario: Cliente hace POST a /proyectos/{id_ticket}/{id_proyecto}/tarea
    Given se quiere derivar un ticket
    When se crea una tarea
    Then se devuelve un status code de 200

  Scenario: Cliente hace un PUT a /tarea/{id_proyecto}/{id_tarea}/{id_ticket}
    Given se quiere derivar un ticket
    When se deriva a una tarea ya creada
    Then se devuelve un status code de 200

  Scenario: Cliente hace un GET a /proyectos/tareas
    Given se quiere recuperar un tarea
    When se busca una tarea con id conocido 421
    Then se la encuentra


