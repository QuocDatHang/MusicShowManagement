package Utils;

public class IdUtils {
    private static long idUser = 10000;
    public static long idShow = 20000;
    public static long idTicket = 30000;
    private static final Object idLock = new Object();
    public static synchronized long nextIdUser(){
        synchronized (idLock) {
            return idUser++;
        }
    }
    public static synchronized long nextIdShow(){
        return idShow++;
    }    public static synchronized long nextIdTicket(){
        return idTicket++;
    }

}
