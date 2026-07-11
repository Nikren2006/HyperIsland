package com.android.systemui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import com.android.systemui.miplay.R;
import com.android.systemui.plugins.ActivityStarter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import miui.systemui.util.CommonUtils;
import miui.util.ReflectionUtils;
import miuix.appcompat.app.AlertDialog;

/* JADX INFO: loaded from: classes.dex */
public class InvisibleModeUtil {
    public static final String KEY_INVISIBLE_MODE_STATE = "key_invisible_mode_state";
    public static final int PRIVATE_FLAG_SHOW_FOR_ALL_USERS = 16;

    public static void applyFlags(Object obj) {
        Object objCallSimpleMethod = SimpleReflectionUtils.callSimpleMethod(obj, "getWindow", new Object[0]);
        SimpleReflectionUtils.callSimpleMethod(objCallSimpleMethod, "setType", 2017);
        SimpleReflectionUtils.callSimpleMethod(objCallSimpleMethod, "addFlags", 655360);
    }

    public static boolean checkInvisibleMode(Context context, boolean z2) {
        return isInvisibleModeON(context) && z2;
    }

    public static AlertDialog getInvisibleModeHintDialog(final Context context) {
        try {
            String string = context.getResources().getString(R.string.close_invisible_mode_dialog_title);
            String string2 = context.getResources().getString(R.string.close_invisible_mode_dialog_message);
            String string3 = context.getResources().getString(R.string.close_invisible_mode_dialog_ok);
            String string4 = context.getResources().getString(R.string.close_invisible_mode_dialog_cancel);
            Object objNewInstance = ReflectionUtils.newInstance(ReflectionUtils.findClass("miuix.appcompat.app.AlertDialog$Builder", context.getClassLoader()), new Object[]{context, Integer.valueOf(context.getResources().getIdentifier("AlertDialog.Theme.DayNight", "style", context.getPackageName()))});
            ReflectionUtils.callMethod(objNewInstance, "setTitle", (Class) null, new Object[]{string});
            ReflectionUtils.callMethod(objNewInstance, "setMessage", (Class) null, new Object[]{string2});
            ReflectionUtils.callMethod(objNewInstance, "setPositiveButton", (Class) null, new Object[]{string3, new DialogInterface.OnClickListener() { // from class: com.android.systemui.InvisibleModeUtil.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) throws IOException {
                    InvisibleModeUtil.onPositiveClick(context);
                }
            }});
            ReflectionUtils.callMethod(objNewInstance, "setNegativeButton", (Class) null, new Object[]{string4, null});
            Object objCallSimpleMethod = SimpleReflectionUtils.callSimpleMethod(objNewInstance, "create", new Object[0]);
            applyFlags(objCallSimpleMethod);
            setShowForAllUsers(objCallSimpleMethod, true);
            SimpleReflectionUtils.callSimpleMethod(objCallSimpleMethod, "show", new Object[0]);
            if (objCallSimpleMethod instanceof AlertDialog) {
                return (AlertDialog) objCallSimpleMethod;
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean isInvisibleModeON(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), KEY_INVISIBLE_MODE_STATE, 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onPositiveClick(Context context) throws IOException {
        Intent intent = new Intent("com.miui.securitycenter.action.INVISIBLE_SETTING");
        intent.addFlags(268435456);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.collapseControlCenter();
        ActivityStarter activityStarter = MiPlayController.INSTANCE.getActivityStarter();
        if (activityStarter != null) {
            activityStarter.postStartActivityDismissingKeyguard(intent, 350);
        } else {
            commonUtils.callDismissKeyGuard(context);
            context.startActivity(intent);
        }
    }

    private static void setShowForAllUsers(Object obj, boolean z2) {
        Object objCallSimpleMethod = SimpleReflectionUtils.callSimpleMethod(SimpleReflectionUtils.callSimpleMethod(obj, "getWindow", new Object[0]), "getAttributes", new Object[0]);
        Integer num = (Integer) ReflectionUtils.getObjectField(objCallSimpleMethod, "privateFlags", Integer.class);
        ReflectionUtils.setObjectField(objCallSimpleMethod, "privateFlags", Integer.valueOf(z2 ? num.intValue() | 16 : num.intValue() & (-17)));
    }

    public static boolean showInvisibleModeHint() {
        try {
            MiPlayController miPlayController = MiPlayController.INSTANCE;
            final Context context = miPlayController.context;
            Context context2 = miPlayController.systemUIContext;
            String string = context.getResources().getString(R.string.close_invisible_mode_dialog_title);
            String string2 = context.getResources().getString(R.string.close_invisible_mode_dialog_message);
            String string3 = context.getResources().getString(R.string.close_invisible_mode_dialog_ok);
            String string4 = context.getResources().getString(R.string.close_invisible_mode_dialog_cancel);
            if (context2 != null) {
                context = context2;
            }
            Object objNewInstance = ReflectionUtils.newInstance(ReflectionUtils.findClass("miuix.appcompat.app.AlertDialog$Builder", context.getClassLoader()), new Object[]{context, Integer.valueOf(context.getResources().getIdentifier("AlertDialog.Theme.DayNight", "style", context.getPackageName()))});
            ReflectionUtils.callMethod(objNewInstance, "setTitle", (Class) null, new Object[]{string});
            ReflectionUtils.callMethod(objNewInstance, "setMessage", (Class) null, new Object[]{string2});
            ReflectionUtils.callMethod(objNewInstance, "setPositiveButton", (Class) null, new Object[]{string3, new DialogInterface.OnClickListener() { // from class: com.android.systemui.InvisibleModeUtil.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) throws IOException {
                    InvisibleModeUtil.onPositiveClick(context);
                }
            }});
            ReflectionUtils.callMethod(objNewInstance, "setNegativeButton", (Class) null, new Object[]{string4, null});
            Object objCallSimpleMethod = SimpleReflectionUtils.callSimpleMethod(objNewInstance, "create", new Object[0]);
            applyFlags(objCallSimpleMethod);
            setShowForAllUsers(objCallSimpleMethod, true);
            SimpleReflectionUtils.callSimpleMethod(objCallSimpleMethod, "show", new Object[0]);
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
