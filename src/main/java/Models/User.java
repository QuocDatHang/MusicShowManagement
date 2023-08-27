package Models;

import Utils.DateUtils;
import Utils.IdUtils;
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
    private String address;
    private String phoneNumber;
    private EGender gender;
    private ERole role;

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", id, name, accountName, password, DateUtils.formatDate(dob), address, phoneNumber, gender, role);
    }

    public static void main(String[] args) {
        long c = IdUtils.nextIdUser();
        System.out.println(c);
        long d = IdUtils.nextIdUser();
        System.out.println(d);
    }
}
