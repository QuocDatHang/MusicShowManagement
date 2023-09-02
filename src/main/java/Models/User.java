package Models;

import Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import Enum.EGender;
import Enum.ERole;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User implements IParseModel {
    private long idUser;
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
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", idUser, name, accountName, password, DateUtils.formatDate(dob), email, address, phoneNumber, gender, role);
    }

    @Override
    public User parse(String line) {
        String[] str = line.split(",");
        User u = new User(Long.parseLong(str[0]), str[1], str[2], str[3], DateUtils.parseDate(str[4]),
                str[5], str[6], str[7], EGender.valueOf(str[8]), ERole.valueOf(str[9]));
        return u;
    }
}
