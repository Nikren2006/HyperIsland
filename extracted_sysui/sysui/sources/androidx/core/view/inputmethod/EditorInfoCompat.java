package androidx.core.view.inputmethod;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public final class EditorInfoCompat {
    private static final String CONTENT_MIME_TYPES_INTEROP_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String CONTENT_MIME_TYPES_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String CONTENT_SELECTION_END_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END";
    private static final String CONTENT_SELECTION_HEAD_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD";
    private static final String CONTENT_SURROUNDING_TEXT_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;

    @VisibleForTesting
    static final int MAX_INITIAL_SELECTION_LENGTH = 1024;

    @VisibleForTesting
    static final int MEMORY_EFFICIENT_TEXT_LENGTH = 2048;

    @VisibleForTesting
    static final String STYLUS_HANDWRITING_ENABLED_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.STYLUS_HANDWRITING_ENABLED";

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static CharSequence getInitialSelectedText(EditorInfo editorInfo, int i2) {
            return editorInfo.getInitialSelectedText(i2);
        }

        public static CharSequence getInitialTextAfterCursor(EditorInfo editorInfo, int i2, int i3) {
            return editorInfo.getInitialTextAfterCursor(i2, i3);
        }

        public static CharSequence getInitialTextBeforeCursor(EditorInfo editorInfo, int i2, int i3) {
            return editorInfo.getInitialTextBeforeCursor(i2, i3);
        }

        public static void setInitialSurroundingSubText(EditorInfo editorInfo, CharSequence charSequence, int i2) {
            editorInfo.setInitialSurroundingSubText(charSequence, i2);
        }
    }

    @RequiresApi(35)
    public static class Api35Impl {
        private Api35Impl() {
        }

        public static boolean isStylusHandwritingEnabled(EditorInfo editorInfo) {
            return editorInfo.isStylusHandwritingEnabled();
        }

        public static void setStylusHandwritingEnabled(EditorInfo editorInfo, boolean z2) {
            editorInfo.setStylusHandwritingEnabled(z2);
        }
    }

    @Deprecated
    public EditorInfoCompat() {
    }

    public static String[] getContentMimeTypes(EditorInfo editorInfo) {
        String[] strArr = editorInfo.contentMimeTypes;
        return strArr != null ? strArr : EMPTY_STRING_ARRAY;
    }

    public static CharSequence getInitialSelectedText(EditorInfo editorInfo, int i2) {
        return Api30Impl.getInitialSelectedText(editorInfo, i2);
    }

    public static CharSequence getInitialTextAfterCursor(EditorInfo editorInfo, int i2, int i3) {
        return Api30Impl.getInitialTextAfterCursor(editorInfo, i2, i3);
    }

    public static CharSequence getInitialTextBeforeCursor(EditorInfo editorInfo, int i2, int i3) {
        return Api30Impl.getInitialTextBeforeCursor(editorInfo, i2, i3);
    }

    public static int getProtocol(EditorInfo editorInfo) {
        return 1;
    }

    private static boolean isCutOnSurrogate(CharSequence charSequence, int i2, int i3) {
        if (i3 == 0) {
            return Character.isLowSurrogate(charSequence.charAt(i2));
        }
        if (i3 != 1) {
            return false;
        }
        return Character.isHighSurrogate(charSequence.charAt(i2));
    }

    private static boolean isPasswordInputType(int i2) {
        int i3 = i2 & 4095;
        return i3 == 129 || i3 == 225 || i3 == 18;
    }

    public static boolean isStylusHandwritingEnabled(EditorInfo editorInfo) {
        Bundle bundle = editorInfo.extras;
        if (bundle != null && bundle.containsKey(STYLUS_HANDWRITING_ENABLED_KEY)) {
            return editorInfo.extras.getBoolean(STYLUS_HANDWRITING_ENABLED_KEY);
        }
        if (Build.VERSION.SDK_INT >= 35) {
            return Api35Impl.isStylusHandwritingEnabled(editorInfo);
        }
        return false;
    }

    public static void setContentMimeTypes(EditorInfo editorInfo, String[] strArr) {
        editorInfo.contentMimeTypes = strArr;
    }

    public static void setInitialSurroundingSubText(EditorInfo editorInfo, CharSequence charSequence, int i2) {
        Preconditions.checkNotNull(charSequence);
        Api30Impl.setInitialSurroundingSubText(editorInfo, charSequence, i2);
    }

    public static void setInitialSurroundingText(EditorInfo editorInfo, CharSequence charSequence) {
        Api30Impl.setInitialSurroundingSubText(editorInfo, charSequence, 0);
    }

    public static void setStylusHandwritingEnabled(EditorInfo editorInfo, boolean z2) {
        if (Build.VERSION.SDK_INT >= 35) {
            Api35Impl.setStylusHandwritingEnabled(editorInfo, z2);
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putBoolean(STYLUS_HANDWRITING_ENABLED_KEY, z2);
    }

    private static void setSurroundingText(EditorInfo editorInfo, CharSequence charSequence, int i2, int i3) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence(CONTENT_SURROUNDING_TEXT_KEY, charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt(CONTENT_SELECTION_HEAD_KEY, i2);
        editorInfo.extras.putInt(CONTENT_SELECTION_END_KEY, i3);
    }

    private static void trimLongSurroundingText(EditorInfo editorInfo, CharSequence charSequence, int i2, int i3) {
        int i4 = i3 - i2;
        int i5 = i4 > 1024 ? 0 : i4;
        int i6 = 2048 - i5;
        int iMin = Math.min(charSequence.length() - i3, i6 - Math.min(i2, (int) (((double) i6) * 0.8d)));
        int iMin2 = Math.min(i2, i6 - iMin);
        int i7 = i2 - iMin2;
        if (isCutOnSurrogate(charSequence, i7, 0)) {
            i7++;
            iMin2--;
        }
        if (isCutOnSurrogate(charSequence, (i3 + iMin) - 1, 1)) {
            iMin--;
        }
        setSurroundingText(editorInfo, i5 != i4 ? TextUtils.concat(charSequence.subSequence(i7, i7 + iMin2), charSequence.subSequence(i3, iMin + i3)) : charSequence.subSequence(i7, iMin2 + i5 + iMin + i7), iMin2, i5 + iMin2);
    }
}
