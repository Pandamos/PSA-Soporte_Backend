package com.example.demo;

import java.net.URI;
import java.util.*;

import com.example.demo.tarea.Tarea;
import com.example.demo.ticket.TicketRepository;
import com.example.demo.ticket.TicketTable;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import scala.Option;
import scala.util.parsing.json.JSON;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TareaTest {

    private final RestTemplate testRestTemplate = new RestTemplate();

    private Tarea tareaEsperada = new Tarea();
    private Tarea tareaEsperada2 = new Tarea();
    private Tarea tareaAuxiliar = new Tarea();
    private ResponseEntity<Tarea> latestResponse;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        tareaEsperada.setDescripcion("Crear menú interactivo de la minuta #213");
        tareaEsperada.setEstado("development");
        tareaEsperada.setNombre("Menú interactivo");
        tareaEsperada.setFechaCreacion("2022-07-01");
        tareaEsperada.setIdProyecto(414);

        tareaEsperada2.setDescripcion("Crear sidebar interactivo de la minuta #213");
        tareaEsperada2.setEstado("development");
        tareaEsperada2.setNombre("Sidebar interactivo");
        tareaEsperada2.setFechaCreacion("2022-07-01");
        tareaEsperada2.setIdProyecto(414);
    }

    //crear y buscar tarea
    @Given("^se quiere derivar un ticket")
    public void givenSeQuiereDerivarUnTicket() {}

    @When("^se crea una tarea")
    public void whenSeCreaUnaTarea() {
        String url = "https://psa-soporte-mvp.herokuapp.com/proyectos/" + 34 + "/" + 414 + "/tarea";
        latestResponse = testRestTemplate.postForEntity(url, tareaEsperada, Tarea.class);
        tareaEsperada = latestResponse.getBody();
        System.out.println(tareaEsperada.getId());
    }

    @And ("^se la busca")
    public void andSeLaBusca() {
        String url = "https://psa-soporte-mvp.herokuapp.com/proyectos/tareas";
        //ResponseEntity<Tarea[]> tareas = testRestTemplate.getForEntity(url, Tarea[].class);
        Tarea[] tareas = testRestTemplate.getForEntity(url, Tarea[].class).getBody();
        tareaAuxiliar = Arrays.stream(tareas).filter(t->t.getId().equals(tareaEsperada.getId())).findFirst().get();
    }


    @Then ("^se encuentra la tarea")
    public void thenSeLaEncuentra() {
        Assertions.assertEquals(tareaEsperada.getIdTicket(), tareaAuxiliar.getIdTicket());
        Assertions.assertEquals(tareaEsperada.getDescripcion(), tareaAuxiliar.getDescripcion());
        Assertions.assertEquals(tareaEsperada.getEstado(), tareaAuxiliar.getEstado());
        Assertions.assertEquals(tareaEsperada.getNombre(), tareaAuxiliar.getNombre());
        Assertions.assertEquals(tareaEsperada.getIdProyecto(), tareaAuxiliar.getIdProyecto());
        Assertions.assertEquals(tareaEsperada.getId(), tareaAuxiliar.getId());
    }


    //modificar tarea


}
