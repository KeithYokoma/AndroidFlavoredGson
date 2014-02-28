# Android Flavored Gson

Declaration of type adapters and some name conversion plicy for GSON according to AOSP code style guide line.

## Usage

```Java
public class SomeModel {
    private static final Gson GSON = AospGsonBuilder.buildExternalUseGson();

    public String serialize(SomeEntity entity) {
        // { "id" : 1, "name" : "Android", type : "foo" }
        return GSON.toJson(entity);
    }

    public SomeEntity deserialize(String string) {
        return GSON.fromJson(string, SomeEntity.class);
    }
}

class SomeEntity {
    private static final int CONSTANT = 3;
    private transient int mPosition = 0;
    private int mId = 1;
    private String mName = "Android";
    private SomeEnum mType = SomeEnum.FOO;
}

enum SomeEnum {
    FOO, BAR;
}
```

## Acknowledgement

This library using the following libraries

- Robolectric
- Google Gson

## License

```
Copyright (C) 2014 KeithYokoma. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
