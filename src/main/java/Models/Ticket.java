package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Ticket implements IParseModel<Ticket> {
    private long idTicket;
    private long idOrder;
    private long idShow;
    private long idSeat;
    private long ticketPrice;

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", idTicket, idOrder, idShow, idSeat, ticketPrice);
    }

    @Override
    public Ticket parse(String line) {
        String[] str = line.split(",");
        Ticket t = new Ticket(Long.parseLong(str[0]), Long.parseLong(str[1]), Long.parseLong(str[2]), Long.parseLong(str[3]), Long.parseLong(str[4]));
        return t;
    }
}

