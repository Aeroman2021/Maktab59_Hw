package jdbcExample.entity.base;

public interface SecondaryBaseEntity<IDOne extends Number,IDTwo extends Number> {
    void setIdOne(IDOne id);
    IDOne getIdOne();
    void setIdTwo(IDTwo id);
    IDTwo getIdTwo();
}
