package jp.yokomark.utils.gson.adapter.internal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import android.net.Uri;

import java.lang.reflect.Type;

import jp.yokomark.utils.gson.utils.JsonElementUtils;

/**
 * @author keishin.yokomaku
 */
public class UriAdapter implements JsonSerializer<Uri>, JsonDeserializer<Uri> {
    @Override
    public Uri deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null) {
            return null;
        }
        JsonPrimitive primitive = JsonElementUtils.getAsJsonPrimitiveOrThrow(json);
        String uri = JsonElementUtils.getAsStringOrThrow(primitive);
        return Uri.parse(uri);
    }

    @Override
    public JsonElement serialize(Uri src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return null;
        }
        return new JsonPrimitive(src.toString());
    }
}