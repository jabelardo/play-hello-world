package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import play.data.validation.Constraints;
import play.mvc.PathBindable;
import play.mvc.QueryStringBindable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jabelardo on 12/3/16.
 */
@Entity
public class Product extends Model implements PathBindable<Product>, QueryStringBindable<Product> {

    @Id
    public Long id;

    @Constraints.Required
    public String ean;

    @Constraints.Required
    public String name;

    public byte[] picture;

    public String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product__tag",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    public List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<StockItem> stockItems = new ArrayList<>();

    public Product() {
    }

    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ean='" + ean + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static List<Product> findAll() {
        return find.all();
    }

    public static Product findByEan(String ean) {
        return find.where().eq("ean", ean).findUnique();
    }

    public static List<Product> findByName(String name) {
        return find.where().eq("name", name.toLowerCase()).findList();
    }

    public void save() {
        StockItem item = new StockItem();
        item.quantity = 0L;
        item.product = this;

        super.save();
        item.save();
    }

    public static Finder<Long, Product> find = new Finder<>(Product.class);

    @Override
    public Product bind(String key, String value) {
        return findByEan(value);
    }

    @Override
    public Optional<Product> bind(String key, Map<String, String[]> data) {
        return Optional.ofNullable(findByEan(data.get("ean")[0]));
    }

    @Override
    public String unbind(String key) {
        return ean;
    }

    @Override
    public String javascriptUnbind() {
        return ean;
    }

    public static PagedList<Product> find(int page) {
        return find.where()
                .orderBy("id asc")
                .setFirstRow(page * 10)
                .setMaxRows(10)
                .findPagedList();
    }
}
