package p005b.p006a.p007a.p008a;

import android.content.Context;
import java.io.File;
import java.util.Collection;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p012c.C0449l;
import p005b.p006a.p007a.p008a.p009a.p012c.DependsOn;

/* renamed from: b.a.a.a.i */
public abstract class Kit<Result> implements Comparable<Kit> {
    Context context;
    final DependsOn dependsOnAnnotation = ((DependsOn) getClass().getAnnotation(DependsOn.class));
    Fabric fabric;
    IdManager idManager;
    InitializationCallback<Result> initializationCallback;
    InitializationTask<Result> initializationTask = new InitializationTask<>(this);

    /* access modifiers changed from: protected */
    public abstract Result doInBackground();

    public abstract String getIdentifier();

    public abstract String getVersion();

    /* access modifiers changed from: package-private */
    public void injectParameters(Context context2, Fabric cVar, InitializationCallback<Result> fVar, IdManager pVar) {
        this.fabric = cVar;
        this.context = new FabricContext(context2, getIdentifier(), getPath());
        this.initializationCallback = fVar;
        this.idManager = pVar;
    }

    /* access modifiers changed from: package-private */
    public final void initialize() {
        this.initializationTask.mo8367a(this.fabric.mo8526f(), null);
    }

    /* access modifiers changed from: protected */
    public boolean onPreExecute() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Result result) {
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Result result) {
    }

    /* access modifiers changed from: protected */
    public IdManager getIdManager() {
        return this.idManager;
    }

    public Context getContext() {
        return this.context;
    }

    public Fabric getFabric() {
        return this.fabric;
    }

    public String getPath() {
        return ".Fabric" + File.separator + getIdentifier();
    }

    public int compareTo(Kit iVar) {
        if (containsAnnotatedDependency(iVar)) {
            return 1;
        }
        if (iVar.containsAnnotatedDependency(this)) {
            return -1;
        }
        if (hasAnnotatedDependency() && !iVar.hasAnnotatedDependency()) {
            return 1;
        }
        if (hasAnnotatedDependency() || !iVar.hasAnnotatedDependency()) {
            return 0;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public boolean containsAnnotatedDependency(Kit iVar) {
        if (!hasAnnotatedDependency()) {
            return false;
        }
        for (Class isAssignableFrom : this.dependsOnAnnotation.mo8365a()) {
            if (isAssignableFrom.isAssignableFrom(iVar.getClass())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnnotatedDependency() {
        return this.dependsOnAnnotation != null;
    }

    /* access modifiers changed from: protected */
    public Collection<C0449l> getDependencies() {
        return this.initializationTask.getDependencies();
    }
}
