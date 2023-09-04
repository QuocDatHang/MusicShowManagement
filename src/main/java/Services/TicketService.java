package Services;

import Models.Order;
import Models.Ticket;

import java.util.List;

public class TicketService implements IModelService<Ticket> {

    @Override
    public void create(Ticket ticket) {

    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public long nextId() {
        long maxIdTicket = 400000;
        List<Ticket> orderList = getAll();
        for (Ticket t : orderList){
            if (t.getIdTicket() > maxIdTicket){
                maxIdTicket = t.getIdTicket();
            }
        }
        return maxIdTicket + 1;
    }

    @Override
    public Ticket findById(long id) {
        return null;
    }
}
