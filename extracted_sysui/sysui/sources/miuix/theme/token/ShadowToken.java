package miuix.theme.token;

/* JADX INFO: loaded from: classes5.dex */
public class ShadowToken {
    public int color;
    public float dispersion = 1.0f;
    public int offsetX;
    public int offsetY;
    public int radius;
    public static ShadowToken Low = new Builder().setConfig(1157627904, 26, 46).build();
    public static ShadowToken Regular = new Builder().setConfig(1375731712, 33, 52).build();
    public static ShadowToken High = new Builder().setConfig(1375731712, 77, 52).build();
    public static ShadowToken ExtraHigh = new Builder().setConfig(1711276032, 105, 92).build();
    public static ShadowToken Float = new Builder().setConfig(1291845632, 0, 79).build();

    public static class Builder {
        private final ShadowToken mToken = new ShadowToken();

        public ShadowToken build() {
            return this.mToken;
        }

        public Builder setConfig(int i2, int i3, int i4) {
            ShadowToken shadowToken = this.mToken;
            shadowToken.color = i2;
            shadowToken.offsetX = 0;
            shadowToken.offsetY = i3;
            shadowToken.radius = i4;
            return this;
        }

        public Builder setConfig(int i2, int i3) {
            ShadowToken shadowToken = this.mToken;
            shadowToken.offsetX = 0;
            shadowToken.offsetY = i2;
            shadowToken.radius = i3;
            return this;
        }

        public Builder setConfig(int i2, int i3, int i4, int i5) {
            ShadowToken shadowToken = this.mToken;
            shadowToken.color = i2;
            shadowToken.offsetX = i3;
            shadowToken.offsetY = i4;
            shadowToken.radius = i5;
            return this;
        }
    }
}
