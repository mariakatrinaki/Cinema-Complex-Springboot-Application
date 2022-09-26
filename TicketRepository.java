package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {


    List<Ticket> findByShowTime_IdShowTime(int idShowTime);

    @Transactional
    @Modifying
    @Query(value = "update cinema.ticket  " +
            "set ticket.pay=:ticket_status where " +
            "ticket.id_ticket=:id_ticket",nativeQuery = true)

    public void updateByIdTicket(@Param("id_ticket") int id_ticket, @Param("ticket_status") String ticketStatus);



}
