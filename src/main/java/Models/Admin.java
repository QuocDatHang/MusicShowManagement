package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Admin {
    private String accountName;
    private String password;

    @Override
    public String toString() {
        return accountName + "," + password;
    }
}
