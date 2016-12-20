package models;

import javax.inject.Singleton;
import java.util.Arrays;

/**
 * Created by jabelardo on 12/4/16.
 */
@Singleton
public class InitModels {
    public InitModels() {
        if (Product.findAll().isEmpty()) {
            new Product("1111111111111", "Paperclips 1", "Paperclips description 1").save();
            new Product("2222222222222", "Paperclips 2", "Paperclips description 2").save();
            new Product("3333333333333", "Paperclips 3", "Paperclips description 3").save();
            new Product("4444444444444", "Paperclips 4", "Paperclips description 4").save();
            new Product("5555555555555", "Paperclips 5", "Paperclips description 5").save();
            new Product("6666666666666", "Paperclips 6", "Paperclips description 6").save();

            new Tag("lightweight",
                    Arrays.asList(Product.findByEan("1111111111111"), Product.findByEan("4444444444444"))).save();

            new Tag("metal",
                    Arrays.asList(Product.findByEan("2222222222222"), Product.findByEan("4444444444444"))).save();

            new Tag("plastic",
                    Arrays.asList(Product.findByEan("3333333333333"), Product.findByEan("5555555555555"))).save();

        }
    }
}
