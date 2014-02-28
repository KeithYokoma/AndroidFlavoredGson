package jp.yokomark.utils.gson.mock;

public class SomeEntity {
    private static final int CONSTANT = 0;
    private transient int mShouldExcluded = 3;
    public String hoge = "hoge";
    private int mId = 1;
    private String mName = "Android";
    private SomeEnum mType = SomeEnum.FOO;
}
