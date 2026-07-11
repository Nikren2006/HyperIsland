package com.miui.circulate.device.api;

import android.net.Uri;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class UriBuilder {
    public static final UriBuilder INSTANCE = new UriBuilder();

    private UriBuilder() {
    }

    public final Uri withAppendedPath(Uri baseUri, String id) {
        n.g(baseUri, "baseUri");
        n.g(id, "id");
        Uri.Builder builderBuildUpon = baseUri.buildUpon();
        builderBuildUpon.appendPath(id);
        Uri uriBuild = builderBuildUpon.build();
        n.f(uriBuild, "builder.build()");
        return uriBuild;
    }
}
