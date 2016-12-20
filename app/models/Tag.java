package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.*;

/**
 * Created by jabelardo on 12/4/16.
 */
@Entity
public class Tag extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @ManyToMany(mappedBy = "tags")
    public List<Product> products;

    public Tag() {}

    public Tag(String name, Collection<Product> products) {
        this.name = name;
        this.products = new ArrayList<>(products);
        for (Product product : products) {
            product.tags.add(this);
        }
    }

    public static Model.Finder<Long, Tag> find = new Model.Finder<>(Tag.class);

    public static Tag findById(Long id) {
        return find.byId(id);
    }
}
