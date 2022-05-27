package com.example.app.resources.network;

import com.example.app.resources.zone.ZoneDate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class WebsiteOutputInterpreter {
    private String content;
    private List<String> list;

    public WebsiteOutputInterpreter(String content) {
        this.content = content;
        this.list = createArrayOfContent();
    }

    private List<String> createArrayOfContent(){
        String content2 = content;
        char buff = '"';
        content2 = content2.replace(Character.toString(buff), "");
        content2 = content2.replace("{", "");
        content2 = content2.replace("}", "");
        return Arrays.asList(content2.split(","));
    }

    public ZoneDate getZoneDateInformation(){
        String place = list.get(2);

        char[] year = new char[4];
        char[] month = new char[2];
        char[] day = new char[2];
        place.getChars(9, 13, year, 0);
        place.getChars(14, 16, month, 0);
        place.getChars(17, 19, day, 0);

        char[] hour = new char[2];
        char[] minutes = new char[2];
        place.getChars(20, 22, hour, 0);
        place.getChars(23, 25, minutes, 0);

        return new ZoneDate(
                Integer.parseInt(String.valueOf(year)),
                Integer.parseInt(String.valueOf(month)),
                Integer.parseInt(String.valueOf(day)),
                Integer.parseInt(String.valueOf(hour)),
                Integer.parseInt(String.valueOf(minutes)));
    }
}
