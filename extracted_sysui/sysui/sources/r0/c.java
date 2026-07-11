package r0;

import android.media.AudioManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.miplay.audio.data.DeviceInfo;
import p0.h;
import z0.e;

/* JADX INFO: loaded from: classes2.dex */
public class c extends h {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final DeviceInfo f6434g;

    public c(String str, AudioManager audioManager) {
        super("LocalSpeaker", audioManager);
        Bundle bundle = new Bundle();
        bundle.putString(DeviceInfo.EXTRA_KEY_MI_ACCOUNT_ID, "LOCAL");
        this.f6434g = new DeviceInfo(str, null, 0, bundle);
    }

    @Override // p0.AbstractC0728a
    public int a(int i2) {
        return 1;
    }

    @Override // p0.AbstractC0728a
    public DeviceInfo c() {
        return this.f6434g;
    }

    @Override // p0.AbstractC0728a
    public int d(int i2) {
        int iD = super.d(i2);
        e.a("LocalSpeaker", "getStatus:" + iD);
        return iD;
    }

    @Override // p0.AbstractC0728a
    public synchronized boolean g(int i2, int i3) {
        return super.g(i2, i3);
    }

    public boolean h(String str) {
        if (TextUtils.equals(str, this.f6434g.getName())) {
            return false;
        }
        this.f6434g.setName(str);
        return true;
    }
}
