package Services;

import Models.Order;
import Models.User;

import java.util.List;

public class OrderService implements IModelService<Order> {

    @Override
    public void create(Order order) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public long nextId() {
        long maxIdOrder = 300000;
        List<Order> orderList = getAll();
        for (Order o : orderList){
            if (o.getIdUser() > maxIdOrder){
                maxIdOrder = o.getIdUser();
            }
        }
        return maxIdOrder + 1;
    }

    @Override
    public Order findById(long id) {
        return null;
    }
}
