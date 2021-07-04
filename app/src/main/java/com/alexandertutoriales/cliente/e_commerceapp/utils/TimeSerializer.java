package com.alexandertutoriales.cliente.e_commerceapp.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Time;

public class TimeSerializer implements JsonDeserializer<Time>, JsonSerializer<Time> {
    @Override
    public Time deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return Time.valueOf(json.getAsString());
    }

    @Override
    public JsonElement serialize(Time src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getHours() + ":" + src.getMinutes() + ":" + src.getSeconds());
    }
}
