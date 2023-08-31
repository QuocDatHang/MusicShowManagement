package Models;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public enum ELocation {
    HaNoi(1,20),
    Hue(2, 15),
    DaNang(3, 25),
    SaiGon(4, 30);

    ELocation(long id, long seatQuantity){
        this.id = id;
        this.seatQuantity = seatQuantity;
    }
    private long id;
    private long seatQuantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(long seatQuantity) {
        this.seatQuantity = seatQuantity;
    }

    public static ELocation findLocationById(long id){
        for (ELocation e : ELocation.values()){
            if (e.getId() == id){
                return e;
            }
        }
        return null;
    }
}
