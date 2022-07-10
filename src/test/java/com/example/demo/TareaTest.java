package com.example.demo;

import java.util.*;

import com.example.demo.tarea.Tarea;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TareaTest {

    private final RestTemplate testRestTemplate = new RestTemplate();

    private Tarea tareaEsperada = new Tarea();
    private Tarea tareaAuxiliar = new Tarea();
    private ResponseEntity<Tarea> latestResponse;

    @Before
    public void setup() {
        tareaEsperada.setDescripcion("Crear menú interactivo de la minuta #213");
        tareaEsperada.setEstado("development");
        tareaEsperada.setNombre("Menú interactivo");
        tareaEsperada.setFechaCreacion("2022-07-01");
        tareaEsperada.setIdProyecto(414);
    }

    //crear y buscar tarea
    @Given("^se quiere derivar un ticket")
    public void givenSeQuiereDerivarUnTicket() {}

    @When("^se crea una tarea")
    public void whenSeCreaUnaTarea() {
        String url = "https://psa-soporte-mvp.herokuapp.com/proyectos/" + 34 + "/" + 414 + "/tarea";
        latestResponse = testRestTemplate.postForEntity(url, tareaEsperada, Tarea.class);
        System.out.println("el latest response es " + latestResponse);

    }

    @Then("^se devuelve un status code de (\\d+)")
    public void thenSeDevuelveUnStatusCodeDe(int statusCode) {
        HttpStatus currentStatusCode = latestResponse.getStatusCode();
        Assertions.assertEquals(statusCode, currentStatusCode.value());
    }

    //modificar tarea
    @When("^se deriva a una tarea ya creada")
    public void whenSeDerivaUnaTareaYaCreada() {
        String url = "https://psa-soporte-mvp.herokuapp.com/proyectos/tarea/" + 413 + "/" + 421 + "/" + 12;
        latestResponse = testRestTemplate.exchange(url, HttpMethod.PUT, null, Tarea.class);
    }

    @Given("^se quiere recuperar un tarea")
    public void givenSeQuiereRecuperarUnaTarea(){

    }


    @When("^se busca una tarea con id conocido (\\d+)")
    public void whenSeBuscaUnaTareaConIdConocido(Integer idTarea) {
        String url = "https://psa-soporte-mvp.herokuapp.com/proyectos/tareas";
        Tarea[] tareas = testRestTemplate.getForEntity(url, Tarea[].class).getBody();
        tareaAuxiliar = Arrays.stream(tareas).filter(t->t.getId().equals(idTarea)).findFirst().get();
        tareaEsperada.setId(idTarea);
    }


    @Then("^se la encuentra")
    public void thenSeLaEncuentra() {
        Assertions.assertEquals(tareaAuxiliar.getId(), tareaEsperada.getId());
    }

/*
    @And ("^se la busca")
    public void andSeLaBusca() {
        {
        }
    }
*/


    /*@Then ("^se encuentra la tarea")
    public void thenSeLaEncuentra() {
        Assertions.assertEquals(tareaEsperada.getIdTicket(), tareaAuxiliar.getIdTicket());
        Assertions.assertEquals(tareaEsperada.getDescripcion(), tareaAuxiliar.getDescripcion());
        Assertions.assertEquals(tareaEsperada.getEstado(), tareaAuxiliar.getEstado());
        Assertions.assertEquals(tareaEsperada.getNombre(), tareaAuxiliar.getNombre());
        Assertions.assertEquals(tareaEsperada.getIdProyecto(), tareaAuxiliar.getIdProyecto());
        Assertions.assertEquals(tareaEsperada.getId(), tareaAuxiliar.getId());
    }*/

}
