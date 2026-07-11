package com.miui.circulate.device.api;

import H0.s;
import I0.m;
import S0.a;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import com.miui.circulate.device.api.Constant;
import com.miui.circulate.device.api.DeviceControlReceiver;
import com.miui.circulate.device.api.ObserverRegister;
import f1.q;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class DeviceControlManager {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int MSG_START = 1;

    @Deprecated
    public static final int MSG_STOP = 2;
    private final HandlerCallback callback;
    private final Context ctx;
    private final LinkedHashMap<String, DeviceInfo> deviceControlCache;
    private DeviceControlReceiver deviceReceiver;
    private final Handler handler;
    private final ArrayList<ContentObserver> listObservers;
    private final ObserverRegister observerRegister;
    private final ContentResolver resolver;
    private ContentObserver updateObserver;
    private final List<Uri> uriList;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class DefaultObserverRegister implements ObserverRegister {
        private final Context ctx;

        public DefaultObserverRegister(Context ctx) {
            n.g(ctx, "ctx");
            this.ctx = ctx;
        }

        @Override // com.miui.circulate.device.api.ObserverRegister
        public Map<Uri, ObserverRegister.ContentObserverDesc> activeObservers() {
            return ObserverRegister.DefaultImpls.activeObservers(this);
        }

        @Override // com.miui.circulate.device.api.ObserverRegister
        public void registerContentObserver(Uri uri, boolean z2, ContentObserver observer) {
            n.g(uri, "uri");
            n.g(observer, "observer");
            this.ctx.getContentResolver().registerContentObserver(uri, z2, observer);
        }

        @Override // com.miui.circulate.device.api.ObserverRegister
        public void unregisterContentObserver(ContentObserver observer) {
            n.g(observer, "observer");
            this.ctx.getContentResolver().unregisterContentObserver(observer);
        }
    }

    public final class HandlerCallback implements Handler.Callback {
        public HandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) throws IllegalAccessException, IOException, InvocationTargetException {
            n.g(msg, "msg");
            String string = msg.getData().getString("from");
            int i2 = msg.what;
            if (i2 == 1) {
                boolean z2 = msg.getData().getBoolean(Constant.PARAM_SEARCH, true);
                DeviceControlManager deviceControlManager = DeviceControlManager.this;
                Object obj = msg.obj;
                n.e(obj, "null cannot be cast to non-null type com.miui.circulate.device.api.DeviceControlReceiver");
                DeviceControlReceiver deviceControlReceiver = (DeviceControlReceiver) obj;
                if (string == null) {
                    string = "";
                }
                deviceControlManager.handleStart(deviceControlReceiver, string, z2);
            } else if (i2 == 2) {
                DeviceControlManager deviceControlManager2 = DeviceControlManager.this;
                Object obj2 = msg.obj;
                n.e(obj2, "null cannot be cast to non-null type com.miui.circulate.device.api.DeviceControlReceiver");
                DeviceControlReceiver deviceControlReceiver2 = (DeviceControlReceiver) obj2;
                if (string == null) {
                    string = "";
                }
                deviceControlManager2.handleStop(deviceControlReceiver2, string);
            }
            return true;
        }
    }

    public final class ListContentObserver extends ContentObserver {
        public ListContentObserver() {
            super(DeviceControlManager.this.handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z2, Uri uri) throws IllegalAccessException, IOException, InvocationTargetException {
            super.onChange(z2, uri);
            Log.i(Constant.TAG, "onChange uri=" + uri);
            DeviceControlManager.this.fillCache(uri);
            DeviceControlReceiver deviceControlReceiver = DeviceControlManager.this.deviceReceiver;
            if (deviceControlReceiver != null) {
                List listUnmodifiableList = Collections.unmodifiableList(new ArrayList(DeviceControlManager.this.deviceControlCache.values()));
                n.f(listUnmodifiableList, "unmodifiableList(ArrayLi…viceControlCache.values))");
                DeviceControlReceiver.DefaultImpls.onDeviceListChange$default(deviceControlReceiver, listUnmodifiableList, false, 2, null);
            }
        }
    }

    public final class UpdateObserver extends ContentObserver {
        public UpdateObserver() {
            super(DeviceControlManager.this.handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z2, Uri uri) throws IllegalAccessException, IOException, InvocationTargetException {
            Cursor cursorQuery;
            super.onChange(z2, uri);
            if (uri != null) {
                DeviceControlManager deviceControlManager = DeviceControlManager.this;
                String strId = deviceControlManager.id(uri);
                if (deviceControlManager.deviceControlCache.containsKey(strId) && (cursorQuery = deviceControlManager.resolver.query(uri, null, null, null, null)) != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            Log.i(Constant.TAG, q.g0(strId, 3) + " update");
                            cursorQuery.moveToFirst();
                            DeviceInfo deviceInfo = DeviceInfo_BindEntityParserKt.parseDeviceInfo(cursorQuery);
                            if (deviceInfo != null) {
                                deviceControlManager.deviceControlCache.put(deviceInfo.getId(), deviceInfo);
                                DeviceControlReceiver deviceControlReceiver = deviceControlManager.deviceReceiver;
                                if (deviceControlReceiver != null) {
                                    deviceControlReceiver.onDeviceChange(deviceInfo);
                                }
                            }
                        }
                        s sVar = s.f314a;
                        a.a(cursorQuery, null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            a.a(cursorQuery, th);
                            throw th2;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DeviceControlManager(Context ctx, Looper looper, List<? extends Uri> uriList, ObserverRegister observerRegister, ContentResolver resolver) {
        n.g(ctx, "ctx");
        n.g(looper, "looper");
        n.g(uriList, "uriList");
        n.g(observerRegister, "observerRegister");
        n.g(resolver, "resolver");
        this.ctx = ctx;
        this.uriList = uriList;
        this.observerRegister = observerRegister;
        this.resolver = resolver;
        HandlerCallback handlerCallback = new HandlerCallback();
        this.callback = handlerCallback;
        this.handler = new Handler(looper, handlerCallback);
        this.deviceControlCache = new LinkedHashMap<>();
        this.listObservers = new ArrayList<>();
        Log.i(Constant.TAG, "DeviceControlManager version[release:2.0.8-8abd0e551:20008]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void fillCache(android.net.Uri r12) throws java.lang.IllegalAccessException, java.io.IOException, java.lang.reflect.InvocationTargetException {
        /*
            r11 = this;
            java.util.LinkedHashMap<java.lang.String, com.miui.circulate.device.api.DeviceInfo> r0 = r11.deviceControlCache
            r0.clear()
            java.util.List<android.net.Uri> r0 = r11.uriList
            java.util.Iterator r0 = r0.iterator()
        Lb:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lc0
            java.lang.Object r1 = r0.next()
            android.net.Uri r1 = (android.net.Uri) r1
            r2 = 0
            if (r12 == 0) goto L5b
            java.lang.String r3 = r1.getPath()
            java.lang.String r4 = r12.getPath()
            r5 = 0
            r6 = 2
            boolean r3 = f1.n.m(r3, r4, r5, r6, r2)
            if (r3 == 0) goto L55
            java.util.Set r3 = r12.getQueryParameterNames()
            java.lang.String r4 = "queryParameterNames"
            kotlin.jvm.internal.n.f(r3, r4)
            java.util.Iterator r3 = r3.iterator()
            r4 = r2
        L38:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L56
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            android.net.Uri$Builder r5 = r1.buildUpon()
            java.lang.String r6 = r12.getQueryParameter(r4)
            android.net.Uri$Builder r4 = r5.appendQueryParameter(r4, r6)
            android.net.Uri r4 = r4.build()
            goto L38
        L55:
            r4 = r1
        L56:
            if (r4 != 0) goto L59
            goto L5b
        L59:
            r6 = r4
            goto L5c
        L5b:
            r6 = r1
        L5c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "fillCache finalUri = "
            r1.append(r3)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "MDC"
            android.util.Log.i(r3, r1)
            android.content.ContentResolver r5 = r11.resolver
            r9 = 0
            r10 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r5.query(r6, r7, r8, r9, r10)
            if (r1 == 0) goto Lb
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb1
            r4.<init>()     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r5 = "load "
            r4.append(r5)     // Catch: java.lang.Throwable -> Lb1
            int r5 = r1.getCount()     // Catch: java.lang.Throwable -> Lb1
            r4.append(r5)     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r5 = " device from cache"
            r4.append(r5)     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> Lb1
            android.util.Log.i(r3, r4)     // Catch: java.lang.Throwable -> Lb1
        L9b:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> Lb1
            if (r3 == 0) goto Lb3
            com.miui.circulate.device.api.DeviceInfo r3 = com.miui.circulate.device.api.DeviceInfo_BindEntityParserKt.parseDeviceInfo(r1)     // Catch: java.lang.Throwable -> Lb1
            if (r3 == 0) goto L9b
            java.util.LinkedHashMap<java.lang.String, com.miui.circulate.device.api.DeviceInfo> r4 = r11.deviceControlCache     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r5 = r3.getId()     // Catch: java.lang.Throwable -> Lb1
            r4.put(r5, r3)     // Catch: java.lang.Throwable -> Lb1
            goto L9b
        Lb1:
            r11 = move-exception
            goto Lba
        Lb3:
            H0.s r3 = H0.s.f314a     // Catch: java.lang.Throwable -> Lb1
            S0.a.a(r1, r2)
            goto Lb
        Lba:
            throw r11     // Catch: java.lang.Throwable -> Lbb
        Lbb:
            r12 = move-exception
            S0.a.a(r1, r11)
            throw r12
        Lc0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.circulate.device.api.DeviceControlManager.fillCache(android.net.Uri):void");
    }

    public static /* synthetic */ void fillCache$default(DeviceControlManager deviceControlManager, Uri uri, int i2, Object obj) throws IllegalAccessException, IOException, InvocationTargetException {
        if ((i2 & 1) != 0) {
            uri = null;
        }
        deviceControlManager.fillCache(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleStart(DeviceControlReceiver deviceControlReceiver, String str, boolean z2) throws IllegalAccessException, IOException, InvocationTargetException {
        if (this.deviceReceiver != null) {
            Log.w(Constant.TAG, "deviceReceiver is not null, please call stop first, refresh cache instead");
            this.deviceReceiver = deviceControlReceiver;
            fillCache$default(this, null, 1, null);
            DeviceControlReceiver deviceControlReceiver2 = this.deviceReceiver;
            if (deviceControlReceiver2 != null) {
                List<DeviceInfo> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.deviceControlCache.values()));
                n.f(listUnmodifiableList, "unmodifiableList(ArrayLi…viceControlCache.values))");
                deviceControlReceiver2.onDeviceListChange(listUnmodifiableList, true);
                return;
            }
            return;
        }
        Log.i(Constant.TAG, "start load device");
        this.deviceReceiver = deviceControlReceiver;
        fillCache$default(this, null, 1, null);
        registerObserver();
        DeviceControlReceiver deviceControlReceiver3 = this.deviceReceiver;
        if (deviceControlReceiver3 != null) {
            List<DeviceInfo> listUnmodifiableList2 = Collections.unmodifiableList(new ArrayList(this.deviceControlCache.values()));
            n.f(listUnmodifiableList2, "unmodifiableList(ArrayLi…viceControlCache.values))");
            deviceControlReceiver3.onDeviceListChange(listUnmodifiableList2, true);
        }
        if (z2) {
            startInstantSearch(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleStop(DeviceControlReceiver deviceControlReceiver, String str) {
        Log.i(Constant.TAG, "stop load device");
        if (!n.c(deviceControlReceiver, this.deviceReceiver)) {
            Log.w(Constant.TAG, "stop receiver is not same with original");
        }
        this.deviceReceiver = null;
        unregisterObserver();
        stopInstantSearch(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String id(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        return lastPathSegment == null ? "" : lastPathSegment;
    }

    private final void registerObserver() {
        try {
            for (Uri uri : this.uriList) {
                ListContentObserver listContentObserver = new ListContentObserver();
                this.observerRegister.registerContentObserver(uri, false, listContentObserver);
                this.listObservers.add(listContentObserver);
            }
            UpdateObserver updateObserver = new UpdateObserver();
            this.observerRegister.registerContentObserver(Constant.INSTANCE.getDEVICE_META_LIST_URI(), true, updateObserver);
            this.updateObserver = updateObserver;
        } catch (Exception e2) {
            Log.e(Constant.TAG, "registerContentObserver", e2);
        }
    }

    public static /* synthetic */ void start$default(DeviceControlManager deviceControlManager, long j2, DeviceControlReceiver deviceControlReceiver, String str, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = TimeUnit.SECONDS.toMillis(3L);
        }
        long j3 = j2;
        if ((i2 & 8) != 0) {
            z2 = true;
        }
        deviceControlManager.start(j3, deviceControlReceiver, str, z2);
    }

    private final void startInstantSearch(String str) {
        try {
            Log.i(Constant.TAG, "startInstantSearch");
            Bundle bundle = new Bundle();
            bundle.putParcelableArray(Constant.QueryParameter.SEARCH_URI_LIST, (Parcelable[]) this.uriList.toArray(new Uri[0]));
            if (str.length() > 0) {
                bundle.putString("from", str);
            }
            this.resolver.call(Constant.INSTANCE.getMETHOD_CALL(), Constant.METHOD_START_SEARCH, (String) null, bundle);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "startInstantSearch", e2);
        }
    }

    private final void stopInstantSearch(String str) {
        try {
            Log.i(Constant.TAG, "stopInstantSearch");
            Bundle bundle = new Bundle();
            bundle.putParcelableArray(Constant.QueryParameter.SEARCH_URI_LIST, (Parcelable[]) this.uriList.toArray(new Uri[0]));
            if (str.length() > 0) {
                bundle.putString("from", str);
            }
            this.resolver.call(Constant.INSTANCE.getMETHOD_CALL(), Constant.METHOD_STOP_SEARCH, (String) null, bundle);
        } catch (Exception e2) {
            Log.e(Constant.TAG, "stopInstantSearch", e2);
        }
    }

    private final void unregisterObserver() {
        try {
            Iterator<T> it = this.listObservers.iterator();
            while (it.hasNext()) {
                this.observerRegister.unregisterContentObserver((ContentObserver) it.next());
            }
            ContentObserver contentObserver = this.updateObserver;
            if (contentObserver != null) {
                this.observerRegister.unregisterContentObserver(contentObserver);
            }
        } catch (Exception e2) {
            Log.e(Constant.TAG, "unregisterContentObserver", e2);
        }
    }

    public final void start(long j2, DeviceControlReceiver receiver, String from, boolean z2) {
        n.g(receiver, "receiver");
        n.g(from, "from");
        this.handler.removeMessages(1);
        Message messageObtain = Message.obtain(this.handler);
        messageObtain.what = 1;
        messageObtain.obj = receiver;
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        bundle.putBoolean(Constant.PARAM_SEARCH, z2);
        messageObtain.setData(bundle);
        this.handler.sendMessageDelayed(messageObtain, j2);
    }

    public final void stop(DeviceControlReceiver receiver, String from) {
        n.g(receiver, "receiver");
        n.g(from, "from");
        this.handler.removeMessages(1);
        Message messageObtain = Message.obtain(this.handler);
        messageObtain.what = 2;
        messageObtain.obj = receiver;
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        messageObtain.setData(bundle);
        this.handler.sendMessage(messageObtain);
    }

    public static /* synthetic */ void start$default(DeviceControlManager deviceControlManager, long j2, DeviceControlReceiver deviceControlReceiver, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = TimeUnit.SECONDS.toMillis(3L);
        }
        deviceControlManager.start(j2, deviceControlReceiver);
    }

    public static /* synthetic */ void start$default(DeviceControlManager deviceControlManager, DeviceControlReceiver deviceControlReceiver, String str, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        deviceControlManager.start(deviceControlReceiver, str, z2);
    }

    public final void stop(DeviceControlReceiver receiver) {
        n.g(receiver, "receiver");
        this.handler.removeMessages(1);
        Message messageObtain = Message.obtain(this.handler);
        messageObtain.what = 2;
        messageObtain.obj = receiver;
        this.handler.sendMessage(messageObtain);
    }

    public final void start(long j2, DeviceControlReceiver receiver) {
        n.g(receiver, "receiver");
        this.handler.removeMessages(1);
        Message messageObtain = Message.obtain(this.handler);
        messageObtain.what = 1;
        messageObtain.obj = receiver;
        this.handler.sendMessageDelayed(messageObtain, j2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DeviceControlManager(Context context, Looper looper, List list, ObserverRegister observerRegister, ContentResolver contentResolver, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            looper = Looper.getMainLooper();
            n.f(looper, "getMainLooper()");
        }
        Looper looper2 = looper;
        List listF = (i2 & 4) != 0 ? m.f(Constant.INSTANCE.getEXPORT_LIST_URI()) : list;
        ObserverRegister defaultObserverRegister = (i2 & 8) != 0 ? new DefaultObserverRegister(context) : observerRegister;
        if ((i2 & 16) != 0) {
            contentResolver = context.getContentResolver();
            n.f(contentResolver, "ctx.contentResolver");
        }
        this(context, looper2, (List<? extends Uri>) listF, defaultObserverRegister, contentResolver);
    }

    public final void start(DeviceControlReceiver receiver, String from, boolean z2) {
        n.g(receiver, "receiver");
        n.g(from, "from");
        start(TimeUnit.SECONDS.toMillis(3L), receiver, from, z2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DeviceControlManager(Context context, Looper looper, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            looper = Looper.getMainLooper();
            n.f(looper, "getMainLooper()");
        }
        this(context, looper, (i2 & 4) != 0 ? m.f(Constant.INSTANCE.getEXPORT_LIST_URI()) : list);
    }

    public final void start(DeviceControlReceiver receiver) {
        n.g(receiver, "receiver");
        start(TimeUnit.SECONDS.toMillis(3L), receiver);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceControlManager(Context ctx, Looper looper, List<? extends Uri> uriList) {
        this(ctx, looper, uriList, new DefaultObserverRegister(ctx), null, 16, null);
        n.g(ctx, "ctx");
        n.g(looper, "looper");
        n.g(uriList, "uriList");
    }
}
