/*
 * Copyright (C) 2014 KeithYokoma. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.yokomark.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.net.Uri;

import java.io.File;
import java.lang.reflect.Modifier;
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
                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
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
                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                .setFieldNamingStrategy(DefaultFieldNamingStrategy.SNAKE_CASE)
                .registerTypeAdapterFactory(EnumTypeAdapterFactory.LOWER_CASE_SNAKE)
                .registerTypeAdapter(Boolean.class, new BooleanAdapter());
    }
}