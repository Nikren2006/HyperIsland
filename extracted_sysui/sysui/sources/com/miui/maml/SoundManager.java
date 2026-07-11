package com.miui.maml;

import android.media.SoundPool;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import com.miui.maml.util.MamlLog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import miui.content.res.ThemeResources;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes2.dex */
public class SoundManager implements SoundPool.OnLoadCompleteListener {
    private static final String ADVANCE = "advance/";
    private static final String LOCKSCREEN_AUDIO = "lockscreen_audio/";
    private static final String LOG_TAG = "MamlSoundManager";
    private static final int MAX_FILE_SIZE = 524288;
    private static final int MAX_STREAMS = 8;
    private Handler mHandler;
    private boolean mInitialized;
    private ResourceManager mResourceManager;
    private SoundPool mSoundPool;
    private ConcurrentHashMap<String, Integer> mSoundPoolMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, SoundOptions> mPendingSoundMap = new ConcurrentHashMap<>();
    private ArrayList<Integer> mPlayingSoundMap = new ArrayList<>();
    private Object mInitSignal = new Object();

    /* JADX INFO: renamed from: com.miui.maml.SoundManager$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$maml$SoundManager$Command;

        static {
            int[] iArr = new int[Command.values().length];
            $SwitchMap$com$miui$maml$SoundManager$Command = iArr;
            try {
                iArr[Command.Play.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$miui$maml$SoundManager$Command[Command.Pause.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$miui$maml$SoundManager$Command[Command.Resume.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$miui$maml$SoundManager$Command[Command.Stop.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum Command {
        Play,
        Pause,
        Resume,
        Stop;

        public static Command parse(String str) {
            return MiPlayEventsKt.POSITION_PAUSE.equals(str) ? Pause : "resume".equals(str) ? Resume : "stop".equals(str) ? Stop : Play;
        }
    }

    public static class SoundOptions {
        public boolean mKeepCur;
        public boolean mLoop;
        public float mVolume;

        public SoundOptions(boolean z2, boolean z3, float f2) {
            this.mKeepCur = z2;
            this.mLoop = z3;
            if (f2 < 0.0f) {
                this.mVolume = 0.0f;
            } else if (f2 > 1.0f) {
                this.mVolume = 1.0f;
            } else {
                this.mVolume = f2;
            }
        }
    }

    public SoundManager(ScreenContext screenContext) {
        this.mResourceManager = screenContext.mResourceManager;
        this.mHandler = screenContext.getHandler();
    }

    private void init() {
        if (this.mInitialized) {
            return;
        }
        if (Thread.currentThread().getId() == this.mHandler.getLooper().getThread().getId()) {
            SoundPool soundPool = new SoundPool(8, 3, 100);
            this.mSoundPool = soundPool;
            soundPool.setOnLoadCompleteListener(this);
            this.mInitialized = true;
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.miui.maml.SoundManager.1
            @Override // java.lang.Runnable
            public void run() {
                SoundManager.this.mSoundPool = new SoundPool(8, 3, 100);
                SoundManager.this.mSoundPool.setOnLoadCompleteListener(SoundManager.this);
                synchronized (SoundManager.this.mInitSignal) {
                    SoundManager.this.mInitialized = true;
                    SoundManager.this.mInitSignal.notify();
                }
            }
        });
        synchronized (this.mInitSignal) {
            while (!this.mInitialized) {
                try {
                    this.mInitSignal.wait();
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    private synchronized int playSoundImp(int i2, SoundOptions soundOptions) {
        int iPlay;
        if (this.mSoundPool != null && soundOptions != null) {
            if (!soundOptions.mKeepCur) {
                stopAllPlaying();
            }
            try {
                synchronized (this.mPlayingSoundMap) {
                    SoundPool soundPool = this.mSoundPool;
                    float f2 = soundOptions.mVolume;
                    iPlay = soundPool.play(i2, f2, f2, 1, soundOptions.mLoop ? -1 : 0, 1.0f);
                    this.mPlayingSoundMap.add(Integer.valueOf(iPlay));
                }
                return iPlay;
            } catch (Exception e2) {
                MamlLog.e(LOG_TAG, e2.toString());
                return 0;
            }
        }
        return 0;
    }

    @Override // android.media.SoundPool.OnLoadCompleteListener
    public void onLoadComplete(SoundPool soundPool, int i2, int i3) {
        if (i3 == 0) {
            playSoundImp(i2, this.mPendingSoundMap.get(Integer.valueOf(i2)));
        }
        this.mPendingSoundMap.remove(Integer.valueOf(i2));
    }

    public void pause() {
        stopAllPlaying();
    }

    public synchronized void playSound(int i2, Command command) {
        try {
            if (!this.mInitialized) {
                init();
            }
            if (this.mSoundPool != null && i2 > 0) {
                int i3 = AnonymousClass2.$SwitchMap$com$miui$maml$SoundManager$Command[command.ordinal()];
                if (i3 == 2) {
                    this.mSoundPool.pause(i2);
                } else if (i3 == 3) {
                    this.mSoundPool.resume(i2);
                } else if (i3 == 4) {
                    this.mSoundPool.stop(i2);
                    synchronized (this.mPlayingSoundMap) {
                        this.mPlayingSoundMap.remove(Integer.valueOf(i2));
                    }
                }
            }
        } finally {
        }
    }

    public synchronized void release() {
        try {
            if (this.mInitialized) {
                stopAllPlaying();
                if (this.mSoundPool != null) {
                    this.mSoundPoolMap.clear();
                    this.mSoundPool.setOnLoadCompleteListener(null);
                    this.mSoundPool.release();
                    this.mSoundPool = null;
                }
                this.mInitialized = false;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void stopAllPlaying() {
        if (this.mPlayingSoundMap.isEmpty()) {
            return;
        }
        synchronized (this.mPlayingSoundMap) {
            try {
                if (this.mSoundPool != null) {
                    Iterator<Integer> it = this.mPlayingSoundMap.iterator();
                    while (it.hasNext()) {
                        this.mSoundPool.stop(it.next().intValue());
                    }
                }
                this.mPlayingSoundMap.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized int playSound(String str, SoundOptions soundOptions) {
        try {
            if (!this.mInitialized) {
                init();
            }
            if (this.mSoundPool == null) {
                return 0;
            }
            Integer numValueOf = this.mSoundPoolMap.get(str);
            if (numValueOf == null) {
                File file = new File(ThemeResources.THEME_MAGIC_PATH + LOCKSCREEN_AUDIO + ADVANCE + str);
                if (!file.exists()) {
                    file = this.mResourceManager.getExtraFile(str);
                    if (file == null || !file.exists()) {
                        MamlLog.e(LOG_TAG, "the sound does not exist: " + str);
                        return 0;
                    }
                } else if (file.length() > 524288) {
                    MamlLog.w(LOG_TAG, String.format("the sound file is larger than %d KB: %s", 512, str));
                    return 0;
                }
                try {
                    ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(file, 268435456);
                    if (parcelFileDescriptorOpen != null) {
                        try {
                            numValueOf = Integer.valueOf(this.mSoundPool.load(parcelFileDescriptorOpen.getFileDescriptor(), 0L, file.length(), 1));
                            this.mSoundPoolMap.put(str, numValueOf);
                        } catch (Throwable th) {
                            try {
                                parcelFileDescriptorOpen.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                    if (parcelFileDescriptorOpen != null) {
                        parcelFileDescriptorOpen.close();
                    }
                } catch (IOException e2) {
                    MamlLog.e(LOG_TAG, "fail to load sound. ", e2);
                }
                this.mPendingSoundMap.put(numValueOf, soundOptions);
                return 0;
            }
            return playSoundImp(numValueOf.intValue(), soundOptions);
        } catch (Throwable th3) {
            throw th3;
        }
    }
}
