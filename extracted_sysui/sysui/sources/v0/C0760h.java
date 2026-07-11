package v0;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import com.miui.miplay.audio.data.MediaMetaData;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: renamed from: v0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0760h extends LinkedList {
    @Override // java.util.LinkedList, java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(int i2, C0759g c0759g) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.LinkedList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque, java.util.Queue
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean add(C0759g c0759g) {
        MediaController mediaControllerO = c0759g.o();
        PlaybackState playbackState = mediaControllerO.getPlaybackState();
        MediaMetadata metadata = mediaControllerO.getMetadata();
        if (playbackState == null || MediaMetaData.isMediaMetadataInvalid(metadata) || playbackState.getState() != 3) {
            addFirst(c0759g);
            return true;
        }
        addLast(c0759g);
        return true;
    }

    public C0759g c(MediaController mediaController) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            C0759g c0759g = (C0759g) it.next();
            if (c0759g.o().getSessionToken().equals(mediaController.getSessionToken())) {
                return c0759g;
            }
        }
        return null;
    }

    public C0759g d() {
        C0759g c0759g;
        if (isEmpty() || (c0759g = (C0759g) getLast()) == null || MediaMetaData.isMediaMetadataInvalid(c0759g.o().getMetadata())) {
            return null;
        }
        return c0759g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean e(C0759g c0759g, int i2) {
        int iIndexOf = indexOf(c0759g);
        if (iIndexOf < 0) {
            z0.e.d("ActiveSessionRecordStack", "illegal record, package: " + c0759g.p());
            return false;
        }
        boolean zIsMediaMetadataInvalid = MediaMetaData.isMediaMetadataInvalid(c0759g.o().getMetadata());
        C0759g c0759g2 = isEmpty() ? null : (C0759g) getLast();
        if (zIsMediaMetadataInvalid || i2 != 3) {
            int size = size() - 1;
            while (true) {
                if (size < 0) {
                    size = -1;
                    break;
                }
                C0759g c0759g3 = (C0759g) get(size);
                if (!MediaMetaData.isMediaMetadataInvalid(c0759g3.o().getMetadata()) && c0759g3.q() == 3) {
                    break;
                }
                size--;
            }
            if (size >= 0) {
                addLast((C0759g) remove(size));
            }
        } else {
            addLast((C0759g) remove(iIndexOf));
        }
        return (isEmpty() ? null : (C0759g) getLast()) != c0759g2;
    }
}
