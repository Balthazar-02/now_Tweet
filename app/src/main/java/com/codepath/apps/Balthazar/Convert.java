package com.codepath.apps.Balthazar;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class Convert {



    @TypeConverter
    public static String fromArray(List<String> ms) {
        return String.join(",,,", ms);
    }

    @TypeConverter
    public static List<String> fromString(String str) {
        return Arrays.asList(str.split(",,,"));
    }


}