package jp.yokomark.utils.gson;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.yokomark.utils.gson.mock.SomeEntity;

@RunWith(RobolectricGradleTestRunner.class)
public class AospGsonBuilderTest {
    @Test
    public void buildInternalUseGson()  throws Exception {
        Gson gson = AospGsonBuilder.buildInternalUseGson();
        Assert.assertNotNull(gson);
    }

    @Test
    public void buildExternalUseGson()  throws Exception {
        Gson gson = AospGsonBuilder.buildExternalUseGson();
        Assert.assertNotNull(gson);
    }

    @Test
    public void convertWithInternalUseGson() throws Exception {
        Gson gson = AospGsonBuilder.buildInternalUseGson();
        String converted = gson.toJson(new SomeEntity());

        JSONObject obj = new JSONObject(converted);
        Assert.assertEquals(1, obj.getInt("id"));
        Assert.assertEquals("hoge", obj.getString("hoge"));
        Assert.assertEquals("foo", obj.getString("type"));
        Assert.assertEquals("Android", obj.getString("name"));
        Assert.assertFalse(obj.has("should_excluded"));
        Assert.assertFalse(obj.has("constant"));
        Assert.assertFalse(obj.has("CONSTANT"));
    }

    @Test
    public void convertWithExternalUseGson() throws Exception {
        Gson gson = AospGsonBuilder.buildInternalUseGson();
        String converted = gson.toJson(new SomeEntity());

        JSONObject obj = new JSONObject(converted);
        Assert.assertEquals(1, obj.getInt("id"));
        Assert.assertEquals("hoge", obj.getString("hoge"));
        Assert.assertEquals("foo", obj.getString("type"));
        Assert.assertEquals("Android", obj.getString("name"));
        Assert.assertFalse(obj.has("should_excluded"));
        Assert.assertFalse(obj.has("constant"));
        Assert.assertFalse(obj.has("CONSTANT"));
    }
}
