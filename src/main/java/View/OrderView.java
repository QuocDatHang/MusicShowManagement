package View;

import Models.*;
import Services.*;
import Utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Enum.EStatus;

import static Services.SeatService.showSeatList;
import static View.MainView.mainMenu;
import static View.ShowView.getAllMusicShows;
import static View.UserView.*;

public class OrderView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final OrderService orderService = new OrderService();
    private static final TicketService ticketService = new TicketService();
    private static final ShowService showService = new ShowService();
    private static final SeatService seatService = new SeatService();

    static void orderMenu() {
        System.out.println("                ╔════════════════════════════════════════════╗");
        System.out.println("                ║           ORDER MANAGEMENT MENU            ║");
        System.out.println("                ║      1. Show All Orders                    ║");
        System.out.println("                ║      2. Get Revenue                        ║");
        System.out.println("                ║      0. Return                             ║");
        System.out.println("                ╚════════════════════════════════════════════╝");

        int choice = MainView.isValidChoice(0, 2);
        switch (choice) {
            case 1: {
                showAllOrders();
                break;
            }
            case 2: {
                showRevenue();
                break;
            }
            case 0: {
                mainMenu();
                break;
            }
        }
    }

    private static void showRevenue() {
        LocalDateTime dayStart = inputDayTime("day start");
        LocalDateTime dayEnd = inputDayTime("day end");
        List<Order> orderListByInputTime = orderService.getOrdersByInputTime(dayStart, dayEnd);
        long revenue = orderService.getRevenue(orderListByInputTime);
        showOrder(orderListByInputTime);
        System.out.printf("%46s | %15s\n", "REVENUE", revenue);
        orderMenu();
    }

    private static void showAllOrders() {
        List<Order> orderList = orderService.getAll();
        System.out.printf("%10s | %10s | %20s | %15s\n", "ID ORDER", "ID USER", "TIME CREATE", "TOTAL PRICE");
        for (Order o : orderList) {
            System.out.printf("%10s | %10s | %20s | %15s\n", o.getIdOrder(), o.getIdUser(),
                    DateUtils.formatDateTime(o.getTimeCreate()), o.getTotalPrice());
        }
        orderMenu();
    }
    private static void showOrder(List<Order> orderList) {
        System.out.printf("%10s | %10s | %20s | %15s\n", "ID ORDER", "ID USER", "TIME CREATE", "TOTAL PRICE");
        for (Order o : orderList) {
            System.out.printf("%10s | %10s | %20s | %15s\n", o.getIdOrder(), o.getIdUser(),
                    DateUtils.formatDateTime(o.getTimeCreate()), o.getTotalPrice());
        }
    }

    public static void yourOrder(long idUser) {
        System.out.printf("%10s | %15s | %20s | %20s | %20s | %20s | %10s | %15s | %15s\n", "ID ORDER", "USER NAME",
                "SHOW NAME", "SINGER", "TIME START", "TIME END", "LOCATION", "SEAT POSITION", "TICKET PRICE");
        List<Order> orderList = orderService.findOrderByIdUser(idUser);
        User user = userService.findById(idUser);
        for (Order order : orderList) {
            List<Ticket> ticketList = ticketService.findByIdOrder(order.getIdOrder());
            for (Ticket t : ticketList) {
                long idShow = t.getIdShow();
                Show show = showService.findById(idShow);
                long idTicket = t.getIdTicket();
                Ticket ticket = ticketService.findById(idTicket);
                long idSeat = t.getIdSeat();
                Seat seat = seatService.findSeatById(idSeat);
                System.out.printf("%10s | %15s | %20s | %20s | %20s | %20s | %10s | %15s | %15s\n", order.getIdOrder(), user.getName(),
                        show.getShowName(), show.getSinger(), DateUtils.formatDateTime(show.getTimeStart()),
                        DateUtils.formatDateTime(show.getTimeEnd()), show.getLocation(), seat.getSeatPosition(), ticket.getTicketPrice());
            }
        }
        userMenu(idUser);
    }

    public static void bookTicket(long idUser) {
        Order order = new Order();
        long idOrder = orderService.nextId();
        boolean checkContinueBooking = false;
        List<Ticket> ticketList = new ArrayList<>();
        do {
            inputTicket(idOrder, ticketList);

            System.out.println("Do you want to book one more ticket? (Y/N)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "Y":
                case "y": {
                    checkContinueBooking = true;
                    break;
                }
                case "N":
                case "n": {
                    checkContinueBooking = false;
                    break;
                }
                default: {
                    System.out.println("Please choose Y or N!");
                    checkContinueBooking = true;
                    break;
                }
            }
        } while (checkContinueBooking);


        long totalPrice = 0;
        for (Ticket t : ticketList) {
            totalPrice += t.getTicketPrice();
        }
        order.setIdOrder(idOrder);
        order.setIdUser(idUser);
        order.setTimeCreate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);
        orderService.create(order);
        userMenu(idUser);
    }

    public static void inputTicket(long idOrder, List<Ticket> ticketList) {
        getAllMusicShows();
        System.out.print("Enter id show want to book: ");
        long idShow = Long.parseLong(scanner.nextLine());
        Show show = showService.findById(idShow);
        List<Seat> seatList = seatService.showSeatListByLocation(show.getLocation());
        boolean isValidSeat = true;
        long idSeat;
        do {
            showSeatList(seatList);
            System.out.println("Choose your seat (id seat): ");
            idSeat = Long.parseLong(scanner.nextLine());
            for (Seat s : seatList) {
                if (s.getIdSeat() == idSeat && s.getStatus().equals(EStatus.AVAILABLE)) {
                    isValidSeat = false;
                    seatService.changeSeatStatus(s.getIdSeat());
                    break;
                }
            }
            if (isValidSeat) {
                System.out.println("No valid seat, please choose again!");
            }
        }
        while (isValidSeat);

        Ticket ticket = new Ticket(ticketService.nextId(), idOrder, idShow, idSeat, show.getShowPrice());
        ticketService.create(ticket);
        ticketList.add(ticket);
    }

    public static LocalDateTime inputDayTime(String message) {
        LocalDateTime day;
        do {
            System.out.print("Enter " + message + " 'dd-mm-yyyy hh:mm': ");
            day = DateUtils.parseDateTime(scanner.nextLine());
        }
        while (day == null);
        return day;
    }
}
