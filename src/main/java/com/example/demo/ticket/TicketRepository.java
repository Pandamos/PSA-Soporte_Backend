package com.example.demo.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketTable,Integer> {

    //@Query("SELECT t FROM Ticket t WHERE t.id = ?1")
    //Optional<TicketTable> findTicketById(Integer id);

    //List<TicketTable> findTicketsByProductIdAndVersionId(Integer productId, Integer versionId);

    //pregunta: como estoy guardando el Estado del Ticket en mi DB?
    //List<TicketTable> findTicketsByEstadoByProductIdAndVersionId(String estado, Integer productoId, Integer versionId);
}
