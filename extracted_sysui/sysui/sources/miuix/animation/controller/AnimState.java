package miuix.animation.controller;

import android.util.Log;
import androidx.annotation.Nullable;
import com.xiaomi.onetrack.api.au;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import miuix.animation.FolmeFactory;
import miuix.animation.IAnimTarget;
import miuix.animation.ViewTarget;
import miuix.animation.base.AnimConfig;
import miuix.animation.base.AnimSpecialConfig;
import miuix.animation.internal.AnimValueUtils;
import miuix.animation.internal.DesignReview;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ColorProperty;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.IIntValueProperty;
import miuix.animation.property.ISpecificProperty;
import miuix.animation.property.IntValueProperty;
import miuix.animation.property.ValueProperty;
import miuix.animation.property.ViewProperty;
import miuix.animation.property.ViewPropertyExt;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.ObjectPool;

/* JADX INFO: loaded from: classes4.dex */
public class AnimState implements DesignReview {
    public static final long FLAG_IN_TOUCH = 4;
    public static final long FLAG_PARALLEL = 2;
    public static final long FLAG_QUEUE = 1;
    private static final int STEP = 100;
    private static final String TAG = "TAG_";
    public static final int VIEW_POS = 1000100;
    public static final int VIEW_SIZE = 1000000;
    private static final AtomicInteger sId = new AtomicInteger();
    public long flags;

    @Nullable
    private volatile String mAlias;
    private final AnimConfig mConfig;
    private final Map<Object, Double> mInitMap;
    private final Map<Object, Double> mMap;
    private volatile Object mTag;
    public final boolean needDuplicate;
    IntValueProperty tempIntValueProperty;
    ValueProperty tempValueProperty;

    public AnimState() {
        this(null, null, false);
    }

    public static void alignState(AnimState animState, Collection<UpdateInfo> collection) {
        for (UpdateInfo updateInfo : collection) {
            if (!animState.contains(updateInfo.property)) {
                if (updateInfo.useInt) {
                    animState.add(updateInfo.property, (int) updateInfo.animInfo.startValue);
                } else {
                    animState.add(updateInfo.property, (float) updateInfo.animInfo.startValue);
                }
            }
        }
        ObjectPool objPool = FolmeFactory.getEngine().getObjPool();
        List list = (List) ObjectPool.acquire(objPool, ArrayList.class, new Object[0]);
        for (Object obj : animState.keySet()) {
            if ((obj instanceof FloatProperty ? UpdateInfo.findBy(collection, (FloatProperty) obj) : UpdateInfo.findByName(collection, (String) obj)) == null) {
                list.add(obj);
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            animState.remove(it.next());
        }
        ObjectPool.release(objPool, list);
    }

    private void append(AnimState animState) {
        this.mConfig.copy(animState.mConfig);
        this.mInitMap.clear();
        this.mInitMap.putAll(animState.mInitMap);
        this.mMap.clear();
        this.mMap.putAll(animState.mMap);
    }

    private Double getInitMapValue(Object obj) {
        Double d2 = this.mInitMap.get(obj);
        return (d2 == null && (obj instanceof FloatProperty)) ? this.mInitMap.get(((FloatProperty) obj).getName()) : d2;
    }

    private Double getMapValue(Object obj) {
        Double d2 = this.mMap.get(obj);
        return (d2 == null && (obj instanceof FloatProperty)) ? this.mMap.get(((FloatProperty) obj).getName()) : d2;
    }

    private double getProperValue(IAnimTarget iAnimTarget, FloatProperty floatProperty, double d2) {
        long configFlags = getConfigFlags(floatProperty);
        boolean zHasFlags = CommonUtils.hasFlags(configFlags, 1L);
        if (!zHasFlags && d2 != 1000000.0d && d2 != 1000100.0d && !(floatProperty instanceof ISpecificProperty)) {
            return d2;
        }
        double value = AnimValueUtils.getValue(iAnimTarget, floatProperty, d2);
        if (!zHasFlags || !AnimValueUtils.isValid(d2)) {
            return value;
        }
        setConfigFlag(floatProperty, configFlags & (-2));
        double d3 = value + d2;
        setMapValue(floatProperty, d3);
        return d3;
    }

    private boolean removeInitMapValue(Object obj) {
        if (obj instanceof FloatProperty) {
            FloatProperty floatProperty = (FloatProperty) obj;
            if (this.mInitMap.containsKey(floatProperty.getName())) {
                this.mInitMap.remove(floatProperty.getName());
                return true;
            }
        }
        if (!this.mInitMap.containsKey(obj)) {
            return false;
        }
        this.mInitMap.remove(obj);
        return true;
    }

    private void setInitMapValue(Object obj, double d2) {
        if (obj instanceof FloatProperty) {
            FloatProperty floatProperty = (FloatProperty) obj;
            if (this.mInitMap.containsKey(floatProperty.getName())) {
                this.mInitMap.put(floatProperty.getName(), Double.valueOf(d2));
                return;
            }
        }
        this.mInitMap.put(obj, Double.valueOf(d2));
    }

    private void setMapValue(Object obj, double d2) {
        if (obj instanceof FloatProperty) {
            FloatProperty floatProperty = (FloatProperty) obj;
            if (this.mMap.containsKey(floatProperty.getName())) {
                this.mMap.put(floatProperty.getName(), Double.valueOf(d2));
                return;
            }
        }
        this.mMap.put(obj, Double.valueOf(d2));
    }

    public AnimState add(String str, float f2) {
        return add(str, f2, (long[]) null);
    }

    public AnimState addWithInit(String str, float f2, float f3) {
        return addWithInit(str, f2, f3, (long[]) null);
    }

    public void clear() {
        this.mConfig.clear();
        this.mInitMap.clear();
        this.mMap.clear();
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.mMap.containsKey(obj)) {
            return true;
        }
        if (obj instanceof FloatProperty) {
            return this.mMap.containsKey(((FloatProperty) obj).getName());
        }
        return false;
    }

