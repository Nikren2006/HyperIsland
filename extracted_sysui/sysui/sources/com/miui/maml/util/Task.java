package com.miui.maml.util;

import com.miui.circulate.device.api.Column;
import com.xiaomi.onetrack.api.ah;
import com.xiaomi.onetrack.api.au;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes2.dex */
public class Task {
    public static String TAG_ACTION = "action";
    public static String TAG_CATEGORY = "category";
    public static String TAG_CLASS = "class";
    public static String TAG_ID = "id";
    public static String TAG_NAME = "name";
    public static String TAG_PACKAGE = "package";
    public static String TAG_TYPE = "type";
    public String action;
    public String category;
    public String className;
    public String id;
    public String name;
    public String packageName;
    public String type;

    public static Task load(Element element) {
        if (element == null) {
            return null;
        }
        Task task = new Task();
        task.id = element.getAttribute(Column.ID);
        task.action = element.getAttribute(com.xiaomi.onetrack.api.a.f2741a);
        task.type = element.getAttribute("type");
        task.category = element.getAttribute("category");
        task.packageName = element.getAttribute("package");
        task.className = element.getAttribute(ah.f2838r);
        task.name = element.getAttribute(au.f2921a);
        return task;
    }
}
