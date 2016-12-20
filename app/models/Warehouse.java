package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jabelardo on 12/12/16.
 */
@Entity
public class Warehouse {

    @Id
    public Long id;

    public String name;

    @OneToOne
    public Address address;

    @OneToMany(mappedBy = "warehouse")
    public List<StockItem> stock = new ArrayList<>();

    @Override
    public String toString() {
        return "Warehouse{" +
                "name='" + name +
                '}';
    }
}
