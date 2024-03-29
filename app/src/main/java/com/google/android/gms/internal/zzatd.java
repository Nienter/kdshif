package com.google.android.gms.internal;

import android.support.p004v7.widget.helper.ItemTouchHelper;
import com.google.android.gms.common.internal.zzac;

public final class zzatd {
    public static zza<Boolean> zzbqS = zza.zzl("measurement.service_enabled", true);
    public static zza<Boolean> zzbqT = zza.zzl("measurement.service_client_enabled", true);
    public static zza<Boolean> zzbqU = zza.zzl("measurement.log_installs_enabled", false);
    public static zza<String> zzbqV = zza.zzk("measurement.log_tag", "FA", "FA-SVC");
    public static zza<Long> zzbqW = zza.zzh("measurement.ad_id_cache_time", 10000);
    public static zza<Long> zzbqX = zza.zzh("measurement.monitoring.sample_period_millis", 86400000);
    public static zza<Long> zzbqY = zza.zzb("measurement.config.cache_time", 86400000, 3600000);
    public static zza<String> zzbqZ = zza.zzV("measurement.config.url_scheme", "https");
    public static zza<Long> zzbrA = zza.zzh("measurement.service_client.idle_disconnect_millis", 5000);
    public static zza<String> zzbra = zza.zzV("measurement.config.url_authority", "app-measurement.com");
    public static zza<Integer> zzbrb = zza.zzB("measurement.upload.max_bundles", 100);
    public static zza<Integer> zzbrc = zza.zzB("measurement.upload.max_batch_size", 65536);
    public static zza<Integer> zzbrd = zza.zzB("measurement.upload.max_bundle_size", 65536);
    public static zza<Integer> zzbre = zza.zzB("measurement.upload.max_events_per_bundle", 1000);
    public static zza<Integer> zzbrf = zza.zzB("measurement.upload.max_events_per_day", 100000);
    public static zza<Integer> zzbrg = zza.zzB("measurement.upload.max_error_events_per_day", 1000);
    public static zza<Integer> zzbrh = zza.zzB("measurement.upload.max_public_events_per_day", 50000);
    public static zza<Integer> zzbri = zza.zzB("measurement.upload.max_conversions_per_day", 500);
    public static zza<Integer> zzbrj = zza.zzB("measurement.upload.max_realtime_events_per_day", 10);
    public static zza<Integer> zzbrk = zza.zzB("measurement.store.max_stored_events_per_app", 100000);
    public static zza<String> zzbrl = zza.zzV("measurement.upload.url", "https://app-measurement.com/a");
    public static zza<Long> zzbrm = zza.zzh("measurement.upload.backoff_period", 43200000);
    public static zza<Long> zzbrn = zza.zzh("measurement.upload.window_interval", 3600000);
    public static zza<Long> zzbro = zza.zzh("measurement.upload.interval", 3600000);
    public static zza<Long> zzbrp = zza.zzh("measurement.upload.realtime_upload_interval", 10000);
    public static zza<Long> zzbrq = zza.zzh("measurement.upload.minimum_delay", 500);
    public static zza<Long> zzbrr = zza.zzh("measurement.alarm_manager.minimum_interval", 60000);
    public static zza<Long> zzbrs = zza.zzh("measurement.upload.stale_data_deletion_interval", 86400000);
    public static zza<Long> zzbrt = zza.zzh("measurement.upload.refresh_blacklisted_config_interval", 604800000);
    public static zza<Long> zzbru = zza.zzh("measurement.upload.initial_upload_delay_time", 15000);
    public static zza<Long> zzbrv = zza.zzh("measurement.upload.retry_time", 1800000);
    public static zza<Integer> zzbrw = zza.zzB("measurement.upload.retry_count", 6);
    public static zza<Long> zzbrx = zza.zzh("measurement.upload.max_queue_time", 2419200000L);
    public static zza<Integer> zzbry = zza.zzB("measurement.lifetimevalue.max_currency_tracked", 4);
    public static zza<Integer> zzbrz = zza.zzB("measurement.audience.filter_result_max_count", ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);

    public static final class zza<V> {
        private final String zzAH;
        private final V zzaeZ;
        private final zzabs<V> zzafa;

        private zza(String str, zzabs<V> zzabs, V v) {
            zzac.zzw(zzabs);
            this.zzafa = zzabs;
            this.zzaeZ = v;
            this.zzAH = str;
        }

        static zza<Integer> zzB(String str, int i) {
            return zzo(str, i, i);
        }

        static zza<String> zzV(String str, String str2) {
            return zzk(str, str2, str2);
        }

        static zza<Long> zzb(String str, long j, long j2) {
            return new zza<>(str, zzabs.zza(str, Long.valueOf(j2)), Long.valueOf(j));
        }

        static zza<Boolean> zzb(String str, boolean z, boolean z2) {
            return new zza<>(str, zzabs.zzj(str, z2), Boolean.valueOf(z));
        }

        static zza<Long> zzh(String str, long j) {
            return zzb(str, j, j);
        }

        static zza<String> zzk(String str, String str2, String str3) {
            return new zza<>(str, zzabs.zzA(str, str3), str2);
        }

        static zza<Boolean> zzl(String str, boolean z) {
            return zzb(str, z, z);
        }

        static zza<Integer> zzo(String str, int i, int i2) {
            return new zza<>(str, zzabs.zza(str, Integer.valueOf(i2)), Integer.valueOf(i));
        }

        public V get() {
            return this.zzaeZ;
        }

        public V get(V v) {
            return v != null ? v : this.zzaeZ;
        }

        public String getKey() {
            return this.zzAH;
        }
    }
}
