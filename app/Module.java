import com.google.inject.AbstractModule;
import models.InitModels;
import play.data.format.Formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jabelardo on 12/4/16.
 */
public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(InitModels.class).asEagerSingleton();

//        Formatters formatters = getProvider(Formatters.class).get();
//
//        formatters.register(Date.class, new Formatters.SimpleFormatter<Date>() {
//
//            private final static String PATTERN = "dd-MM-yyyy";
//
//            @Override
//            public Date parse(String text, Locale locale) throws ParseException {
//                if (text == null || text.trim().isEmpty()) {
//                    return null;
//                }
//                SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, locale);
//                sdf.setLenient(false);
//                return sdf.parse(text);
//            }
//
//            @Override
//            public String print(Date date, Locale locale) {
//                if (date == null) {
//                    return "";
//                }
//                return new SimpleDateFormat(PATTERN, locale).format(date);
//            }
//        });
    }
}
