package com.example.demo;

import java.net.URI;
import java.util.*;

import com.example.demo.ticket.TicketRepository;
import com.example.demo.ticket.TicketTable;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@CucumberContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TicketTableTest {
    private final RestTemplate testRestTemplate = new RestTemplate();
    private TicketTable ticketTableEsperado = new TicketTable();
    private TicketTable ticketTableAuxiliar = new TicketTable();
    private ResponseEntity<TicketTable> latestResponse;

    @Autowired
    private TicketRepository ticketRepository;

    @Before
    public void setup() {
        ticketTableEsperado.setDescripcion("Descripcion Ticket 1");
        ticketTableEsperado.setSeveridad(1);
        ticketTableEsperado.setFechaDeCreacion("2022-07-02");
        ticketTableEsperado.setFechaDeFinalizacion("2022-07-12");
        ticketTableEsperado.setCuit("1-234-5");
        ticketTableEsperado.setEstado("abierto");
        ticketTableEsperado.setVersionId(2);
        ticketTableEsperado.setTitulo("Titulo ticket 1");
        ticketTableEsperado.setLegajoResponsable(14);

        ticketTableAuxiliar.setDescripcion("Descripcion Ticket 2");
        ticketTableAuxiliar.setSeveridad(2);
        ticketTableAuxiliar.setFechaDeCreacion("2022-07-03");
        ticketTableAuxiliar.setFechaDeFinalizacion("2022-07-10");
        ticketTableAuxiliar.setCuit("1-234-6");
        ticketTableAuxiliar.setEstado("abierto");
        ticketTableAuxiliar.setVersionId(2);
        ticketTableAuxiliar.setTitulo("Titulo ticket 2");
        ticketTableAuxiliar.setLegajoResponsable(15);

    }

    //crear ticket
    @Given("^soy un cliente")
    public void givenSoyUnCliente(){
    }

    @When("^el cliente crea un ticket")
    public void whenElClienteCreaUnTicket() {
        String url = "https://psa-soporte-mvp.herokuapp.com";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/soporte/ticket").queryParam("idVersion", 2).build().toUri();
        latestResponse = testRestTemplate.postForEntity(uri, ticketTableEsperado, TicketTable.class);
        ticketTableEsperado = latestResponse.getBody();
    }

    @And("^el cliente recibe un status code de (\\d+)")
    public void andElClienteRecibeUnStatusCodeDe(int statusCode) {
        HttpStatus currentStatusCode = latestResponse.getStatusCode();
        Assertions.assertEquals(statusCode, currentStatusCode.value());
    }

    @Then("^el ticket se crea")
    public void thenElTicketSeCrea() {
        TicketTable ticketTableActual = ticketRepository.findById(ticketTableEsperado.getId()).orElseThrow(() -> new IllegalStateException("ticket with id " + ticketTableEsperado.getId() + " does not exist"));
        Assertions.assertEquals(ticketTableEsperado.getId(), ticketTableActual.getId());
    }

    //modificar ticket

    @Given("^hay tickets cargados")
    public void givenHayTicketsCargados(){
        String url = "https://psa-soporte-mvp.herokuapp.com";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/soporte/ticket").queryParam("idVersion", 2).build().toUri();
        latestResponse = testRestTemplate.postForEntity(uri, ticketTableEsperado, TicketTable.class);
        ticketTableEsperado = latestResponse.getBody();

        latestResponse = testRestTemplate.postForEntity(uri, ticketTableAuxiliar, TicketTable.class);
        ticketTableAuxiliar = latestResponse.getBody();
    }

    @When("^el cliente modifica un ticket con exito")
    public void whenElClienteModificaUnTicketConExito() {
        testRestTemplate.put("https://psa-soporte-mvp.herokuapp.com/soporte/ticket/" + ticketTableAuxiliar.getId(), ticketTableEsperado, TicketTable.class);
    }


    @Then("^se actuliza el ticket")
    public void thenSeActualizaElTicket(){
        Assertions.assertEquals(ticketTableAuxiliar.getCuit(), ticketTableEsperado.getCuit());
        Assertions.assertEquals(ticketTableAuxiliar.getSeveridad(), ticketTableEsperado.getSeveridad());
        Assertions.assertEquals(ticketTableAuxiliar.getDescripcion(), ticketTableEsperado.getDescripcion());
        Assertions.assertEquals(ticketTableAuxiliar.getEstado(), ticketTableEsperado.getEstado());
        Assertions.assertEquals(ticketTableAuxiliar.getTitulo(), ticketTableEsperado.getTitulo());
        Assertions.assertEquals(ticketTableAuxiliar.getLegajoResponsable(), ticketTableEsperado.getLegajoResponsable());
        Assertions.assertEquals(ticketTableAuxiliar.getVersionId(), ticketTableEsperado.getVersionId());
        Assertions.assertNotEquals(ticketTableAuxiliar.getId(), ticketTableEsperado.getId());
    }

    //obtener ticket
    @When("^el cliente pide un ticket")
    public void whenElClientePideUnTicket() {
        Integer idTicket = ticketTableEsperado.getId();
        latestResponse = testRestTemplate.getForEntity("https://psa-soporte-mvp.herokuapp.com/soporte/ticket/{idTicket}", TicketTable.class, idTicket);
    }

    @Then("^se devuelve el ticket pedido")
    public void thenSeDevuelveElTicketPedido(){
        TicketTable ticketTableActual = ticketRepository.findById(ticketTableEsperado.getId()).orElseThrow(() -> new IllegalStateException("ticket with id" + ticketTableEsperado.getId() + "does not exist"));
        Assertions.assertEquals(latestResponse.getBody().getId(), ticketTableActual.getId());
    }


}
