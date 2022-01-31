package com.amirservices.showroom.dto;

import org.apache.commons.lang3.StringUtils;

public class OwnerConverter {

    private static final String OWNER_TYPE_PERSON = "PERSON";
    private static final String OWNER_TYPE_COMPANY = "COMPANY";

    public static String enlarge(Character charOwner) {
        if(charOwner== null)
            return "INVALID";

        if(charOwner == 'P'){
            return OWNER_TYPE_PERSON;
        }else if(charOwner == 'C'){
            return OWNER_TYPE_COMPANY;
        }else{
            return  "INVALID";
        }

    }

    public static Character abbreviate(String stringOwner) {
        if(StringUtils.isBlank(stringOwner))
            return 'I';

        if(StringUtils.trim(stringOwner).equalsIgnoreCase(OWNER_TYPE_PERSON)){
            return 'P';
        }else if(StringUtils.trim(stringOwner).equalsIgnoreCase(OWNER_TYPE_COMPANY)){
            return 'C';
        }else{
            return 'I';
        }
    }
}

