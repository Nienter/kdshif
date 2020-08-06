package android.support.transition;

import android.view.View;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TransitionValues {
    public final Map<String, Object> values = new HashMap();
    public View view;

    public boolean equals(Object obj) {
        if (!(obj instanceof TransitionValues) || this.view != ((TransitionValues) obj).view || !this.values.equals(((TransitionValues) obj).values)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.view.hashCode() * 31) + this.values.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.view + "\n") + "    values:";
        Iterator<String> it = this.values.keySet().iterator();
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return str2;
            }
            String next = it.next();
            str = str2 + "    " + next + ": " + this.values.get(next) + "\n";
        }
    }
}
