package com.example.demo.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.id = ?1")
    Optional<Ticket> findTicketById(Integer id);

    List<Ticket> findTicketsByProductIdAndVersionId(Integer productId, Integer versionId);

    //pregunta: como estoy guardando el Estado del Ticket en mi DB?
    List<Ticket> findTicketsByEstadoByProductIdAndVersionId(String estado, Integer productoId, Integer versionId);
}
