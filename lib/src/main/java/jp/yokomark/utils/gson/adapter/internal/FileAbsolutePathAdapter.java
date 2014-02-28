package jp.yokomark.utils.gson.adapter.internal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.File;
import java.lang.reflect.Type;

import jp.yokomark.utils.gson.utils.JsonElementUtils;

/**
 * {@link java.io.File} serialization and de-serialization policy.
 * This converts {@link java.io.File} to a string that represents the absolute path of the {@link java.io.File},
 * so de-serialization is to use json string and convert it to {@link java.io.File}.
 *
 * @author KeithYokoma
 */
public final class FileAbsolutePathAdapter implements JsonSerializer<File>, JsonDeserializer<File> {
    @Override
    public File deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null) {
            return null;
        }
        JsonPrimitive primitive = JsonElementUtils.getAsJsonPrimitiveOrThrow(json);
        String path = JsonElementUtils.getAsStringOrThrow(primitive);
        return new File(path);
    }

    @Override
    public JsonElement serialize(File src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return null;
        }
        return new JsonPrimitive(src.getAbsolutePath());
    }
}
