package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T> extends zzb<T> {
    private T zzaCF;

    public zzg(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.zzaCk).toString());
        }
        this.zzaCk++;
        if (this.zzaCk == 0) {
            this.zzaCF = this.zzaCj.get(0);
            if (!(this.zzaCF instanceof zzc)) {
                String valueOf = String.valueOf(this.zzaCF.getClass());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 44).append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
            }
        } else {
            ((zzc) this.zzaCF).zzcA(this.zzaCk);
        }
        return this.zzaCF;
    }
}
