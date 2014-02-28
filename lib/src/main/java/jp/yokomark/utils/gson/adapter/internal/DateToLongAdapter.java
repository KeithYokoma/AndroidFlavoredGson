package jp.yokomark.utils.gson.adapter.internal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

import jp.yokomark.utils.gson.utils.JsonElementUtils;

/**
 * {@link java.util.Date} serialization and de-serialization policy.
 * This converts {@link java.util.Date} to long using {@link java.util.Date#getTime()},
 * so de-serialization is to use json number and convert it to {@link java.util.Date}.
 *
 * @author keishin.yokomaku
 */
public class DateToLongAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null) {
            return null;
        }
        JsonPrimitive primitive = JsonElementUtils.getAsJsonPrimitiveOrThrow(json);
        long millis = JsonElementUtils.getAsLongOrThrow(primitive);
        return new Date(millis);
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return null;
        }
        return new JsonPrimitive(src.getTime());
    }
}
