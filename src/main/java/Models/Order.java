package Models;

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

public class Order {
    private long idOrder;
    private long idUser;
    List<Ticket> ticketList;
    private LocalDateTime timeCreate;
    private long totalPrice;
}
