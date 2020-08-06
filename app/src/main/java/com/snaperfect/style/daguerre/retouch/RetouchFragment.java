package com.snaperfect.style.daguerre.retouch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.utils.IntentUtils;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import com.snaperfect.style.daguerre.utils.ScreenInfo;

public class RetouchFragment extends Fragment {

    /* renamed from: a */
    private Activity f2104a;

    /* renamed from: b */
    private SeekBar f2105b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C1552a f2106c;

    /* renamed from: d */
    private PhotoAsset f2107d;

    /* renamed from: e */
    private int f2108e;

    /* renamed from: f */
    private int f2109f;

    /* renamed from: com.snaperfect.style.daguerre.retouch.RetouchFragment$a */
    public interface C1552a {
        /* renamed from: e */
        void mo16919e(int i);

        /* renamed from: e */
        void mo16921e(boolean z);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.retouch_fragment, viewGroup, false);
        inflate.setVisibility(0);
        this.f2104a = getActivity();
        this.f2109f = (int) (ScreenInfo.m3112a(this.f2104a).f2102a / 4.0f);
        this.f2105b = (SeekBar) inflate.findViewById(R.id.retouch_seekbar);
        this.f2105b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                RetouchFragment.this.f2106c.mo16919e(seekBar.getProgress());
            }
        });
        ((TextView) inflate.findViewById(R.id.menu_title)).setText(R.string.edit_toolbar_retouch);
        m2903a(inflate.findViewById(R.id.menu_cancel));
        m2903a(inflate.findViewById(R.id.menu_confirm));
        m2902a();
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.f2106c = (C1552a) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnToolbarSelectedListener");
        }
    }

    /* renamed from: a */
    private void m2903a(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RetouchFragment.this.getView().setVisibility(8);
                RetouchFragment.this.f2106c.mo16921e(view.getId() == R.id.menu_cancel);
            }
        });
    }

    /* renamed from: a */
    private void m2902a() {
        Bundle arguments = getArguments();
        this.f2107d = (PhotoAsset) IntentUtils.m3094a(arguments, "asset", PhotoAsset.CREATOR);
        this.f2108e = arguments.getInt("retouch");
        this.f2105b.setProgress(this.f2108e);
    }
}
