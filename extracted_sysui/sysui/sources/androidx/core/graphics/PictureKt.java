package androidx.core.graphics;

import android.graphics.Canvas;
import android.graphics.Picture;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.m;

/* JADX INFO: loaded from: classes.dex */
public final class PictureKt {
    public static final Picture record(Picture picture, int i2, int i3, Function1 function1) {
        Canvas canvasBeginRecording = picture.beginRecording(i2, i3);
        try {
            function1.invoke(canvasBeginRecording);
            return picture;
        } finally {
            m.b(1);
            picture.endRecording();
            m.a(1);
        }
    }
}
