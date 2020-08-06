package p000a;

/* renamed from: a.h */
public interface Continuation<TTaskResult, TContinuationResult> {
    TContinuationResult then(Task<TTaskResult> jVar);
}
