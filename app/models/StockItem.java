package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by jabelardo on 12/12/16.
 */
@Entity
public class StockItem extends Model {

    @ManyToOne
    public Warehouse warehouse;

    @ManyToOne
    public Product product;

    public Long quantity;

    public static Finder<Long, StockItem> find = new Finder<>(StockItem.class);


    @Override
    public String toString() {
        return "StockItem{" +
                "warehouse=" + (warehouse == null ? "null" : warehouse.name) +
                ", product=" + (product == null ? "null" : product.name) +
                ", quantity=" + quantity +
                '}';
    }
}
