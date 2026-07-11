package m;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: renamed from: m.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0462b implements f {
    @Override // m.f
    public d a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        return new C0461a(httpURLConnection);
    }
}
