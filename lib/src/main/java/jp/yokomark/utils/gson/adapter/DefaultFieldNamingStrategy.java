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
package jp.yokomark.utils.gson.adapter;

import com.google.gson.FieldNamingStrategy;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Default AOSP style naming conventions strategy.
 * @author KeithYokoma
 */
public enum  DefaultFieldNamingStrategy implements FieldNamingStrategy {
    /**
     * Converted field name as snake case into json name.
     */
    SNAKE_CASE {
        @Override
        protected String convertToJsonName(String fieldName) {
            String jsonName = fieldName.replaceAll("([A-Z])", "_$1");
            jsonName = jsonName.toLowerCase();
            return jsonName;
        }
    },
    /**
     * Converted field name as camel case into json name.
     */
    CAMEL_CASE {
        @Override
        protected String convertToJsonName(String fieldName) {
            return fieldName; // already in camel case, so nothing to do
        }
    };
    private static final String TAG = DefaultFieldNamingStrategy.class.getSimpleName();
    private static final String PREFIX_NON_PUBLIC_MEMBER_NAME = "m";
    private static final int PREFIX_LENGTH = PREFIX_NON_PUBLIC_MEMBER_NAME.length();

    /**
     * Tanslates Java field names using AOSP's style guideline conventions.
     */
    @Override
    public String translateName(final Field field) {
        StringBuilder fieldName = new StringBuilder(field.getName());

        int fieldModifiers = field.getModifiers();

        if ((!Modifier.isStatic(fieldModifiers)) && (!Modifier.isPublic(fieldModifiers))) {
            if ((fieldName.length() >= PREFIX_LENGTH) && PREFIX_NON_PUBLIC_MEMBER_NAME.equals(fieldName.substring(0, PREFIX_LENGTH))) {
                //remove prefix and lower the case of the 1st character to get the unprefixed Java field name
                fieldName.delete(0, PREFIX_LENGTH);
                if (fieldName.length() > 0) fieldName.setCharAt(0, Character.toLowerCase(fieldName.charAt(0)));
            } else {
                Log.d(TAG, "The following field naming doesn't follow AOSP guidelines : " + field);
            }
        }

        return convertToJsonName(fieldName.toString());
    }

    protected abstract String convertToJsonName(final String fieldName);
}
