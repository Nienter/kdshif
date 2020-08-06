package android.support.p001v4.net;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.net.DatagramSocket;

@TargetApi(24)
@RequiresApi(24)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.net.TrafficStatsCompatApi24 */
public class TrafficStatsCompatApi24 {
    public static void tagDatagramSocket(DatagramSocket datagramSocket) {
        TrafficStats.tagDatagramSocket(datagramSocket);
    }

    public static void untagDatagramSocket(DatagramSocket datagramSocket) {
        TrafficStats.untagDatagramSocket(datagramSocket);
    }
}
