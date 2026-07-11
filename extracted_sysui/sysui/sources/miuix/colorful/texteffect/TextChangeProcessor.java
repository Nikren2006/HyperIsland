package miuix.colorful.texteffect;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public interface TextChangeProcessor {
    void clear();

    @Nullable
    CharSequence formatContent(TimerTextEffectView timerTextEffectView, @Nullable CharSequence charSequence);

    boolean isRunningAnim();
}
