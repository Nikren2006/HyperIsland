package com.android.systemui;

import miui.systemui.dynamicisland.touch.TouchEvent;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayRef {
    private String mRef;

    public final boolean fromActivity() {
        String str = this.mRef;
        if (str == null) {
            return true;
        }
        int iHashCode = str.hashCode();
        if (iHashCode != 508535718) {
            if (iHashCode != 595233003) {
                if (iHashCode != 1328412658 || !str.equals("controlcenter")) {
                    return true;
                }
            } else if (!str.equals("notification")) {
                return true;
            }
        } else if (!str.equals("keyguard")) {
            return true;
        }
        return false;
    }

    public final String getMRef() {
        return this.mRef;
    }

    public final boolean refMatchNotification() {
        return kotlin.jvm.internal.n.c("notification", this.mRef) || kotlin.jvm.internal.n.c("keyguard", this.mRef) || kotlin.jvm.internal.n.c(TouchEvent.SOURCE_DYNAMIC_ISLAND, this.mRef);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int refToMiplaySDK() {
        String str = this.mRef;
        if (str == null || str == null) {
            return 5;
        }
        switch (str.hashCode()) {
            case -168707038:
                if (!str.equals(MiPlayExtentionsKt.REF_MIUIMUSIC_NOWPLAYING)) {
                }
                break;
            case 508535718:
                if (!str.equals("keyguard")) {
                }
                break;
            case 595233003:
                if (!str.equals("notification")) {
                }
                break;
            case 1328412658:
                if (str.equals("controlcenter")) {
                }
                break;
        }
        return 5;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int refToStatType() {
        String str = this.mRef;
        if (str == null || str == null) {
            return 0;
        }
        switch (str.hashCode()) {
            case -168707038:
                if (!str.equals(MiPlayExtentionsKt.REF_MIUIMUSIC_NOWPLAYING)) {
                }
                break;
            case 108971:
                if (!str.equals("nfc")) {
                }
                break;
            case 508535718:
                if (!str.equals("keyguard")) {
                }
                break;
            case 595233003:
                if (!str.equals("notification")) {
                }
                break;
            case 1328412658:
                str.equals("controlcenter");
                break;
        }
        return 0;
    }

    public final void setMRef(String str) {
        this.mRef = str;
    }
}
