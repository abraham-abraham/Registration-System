package com.soen387.registrationsystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSON {
    public static <T> String getResponse(ResponseObject<T> res) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(res);
    }
}