    public double get(IAnimTarget iAnimTarget, FloatProperty floatProperty) {
        Double mapValue = getMapValue(floatProperty);
        if (mapValue != null) {
            return getProperValue(iAnimTarget, floatProperty, mapValue.doubleValue());
        }
        return Double.MAX_VALUE;
    }

    @Nullable
    public String getAlias() {
        return this.mAlias;
    }

    public AnimConfig getConfig() {
        return this.mConfig;
    }

    public long getConfigFlags(Object obj) {
        AnimSpecialConfig specialConfig = this.mConfig.getSpecialConfig(obj instanceof FloatProperty ? ((FloatProperty) obj).getName() : (String) obj);
        if (specialConfig != null) {
            return specialConfig.flags;
        }
        return 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.animation.internal.DesignReview
    public String getDesignInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"");
        sb.append(au.f2921a);
        sb.append("\": ");
        sb.append("\"");
        sb.append(this.mAlias == null ? "null" : this.mAlias);
        sb.append("\", ");
        for (Object obj : this.mMap.keySet()) {
            String name = obj instanceof FloatProperty ? ((FloatProperty) obj).getName() : obj.toString();
            FloatProperty property = getProperty(obj);
            sb.append("\"");
            sb.append(name);
            sb.append("\": ");
            if (property == ViewPropertyExt.FOREGROUND || property == ViewPropertyExt.BACKGROUND || (property instanceof ColorProperty)) {
                int i2 = getInt((IIntValueProperty) property);
                if (i2 == 0) {
                    sb.append("\"#00000000\"");
                } else {
                    sb.append("\"#" + Integer.toHexString(i2) + "\"");
                }
            } else if (property instanceof IIntValueProperty) {
                sb.append(getInt((IIntValueProperty) property));
            } else {
                sb.append(getFloat(property));
            }
            sb.append(", ");
        }
        int iLastIndexOf = sb.lastIndexOf(", ");
        sb.delete(iLastIndexOf, iLastIndexOf + 2);
        sb.append('}');
        return sb.toString();
    }

    public float getFloat(FloatProperty floatProperty) {
        Double mapValue = getMapValue(floatProperty);
        if (mapValue != null) {
            return mapValue.floatValue();
        }
        return Float.MAX_VALUE;
    }

    public double getInit(IAnimTarget iAnimTarget, FloatProperty floatProperty) {
        Double initMapValue = getInitMapValue(floatProperty);
        if (initMapValue != null) {
            return initMapValue.doubleValue();
        }
        return Double.MAX_VALUE;
    }

    public int getInt(IIntValueProperty iIntValueProperty) {
        Double mapValue = getMapValue(iIntValueProperty);
        if (mapValue != null) {
            return mapValue.intValue();
        }
        return Integer.MAX_VALUE;
    }

    public FloatProperty getProperty(IAnimTarget iAnimTarget, Object obj) {
        FloatProperty floatProperty = ((obj instanceof String) && (iAnimTarget instanceof ViewTarget)) ? ViewTarget.getFloatProperty((String) obj) : null;
        return floatProperty == null ? getProperty(obj) : floatProperty;
    }

    public Object getTag() {
        return this.mTag;
    }

    public FloatProperty getTempProperty(Object obj) {
        if (obj instanceof FloatProperty) {
            return (FloatProperty) obj;
        }
        String str = (String) obj;
        ValueProperty valueProperty = CommonUtils.hasFlags(getConfigFlags(str), 4L) ? this.tempIntValueProperty : this.tempValueProperty;
        valueProperty.setName(str);
        return valueProperty;
    }

    public boolean isEmpty() {
        return this.mMap.isEmpty();
    }

    public Set<Object> keySet() {
        return this.mMap.keySet();
    }

    public AnimState remove(Object obj) {
        this.mMap.remove(obj);
        if (obj instanceof FloatProperty) {
            this.mMap.remove(((FloatProperty) obj).getName());
        }
        return this;
    }

    public void set(AnimState animState) {
        if (animState == null) {
            return;
        }
        setTag(animState.mTag);
        append(animState);
    }

    public final AnimState setAlias(String str) {
        this.mAlias = str;
        return this;
    }

    public void setConfigFlag(Object obj, long j2) {
        this.mConfig.queryAndCreateSpecial(obj instanceof FloatProperty ? ((FloatProperty) obj).getName() : (String) obj).flags = j2;
    }

    public final void setTag(Object obj) {
        if (obj == null) {
            obj = TAG + sId.incrementAndGet();
        }
        this.mTag = obj;
    }

    public String toString() {
        return "\nState{\n\ttag='" + this.mTag + "',\n\tflags=" + this.flags + ",\n\tconfig=" + this.mConfig + ",\n\tmaps=" + ((Object) CommonUtils.mapToString(this.mMap, "    ")) + "\n}";
    }

    public AnimState(Object obj) {
        this(obj, null, false);
    }

    public AnimState add(String str, float f2, long... jArr) {
        if (jArr != null && jArr.length > 0) {
            setConfigFlag(str, jArr[0]);
        }
        return add(str, f2);
    }

    public AnimState addWithInit(String str, float f2, float f3, long... jArr) {
        if (jArr != null && jArr.length > 0) {
            setConfigFlag(str, jArr[0]);
        }
        return addWithInit(str, f2, f3);
    }

    public AnimState(Object obj, String str) {
        this(obj, str, false);
    }

    public float getFloat(String str) {
        Double mapValue = getMapValue(str);
        if (mapValue != null) {
            return mapValue.floatValue();
        }
        return Float.MAX_VALUE;
    }

    public int getInt(String str) {
        return getInt(new IntValueProperty(str));
    }

    public AnimState(Object obj, boolean z2) {
        this.tempValueProperty = new ValueProperty("");
        this.tempIntValueProperty = new IntValueProperty("");
        this.mConfig = new AnimConfig();
        this.mInitMap = new ConcurrentHashMap();
        this.mMap = new ConcurrentHashMap();
        setTag(obj);
        if (obj instanceof String) {
            setAlias((String) obj);
        }
        this.needDuplicate = z2;
    }

    public FloatProperty getProperty(Object obj) {
        if (obj instanceof FloatProperty) {
            return (FloatProperty) obj;
        }
        String str = (String) obj;
        if (CommonUtils.hasFlags(getConfigFlags(str), 4L)) {
            return new IntValueProperty(str);
        }
        return new ValueProperty(str);
    }

    public AnimState add(String str, int i2) {
        return add(str, i2, (long[]) null);
    }

    public AnimState addWithInit(String str, int i2, int i3) {
        return addWithInit(str, i2, i3, (long[]) null);
    }

    public AnimState add(String str, int i2, long... jArr) {
        if (jArr != null && jArr.length > 0) {
            setConfigFlag(str, 4 | jArr[0]);
        } else {
            setConfigFlag(str, 4 | getConfigFlags(str));
        }
        return add(str, i2);
    }

    public AnimState addWithInit(String str, int i2, int i3, long... jArr) {
        if (jArr != null && jArr.length > 0) {
            setConfigFlag(str, 4 | jArr[0]);
        } else {
            setConfigFlag(str, 4 | getConfigFlags(str));
        }
        return addWithInit(str, i2, i3);
    }

    public AnimState add(ViewProperty viewProperty, float f2, long... jArr) {
        return add((FloatProperty) viewProperty, f2, jArr);
    }

    public AnimState addWithInit(ViewProperty viewProperty, float f2, float f3, long... jArr) {
        return addWithInit((FloatProperty) viewProperty, f2, f3, jArr);
    }

    public AnimState add(ViewProperty viewProperty, int i2, long... jArr) {
        return add((FloatProperty) viewProperty, i2, jArr);
    }

    public AnimState addWithInit(ViewProperty viewProperty, int i2, int i3, long... jArr) {
        return addWithInit((FloatProperty) viewProperty, i2, i3, jArr);
    }

    public AnimState add(FloatProperty floatProperty, float f2, long... jArr) {
        if (jArr != null && jArr.length > 0) {
            setConfigFlag(floatProperty, jArr[0]);
        }
        return add(floatProperty, f2);
    }

    public AnimState addWithInit(FloatProperty floatProperty, float f2, float f3, long... jArr) {
        if (jArr != null && jArr.length > 0) {
            setConfigFlag(floatProperty, jArr[0]);
        }
        return addWithInit(floatProperty, f2, f3);
    }

    public AnimState(Object obj, String str, boolean z2) {
        this.tempValueProperty = new ValueProperty("");
        this.tempIntValueProperty = new IntValueProperty("");
        this.mConfig = new AnimConfig();
        this.mInitMap = new ConcurrentHashMap();
        this.mMap = new ConcurrentHashMap();
        setTag(obj);
        if (str == null) {
            if (obj instanceof String) {
                setAlias((String) obj);
            }
        } else {
            setAlias(str);
        }
        this.needDuplicate = z2;
    }

    public AnimState add(FloatProperty floatProperty, int i2, long... jArr) {
        if (jArr != null && jArr.length > 0) {
            setConfigFlag(floatProperty, 4 | jArr[0]);
        } else {
            setConfigFlag(floatProperty, 4 | getConfigFlags(floatProperty));
        }
        return add(floatProperty, i2);
    }

    public AnimState addWithInit(FloatProperty floatProperty, int i2, int i3, long... jArr) {
        if (jArr != null && jArr.length > 0) {
            setConfigFlag(floatProperty, 4 | jArr[0]);
        } else {
            setConfigFlag(floatProperty, 4 | getConfigFlags(floatProperty));
        }
        return addWithInit(floatProperty, i2, i3);
    }

    public AnimState add(Object obj, double d2) {
        if (Double.isNaN(d2)) {
            Log.w(CommonUtils.TAG, "warning! the add value is NaN, will not add to AnimState. key: " + obj + " trace: " + Log.getStackTraceString(new Throwable()));
            return this;
        }
        if (Double.isInfinite(d2)) {
            Log.w(CommonUtils.TAG, "warning! the add value is Infinite, will not add to AnimState. key: " + obj + " trace: " + Log.getStackTraceString(new Throwable()));
            return this;
        }
        if (removeInitMapValue(obj)) {
            setConfigFlag(obj, getConfigFlags(obj) & (-9));
        }
        setMapValue(obj, d2);
        return this;
    }

    public AnimState addWithInit(Object obj, double d2, double d3) {
        if (Double.isNaN(d2)) {
            Log.w(CommonUtils.TAG, "warning! the add initValue is NaN, will not add to AnimState. key: " + obj + " trace: " + Log.getStackTraceString(new Throwable()));
            return this;
        }
        if (Double.isInfinite(d2)) {
            Log.w(CommonUtils.TAG, "warning! the add initValue is Infinite, will not add to AnimState. key: " + obj + " trace: " + Log.getStackTraceString(new Throwable()));
            return this;
        }
        if (Double.isNaN(d3)) {
            Log.w(CommonUtils.TAG, "warning! the add value is NaN, will not add to AnimState. key: " + obj + " trace: " + Log.getStackTraceString(new Throwable()));
            return this;
        }
        if (Double.isInfinite(d3)) {
            Log.w(CommonUtils.TAG, "warning! the add value is Infinite, will not add to AnimState. key: " + obj + " trace: " + Log.getStackTraceString(new Throwable()));
            return this;
        }
        setConfigFlag(obj, getConfigFlags(obj) | 8);
        setInitMapValue(obj, d2);
        setMapValue(obj, d3);
        return this;
    }
}
