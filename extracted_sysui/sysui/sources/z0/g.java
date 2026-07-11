package z0;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g {
    public static boolean a() {
        try {
            Field field = Class.forName("miui.os.Build").getField("IS_INTERNATIONAL_BUILD");
            field.setAccessible(true);
            return field.getBoolean(null);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
