package com.android.systemui;

/* JADX INFO: loaded from: classes.dex */
public final class MiplayConstant {
    private static final int CAST_MODE_IDLE = 0;
    public static final MiplayConstant INSTANCE = new MiplayConstant();
    private static final int CAST_MODE_CASTING = 1;
    private static final int CAST_MODE_SUCCESS = 2;

    private MiplayConstant() {
    }

    public final int getCAST_MODE_CASTING() {
        return CAST_MODE_CASTING;
    }

    public final int getCAST_MODE_IDLE() {
        return CAST_MODE_IDLE;
    }

    public final int getCAST_MODE_SUCCESS() {
        return CAST_MODE_SUCCESS;
    }
}
