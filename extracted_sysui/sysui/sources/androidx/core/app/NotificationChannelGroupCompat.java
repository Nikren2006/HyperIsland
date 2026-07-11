package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChannelGroupCompat {
    private boolean mBlocked;
    private List<NotificationChannelCompat> mChannels;
    String mDescription;
    final String mId;
    CharSequence mName;

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static NotificationChannelGroup createNotificationChannelGroup(String str, CharSequence charSequence) {
            return new NotificationChannelGroup(str, charSequence);
        }

        public static List<NotificationChannel> getChannels(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getChannels();
        }

        public static String getGroup(NotificationChannel notificationChannel) {
            return notificationChannel.getGroup();
        }

        public static String getId(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getId();
        }

        public static CharSequence getName(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getName();
        }
    }

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static String getDescription(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getDescription();
        }

        public static boolean isBlocked(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.isBlocked();
        }

        public static void setDescription(NotificationChannelGroup notificationChannelGroup, String str) {
            notificationChannelGroup.setDescription(str);
        }
    }

    public static class Builder {
        final NotificationChannelGroupCompat mGroup;

        public Builder(String str) {
            this.mGroup = new NotificationChannelGroupCompat(str);
        }

        public NotificationChannelGroupCompat build() {
            return this.mGroup;
        }

        public Builder setDescription(String str) {
            this.mGroup.mDescription = str;
            return this;
        }

        public Builder setName(CharSequence charSequence) {
            this.mGroup.mName = charSequence;
            return this;
        }
    }

    public NotificationChannelGroupCompat(String str) {
        this.mChannels = Collections.emptyList();
        this.mId = (String) Preconditions.checkNotNull(str);
    }

    @RequiresApi(26)
    private List<NotificationChannelCompat> getChannelsCompat(List<NotificationChannel> list) {
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel notificationChannel : list) {
            if (this.mId.equals(Api26Impl.getGroup(notificationChannel))) {
                arrayList.add(new NotificationChannelCompat(notificationChannel));
            }
        }
        return arrayList;
    }

    public List<NotificationChannelCompat> getChannels() {
        return this.mChannels;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getId() {
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public NotificationChannelGroup getNotificationChannelGroup() {
        NotificationChannelGroup notificationChannelGroupCreateNotificationChannelGroup = Api26Impl.createNotificationChannelGroup(this.mId, this.mName);
        Api28Impl.setDescription(notificationChannelGroupCreateNotificationChannelGroup, this.mDescription);
        return notificationChannelGroupCreateNotificationChannelGroup;
    }

    public boolean isBlocked() {
        return this.mBlocked;
    }

    public Builder toBuilder() {
        return new Builder(this.mId).setName(this.mName).setDescription(this.mDescription);
    }

    @RequiresApi(28)
    public NotificationChannelGroupCompat(NotificationChannelGroup notificationChannelGroup) {
        this(notificationChannelGroup, Collections.emptyList());
    }

    @RequiresApi(26)
    public NotificationChannelGroupCompat(NotificationChannelGroup notificationChannelGroup, List<NotificationChannel> list) {
        this(Api26Impl.getId(notificationChannelGroup));
        this.mName = Api26Impl.getName(notificationChannelGroup);
        this.mDescription = Api28Impl.getDescription(notificationChannelGroup);
        this.mBlocked = Api28Impl.isBlocked(notificationChannelGroup);
        this.mChannels = getChannelsCompat(Api26Impl.getChannels(notificationChannelGroup));
    }
}
