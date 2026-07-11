package androidx.core.app;

import android.app.GrammaticalInflectionManager;
import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.OptIn;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.os.BuildCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class GrammaticalInflectionManagerCompat {
    public static final int GRAMMATICAL_GENDER_FEMININE = 2;
    public static final int GRAMMATICAL_GENDER_MASCULINE = 3;
    public static final int GRAMMATICAL_GENDER_NEUTRAL = 1;
    public static final int GRAMMATICAL_GENDER_NOT_SPECIFIED = 0;

    @RequiresApi(34)
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static int getApplicationGrammaticalGender(Context context) {
            return getGrammaticalInflectionManager(context).getApplicationGrammaticalGender();
        }

        private static GrammaticalInflectionManager getGrammaticalInflectionManager(Context context) {
            return (GrammaticalInflectionManager) context.getSystemService(GrammaticalInflectionManager.class);
        }

        public static void setRequestedApplicationGrammaticalGender(Context context, int i2) {
            getGrammaticalInflectionManager(context).setRequestedApplicationGrammaticalGender(i2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface GrammaticalGender {
    }

    private GrammaticalInflectionManagerCompat() {
    }

    @AnyThread
    @OptIn(markerClass = {BuildCompat.PrereleaseSdkCheck.class})
    public static int getApplicationGrammaticalGender(Context context) {
        return Api34Impl.getApplicationGrammaticalGender(context);
    }

    @AnyThread
    @OptIn(markerClass = {BuildCompat.PrereleaseSdkCheck.class})
    public static void setRequestedApplicationGrammaticalGender(Context context, int i2) {
        Api34Impl.setRequestedApplicationGrammaticalGender(context, i2);
    }
}
