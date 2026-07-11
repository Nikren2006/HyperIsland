package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChannelCompat {
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    private static final int DEFAULT_LIGHT_COLOR = 0;
    private static final boolean DEFAULT_SHOW_BADGE = true;
    AudioAttributes mAudioAttributes;
    private boolean mBypassDnd;
    private boolean mCanBubble;
    String mConversationId;
    String mDescription;
    String mGroupId;
    final String mId;
    int mImportance;
    private boolean mImportantConversation;
    int mLightColor;
    boolean mLights;
    private int mLockscreenVisibility;
    CharSequence mName;
    String mParentId;
    boolean mShowBadge;
    Uri mSound;
    boolean mVibrationEnabled;
    long[] mVibrationPattern;

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static boolean canBypassDnd(NotificationChannel notificationChannel) {
            return notificationChannel.canBypassDnd();
        }

        public static boolean canShowBadge(NotificationChannel notificationChannel) {
            return notificationChannel.canShowBadge();
        }

        public static NotificationChannel createNotificationChannel(String str, CharSequence charSequence, int i2) {
            return new NotificationChannel(str, charSequence, i2);
        }

        public static void enableLights(NotificationChannel notificationChannel, boolean z2) {
            notificationChannel.enableLights(z2);
        }

        public static void enableVibration(NotificationChannel notificationChannel, boolean z2) {
            notificationChannel.enableVibration(z2);
        }

        public static AudioAttributes getAudioAttributes(NotificationChannel notificationChannel) {
            return notificationChannel.getAudioAttributes();
        }

        public static String getDescription(NotificationChannel notificationChannel) {
            return notificationChannel.getDescription();
        }

        public static String getGroup(NotificationChannel notificationChannel) {
            return notificationChannel.getGroup();
        }

        public static String getId(NotificationChannel notificationChannel) {
            return notificationChannel.getId();
        }

        public static int getImportance(NotificationChannel notificationChannel) {
            return notificationChannel.getImportance();
        }

        public static int getLightColor(NotificationChannel notificationChannel) {
            return notificationChannel.getLightColor();
        }

        public static int getLockscreenVisibility(NotificationChannel notificationChannel) {
            return notificationChannel.getLockscreenVisibility();
        }

        public static CharSequence getName(NotificationChannel notificationChannel) {
            return notificationChannel.getName();
        }

        public static Uri getSound(NotificationChannel notificationChannel) {
            return notificationChannel.getSound();
        }

        public static long[] getVibrationPattern(NotificationChannel notificationChannel) {
            return notificationChannel.getVibrationPattern();
        }

        public static void setDescription(NotificationChannel notificationChannel, String str) {
            notificationChannel.setDescription(str);
        }

        public static void setGroup(NotificationChannel notificationChannel, String str) {
            notificationChannel.setGroup(str);
        }

        public static void setLightColor(NotificationChannel notificationChannel, int i2) {
            notificationChannel.setLightColor(i2);
        }

        public static void setShowBadge(NotificationChannel notificationChannel, boolean z2) {
            notificationChannel.setShowBadge(z2);
        }

        public static void setSound(NotificationChannel notificationChannel, Uri uri, AudioAttributes audioAttributes) {
            notificationChannel.setSound(uri, audioAttributes);
        }

        public static void setVibrationPattern(NotificationChannel notificationChannel, long[] jArr) {
            notificationChannel.setVibrationPattern(jArr);
        }

        public static boolean shouldShowLights(NotificationChannel notificationChannel) {
            return notificationChannel.shouldShowLights();
        }

        public static boolean shouldVibrate(NotificationChannel notificationChannel) {
            return notificationChannel.shouldVibrate();
        }
    }

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static boolean canBubble(NotificationChannel notificationChannel) {
            return notificationChannel.canBubble();
        }
    }

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static String getConversationId(NotificationChannel notificationChannel) {
            return notificationChannel.getConversationId();
        }

        public static String getParentChannelId(NotificationChannel notificationChannel) {
            return notificationChannel.getParentChannelId();
        }

        public static boolean isImportantConversation(NotificationChannel notificationChannel) {
            return notificationChannel.isImportantConversation();
        }

        public static void setConversationId(NotificationChannel notificationChannel, String str, String str2) {
            notificationChannel.setConversationId(str, str2);
        }
    }

    public static class Builder {
        private final NotificationChannelCompat mChannel;

        public Builder(String str, int i2) {
            this.mChannel = new NotificationChannelCompat(str, i2);
        }

        public NotificationChannelCompat build() {
            return this.mChannel;
        }

        public Builder setConversationId(String str, String str2) {
            NotificationChannelCompat notificationChannelCompat = this.mChannel;
            notificationChannelCompat.mParentId = str;
            notificationChannelCompat.mConversationId = str2;
            return this;
        }

        public Builder setDescription(String str) {
            this.mChannel.mDescription = str;
            return this;
        }

        public Builder setGroup(String str) {
            this.mChannel.mGroupId = str;
            return this;
        }

        public Builder setImportance(int i2) {
            this.mChannel.mImportance = i2;
            return this;
        }

        public Builder setLightColor(int i2) {
            this.mChannel.mLightColor = i2;
            return this;
        }

        public Builder setLightsEnabled(boolean z2) {
            this.mChannel.mLights = z2;
            return this;
        }

        public Builder setName(CharSequence charSequence) {
            this.mChannel.mName = charSequence;
            return this;
        }

        public Builder setShowBadge(boolean z2) {
            this.mChannel.mShowBadge = z2;
            return this;
        }

        public Builder setSound(Uri uri, AudioAttributes audioAttributes) {
            NotificationChannelCompat notificationChannelCompat = this.mChannel;
            notificationChannelCompat.mSound = uri;
            notificationChannelCompat.mAudioAttributes = audioAttributes;
            return this;
        }

        public Builder setVibrationEnabled(boolean z2) {
            this.mChannel.mVibrationEnabled = z2;
            return this;
        }

        public Builder setVibrationPattern(long[] jArr) {
            NotificationChannelCompat notificationChannelCompat = this.mChannel;
            notificationChannelCompat.mVibrationEnabled = jArr != null && jArr.length > 0;
            notificationChannelCompat.mVibrationPattern = jArr;
            return this;
        }
    }

    public NotificationChannelCompat(String str, int i2) {
        this.mShowBadge = true;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        this.mId = (String) Preconditions.checkNotNull(str);
        this.mImportance = i2;
        this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    }

    public boolean canBubble() {
        return this.mCanBubble;
    }

    public boolean canBypassDnd() {
        return this.mBypassDnd;
    }

    public boolean canShowBadge() {
        return this.mShowBadge;
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public String getConversationId() {
        return this.mConversationId;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getGroup() {
        return this.mGroupId;
    }

    public String getId() {
        return this.mId;
    }

    public int getImportance() {
        return this.mImportance;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public int getLockscreenVisibility() {
        return this.mLockscreenVisibility;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public NotificationChannel getNotificationChannel() {
        String str;
        NotificationChannel notificationChannelCreateNotificationChannel = Api26Impl.createNotificationChannel(this.mId, this.mName, this.mImportance);
        Api26Impl.setDescription(notificationChannelCreateNotificationChannel, this.mDescription);
        Api26Impl.setGroup(notificationChannelCreateNotificationChannel, this.mGroupId);
        Api26Impl.setShowBadge(notificationChannelCreateNotificationChannel, this.mShowBadge);
        Api26Impl.setSound(notificationChannelCreateNotificationChannel, this.mSound, this.mAudioAttributes);
        Api26Impl.enableLights(notificationChannelCreateNotificationChannel, this.mLights);
        Api26Impl.setLightColor(notificationChannelCreateNotificationChannel, this.mLightColor);
        Api26Impl.setVibrationPattern(notificationChannelCreateNotificationChannel, this.mVibrationPattern);
        Api26Impl.enableVibration(notificationChannelCreateNotificationChannel, this.mVibrationEnabled);
        String str2 = this.mParentId;
        if (str2 != null && (str = this.mConversationId) != null) {
            Api30Impl.setConversationId(notificationChannelCreateNotificationChannel, str2, str);
        }
        return notificationChannelCreateNotificationChannel;
    }

    public String getParentChannelId() {
        return this.mParentId;
    }

    public Uri getSound() {
        return this.mSound;
    }

    public long[] getVibrationPattern() {
        return this.mVibrationPattern;
    }

    public boolean isImportantConversation() {
        return this.mImportantConversation;
    }

    public boolean shouldShowLights() {
        return this.mLights;
    }

    public boolean shouldVibrate() {
        return this.mVibrationEnabled;
    }

    public Builder toBuilder() {
        return new Builder(this.mId, this.mImportance).setName(this.mName).setDescription(this.mDescription).setGroup(this.mGroupId).setShowBadge(this.mShowBadge).setSound(this.mSound, this.mAudioAttributes).setLightsEnabled(this.mLights).setLightColor(this.mLightColor).setVibrationEnabled(this.mVibrationEnabled).setVibrationPattern(this.mVibrationPattern).setConversationId(this.mParentId, this.mConversationId);
    }

    @RequiresApi(26)
    public NotificationChannelCompat(NotificationChannel notificationChannel) {
        this(Api26Impl.getId(notificationChannel), Api26Impl.getImportance(notificationChannel));
        this.mName = Api26Impl.getName(notificationChannel);
        this.mDescription = Api26Impl.getDescription(notificationChannel);
        this.mGroupId = Api26Impl.getGroup(notificationChannel);
        this.mShowBadge = Api26Impl.canShowBadge(notificationChannel);
        this.mSound = Api26Impl.getSound(notificationChannel);
        this.mAudioAttributes = Api26Impl.getAudioAttributes(notificationChannel);
        this.mLights = Api26Impl.shouldShowLights(notificationChannel);
        this.mLightColor = Api26Impl.getLightColor(notificationChannel);
        this.mVibrationEnabled = Api26Impl.shouldVibrate(notificationChannel);
        this.mVibrationPattern = Api26Impl.getVibrationPattern(notificationChannel);
        this.mParentId = Api30Impl.getParentChannelId(notificationChannel);
        this.mConversationId = Api30Impl.getConversationId(notificationChannel);
        this.mBypassDnd = Api26Impl.canBypassDnd(notificationChannel);
        this.mLockscreenVisibility = Api26Impl.getLockscreenVisibility(notificationChannel);
        this.mCanBubble = Api29Impl.canBubble(notificationChannel);
        this.mImportantConversation = Api30Impl.isImportantConversation(notificationChannel);
    }
}
