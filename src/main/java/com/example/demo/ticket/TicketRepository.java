package com.example.demo.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<TicketTable,Integer> {


    @Query(value = "SELECT * FROM ticket_table c WHERE c.version_id=:version_id",nativeQuery = true)
    public List<TicketTable> findByVersion(@Param("version_id") Integer versionId);
    //@Query("SELECT t FROM Ticket t WHERE t.id = ?1")
    //Optional<TicketTable> findTicketById(Integer id);

    //List<TicketTable> findTicketsByProductIdAndVersionId(Integer productId, Integer versionId);

    //pregunta: como estoy guardando el Estado del Ticket en mi DB?
    //List<TicketTable> findTicketsByEstadoByProductIdAndVersionId(String estado, Integer productoId, Integer versionId);
}
