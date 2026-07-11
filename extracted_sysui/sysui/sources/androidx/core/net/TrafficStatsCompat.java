package androidx.core.net;

import android.net.TrafficStats;
import androidx.annotation.RequiresApi;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
public final class TrafficStatsCompat {

    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static void tagDatagramSocket(DatagramSocket datagramSocket) throws SocketException {
            TrafficStats.tagDatagramSocket(datagramSocket);
        }

        public static void untagDatagramSocket(DatagramSocket datagramSocket) throws SocketException {
            TrafficStats.untagDatagramSocket(datagramSocket);
        }
    }

    private TrafficStatsCompat() {
    }

    @Deprecated
    public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }

    @Deprecated
    public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }

    @Deprecated
    public static void incrementOperationCount(int i2) {
        TrafficStats.incrementOperationCount(i2);
    }

    @Deprecated
    public static void setThreadStatsTag(int i2) {
        TrafficStats.setThreadStatsTag(i2);
    }

    public static void tagDatagramSocket(DatagramSocket datagramSocket) throws SocketException {
        Api24Impl.tagDatagramSocket(datagramSocket);
    }

    @Deprecated
    public static void tagSocket(Socket socket) throws SocketException {
        TrafficStats.tagSocket(socket);
    }

    public static void untagDatagramSocket(DatagramSocket datagramSocket) throws SocketException {
        Api24Impl.untagDatagramSocket(datagramSocket);
    }

    @Deprecated
    public static void untagSocket(Socket socket) throws SocketException {
        TrafficStats.untagSocket(socket);
    }

    @Deprecated
    public static void incrementOperationCount(int i2, int i3) {
        TrafficStats.incrementOperationCount(i2, i3);
    }
}
