package jp.yokomark.utils.gson.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * Utility for verifying {@link com.google.gson.JsonElement}.
 * @author keishin.yokomaku
 */
public final class JsonElementUtils {
    private JsonElementUtils() {
        throw new AssertionError("constructor should not be called");
    }

    public static JsonPrimitive getAsJsonPrimitiveOrThrow(JsonElement element) throws JsonParseException {
        if (!element.isJsonPrimitive()) {
            throw new JsonParseException("this element is not a json primitive: " + element);
        }
        return element.getAsJsonPrimitive();
    }

    public static String getAsStringOrThrow(JsonPrimitive primitive) throws JsonParseException {
        if (!primitive.isString()) {
            throw new JsonParseException("this primitive is not a json string: " + primitive);
        }
        return primitive.getAsString();
    }

    public static long getAsLongOrThrow(JsonPrimitive primitive) throws JsonParseException {
        if (!primitive.isNumber()) {
            throw new JsonParseException("this primitive is not a json number: " + primitive);
        }
        return primitive.getAsLong();
    }
}
