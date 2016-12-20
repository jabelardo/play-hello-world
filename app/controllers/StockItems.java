package controllers;

import models.StockItem;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class StockItems extends Controller {
    public Result index() {
        List<StockItem> items = StockItem.find.findList();
        return ok(items.toString());
    }

}
