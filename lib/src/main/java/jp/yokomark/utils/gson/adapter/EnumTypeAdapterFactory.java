package jp.yokomark.utils.gson.adapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Enum type adapter factory to convert constant names of enum to a specific case string.
 * @author KeithYokoma
 */
public enum EnumTypeAdapterFactory implements TypeAdapterFactory {
    /**
     * Makes constant names always lower case in JSON.
     */
    LOWER_CASE_SNAKE {
        @Override
        protected String constantNameToJsonName(final String constantName) {
            return constantName.toLowerCase(Locale.US);
        }
    },
    /**
     * Makes constant names camel case in JSON.
     */
    CAMEL_CASE {
        @Override
        protected String constantNameToJsonName(final String constantName) {
            final String[] constantNameParts = constantName.split("_");
            final StringBuilder jsonNameBuilder = new StringBuilder(constantNameParts[0].toLowerCase());
            for (int i = 1; i < constantNameParts.length; ++i) {
                if (constantNameParts[i].length() > 0) {
                    jsonNameBuilder.append(Character.toUpperCase(constantNameParts[i].charAt(0)));
                    if (constantNameParts[i].length() > 1 ) {
                        jsonNameBuilder.append(constantNameParts[i].substring(1).toLowerCase());
                    }
                }
            }
            return jsonNameBuilder.toString();
        }
    };

    // we know that the unchecked type cast between raw type and generic type is safe because they will be the same at runtime due to type erasure.
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        final Class<? super T> rawType = type.getRawType();
        if (!rawType.isEnum()) { // not an enum type, so do not create any of adapter.
            return null;
        }

        final Map<String, T> jsonNameToInstance = new HashMap<String, T>();
        for (T constant : (T[])rawType.getEnumConstants()) {
            jsonNameToInstance.put(constantNameToJsonName(((Enum<?>)constant).name()), constant);
        }

        return (new TypeAdapter<T>() {

            @Override
            public T read(final JsonReader reader) throws IOException {
                final String constValue = reader.nextString();
                return jsonNameToInstance.get(constValue);
            }

            @Override
            public void write(final JsonWriter writer, T constant) throws IOException {
                writer.value(constantNameToJsonName(((Enum<?>)constant).name()));
            }
        }).nullSafe();
    }

    protected abstract String constantNameToJsonName (final String constantName);
}
