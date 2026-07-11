package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigurationCompat {

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static LocaleList getLocales(Configuration configuration) {
            return configuration.getLocales();
        }

        public static void setLocales(Configuration configuration, LocaleListCompat localeListCompat) {
            configuration.setLocales((LocaleList) localeListCompat.unwrap());
        }
    }

    private ConfigurationCompat() {
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        return LocaleListCompat.wrap(Api24Impl.getLocales(configuration));
    }

    public static void setLocales(Configuration configuration, LocaleListCompat localeListCompat) {
        Api24Impl.setLocales(configuration, localeListCompat);
    }
}
