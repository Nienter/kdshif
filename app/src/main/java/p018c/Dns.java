package p018c;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* renamed from: c.o */
public interface Dns {

    /* renamed from: a */
    public static final Dns f1025a = new Dns() {
        /* renamed from: a */
        public List<InetAddress> mo8930a(String str) {
            if (str != null) {
                return Arrays.asList(InetAddress.getAllByName(str));
            }
            throw new UnknownHostException("hostname == null");
        }
    };

    /* renamed from: a */
    List<InetAddress> mo8930a(String str);
}
