package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.LocaleList;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.annotation.RequiresApi;
import androidx.core.os.LocaleListCompat;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_MAGNIFICATION_OVERLAY = 6;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private static final int UNDEFINED = -1;
    private final Object mInfo;

    @RequiresApi(21)
    public static class Api21Impl {
        private Api21Impl() {
        }

        public static void getBoundsInScreen(AccessibilityWindowInfo accessibilityWindowInfo, Rect rect) {
            accessibilityWindowInfo.getBoundsInScreen(rect);
        }

        public static AccessibilityWindowInfo getChild(AccessibilityWindowInfo accessibilityWindowInfo, int i2) {
            return accessibilityWindowInfo.getChild(i2);
        }

        public static int getChildCount(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getChildCount();
        }

        public static int getId(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getId();
        }

        public static int getLayer(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getLayer();
        }

        public static AccessibilityWindowInfo getParent(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getParent();
        }

        public static AccessibilityNodeInfo getRoot(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getRoot();
        }

        public static int getType(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getType();
        }

        public static boolean isAccessibilityFocused(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isAccessibilityFocused();
        }

        public static boolean isActive(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isActive();
        }

        public static boolean isFocused(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isFocused();
        }

        public static AccessibilityWindowInfo obtain() {
            return AccessibilityWindowInfo.obtain();
        }

        public static AccessibilityWindowInfo obtain(AccessibilityWindowInfo accessibilityWindowInfo) {
            return AccessibilityWindowInfo.obtain(accessibilityWindowInfo);
        }
    }

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static AccessibilityNodeInfo getAnchor(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getAnchor();
        }

        public static CharSequence getTitle(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getTitle();
        }
    }

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static boolean isInPictureInPictureMode(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.isInPictureInPictureMode();
        }
    }

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static AccessibilityWindowInfo instantiateAccessibilityWindowInfo() {
            return new AccessibilityWindowInfo();
        }
    }

    @RequiresApi(33)
    public static class Api33Impl {
        private Api33Impl() {
        }

        public static int getDisplayId(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getDisplayId();
        }

        public static void getRegionInScreen(AccessibilityWindowInfo accessibilityWindowInfo, Region region) {
            accessibilityWindowInfo.getRegionInScreen(region);
        }

        public static AccessibilityNodeInfoCompat getRoot(Object obj, int i2) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) obj).getRoot(i2));
        }
    }

    @RequiresApi(34)
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static LocaleList getLocales(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getLocales();
        }

        public static long getTransitionTimeMillis(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.getTransitionTimeMillis();
        }
    }

    public AccessibilityWindowInfoCompat() {
        this.mInfo = Api30Impl.instantiateAccessibilityWindowInfo();
    }

    public static AccessibilityWindowInfoCompat obtain() {
        return wrapNonNullInstance(Api21Impl.obtain());
    }

    private static String typeToString(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "<UNKNOWN>" : "TYPE_ACCESSIBILITY_OVERLAY" : "TYPE_SYSTEM" : "TYPE_INPUT_METHOD" : "TYPE_APPLICATION";
    }

    public static AccessibilityWindowInfoCompat wrapNonNullInstance(Object obj) {
        if (obj != null) {
            return new AccessibilityWindowInfoCompat(obj);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityWindowInfoCompat)) {
            return false;
        }
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat) obj;
        Object obj2 = this.mInfo;
        return obj2 == null ? accessibilityWindowInfoCompat.mInfo == null : obj2.equals(accessibilityWindowInfoCompat.mInfo);
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(Api24Impl.getAnchor((AccessibilityWindowInfo) this.mInfo));
    }

    public void getBoundsInScreen(Rect rect) {
        Api21Impl.getBoundsInScreen((AccessibilityWindowInfo) this.mInfo, rect);
    }

    public AccessibilityWindowInfoCompat getChild(int i2) {
        return wrapNonNullInstance(Api21Impl.getChild((AccessibilityWindowInfo) this.mInfo, i2));
    }

    public int getChildCount() {
        return Api21Impl.getChildCount((AccessibilityWindowInfo) this.mInfo);
    }

    public int getDisplayId() {
        return Api33Impl.getDisplayId((AccessibilityWindowInfo) this.mInfo);
    }

    public int getId() {
        return Api21Impl.getId((AccessibilityWindowInfo) this.mInfo);
    }

    public int getLayer() {
        return Api21Impl.getLayer((AccessibilityWindowInfo) this.mInfo);
    }

    public LocaleListCompat getLocales() {
        return LocaleListCompat.wrap(Api34Impl.getLocales((AccessibilityWindowInfo) this.mInfo));
    }

    public AccessibilityWindowInfoCompat getParent() {
        return wrapNonNullInstance(Api21Impl.getParent((AccessibilityWindowInfo) this.mInfo));
    }

    public void getRegionInScreen(Region region) {
        Api33Impl.getRegionInScreen((AccessibilityWindowInfo) this.mInfo, region);
    }

    public AccessibilityNodeInfoCompat getRoot() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(Api21Impl.getRoot((AccessibilityWindowInfo) this.mInfo));
    }

    public CharSequence getTitle() {
        return Api24Impl.getTitle((AccessibilityWindowInfo) this.mInfo);
    }

    public long getTransitionTimeMillis() {
        return Api34Impl.getTransitionTimeMillis((AccessibilityWindowInfo) this.mInfo);
    }

    public int getType() {
        return Api21Impl.getType((AccessibilityWindowInfo) this.mInfo);
    }

    public int hashCode() {
        Object obj = this.mInfo;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean isAccessibilityFocused() {
        return Api21Impl.isAccessibilityFocused((AccessibilityWindowInfo) this.mInfo);
    }

    public boolean isActive() {
        return Api21Impl.isActive((AccessibilityWindowInfo) this.mInfo);
    }

    public boolean isFocused() {
        return Api21Impl.isFocused((AccessibilityWindowInfo) this.mInfo);
    }

    public boolean isInPictureInPictureMode() {
        return Api26Impl.isInPictureInPictureMode((AccessibilityWindowInfo) this.mInfo);
    }

    @Deprecated
    public void recycle() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Rect rect = new Rect();
        getBoundsInScreen(rect);
        sb.append("AccessibilityWindowInfo[");
        sb.append("id=");
        sb.append(getId());
        sb.append(", type=");
        sb.append(typeToString(getType()));
        sb.append(", layer=");
        sb.append(getLayer());
        sb.append(", bounds=");
        sb.append(rect);
        sb.append(", focused=");
        sb.append(isFocused());
        sb.append(", active=");
        sb.append(isActive());
        sb.append(", hasParent=");
        sb.append(getParent() != null);
        sb.append(", hasChildren=");
        sb.append(getChildCount() > 0);
        sb.append(", transitionTime=");
        sb.append(getTransitionTimeMillis());
        sb.append(", locales=");
        sb.append(getLocales());
        sb.append(']');
        return sb.toString();
    }

    public AccessibilityWindowInfo unwrap() {
        return (AccessibilityWindowInfo) this.mInfo;
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat accessibilityWindowInfoCompat) {
        if (accessibilityWindowInfoCompat == null) {
            return null;
        }
        return wrapNonNullInstance(Api21Impl.obtain((AccessibilityWindowInfo) accessibilityWindowInfoCompat.mInfo));
    }

    private AccessibilityWindowInfoCompat(Object obj) {
        this.mInfo = obj;
    }

    public AccessibilityNodeInfoCompat getRoot(int i2) {
        return Api33Impl.getRoot(this.mInfo, i2);
    }
}
