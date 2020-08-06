package p034jp.p035co.cyberagent.android.gpuimage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: jp.co.cyberagent.android.gpuimage.ad */
public class GraphNode {

    /* renamed from: a */
    public static final GraphNode f2737a = new GraphNode(null);

    /* renamed from: b */
    private GPUImageFilter f2738b;

    /* renamed from: c */
    private GraphNode[] f2739c = new GraphNode[3];

    /* renamed from: a */
    private void m3731a(GraphNode adVar, int i) {
        this.f2739c[i] = adVar;
    }

    public GraphNode(GPUImageFilter fVar) {
        this.f2738b = fVar;
    }

    /* renamed from: a */
    public GPUImageFilter mo17815a() {
        return this.f2738b;
    }

    /* renamed from: b */
    public void mo17818b() {
        if (this.f2738b != null) {
            this.f2738b.mo17842c();
        }
    }

    /* renamed from: c */
    public void mo17820c() {
        if (this.f2738b != null) {
            this.f2738b.mo17845f();
        }
    }

    /* renamed from: a */
    public void mo17816a(int i, int i2) {
        if (this.f2738b != null) {
            this.f2738b.mo17810a(i, i2);
        }
    }

    /* renamed from: a */
    public void mo17817a(GraphNode adVar) {
        m3731a(adVar, 0);
    }

    /* renamed from: b */
    public void mo17819b(GraphNode adVar) {
        m3731a(adVar, 1);
    }

    /* renamed from: c */
    public void mo17821c(GraphNode adVar) {
        m3731a(adVar, 2);
    }

    /* renamed from: d */
    public GraphNode mo17822d() {
        return this.f2739c[0];
    }

    /* renamed from: e */
    public GraphNode mo17823e() {
        return this.f2739c[1];
    }

    /* renamed from: f */
    public GraphNode mo17824f() {
        return this.f2739c[2];
    }

    /* renamed from: a */
    private GraphNode m3730a(List<GraphNode> list, GPUImageFilter fVar) {
        for (GraphNode next : list) {
            if (next.mo17815a() == fVar) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: a */
    public GraphNode mo17814a(List<GraphNode> list) {
        if (this == f2737a) {
            return this;
        }
        GraphNode a = this.f2739c[0].mo17814a(list);
        if (this.f2738b instanceof GPUImageFilterGroup) {
            ((GPUImageFilterGroup) this.f2738b).mo17861n();
            List<GPUImageFilter> m = ((GPUImageFilterGroup) this.f2738b).mo17860m();
            GraphNode a2 = m3730a(list, m.get(m.size() - 1));
            if (a2 != null) {
                return a2;
            }
            for (GPUImageFilter adVar : m) {
                GraphNode adVar2 = new GraphNode(adVar);
                adVar2.mo17817a(a);
                list.add(adVar2);
                a = adVar2;
            }
        } else {
            GraphNode a3 = m3730a(list, this.f2738b);
            if (a3 != null) {
                return a3;
            }
            GraphNode adVar3 = new GraphNode(this.f2738b);
            adVar3.mo17817a(a);
            if ((this.f2738b instanceof GPUImageTwoInputFilter) && ((GPUImageTwoInputFilter) this.f2738b).mo17807n() == null) {
                adVar3.mo17819b(this.f2739c[1].mo17814a(list));
            }
            if ((this.f2738b instanceof GPUImageThreeInputFilter) && ((GPUImageThreeInputFilter) this.f2738b).mo17902m() == null) {
                adVar3.mo17821c(this.f2739c[2].mo17814a(list));
            }
            list.add(adVar3);
            a = adVar3;
        }
        return a;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList(3);
        for (int i = 0; i < this.f2739c.length; i++) {
            if (this.f2739c[i] != null) {
                GPUImageFilter a = this.f2739c[i].mo17815a();
                if (a != null) {
                    arrayList.add(String.format(Locale.US, "%s@%x", new Object[]{a.getClass().getSimpleName(), Integer.valueOf(a.hashCode())}));
                } else {
                    arrayList.add("INPUT_IMAGE");
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append(String.format(Locale.US, "GraphNode@%x", new Object[]{Integer.valueOf(hashCode())}));
        stringBuffer.append("(");
        stringBuffer.append((String) arrayList.get(0));
        for (int i2 = 1; i2 < arrayList.size(); i2++) {
            stringBuffer.append(",");
            stringBuffer.append((String) arrayList.get(i2));
        }
        stringBuffer.append(")->");
        stringBuffer.append(String.format(Locale.US, "%s@%x", new Object[]{this.f2738b.getClass().getSimpleName(), Integer.valueOf(this.f2738b.hashCode())}));
        return stringBuffer.toString();
    }
}
