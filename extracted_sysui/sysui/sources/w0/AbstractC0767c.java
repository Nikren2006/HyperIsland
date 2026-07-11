package w0;

import android.media.session.MediaController;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: w0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0767c {
    public static boolean a(List list, MediaController mediaController) {
        if (list == null) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((MediaController) it.next()).getSessionToken().equals(mediaController.getSessionToken())) {
                return true;
            }
        }
        return false;
    }

    public static void b(List list, List list2, List list3, List list4) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MediaController mediaController = (MediaController) it.next();
                if (!a(list2, mediaController)) {
                    list4.add(mediaController);
                }
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            MediaController mediaController2 = (MediaController) it2.next();
            if (!a(list, mediaController2)) {
                list3.add(mediaController2);
            }
        }
    }
}
