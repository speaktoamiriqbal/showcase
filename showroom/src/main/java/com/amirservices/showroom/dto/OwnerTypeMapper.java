package com.amirservices.showroom.dto;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OwnerTypeMapper {

    public static String OWNER_TYPE_PERSON = "PERSON";
    public static String OWNER_TYPE_COMPANY = "COMPANY";
    public static String OWNER_TYPE_INVALID = "INVALID";

    public static Character CHAR_OWNER_TYPE_PERSON = 'P';
    public static Character CHAR_OWNER_TYPE_COMPANY = 'C';
    public static Character CHAR_OWNER_TYPE_INVALID = 'I';


    public String asString(Character ownerType) {
        if (ownerType== null)
            return OWNER_TYPE_INVALID;


        if (ownerType == CHAR_OWNER_TYPE_PERSON){
            return OWNER_TYPE_PERSON;
        }
        else if (ownerType == CHAR_OWNER_TYPE_COMPANY){
            return OWNER_TYPE_COMPANY;
        }
        else {
            return OWNER_TYPE_INVALID;
        }
    }


    public Character asCharacter(String ownerType) {
        if (StringUtils.isBlank(ownerType))
            return CHAR_OWNER_TYPE_COMPANY;


        if (StringUtils.equalsIgnoreCase(ownerType, OWNER_TYPE_PERSON)){
            return CHAR_OWNER_TYPE_PERSON;
        }
        else if (StringUtils.equalsIgnoreCase(ownerType, OWNER_TYPE_COMPANY)){
            return CHAR_OWNER_TYPE_COMPANY;
        }
        else {
            return CHAR_OWNER_TYPE_INVALID;
        }
    }


}
