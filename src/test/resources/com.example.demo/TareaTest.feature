Feature: Interacción con tareas
  Scenario: Cliente hace POST a /proyectos/{id_ticket}/{id_proyecto}/tarea
  Given se quiere derivar un ticket
  When se crea una tarea
  And se la busca
  Then se encuentra la tarea

  Scenario: Cliente hace un PUT a /tarea/{id_proyecto}/{id_tarea}/{id_ticket}
    Given se quiere derivar un ticket
    When se deriva a una tarea ya creada
    Then el id del ticket se actualiza correctamente

