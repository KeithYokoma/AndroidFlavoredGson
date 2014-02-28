package jp.yokomark.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.net.Uri;

import java.io.File;
import java.util.Date;

import jp.yokomark.utils.gson.adapter.DefaultFieldNamingStrategy;
import jp.yokomark.utils.gson.adapter.EnumTypeAdapterFactory;
import jp.yokomark.utils.gson.adapter.external.BooleanAdapter;
import jp.yokomark.utils.gson.adapter.internal.DateToLongAdapter;
import jp.yokomark.utils.gson.adapter.internal.FileAbsolutePathAdapter;
import jp.yokomark.utils.gson.adapter.internal.UriAdapter;

/**
 * {@link com.google.gson.Gson} converter builder that AOSP's code guide line applied.
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public APIs
public final class AospGsonBuilder {

    /**
     * This is a utility class, so do not instantiate.
     */
    private AospGsonBuilder() {
        throw new AssertionError("Constructor should not be called");
    }

    /**
     * {@link com.google.gson.Gson} to use at application internal serialization and de-serialization.
     * @return a {@link com.google.gson.Gson} object.
     */
    public static Gson buildInternalUseGson() {
        return buildInternalUseGsonBuilder().create();
    }

    /**
     * {@link com.google.gson.GsonBuilder} to use at application internal serialization and de-serialization.
     * @return a {@link com.google.gson.GsonBuilder} object.
     */
    public static GsonBuilder buildInternalUseGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        return builder
                .setFieldNamingStrategy(DefaultFieldNamingStrategy.SNAKE_CASE)
                .registerTypeAdapterFactory(EnumTypeAdapterFactory.CAMEL_CASE)
                .registerTypeAdapter(Date.class, new DateToLongAdapter())
                .registerTypeAdapter(File.class, new FileAbsolutePathAdapter())
                .registerTypeAdapter(Uri.class, new UriAdapter());
    }

    /**
     * {@link com.google.gson.Gson} to use at JSON-RPC serialization and de-serialization.
     * @return a {@link com.google.gson.Gson} object.
     */
    public static Gson buildExternalUseGson() {
        return buildExternalUseGsonBuilder().create();
    }

    /**
     * {@link com.google.gson.GsonBuilder} to use at JSON-RPC serialization and de-serialization.
     * @return a {@link com.google.gson.GsonBuilder} object.
     */
    public static GsonBuilder buildExternalUseGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        return builder
                .setFieldNamingStrategy(DefaultFieldNamingStrategy.SNAKE_CASE)
                .registerTypeAdapterFactory(EnumTypeAdapterFactory.LOWER_CASE_SNAKE)
                .registerTypeAdapter(Boolean.class, new BooleanAdapter());
    }
}