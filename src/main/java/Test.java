import Utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Test {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String current = DateUtils.formatDateTime(LocalDateTime.now());
        System.out.println(current);

    }
}
