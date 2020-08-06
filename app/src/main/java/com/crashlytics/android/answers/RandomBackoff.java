package com.crashlytics.android.answers;

import java.util.Random;
import p005b.p006a.p007a.p008a.p009a.p012c.p013a.Backoff;

class RandomBackoff implements Backoff {
    final Backoff backoff;
    final double jitterPercent;
    final Random random;

    public RandomBackoff(Backoff aVar, double d) {
        this(aVar, d, new Random());
    }

    public RandomBackoff(Backoff aVar, double d, Random random2) {
        if (d < 0.0d || d > 1.0d) {
            throw new IllegalArgumentException("jitterPercent must be between 0.0 and 1.0");
        } else if (aVar == null) {
            throw new NullPointerException("backoff must not be null");
        } else if (random2 == null) {
            throw new NullPointerException("random must not be null");
        } else {
            this.backoff = aVar;
            this.jitterPercent = d;
            this.random = random2;
        }
    }

    public long getDelayMillis(int i) {
        return (long) (randomJitter() * ((double) this.backoff.getDelayMillis(i)));
    }

    /* access modifiers changed from: package-private */
    public double randomJitter() {
        double d = 1.0d - this.jitterPercent;
        return d + (((this.jitterPercent + 1.0d) - d) * this.random.nextDouble());
    }
}
