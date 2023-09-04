package Services;

import Models.Seat;
import Enum.ELocation;
import Enum.EStatus;
import Utils.FileUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeatService {
    private String fileSeat = "./data/Seats.txt";
    public List<Seat> getAllSeats(){
        return FileUtils.readData(fileSeat, Seat.class);
    }
    public List<Seat> showSeatListByLocation(ELocation location){
        List<Seat> seatList = getAllSeats();
        return seatList.stream().filter(seat -> seat.getLocation().equals(location)).collect(Collectors.toList());
    }
    public Seat findSeatById(long id){
        List<Seat> seatList = getAllSeats();
        return seatList.stream().filter(seat -> seat.getIdSeat() == id).findFirst().orElse(null);
    }
    public void changeSeatStatus(long id){
        List<Seat> seatList = getAllSeats();
        Seat s = seatList.stream().filter(seat -> seat.getIdSeat() == id).findFirst().orElse(null);
        if (s.getStatus().equals(EStatus.AVAILABLE)){
            s.setStatus(EStatus.SELECTED);
        } else {
            s.setStatus(EStatus.AVAILABLE);
        }
        FileUtils.writeData(fileSeat, seatList);
    }
    public static void showSeatList(List<Seat> seatList){
        System.out.printf("%15s | %15s | %20s\n", "ID SEAT", "SEAT POSITION", "STATUS");
        for (Seat s: seatList){
            System.out.printf("%15s | %15s | %20s\n", s.getIdSeat(), s.getSeatPosition(), s.getStatus());
        }
    }
}
