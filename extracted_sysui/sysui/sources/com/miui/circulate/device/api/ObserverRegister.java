package com.miui.circulate.device.api;

import I0.G;
import android.database.ContentObserver;
import android.net.Uri;
import java.util.Map;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public interface ObserverRegister {

    public static final class ContentObserverDesc {
        private final boolean notifyForDescendants;
        private final ContentObserver observer;
        private final Uri uri;

        public ContentObserverDesc(Uri uri, boolean z2, ContentObserver observer) {
            n.g(uri, "uri");
            n.g(observer, "observer");
            this.uri = uri;
            this.notifyForDescendants = z2;
            this.observer = observer;
        }

        public static /* synthetic */ ContentObserverDesc copy$default(ContentObserverDesc contentObserverDesc, Uri uri, boolean z2, ContentObserver contentObserver, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                uri = contentObserverDesc.uri;
            }
            if ((i2 & 2) != 0) {
                z2 = contentObserverDesc.notifyForDescendants;
            }
            if ((i2 & 4) != 0) {
                contentObserver = contentObserverDesc.observer;
            }
            return contentObserverDesc.copy(uri, z2, contentObserver);
        }

        public final Uri component1() {
            return this.uri;
        }

        public final boolean component2() {
            return this.notifyForDescendants;
        }

        public final ContentObserver component3() {
            return this.observer;
        }

        public final ContentObserverDesc copy(Uri uri, boolean z2, ContentObserver observer) {
            n.g(uri, "uri");
            n.g(observer, "observer");
            return new ContentObserverDesc(uri, z2, observer);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ContentObserverDesc)) {
                return false;
            }
            ContentObserverDesc contentObserverDesc = (ContentObserverDesc) obj;
            return n.c(this.uri, contentObserverDesc.uri) && this.notifyForDescendants == contentObserverDesc.notifyForDescendants && n.c(this.observer, contentObserverDesc.observer);
        }

        public final boolean getNotifyForDescendants() {
            return this.notifyForDescendants;
        }

        public final ContentObserver getObserver() {
            return this.observer;
        }

        public final Uri getUri() {
            return this.uri;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v3 */
        public int hashCode() {
            int iHashCode = this.uri.hashCode() * 31;
            boolean z2 = this.notifyForDescendants;
            ?? r12 = z2;
            if (z2) {
                r12 = 1;
            }
            return ((iHashCode + r12) * 31) + this.observer.hashCode();
        }

        public String toString() {
            return "ContentObserverDesc(uri=" + this.uri + ", notifyForDescendants=" + this.notifyForDescendants + ", observer=" + this.observer + ')';
        }
    }

    public static final class DefaultImpls {
        public static Map<Uri, ContentObserverDesc> activeObservers(ObserverRegister observerRegister) {
            return G.f();
        }
    }

    Map<Uri, ContentObserverDesc> activeObservers();

    void registerContentObserver(Uri uri, boolean z2, ContentObserver contentObserver);

    void unregisterContentObserver(ContentObserver contentObserver);
}
