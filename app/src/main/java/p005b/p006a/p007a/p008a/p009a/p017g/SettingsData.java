package p005b.p006a.p007a.p008a.p009a.p017g;

/* renamed from: b.a.a.a.a.g.t */
public class SettingsData {

    /* renamed from: a */
    public final AppSettingsData f338a;

    /* renamed from: b */
    public final SessionSettingsData f339b;

    /* renamed from: c */
    public final PromptSettingsData f340c;

    /* renamed from: d */
    public final FeaturesSettingsData f341d;

    /* renamed from: e */
    public final AnalyticsSettingsData f342e;

    /* renamed from: f */
    public final BetaSettingsData f343f;

    /* renamed from: g */
    public final long f344g;

    /* renamed from: h */
    public final int f345h;

    /* renamed from: i */
    public final int f346i;

    public SettingsData(long j, AppSettingsData eVar, SessionSettingsData pVar, PromptSettingsData oVar, FeaturesSettingsData mVar, AnalyticsSettingsData bVar, BetaSettingsData fVar, int i, int i2) {
        this.f344g = j;
        this.f338a = eVar;
        this.f339b = pVar;
        this.f340c = oVar;
        this.f341d = mVar;
        this.f345h = i;
        this.f346i = i2;
        this.f342e = bVar;
        this.f343f = fVar;
    }

    /* renamed from: a */
    public boolean mo8503a(long j) {
        return this.f344g < j;
    }
}
