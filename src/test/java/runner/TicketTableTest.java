package runner;

import java.util.*;

import com.example.demo.ticket.TicketRepository;
import com.example.demo.ticket.TicketTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import org.junit.Before;
import org.springframework.http.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.web.client.TestRestTemplate;

@CucumberContextConfiguration
@SpringBootTest
@Transactional
public class TicketTableTest {
    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
    private TicketTable ticketTableEsperado;
    private ArrayList <TicketTable> ticketsEsperados = new ArrayList<>();
    private TicketTable ticketTableAuxiliar;
    private ResponseEntity<TicketTable> latestResponse;
    private ResponseEntity<TicketTable[]> latestResponseArray;

    private ObjectMapper objectMapper;

    @Autowired
    private TicketRepository ticketRepository;

    @Before
    public void setup() {
        ticketTableEsperado = new TicketTable(100, "Descripcion Ticket 1", 1, "2022-07-02", "2022-07-12", "1-234-5", "abierto", 2, "Titulo ticket 1", 14);
        ticketTableAuxiliar = new TicketTable(200, "Descripcion Ticket 2", 2, "2022-07-03", "2022-07-10", "1-234-6", "abierto", 2, "Titulo ticket 2", 15);
    }

    //crear ticket
    @Given("^soy un cliente")
    public void givenSoyUnCliente(){
    }

    @When("^el cliente crea un ticket")
    public void whenElClienteCreaUnTicket(){
        latestResponse = testRestTemplate.postForEntity("https://psa-soporte-mvp.herokuapp.com/soporte/ticket?idVersion=" + 2,
                                                        ticketTableEsperado, TicketTable.class);
    }

    @And("^el cliente recibe un status code CREATED")
    public void andElClienteRecibeUnStatusCodeCREATED() {
        HttpStatus currentStatusCode = null;
        if (latestResponse != null) {
            currentStatusCode = latestResponse.getStatusCode();
        } else {
            currentStatusCode = latestResponseArray.getStatusCode();
        }
        Assertions.assertEquals(HttpStatus.CREATED.value(), currentStatusCode.value());
    }

    @Then("^el ticket se crea")
    public void thenElTicketSeCrea() {
        TicketTable ticketTableActual = ticketRepository.findById(ticketTableEsperado.getId()).orElseThrow(() -> new IllegalStateException("ticket with id" + ticketTableEsperado.getId() + "does not exist"));
        Assertions.assertEquals(ticketTableActual.getId(), ticketTableEsperado.getId());
    }

    //modificar ticket

    @Given("^hay tickets cargados")
    public void givenHayTicketsCargados(){
        ticketRepository.save(ticketTableEsperado);
        ticketRepository.save(ticketTableAuxiliar);
    }

    @When("^el cliente modifica un ticket con exito")
    public void whenElClientemodificaUnTicketConExito() {
        testRestTemplate.put("https://psa-soporte-mvp.herokuapp.com/soporte/ticket/" + ticketTableAuxiliar.getId(), ticketTableEsperado, TicketTable.class);
    }


    @Then("^se actuliza el ticket")
    public void thenSeActualizaElTicket(){
        Assertions.assertEquals(ticketTableAuxiliar.getCuit(), ticketTableEsperado.getCuit());
        Assertions.assertEquals(ticketTableAuxiliar.getSeveridad(), ticketTableEsperado.getSeveridad());
        Assertions.assertEquals(ticketTableAuxiliar.getDescripcion(), ticketTableEsperado.getDescripcion());
        Assertions.assertEquals(ticketTableAuxiliar.getEstado(), ticketTableEsperado.getEstado());
    }

    //obtener ticket


    //derivar ticket

}
