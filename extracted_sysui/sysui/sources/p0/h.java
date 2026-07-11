package p0;

import android.media.AudioManager;
import com.miui.miplay.audio.data.MediaMetaData;

/* JADX INFO: loaded from: classes2.dex */
public abstract class h extends AbstractC0728a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AudioManager f6378c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6379d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public MediaMetaData f6380e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f6381f;

    public h(String str, AudioManager audioManager) {
        super(str);
        this.f6380e = new MediaMetaData();
        this.f6381f = getClass().getSimpleName();
        this.f6378c = audioManager;
        this.f6379d = audioManager.getStreamMaxVolume(3);
    }
}
