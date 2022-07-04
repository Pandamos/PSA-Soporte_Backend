package com.example.demo.ticket;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<TicketTable,Integer> {
    @Query(value = "SELECT * FROM ticket_table c WHERE c.version_id=:versionId",nativeQuery = true)
    public List<TicketTable> findByVersion(@Param("versionId") Integer versionId);


    //@Query("SELECT t FROM Ticket t WHERE t.id = ?1")
    //Optional<TicketTable> findTicketById(Integer id);

}
