package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Enum.EStatus;
import Enum.ELocation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Seat implements IParseModel{
    private long idSeat;
    private ELocation location;
    private String seatPosition;
    private EStatus status;

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", idSeat, location, seatPosition, status);
    }

    @Override
    public Object parse(String line) {
        String[] str = line.split(",");
        Seat s = new Seat(Long.parseLong(str[0]), ELocation.valueOf(str[1]), str[2], EStatus.valueOf(str[3]));
        return s;
    }
}
