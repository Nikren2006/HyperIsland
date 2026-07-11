package miui.systemui.notification.focus.template;

import U0.a;
import d1.InterfaceC0324c;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.z;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.notification.focus.Const;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusTemplateKt {
    private static boolean isFlipDevice;
    private static final Map<String, InterfaceC0324c> templateMaps = new LinkedHashMap();

    public static final FocusTemplate covert(JSONObject focusParam, boolean z2) {
        Class clsA;
        Constructor constructor;
        n.g(focusParam, "focusParam");
        String strOptString = focusParam.optString(Const.Param.SCENE);
        isFlipDevice = z2;
        initTemplateMap();
        InterfaceC0324c interfaceC0324c = templateMaps.get(strOptString);
        if (interfaceC0324c == null || (clsA = a.a(interfaceC0324c)) == null || (constructor = clsA.getConstructor(JSONObject.class)) == null) {
            return null;
        }
        return (FocusTemplate) constructor.newInstance(focusParam);
    }

    public static final Map<String, InterfaceC0324c> getTemplateMaps() {
        return templateMaps;
    }

    public static final void initTemplateMap() {
        Map<String, InterfaceC0324c> map = templateMaps;
        if (map.isEmpty()) {
            map.put(Const.Template.TEMPLATE_BASE, z.b(TemplateBase.class));
            map.put(Const.Scene.SMART_HOME_ALERT, z.b(TemplateBase.class));
            map.put(Const.Scene.SOS, z.b(TemplateBase.class));
            map.put(Const.Scene.MISSED_CALLS, z.b(TemplateBase.class));
            map.put(Const.Template.TEMPLATE_BASE_PROGRESS, z.b(TemplateBaseProgress.class));
            map.put(Const.Scene.CAR_HAILING, z.b(TemplateBaseProgress.class));
            map.put("events", z.b(TemplateBaseProgress.class));
            map.put(Const.Template.TEMPLATE_BASE_REVERT, z.b(TemplateRevert.class));
            map.put(Const.Scene.VERIFY_CODE, z.b(TemplateRevert.class));
            map.put(Const.Scene.RECORDER, z.b(TemplateRevert.class));
            map.put("alarm", z.b(TemplateRevert.class));
            map.put(Const.Scene.TIMER, z.b(TemplateRevert.class));
            map.put(Const.Template.TEMPLATE_BASE_REVERT_PROGRESS, z.b(TemplateRevertProgress.class));
            map.put(Const.Scene.FOOD_DELIVERY, z.b(TemplateRevertProgress.class));
            map.put(Const.Template.TEMPLATE_BASE_REVERT_OVERSIZE, z.b(TemplateRevertOversize.class));
            NotificationUtil.debugLog(Const.TAG, "v1 all temp=" + map.size() + ", " + map);
        }
    }
}
