package miuix.view;

/* JADX INFO: loaded from: classes5.dex */
public interface EditActionMode {
    public static final int BUTTON1 = 16908313;
    public static final int BUTTON2 = 16908314;

    void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener);

    void announceAccessibilityEvent(String str);

    void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener);

    void setAnnounceAccessibilityEnabled(boolean z2);

    void setButton(int i2, int i3);

    void setButton(int i2, int i3, int i4);

    void setButton(int i2, CharSequence charSequence);

    void setButton(int i2, CharSequence charSequence, int i3);

    void setButton(int i2, CharSequence charSequence, int i3, CharSequence charSequence2, int i4);

    void setButton(int i2, CharSequence charSequence, CharSequence charSequence2, int i3);

    void setFinishEditActionModeDescription(int i2);

    void setStartEditActionModeDescription(int i2);
}
