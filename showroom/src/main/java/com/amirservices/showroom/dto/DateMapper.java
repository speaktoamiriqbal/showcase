package com.amirservices.showroom.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMapper {
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";


    public String asString(Date date) {
        return date != null ? new SimpleDateFormat( DATE_FORMAT_PATTERN )
                .format( date ).substring(0,4) : null;
    }

    public Date asDate(String date) {
        try {
            return date != null ? new SimpleDateFormat( DATE_FORMAT_PATTERN )
                    .parse( date ) : null;
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
    }

}