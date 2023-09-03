package Models;

import Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Order implements IParseModel{
    private long idOrder;
    private long idUser;
    List<Ticket> ticketList;
    private LocalDateTime timeCreate;
    private long totalPrice;

    public Order(long idOrder, long idUser, LocalDateTime timeCreate, long totalPrice) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.timeCreate = timeCreate;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", idOrder, idUser, DateUtils.formatDateTime(timeCreate), totalPrice);
    }

    @Override
    public Order parse(String line) {
        String[] str = line.split(",");
        Order o = new Order(Long.parseLong(str[1]), Long.parseLong(str[2]), DateUtils.parseDateTime(str[3]), Long.parseLong(str[4]));
        return o;
    }
}
