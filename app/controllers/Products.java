package controllers;

import com.avaje.ebean.PagedList;
import com.google.common.io.Files;
import models.Product;
import models.Tag;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;
import utils.CatchAction;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jabelardo on 12/3/16.
 */

@With(CatchAction.class)
public class Products extends Controller {

    private final Form<Product> productForm;

    @Inject
    public Products(FormFactory formFactory) {
        productForm = formFactory.form(Product.class);
    }

    public Result index() {
        return redirect(routes.Products.list(0));
    }

    public Result list(Integer page) {
        PagedList<Product> products = Product.find(page);
        return ok(views.html.products.list.render(products, page));
    }

    public Result newProduct() {
        return ok(views.html.products.details.render(productForm));
    }

    public Result details(Product product) {
        Form<Product> filledForm = productForm.fill(product);
        return ok(views.html.products.details.render(filledForm));
    }

    public Result save() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Form<Product> boundForm = productForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(views.html.products.details.render(boundForm));
        }

        Product product = boundForm.get();

        Http.MultipartFormData.FilePart<File> part = body.getFile("picture");

        if (part != null) {
            File picture = part.getFile();
            try {
                product.picture = Files.toByteArray(picture);
            } catch (IOException e) {
                return internalServerError("Error reading file upload");
            }
        }

        List<Tag> tags = new ArrayList<>();
        for (Tag tag : product.tags) {
            if (tag.id != null) {
                tags.add(Tag.findById(tag.id));
            }
        }
        product.tags = tags;

        if (product.id == null) {
            product.save();
        } else {
            product.update();
        }

        flash("success",
                String.format("Successfully added product %s", product));
        return redirect(routes.Products.index());
    }

    public Result delete(String ean) {
        final Product product = Product.findByEan(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exists.", ean));
        }
        product.delete();
        return redirect(routes.Products.list(1));
    }
}
