package com.xiaomi.onetrack;

import android.text.TextUtils;
import com.xiaomi.onetrack.OneTrack;

/* JADX INFO: loaded from: classes2.dex */
public class Configuration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2576b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2577c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2578d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f2579e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private OneTrack.Mode f2580f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2581g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2582h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f2583i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f2584j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f2585k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f2586l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private String f2587m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f2588n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private String f2589o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private OneTrack.IEventHook f2590p;

    public static class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2591a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2592b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2593c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f2594d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f2595e;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private String f2603m;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private String f2605o;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private OneTrack.Mode f2596f = OneTrack.Mode.APP;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f2597g = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f2598h = true;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f2599i = true;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private boolean f2600j = false;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private boolean f2601k = true;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private boolean f2602l = false;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private boolean f2604n = false;

        public Configuration build() {
            return new Configuration(this);
        }

        public Builder setAdEventAppId(String str) {
            this.f2605o = str;
            return this;
        }

        public Builder setAppId(String str) {
            this.f2591a = str;
            return this;
        }

        public Builder setAutoTrackActivityAction(boolean z2) {
            this.f2601k = z2;
            return this;
        }

        public Builder setChannel(String str) {
            this.f2593c = str;
            return this;
        }

        public Builder setExceptionCatcherEnable(boolean z2) {
            this.f2600j = z2;
            return this;
        }

        @Deprecated
        public Builder setGAIDEnable(boolean z2) {
            this.f2597g = z2;
            return this;
        }

        public Builder setImeiEnable(boolean z2) {
            this.f2599i = z2;
            return this;
        }

        public Builder setImsiEnable(boolean z2) {
            this.f2598h = z2;
            return this;
        }

        public Builder setInstanceId(String str) {
            this.f2603m = str;
            return this;
        }

        public Builder setInternational(boolean z2) {
            this.f2594d = z2;
            return this;
        }

        public Builder setMode(OneTrack.Mode mode) {
            this.f2596f = mode;
            return this;
        }

        public Builder setOverrideMiuiRegionSetting(boolean z2) {
            this.f2602l = z2;
            return this;
        }

        public Builder setPluginId(String str) {
            this.f2592b = str;
            return this;
        }

        public Builder setRegion(String str) {
            this.f2595e = str;
            return this;
        }

        public Builder setUseCustomPrivacyPolicy(boolean z2) {
            this.f2604n = z2;
            return this;
        }
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str) || str.length() <= 4) {
            sb.append(str);
        } else {
            for (int i2 = 0; i2 < str.length(); i2++) {
                if (i2 == 0 || i2 == 1 || i2 == str.length() - 2 || i2 == str.length() - 1) {
                    sb.append(str.charAt(i2));
                } else {
                    sb.append("*");
                }
            }
        }
        return sb.toString();
    }

    public String getAdEventAppId() {
        return this.f2589o;
    }

    public String getAppId() {
        return this.f2575a;
    }

    public String getChannel() {
        return this.f2577c;
    }

    public String getInstanceId() {
        return this.f2587m;
    }

    public OneTrack.Mode getMode() {
        return this.f2580f;
    }

    public String getPluginId() {
        return this.f2576b;
    }

    public String getRegion() {
        return this.f2579e;
    }

    public boolean isAutoTrackActivityAction() {
        return this.f2585k;
    }

    public boolean isExceptionCatcherEnable() {
        return this.f2584j;
    }

    @Deprecated
    public boolean isGAIDEnable() {
        return this.f2581g;
    }

    public boolean isIMEIEnable() {
        return this.f2583i;
    }

    public boolean isIMSIEnable() {
        return this.f2582h;
    }

    public boolean isInternational() {
        return this.f2578d;
    }

    public boolean isOverrideMiuiRegionSetting() {
        return this.f2586l;
    }

    public boolean isUseCustomPrivacyPolicy() {
        return this.f2588n;
    }

    public String toString() {
        try {
            return "Configuration{appId='" + a(this.f2575a) + "', pluginId='" + a(this.f2576b) + "', channel='" + this.f2577c + "', international=" + this.f2578d + ", region='" + this.f2579e + "', overrideMiuiRegionSetting=" + this.f2586l + ", mode=" + this.f2580f + ", GAIDEnable=" + this.f2581g + ", IMSIEnable=" + this.f2582h + ", IMEIEnable=" + this.f2583i + ", ExceptionCatcherEnable=" + this.f2584j + ", instanceId=" + a(this.f2587m) + '}';
        } catch (Exception unused) {
            return "";
        }
    }

    private Configuration(Builder builder) {
        this.f2580f = OneTrack.Mode.APP;
        this.f2581g = true;
        this.f2582h = true;
        this.f2583i = true;
        this.f2585k = true;
        this.f2586l = false;
        this.f2588n = false;
        this.f2575a = builder.f2591a;
        this.f2576b = builder.f2592b;
        this.f2577c = builder.f2593c;
        this.f2578d = builder.f2594d;
        this.f2579e = builder.f2595e;
        this.f2580f = builder.f2596f;
        this.f2581g = builder.f2597g;
        this.f2583i = builder.f2599i;
        this.f2582h = builder.f2598h;
        this.f2584j = builder.f2600j;
        this.f2585k = builder.f2601k;
        this.f2586l = builder.f2602l;
        this.f2587m = builder.f2603m;
        this.f2588n = builder.f2604n;
        this.f2589o = builder.f2605o;
    }
}
