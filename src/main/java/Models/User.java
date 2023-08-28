package Models;

import Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private long id;
    private String name;
    private String accountName;
    private String password;
    private LocalDate dob;
    private String email;
    private String address;
    private String phoneNumber;
    private EGender gender;
    private ERole role;

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", id, name, accountName, password, DateUtils.formatDate(dob), email, address, phoneNumber, gender, role);
    }

}
