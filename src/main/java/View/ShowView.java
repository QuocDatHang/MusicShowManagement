package View;

import Models.Show;
import Services.ShowService;
import Utils.DateUtils;
import Utils.ValidateUtils;
import Enum.ELocation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static View.AdminView.manageMusicShowMenu;

public class ShowView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ShowService iShowService = new ShowService();

    static void showAllShows() {
        List<Show> showList = iShowService.getAll();
        System.out.printf("%10s | %25s | %20s | %30s | %30s | %20s | %20s\n", "ID SHOW", "SHOW NAME", "SINGER",
                "TIME START", "TIME END", "LOCATION", "SHOW PRICE(VND)");
        for (Show s : showList){
            System.out.printf("%10s | %25s | %20s | %30s | %30s | %20s | %20.0f\n", s.getIdShow(), s.getShowName(),
                    s.getSinger(), DateUtils.formatDateTime(s.getTimeStart()), DateUtils.formatDateTime(s.getTimeEnd()), s.getLocation(), s.getShowPrice());
        }
        manageMusicShowMenu();
    }

    static void addShow() {
        String showName = inputShowName();
        String singer = inputSinger();
        LocalDateTime timeStart = inputTimeStart();
        LocalDateTime timeEnd = inputTimeEnd();
        ELocation location = inputLocation();
        double showPrice = Double.parseDouble(inputShowPrice());

        Show show = new Show(iShowService.nextId(), showName, singer, timeStart, timeEnd, location, showPrice);
        iShowService.create(show);
        manageMusicShowMenu();
    }

    static void editShow(){

    }

    static void deleteShow(){
        System.out.print("Enter id show to delete: ");
        long idShow = Long.parseLong(scanner.nextLine());
        iShowService.delete(idShow);
        manageMusicShowMenu();
    }

    private static String inputShowName(){
        boolean validateName = true;
        String showName;
        do {
            System.out.print("Enter show name: ");
            showName = scanner.nextLine();
            if (ValidateUtils.isValidShowName(showName)){
                validateName = false;
            } else {
                System.out.println("'Show Name' must start with an alphabetical character or number," +
                        " included 2-30 character");
            }
        } while (validateName);
        return showName;
    }
    private static String inputSinger(){
        boolean validateSinger = true;
        String singer;
        do {
            System.out.print("Enter singer: ");
            singer = scanner.nextLine();
            if (ValidateUtils.isValidSinger(singer)){
                validateSinger = false;
            } else {
                System.out.println("'Singer' must start with an alphabetical character," +
                        " included 2-20 character");
            }
        } while (validateSinger);
        return singer;
    }
    private static ELocation inputLocation(){
        System.out.print("Choose location: ");
        for (ELocation e : ELocation.values()) {
            System.out.print(e.getId() + ". " + e +"\t");
        }
        ELocation location;
        do {
            long idLocation = Long.parseLong(scanner.nextLine());
            location = ELocation.findLocationById(idLocation);
        }
        while (location == null);
        return location;
    }
    private static LocalDateTime inputTimeStart(){
        LocalDateTime timeStart;
        do {
            System.out.print("Enter time start (dd-mm-yyyy hh:mm): ");
            timeStart = DateUtils.parseDateTime(scanner.nextLine());
        }
        while (timeStart == null);
        return timeStart;
    }
    private static LocalDateTime inputTimeEnd(){
        LocalDateTime timeEnd;
        do {
            System.out.print("Enter time end (dd-mm-yyyy hh:mm): ");
            timeEnd = DateUtils.parseDateTime(scanner.nextLine());
        }
        while (timeEnd == null);
        return timeEnd;
    }
    private static String inputShowPrice(){
        boolean validateShowPrice = true;
        String showPrice;
        do {
            System.out.print("Enter show price: ");
            showPrice = scanner.nextLine();
            if (ValidateUtils.isValidShowPrice(showPrice)){
                validateShowPrice = false;
            } else {
                System.out.println("'Show Price' is a number");
            }
        } while (validateShowPrice);
        return showPrice;
    }

}
