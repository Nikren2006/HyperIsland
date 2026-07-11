package B0;

import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static HashMap f37a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f38b;

    static {
        h();
        g();
        i("/product/etc/removable_apk_info.xml");
        i("/mi_ext/product/etc/miext_removable_apk_info.xml");
    }

    public static String a() {
        try {
            Object obj = Class.forName("miui.os.Build").getField("DEVICE").get(null);
            k0.b.e("FeatureParser", "Device is " + ((String) obj));
            return (String) obj;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    public static String b() {
        return "/product/etc/device_features";
    }

    public static String c() {
        try {
            Object obj = Class.forName("miui.os.Build").getField("MODEL").get(null);
            k0.b.e("FeatureParser", "DeviceModel is " + ((String) obj));
            return (String) obj;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    public static String d() {
        String str = f38b;
        if (str != null) {
            return str;
        }
        try {
            f38b = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, "ro.product.device");
            k0.b.e("FeatureParser", "ro.product.device is, " + f38b);
            return f38b;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return "";
        }
    }

    public static String e(String str) {
        return (String) f37a.get(str);
    }

    public static String f() {
        return e("support_audio_share_ui");
    }

    public static void g() throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        File file;
        FileInputStream fileInputStream2 = null;
        String attributeValue = null;
        fileInputStream2 = null;
        try {
            try {
                file = new File("/product/etc/ccm.xml");
            } catch (IOException unused) {
                return;
            }
        } catch (IOException unused2) {
        } catch (XmlPullParserException unused3) {
        } catch (Throwable th2) {
            fileInputStream = null;
            th = th2;
        }
        if (!file.exists()) {
            k0.b.a("FeatureParser", "CCM feature override file does not exist ");
            return;
        }
        fileInputStream = new FileInputStream(file);
        try {
            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParserNewPullParser.setInput(fileInputStream, "UTF-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); 1 != eventType; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (xmlPullParserNewPullParser.getAttributeCount() > 0) {
                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(0);
                    }
                    if (TypedValues.Custom.S_STRING.equals(name)) {
                        f37a.put(attributeValue, xmlPullParserNewPullParser.nextText());
                    }
                }
            }
            fileInputStream.close();
        } catch (IOException unused4) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 == null) {
                return;
            }
            fileInputStream2.close();
        } catch (XmlPullParserException unused5) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 == null) {
                return;
            }
            fileInputStream2.close();
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused6) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void h() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: B0.b.h():void");
    }

    public static void i(String str) throws Throwable {
        Throwable th;
        FileInputStream fileInputStream;
        File file;
        FileInputStream fileInputStream2 = null;
        String attributeValue = null;
        fileInputStream2 = null;
        try {
            try {
                file = new File(str);
            } catch (IOException unused) {
                return;
            }
        } catch (IOException unused2) {
        } catch (XmlPullParserException unused3) {
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        if (!file.exists()) {
            k0.b.a("FeatureParser", "removable system apk list from xml file does not exist ");
            return;
        }
        fileInputStream = new FileInputStream(file);
        try {
            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParserNewPullParser.setInput(fileInputStream, "UTF-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); 1 != eventType; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (xmlPullParserNewPullParser.getAttributeCount() > 0) {
                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(0);
                    }
                    if (TypedValues.Custom.S_STRING.equals(name)) {
                        f37a.put(attributeValue, xmlPullParserNewPullParser.nextText());
                    }
                }
            }
            fileInputStream.close();
        } catch (IOException unused4) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 == null) {
                return;
            }
            fileInputStream2.close();
        } catch (XmlPullParserException unused5) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 == null) {
                return;
            }
            fileInputStream2.close();
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused6) {
                }
            }
            throw th;
        }
    }

    public static boolean j() {
        return TextUtils.equals(f(), d());
    }
}
