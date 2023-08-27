package Models;

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
    private long seatQuantity;
    private String location;

}
