package hw8.q4.backend.entities;

public class Stadium {

    private Integer id;
    private StadiumName name;
    private int capacity;

    public Stadium(Integer id, StadiumName name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StadiumName getName() {
        return name;
    }

    public void setName(StadiumName name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}
