package Models;

import Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Show {
    private long id;
    private String showName;
    private String singer;
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private ELocation location;

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", id, showName, singer, DateUtils.formatDate(timeStart), DateUtils.formatDate(timeEnd), location);
    }
}
