package com.miui.maml.util;

import android.text.TextUtils;
import b0.C0223a;
import com.xiaomi.onetrack.api.ah;
import com.xiaomi.onetrack.api.au;
import com.xiaomi.onetrack.util.aa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes2.dex */
public class LargeIconConfigFile {
    private static final String LOG_TAG = "LargeIconConfigFile";
    private HashMap<String, Icon> mIconsConfigs = new HashMap<>();

    public static class Icon {
        public String associatedPackage;
        public int enableIconMask = -1;
        public boolean isPairApp;
        public String localId;
        public String name;
        public String packageName;
        public String packageName1;
        public String packageName2;
        public String path;
        public String size;
        public String uuid;
    }

    public static String readConfigFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                int i2 = inputStreamReader.read();
                if (i2 == -1) {
                    fileReader.close();
                    inputStreamReader.close();
                    return Pattern.compile("\\s*|\t|\r|\n").matcher(stringBuffer.toString()).replaceAll("");
                }
                stringBuffer.append((char) i2);
            }
        } catch (Exception e2) {
            MamlLog.e(LOG_TAG, "readConfigFile failed", e2);
            return null;
        }
    }

    public static int readTransFormConfig(Icon icon, String str) {
        int i2;
        if (icon != null && (i2 = icon.enableIconMask) != -1) {
            return i2;
        }
        try {
            Element documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(str)).getDocumentElement();
            if (documentElement != null) {
                NodeList childNodes = documentElement.getChildNodes();
                for (int i3 = 0; i3 < childNodes.getLength(); i3++) {
                    if (childNodes.item(i3).getNodeType() == 1) {
                        Element element = (Element) childNodes.item(i3);
                        if ("Config".equals(element.getTagName())) {
                            String attribute = element.getAttribute(au.f2921a);
                            String attribute2 = element.getAttribute(ah.f2836p);
                            if ("enableIconMask".equalsIgnoreCase(attribute) && icon != null) {
                                boolean z2 = Boolean.parseBoolean(attribute2);
                                icon.enableIconMask = z2 ? 1 : 0;
                                return z2 ? 1 : 0;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        } catch (Exception e2) {
            MamlLog.e(LOG_TAG, "load icon config failed.", e2);
        }
        return 0;
    }

    public HashMap<String, Icon> getIconsConfigs() {
        return this.mIconsConfigs;
    }

    public boolean load(String str) {
        File file = new File(str);
        if (!file.exists()) {
            MamlLog.w(LOG_TAG, "generateLargeIconConfigFile not found");
            return false;
        }
        String configFile = readConfigFile(file);
        if (TextUtils.isEmpty(configFile)) {
            return false;
        }
        try {
            HashMap map = (HashMap) new U.d().k(configFile, new C0223a() { // from class: com.miui.maml.util.LargeIconConfigFile.1
            }.getType());
            if (map == null) {
                return false;
            }
            for (String str2 : map.keySet()) {
                Icon icon = (Icon) map.get(str2);
                if (icon != null) {
                    Icon icon2 = this.mIconsConfigs.get(str2);
                    if (icon2 == null) {
                        icon2 = new Icon();
                        icon2.packageName = icon.packageName;
                        this.mIconsConfigs.put(str2, icon2);
                    }
                    icon2.name = icon.name;
                    icon2.size = icon.size;
                    icon2.path = icon.path;
                    icon2.isPairApp = icon.isPairApp;
                    icon2.uuid = icon.uuid;
                    icon2.localId = icon.localId;
                    String str3 = icon.associatedPackage;
                    if (!TextUtils.isEmpty(str3)) {
                        for (String str4 : str3.split(aa.f3429b)) {
                            if (!TextUtils.isEmpty(str4)) {
                                this.mIconsConfigs.put(str4, icon2);
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e2) {
            MamlLog.e(LOG_TAG, "get json failed", e2);
            return false;
        }
    }
}
