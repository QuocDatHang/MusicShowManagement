package Models;

public enum EGender {
    Male(1, "MALE"), Female(2, "FEMALE"), Other(3, "OTHER");

    EGender(long id, String name) {
        this.id = id;
        this.name = name;
    }
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static EGender findGenderById(long id){
        for (EGender e : EGender.values()){
            if (e.getId() == id){
                return e;
            }
        }
        return null;
    }
}
