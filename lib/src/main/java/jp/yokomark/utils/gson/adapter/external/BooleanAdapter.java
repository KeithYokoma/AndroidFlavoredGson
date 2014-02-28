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
package jp.yokomark.utils.gson.adapter.external;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;

import jp.yokomark.utils.gson.utils.JsonElementUtils;

/**
 * {@link java.lang.Boolean} de-serialization policy.
 * This converts not only true/false literal but also 0/1 literal that is expected as boolean type to boolean value.
 *
 * @author KeithYokoma
 */
public class BooleanAdapter implements JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null) {
            return null;
        }
        JsonPrimitive primitive = JsonElementUtils.getAsJsonPrimitiveOrThrow(json);
        String bool = JsonElementUtils.getAsStringOrThrow(primitive);
        try {
            Integer flag = Integer.valueOf(bool);
            return !flag.equals(Integer.valueOf(0));
        } catch (NumberFormatException e) {
            // not a number as boolean flag, so it seems just a true/false literal.
        }
        return Boolean.valueOf(bool);
    }
}
