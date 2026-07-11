package com.miui.maml;

import android.icu.text.PluralRules;
import android.os.SystemClock;
import android.text.TextUtils;
import com.miui.maml.LanguageHelper;
import com.miui.maml.data.Variables;
import com.miui.maml.util.MamlLog;
import com.miui.maml.util.Utils;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.BiConsumer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageHelper {
    private static final String COMPATIBLE_STRING_ROOT_TAG = "strings";
    private static final String DEFAULT_STRING_FILE_PATH = "strings/strings.xml";
    private static final int ID_OTHER = 16777220;
    private static final String ITEM_TAG = "item";
    private static final String LOG_TAG = "LanguageHelper";
    private static final String NAME_TAG = "name";
    private static final String PLURALS_TAG = "plurals";
    private static final String QUANTITY_TAG = "quantity";
    private static final String STRING_FILE_PATH = "strings/strings.xml";
    private static final String STRING_ROOT_TAG = "resources";
    private static final String STRING_TAG = "string";
    private static final String VALUE_TAG = "value";
    private static PluralRules pluralRules;

    public static class Plurals {
        String PluralsName;
        String PluralsValue;

        public Plurals(String str, String str2) {
            this.PluralsName = str;
            this.PluralsValue = str2;
        }
    }

    private static int attrForQuantityCode(String str) {
        str.hashCode();
        switch (str) {
            case "few":
                return 16777224;
            case "one":
                return 16777222;
            case "two":
                return 16777223;
            case "many":
                return 16777225;
            case "zero":
                return 16777221;
            default:
                return ID_OTHER;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setPluralsVariables$0(Variables variables, Plurals plurals, HashMap map) {
        double d2 = variables.getDouble(plurals.PluralsValue);
        String str = (String) map.get(Integer.valueOf(attrForQuantityCode(pluralRules.select(d2))));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.contains("%d")) {
            str = String.format(str, Long.valueOf((long) d2));
        }
        variables.put(plurals.PluralsName, str);
    }

    public static boolean load(Locale locale, ResourceManager resourceManager, Variables variables) {
        String strAddFileNameSuffix;
        String str = "strings/strings.xml";
        InputStream inputStream = null;
        if (locale != null) {
            strAddFileNameSuffix = Utils.addFileNameSuffix("strings/strings.xml", locale.toString());
            if (!resourceManager.resourceExists(strAddFileNameSuffix)) {
                strAddFileNameSuffix = Utils.addFileNameSuffix("strings/strings.xml", locale.getLanguage());
            }
        } else {
            strAddFileNameSuffix = null;
        }
        if (resourceManager.resourceExists(strAddFileNameSuffix)) {
            str = strAddFileNameSuffix;
        } else if (!resourceManager.resourceExists("strings/strings.xml")) {
            MamlLog.i(LOG_TAG, "no available string resources to load.");
            return false;
        }
        try {
            try {
                DocumentBuilder documentBuilderNewDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                inputStream = resourceManager.getInputStream(str);
                Document document = documentBuilderNewDocumentBuilder.parse(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    pluralRules = PluralRules.forLocale(locale);
                    setPluralsList(document, resourceManager);
                } catch (Exception e3) {
                    MamlLog.e(LOG_TAG, " load Plurals fail," + e3.getMessage());
                }
                return setVariables(document, variables);
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            MamlLog.e(LOG_TAG, e5.getMessage());
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            return false;
        }
    }

    private static void setPluralsList(Document document, ResourceManager resourceManager) {
        NodeList elementsByTagName = document.getElementsByTagName(STRING_ROOT_TAG);
        if (elementsByTagName.getLength() <= 0) {
            elementsByTagName = document.getElementsByTagName(COMPATIBLE_STRING_ROOT_TAG);
            if (elementsByTagName.getLength() <= 0) {
                return;
            }
        }
        NodeList elementsByTagName2 = ((Element) elementsByTagName.item(0)).getElementsByTagName(PLURALS_TAG);
        HashMap<Plurals, HashMap<Integer, String>> pluralsMap = resourceManager.getPluralsMap();
        pluralsMap.clear();
        for (int i2 = 0; i2 < elementsByTagName2.getLength(); i2++) {
            Node nodeItem = elementsByTagName2.item(i2);
            if (nodeItem.getNodeType() == 1) {
                Element element = (Element) nodeItem;
                String attribute = element.getAttribute("name");
                String attribute2 = element.getAttribute("value");
                if (!attribute.isEmpty() && !attribute2.isEmpty() && element.getElementsByTagName("item").getLength() > 0) {
                    HashMap<Integer, String> map = new HashMap<>();
                    NodeList elementsByTagName3 = element.getElementsByTagName("item");
                    for (int i3 = 0; i3 < elementsByTagName3.getLength(); i3++) {
                        Node nodeItem2 = elementsByTagName3.item(i3);
                        if (nodeItem2.getNodeType() == 1) {
                            Element element2 = (Element) nodeItem2;
                            map.put(Integer.valueOf(attrForQuantityCode(element2.getAttribute(QUANTITY_TAG))), element2.getTextContent());
                        }
                    }
                    pluralsMap.put(new Plurals(attribute, attribute2), map);
                }
            }
        }
    }

    public static void setPluralsVariables(final Variables variables, ResourceManager resourceManager) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - resourceManager.getLastPluralsUpdateTime() < 200) {
            return;
        }
        resourceManager.setLastPluralsUpdateTime(jElapsedRealtime);
        HashMap<Plurals, HashMap<Integer, String>> pluralsMap = resourceManager.getPluralsMap();
        MamlLog.w(LOG_TAG, "setPluralsVariables: " + pluralsMap.size());
        pluralsMap.forEach(new BiConsumer() { // from class: com.miui.maml.b
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                LanguageHelper.lambda$setPluralsVariables$0(variables, (LanguageHelper.Plurals) obj, (HashMap) obj2);
            }
        });
        MamlLog.w(LOG_TAG, "setPluralsVariables: end");
    }

    private static boolean setVariables(Document document, Variables variables) {
        boolean z2;
        NodeList elementsByTagName = document.getElementsByTagName(STRING_ROOT_TAG);
        if (elementsByTagName.getLength() <= 0) {
            elementsByTagName = document.getElementsByTagName(COMPATIBLE_STRING_ROOT_TAG);
            if (elementsByTagName.getLength() <= 0) {
                return false;
            }
            z2 = false;
        } else {
            z2 = true;
        }
        NodeList elementsByTagName2 = ((Element) elementsByTagName.item(0)).getElementsByTagName("string");
        for (int i2 = 0; i2 < elementsByTagName2.getLength(); i2++) {
            Element element = (Element) elementsByTagName2.item(i2);
            variables.put(element.getAttribute("name"), (z2 ? element.getTextContent() : element.getAttribute("value")).replaceAll("\\\\", ""));
        }
        return true;
    }
}
