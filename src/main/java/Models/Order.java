package Models;

import Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Order implements IParseModel{
    private long idOrder;
    private long idUser;
    private LocalDateTime timeCreate;
    private long totalPrice;

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", idOrder, idUser, DateUtils.formatDateTime(timeCreate), totalPrice);
    }

    @Override
    public Order parse(String line) {
        String[] str = line.split(",");
        Order o = new Order(Long.parseLong(str[0]), Long.parseLong(str[1]), DateUtils.parseDateTime(str[2]), Long.parseLong(str[3]));
        return o;
    }

}
