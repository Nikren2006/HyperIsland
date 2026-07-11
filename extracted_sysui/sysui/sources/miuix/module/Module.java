package miuix.module;

/* JADX INFO: loaded from: classes.dex */
public class Module {
    public static final int CONTENT_DEFAULT = 0;
    public static final int CONTENT_DEX = 1;
    public static final int CONTENT_LIB = 2;
    public static final int CONTENT_NONE = 0;
    public static final int CONTENT_RES = 4;
    private int content;
    private int level;
    private String name;

    public int getContent() {
        return this.content;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    public void setContent(int i2) {
        this.content = i2;
    }

    public void setLevel(int i2) {
        this.level = i2;
    }

    public void setName(String str) {
        this.name = str;
    }
}
