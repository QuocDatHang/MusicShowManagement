package Models;

import Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import Enum.ELocation;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Show implements IParseModel {
    private long idShow;
    private String showName;
    private String singer;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private ELocation location;
    private double showPrice;

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%.0f", idShow, showName, singer, DateUtils.formatDateTime(timeStart), DateUtils.formatDateTime(timeEnd), location, showPrice);
    }

    @Override
    public Show parse(String line) {
        String[] str = line.split(",");
        Show s = new Show(Long.parseLong(str[0]), str[1], str[2],DateUtils.parseDateTime(str[3]),
                DateUtils.parseDateTime(str[4]), ELocation.valueOf(str[5]), Double.parseDouble(str[6]));
        return s;
    }
}
