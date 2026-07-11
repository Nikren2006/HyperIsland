package android.support.v4.media;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.b;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.media.MediaBrowserProtocol;
import androidx.media.MediaBrowserServiceCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MediaBrowserCompat {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f989b = Log.isLoggable("MediaBrowserCompat", 3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f990a;

    public static class CustomActionResultReceiver extends c.b {
        @Override // c.b
        public void q(int i2, Bundle bundle) {
        }
    }

    public static class ItemReceiver extends c.b {
        @Override // c.b
        public void q(int i2, Bundle bundle) {
            if (bundle != null) {
                bundle = MediaSessionCompat.j(bundle);
            }
            if (i2 != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                throw null;
            }
            Parcelable parcelable = bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
            if (parcelable != null && !(parcelable instanceof MediaItem)) {
                throw null;
            }
            throw null;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f991a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final MediaDescriptionCompat f992b;

        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public MediaItem[] newArray(int i2) {
                return new MediaItem[i2];
            }
        }

        public MediaItem(Parcel parcel) {
            this.f991a = parcel.readInt();
            this.f992b = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.f991a + ", mDescription=" + this.f992b + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f991a);
            this.f992b.writeToParcel(parcel, i2);
        }
    }

    public static class SearchResultReceiver extends c.b {
        @Override // c.b
        public void q(int i2, Bundle bundle) {
            if (bundle != null) {
                bundle = MediaSessionCompat.j(bundle);
            }
            if (i2 != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS)) {
                throw null;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
            parcelableArray.getClass();
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaItem) parcelable);
            }
            throw null;
        }
    }

    public static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f993a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakReference f994b;

        public a(g gVar) {
            this.f993a = new WeakReference(gVar);
        }

        public void a(Messenger messenger) {
            this.f994b = new WeakReference(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference weakReference = this.f994b;
            if (weakReference == null || weakReference.get() == null || this.f993a.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.a(data);
            g gVar = (g) this.f993a.get();
            Messenger messenger = (Messenger) this.f994b.get();
            try {
                int i2 = message.what;
                if (i2 == 1) {
                    Bundle bundle = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                    MediaSessionCompat.a(bundle);
                    gVar.e(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (MediaSessionCompat.Token) data.getParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN), bundle);
                } else if (i2 == 2) {
                    gVar.g(messenger);
                } else if (i2 != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                    MediaSessionCompat.a(bundle2);
                    Bundle bundle3 = data.getBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS);
                    MediaSessionCompat.a(bundle3);
                    gVar.b(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST), bundle2, bundle3);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    gVar.g(messenger);
                }
            }
        }
    }

    public static class b {
        final MediaBrowser.ConnectionCallback mConnectionCallbackFwk = new a();
        InterfaceC0031b mConnectionCallbackInternal;

        public class a extends MediaBrowser.ConnectionCallback {
            public a() {
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnected() {
                InterfaceC0031b interfaceC0031b = b.this.mConnectionCallbackInternal;
                if (interfaceC0031b != null) {
                    interfaceC0031b.onConnected();
                }
                b.this.onConnected();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionFailed() {
                InterfaceC0031b interfaceC0031b = b.this.mConnectionCallbackInternal;
                if (interfaceC0031b != null) {
                    interfaceC0031b.d();
                }
                b.this.onConnectionFailed();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionSuspended() {
                InterfaceC0031b interfaceC0031b = b.this.mConnectionCallbackInternal;
                if (interfaceC0031b != null) {
                    interfaceC0031b.f();
                }
                b.this.onConnectionSuspended();
            }
        }

        /* JADX INFO: renamed from: android.support.v4.media.MediaBrowserCompat$b$b, reason: collision with other inner class name */
        public interface InterfaceC0031b {
            void d();

            void f();

            void onConnected();
        }

        public abstract void onConnected();

        public abstract void onConnectionFailed();

        public abstract void onConnectionSuspended();

        public void setInternalConnectionCallback(InterfaceC0031b interfaceC0031b) {
            this.mConnectionCallbackInternal = interfaceC0031b;
        }
    }

    public interface c {
        MediaSessionCompat.Token a();

        void c();

        void disconnect();
    }

    public static class d implements c, g, b.InterfaceC0031b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final MediaBrowser f997b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f998c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f999d = new a(this);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ArrayMap f1000e = new ArrayMap();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1001f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public h f1002g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Messenger f1003h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public MediaSessionCompat.Token f1004i;

        public d(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            this.f996a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.f998c = bundle2;
            bundle2.putInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 1);
            bundle2.putInt(MediaBrowserProtocol.EXTRA_CALLING_PID, Process.myPid());
            bVar.setInternalConnectionCallback(this);
            this.f997b = new MediaBrowser(context, componentName, bVar.mConnectionCallbackFwk, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public MediaSessionCompat.Token a() {
            if (this.f1004i == null) {
                this.f1004i = MediaSessionCompat.Token.q(this.f997b.getSessionToken());
            }
            return this.f1004i;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void b(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.f1003h != messenger) {
                return;
            }
            android.support.v4.media.a.a(this.f1000e.get(str));
            if (MediaBrowserCompat.f989b) {
                Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void c() {
            this.f997b.connect();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0031b
        public void d() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void disconnect() {
            Messenger messenger;
            h hVar = this.f1002g;
            if (hVar != null && (messenger = this.f1003h) != null) {
                try {
                    hVar.c(messenger);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            this.f997b.disconnect();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void e(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0031b
        public void f() {
            this.f1002g = null;
            this.f1003h = null;
            this.f1004i = null;
            this.f999d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void g(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0031b
        public void onConnected() {
            try {
                Bundle extras = this.f997b.getExtras();
                if (extras == null) {
                    return;
                }
                this.f1001f = extras.getInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 0);
                IBinder binder = BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER);
                if (binder != null) {
                    this.f1002g = new h(binder, this.f998c);
                    Messenger messenger = new Messenger(this.f999d);
                    this.f1003h = messenger;
                    this.f999d.a(messenger);
                    try {
                        this.f1002g.a(this.f996a, this.f1003h);
                    } catch (RemoteException unused) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
                android.support.v4.media.session.b bVarZ0 = b.a.Z0(BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_SESSION_BINDER));
                if (bVarZ0 != null) {
                    this.f1004i = MediaSessionCompat.Token.r(this.f997b.getSessionToken(), bVarZ0);
                }
            } catch (IllegalStateException e2) {
                Log.e("MediaBrowserCompat", "Unexpected IllegalStateException", e2);
            }
        }
    }

    public static class e extends d {
        public e(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    public static class f extends e {
        public f(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    public interface g {
        void b(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);

        void e(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);

        void g(Messenger messenger);
    }

    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Messenger f1005a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Bundle f1006b;

        public h(IBinder iBinder, Bundle bundle) {
            this.f1005a = new Messenger(iBinder);
            this.f1006b = bundle;
        }

        public void a(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putInt(MediaBrowserProtocol.DATA_CALLING_PID, Process.myPid());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.f1006b);
            b(6, bundle, messenger);
        }

        public final void b(int i2, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = 1;
            messageObtain.setData(bundle);
            messageObtain.replyTo = messenger;
            this.f1005a.send(messageObtain);
        }

        public void c(Messenger messenger) throws RemoteException {
            b(7, null, messenger);
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, b bVar, Bundle bundle) {
        this.f990a = new f(context, componentName, bVar, bundle);
    }

    public void a() {
        Log.d("MediaBrowserCompat", "Connecting to a MediaBrowserService.");
        this.f990a.c();
    }

    public void b() {
        this.f990a.disconnect();
    }

    public MediaSessionCompat.Token c() {
        return this.f990a.a();
    }
}
