package p005b.p006a.p007a.p008a.p009a.p011b;

/* renamed from: b.a.a.a.a.b.l */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);
    
    public static final String BETA_APP_PACKAGE_NAME = "io.crash.air";

    /* renamed from: id */
    private final int f128id;

    private DeliveryMechanism(int i) {
        this.f128id = i;
    }

    public int getId() {
        return this.f128id;
    }

    public String toString() {
        return Integer.toString(this.f128id);
    }

    public static DeliveryMechanism determineFrom(String str) {
        if (BETA_APP_PACKAGE_NAME.equals(str)) {
            return TEST_DISTRIBUTION;
        }
        if (str != null) {
            return APP_STORE;
        }
        return DEVELOPER;
    }
}
