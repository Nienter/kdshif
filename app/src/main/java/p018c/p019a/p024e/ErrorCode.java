package p018c.p019a.p024e;

/* renamed from: c.a.e.b */
public enum ErrorCode {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8);
    
    public final int httpCode;

    private ErrorCode(int i) {
        this.httpCode = i;
    }

    public static ErrorCode fromHttp2(int i) {
        for (ErrorCode bVar : values()) {
            if (bVar.httpCode == i) {
                return bVar;
            }
        }
        return null;
    }
}
