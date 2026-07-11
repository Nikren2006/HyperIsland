package com.miui.circulate.device.api;

import I0.AbstractC0180h;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public final class BatteryInfo implements Flat {
    public static final Companion Companion = new Companion(null);
    private final Double[] values;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BatteryInfo of(double d2) {
            return new BatteryInfo(new Double[]{Double.valueOf(d2)});
        }

        public final BatteryInfo ofMultiple(double... values) {
            n.g(values, "values");
            return new BatteryInfo(AbstractC0180h.v(values));
        }

        public final BatteryInfo parse(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            Double[] dArr = new Double[length];
            for (int i2 = 0; i2 < length; i2++) {
                dArr[i2] = Double.valueOf(0.0d);
            }
            int length2 = jSONArray.length();
            for (int i3 = 0; i3 < length2; i3++) {
                dArr[i3] = Double.valueOf(jSONArray.getDouble(i3));
            }
            return new BatteryInfo(dArr);
        }

        private Companion() {
        }
    }

    public BatteryInfo(Double[] values) {
        n.g(values, "values");
        this.values = values;
    }

    public static /* synthetic */ BatteryInfo copy$default(BatteryInfo batteryInfo, Double[] dArr, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            dArr = batteryInfo.values;
        }
        return batteryInfo.copy(dArr);
    }

    public static final BatteryInfo of(double d2) {
        return Companion.of(d2);
    }

    public static final BatteryInfo ofMultiple(double... dArr) {
        return Companion.ofMultiple(dArr);
    }

    public final Double[] component1() {
        return this.values;
    }

    public final BatteryInfo copy(Double[] values) {
        n.g(values, "values");
        return new BatteryInfo(values);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!n.c(BatteryInfo.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        n.e(obj, "null cannot be cast to non-null type com.miui.circulate.device.api.BatteryInfo");
        return Arrays.equals(this.values, ((BatteryInfo) obj).values);
    }

    @Override // com.miui.circulate.device.api.Flat
    public String flat() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Double d2 : this.values) {
            jSONArray.put(d2.doubleValue());
        }
        String string = jSONArray.toString();
        n.f(string, "json.toString()");
        return string;
    }

    public final Double[] getValues() {
        return this.values;
    }

    public int hashCode() {
        return Arrays.hashCode(this.values);
    }

    public String toString() {
        return "BatteryInfo(values=" + Arrays.toString(this.values) + ')';
    }
}
