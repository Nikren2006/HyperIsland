package miuix.mgl.particle;

/* JADX INFO: loaded from: classes3.dex */
public class Particle {
    public static final int DISPATCH_SIZE = 32;
    public float alpha;
    public float duration;
    public float lifeTime;
    public float preWarmFactor;
    public float rotateFactor;
    public float scaleFactor;
    public float startTime;
    public int state;
    public float[] param1 = new float[4];
    public float[] param2 = new float[4];
    public float[] matrix = new float[16];

    public enum State {
        BORN,
        WAIT,
        RUN,
        DIE
    }

    public static int byteSizeSTD430() {
        return 128;
    }
}
