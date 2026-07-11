package V;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.RUNTIME)
public @interface c {
    String[] alternate() default {};

    String value();
}
