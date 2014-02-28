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