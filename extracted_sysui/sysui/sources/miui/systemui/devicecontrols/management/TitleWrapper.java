package miui.systemui.devicecontrols.management;

/* JADX INFO: loaded from: classes3.dex */
public final class TitleWrapper extends ElementWrapper {
    private final CharSequence title;

    public TitleWrapper(CharSequence charSequence) {
        this.title = charSequence;
    }

    public static /* synthetic */ TitleWrapper copy$default(TitleWrapper titleWrapper, CharSequence charSequence, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = titleWrapper.title;
        }
        return titleWrapper.copy(charSequence);
    }

    public final CharSequence component1() {
        return this.title;
    }

    public final TitleWrapper copy(CharSequence charSequence) {
        return new TitleWrapper(charSequence);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TitleWrapper) && kotlin.jvm.internal.n.c(this.title, ((TitleWrapper) obj).title);
    }

    public final CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        CharSequence charSequence = this.title;
        if (charSequence == null) {
            return 0;
        }
        return charSequence.hashCode();
    }

    public String toString() {
        return "TitleWrapper(title=" + ((Object) this.title) + ")";
    }
}
