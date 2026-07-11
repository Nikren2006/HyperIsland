package com.android.systemui;

import android.content.Context;
import android.text.TextUtils;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes.dex */
public final class FoldFieldManager {
    public static final FoldFieldManager INSTANCE;
    private static final String PHONE_TYPE_COMMON = "直板";
    private static final String PHONE_TYPE_FLIP = "flip";
    private static final String PHONE_TYPE_FOLD = "fold";
    private static final String SCREEN_TYPE_COMMON = "nothing";
    private static final String SCREEN_TYPE_INNER = "内屏";
    private static final String SCREEN_TYPE_OUTER = "外屏";
    private static String phoneType;

    static {
        FoldFieldManager foldFieldManager = new FoldFieldManager();
        INSTANCE = foldFieldManager;
        foldFieldManager.setPhoneType(foldFieldManager.getPhoneTypeImpl());
    }

    private FoldFieldManager() {
    }

    private final String getPhoneTypeImpl() {
        return CommonUtils.isFlipDevice() ? "flip" : CommonUtils.INSTANCE.getIS_FOLD() ? "fold" : "直板";
    }

    private final boolean isScreenLayoutLarge(Context context) {
        if (context == null) {
            return false;
        }
        int i2 = context.getResources().getConfiguration().screenLayout & 15;
        return i2 == 3 || i2 == 4;
    }

    public final String getPhoneType() {
        if (!TextUtils.isEmpty(phoneType)) {
            return phoneType;
        }
        String phoneTypeImpl = getPhoneTypeImpl();
        phoneType = phoneTypeImpl;
        return phoneTypeImpl;
    }

    public final String getScreenType() {
        if (!CommonUtils.isFlipDevice()) {
            if (CommonUtils.INSTANCE.getIS_FOLD()) {
                MiPlayController miPlayController = MiPlayController.INSTANCE;
                if (miPlayController.isContextValid()) {
                    if (isScreenLayoutLarge(miPlayController.getContext())) {
                        return SCREEN_TYPE_INNER;
                    }
                }
            }
            return SCREEN_TYPE_COMMON;
        }
        if (!MiPlayDetailViewModel.INSTANCE.getLastFoldState()) {
            return SCREEN_TYPE_INNER;
        }
        return SCREEN_TYPE_OUTER;
    }

    public final void setPhoneType(String str) {
        if (kotlin.jvm.internal.n.c(phoneType, str)) {
            return;
        }
        phoneType = str;
    }
}
