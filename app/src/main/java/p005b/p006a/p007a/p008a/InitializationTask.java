package p005b.p006a.p007a.p008a;

import p005b.p006a.p007a.p008a.p009a.p011b.TimingMetric;
import p005b.p006a.p007a.p008a.p009a.p012c.Priority;
import p005b.p006a.p007a.p008a.p009a.p012c.PriorityAsyncTask;
import p005b.p006a.p007a.p008a.p009a.p012c.UnmetDependencyException;

/* renamed from: b.a.a.a.h */
class InitializationTask<Result> extends PriorityAsyncTask<Void, Void, Result> {

    /* renamed from: a */
    final Kit<Result> f392a;

    public InitializationTask(Kit<Result> iVar) {
        this.f392a = iVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8324a() {
        super.mo8324a();
        TimingMetric a = m483a("onPreExecute");
        try {
            boolean onPreExecute = this.f392a.onPreExecute();
            a.mo8321b();
            if (!onPreExecute) {
                mo8326a(true);
            }
        } catch (UnmetDependencyException e) {
            throw e;
        } catch (Exception e2) {
            Fabric.m456h().mo8516e("Fabric", "Failure onPreExecute()", e2);
            a.mo8321b();
            mo8326a(true);
        } catch (Throwable th) {
            a.mo8321b();
            mo8326a(true);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Result mo8323a(Void... voidArr) {
        TimingMetric a = m483a("doInBackground");
        Result result = null;
        if (!mo8331d()) {
            result = this.f392a.doInBackground();
        }
        a.mo8321b();
        return result;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8325a(Result result) {
        this.f392a.onPostExecute(result);
        this.f392a.initializationCallback.mo8529a(result);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8328b(Result result) {
        this.f392a.onCancelled(result);
        this.f392a.initializationCallback.mo8528a((Exception) new InitializationException(this.f392a.getIdentifier() + " Initialization was cancelled"));
    }

    public Priority getPriority() {
        return Priority.HIGH;
    }

    /* renamed from: a */
    private TimingMetric m483a(String str) {
        TimingMetric uVar = new TimingMetric(this.f392a.getIdentifier() + "." + str, "KitInitialization");
        uVar.mo8320a();
        return uVar;
    }
}
