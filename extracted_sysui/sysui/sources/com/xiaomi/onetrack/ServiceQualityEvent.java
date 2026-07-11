package com.xiaomi.onetrack;

import android.text.TextUtils;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.util.aa;
import java.util.Map;
import miui.systemui.notification.focus.Const;
import miuix.android.content.MiuiIntent;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceQualityEvent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2650a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2651b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f2652c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2653d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f2654e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Integer f2655f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Integer f2656g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f2657h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private String f2658i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Integer f2659j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Long f2660k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Long f2661l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Long f2662m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Long f2663n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Long f2664o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Long f2665p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Long f2666q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private Long f2667r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private String f2668s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private String f2669t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private Map<String, Object> f2670u;

    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2671a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2672b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Integer f2673c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f2674d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f2675e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private Integer f2676f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private Integer f2677g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f2678h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private ResultType f2679i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private Integer f2680j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private Long f2681k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private Long f2682l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private Long f2683m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private Long f2684n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private Long f2685o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private Long f2686p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private Long f2687q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private Long f2688r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        private OneTrack.NetType f2689s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        private String f2690t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        private Map<String, Object> f2691u;

        public ServiceQualityEvent build() {
            return new ServiceQualityEvent(this);
        }

        public Builder setDnsLookupTime(Long l2) {
            this.f2681k = l2;
            return this;
        }

        public Builder setDuration(Long l2) {
            this.f2687q = l2;
            return this;
        }

        public Builder setExceptionTag(String str) {
            this.f2678h = str;
            return this;
        }

        public Builder setExtraParams(Map<String, Object> map) {
            this.f2691u = map;
            return this;
        }

        public Builder setHandshakeTime(Long l2) {
            this.f2683m = l2;
            return this;
        }

        public Builder setHost(String str) {
            this.f2672b = str;
            return this;
        }

        public Builder setIps(String... strArr) {
            if (strArr != null) {
                this.f2675e = TextUtils.join(aa.f3429b, strArr);
            }
            return this;
        }

        public Builder setNetSdkVersion(String str) {
            this.f2690t = str;
            return this;
        }

        public Builder setPath(String str) {
            this.f2674d = str;
            return this;
        }

        public Builder setPort(Integer num) {
            this.f2673c = num;
            return this;
        }

        public Builder setReceiveAllByteTime(Long l2) {
            this.f2686p = l2;
            return this;
        }

        public Builder setReceiveFirstByteTime(Long l2) {
            this.f2685o = l2;
            return this;
        }

        public Builder setRequestDataSendTime(Long l2) {
            this.f2684n = l2;
            return this;
        }

        public Builder setRequestNetType(OneTrack.NetType netType) {
            this.f2689s = netType;
            return this;
        }

        public Builder setRequestTimestamp(Long l2) {
            this.f2688r = l2;
            return this;
        }

        public Builder setResponseCode(Integer num) {
            this.f2676f = num;
            return this;
        }

        public Builder setResultType(ResultType resultType) {
            this.f2679i = resultType;
            return this;
        }

        public Builder setRetryCount(Integer num) {
            this.f2680j = num;
            return this;
        }

        public Builder setScheme(String str) {
            this.f2671a = str;
            return this;
        }

        public Builder setStatusCode(Integer num) {
            this.f2677g = num;
            return this;
        }

        public Builder setTcpConnectTime(Long l2) {
            this.f2682l = l2;
            return this;
        }
    }

    public enum ResultType {
        SUCCESS(MiuiIntent.COMMAND_ICON_PANEL_OK),
        FAILED("failed"),
        TIMEOUT(Const.Param.TIMEOUT_MIN);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2693a;

        ResultType(String str) {
            this.f2693a = str;
        }

        public String getResultType() {
            return this.f2693a;
        }
    }

    public Long getDnsLookupTime() {
        return this.f2660k;
    }

    public Long getDuration() {
        return this.f2666q;
    }

    public String getExceptionTag() {
        return this.f2657h;
    }

    public Map<String, Object> getExtraParams() {
        return this.f2670u;
    }

    public Long getHandshakeTime() {
        return this.f2662m;
    }

    public String getHost() {
        return this.f2651b;
    }

    public String getIps() {
        return this.f2654e;
    }

    public String getNetSdkVersion() {
        return this.f2669t;
    }

    public String getPath() {
        return this.f2653d;
    }

    public Integer getPort() {
        return this.f2652c;
    }

    public Long getReceiveAllByteTime() {
        return this.f2665p;
    }

    public Long getReceiveFirstByteTime() {
        return this.f2664o;
    }

    public Long getRequestDataSendTime() {
        return this.f2663n;
    }

    public String getRequestNetType() {
        return this.f2668s;
    }

    public Long getRequestTimestamp() {
        return this.f2667r;
    }

    public Integer getResponseCode() {
        return this.f2655f;
    }

    public String getResultType() {
        return this.f2658i;
    }

    public Integer getRetryCount() {
        return this.f2659j;
    }

    public String getScheme() {
        return this.f2650a;
    }

    public Integer getStatusCode() {
        return this.f2656g;
    }

    public Long getTcpConnectTime() {
        return this.f2661l;
    }

    private ServiceQualityEvent(Builder builder) {
        this.f2650a = builder.f2671a;
        this.f2651b = builder.f2672b;
        this.f2652c = builder.f2673c;
        this.f2653d = builder.f2674d;
        this.f2654e = builder.f2675e;
        this.f2655f = builder.f2676f;
        this.f2656g = builder.f2677g;
        this.f2657h = builder.f2678h;
        this.f2658i = builder.f2679i != null ? builder.f2679i.getResultType() : null;
        this.f2659j = builder.f2680j;
        this.f2660k = builder.f2681k;
        this.f2661l = builder.f2682l;
        this.f2662m = builder.f2683m;
        this.f2664o = builder.f2685o;
        this.f2665p = builder.f2686p;
        this.f2667r = builder.f2688r;
        this.f2668s = builder.f2689s != null ? builder.f2689s.toString() : null;
        this.f2663n = builder.f2684n;
        this.f2666q = builder.f2687q;
        this.f2669t = builder.f2690t;
        this.f2670u = builder.f2691u;
    }
}
