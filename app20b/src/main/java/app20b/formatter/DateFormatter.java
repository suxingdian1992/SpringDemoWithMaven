package app20b.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

	//获取spring配置文件中说明的转换格式
    private String datePattern;
    private SimpleDateFormat dateFormat;

    public DateFormatter(String datePattern) {
        System.out.println("DateFormatter()5b========");
        this.datePattern = datePattern;
        dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
    }

    public String print(Date date, Locale locale) {
        return dateFormat.format(date);
    }

    public Date parse(String s, Locale locale) throws ParseException {
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            // the error message will be displayed when using <form:errors>
            throw new IllegalArgumentException(
                    "invalid date format. Please use this pattern\""
                            + datePattern + "\"");
        }
    }
}