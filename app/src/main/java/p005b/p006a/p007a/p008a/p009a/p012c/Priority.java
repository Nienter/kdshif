package p005b.p006a.p007a.p008a.p009a.p012c;

/* renamed from: b.a.a.a.a.c.e */
public enum Priority {
    LOW,
    NORMAL,
    HIGH,
    IMMEDIATE;

    static <Y> int compareTo(PriorityProvider iVar, Y y) {
        Priority eVar;
        if (y instanceof PriorityProvider) {
            eVar = ((PriorityProvider) y).getPriority();
        } else {
            eVar = NORMAL;
        }
        return eVar.ordinal() - iVar.getPriority().ordinal();
    }
}
