package com.snaperfect.style.daguerre.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.snaperfect.inframe1.R;
import java.util.ArrayList;

/* renamed from: com.snaperfect.style.daguerre.text.b */
public class TextFontAdapter extends BaseAdapter {

    /* renamed from: a */
    private Context f2181a;

    /* renamed from: b */
    private LayoutInflater f2182b;

    /* renamed from: c */
    private ArrayList<C1569a> f2183c = new ArrayList<>();

    /* renamed from: d */
    private String[][] f2184d = {new String[]{"always  forever", "fonts/always_forever.ttf"}, new String[]{"Bevan", "fonts/Bevan.ttf"}, new String[]{"Black Jack", "fonts/BlackJackRegular.ttf"}, new String[]{"Boogaloo", "fonts/Billabong.ttf"}, new String[]{"Capture it", "fonts/Capture_it.ttf"}, new String[]{"Caviar Dreams", "fonts/CaviarDreams.ttf"}, new String[]{"CuteLove", "fonts/CuteLove.ttf"}, new String[]{"Impact Label", "fonts/Impact_Label.ttf"}, new String[]{"Jenna Sue", "fonts/JennaSue.ttf"}, new String[]{"Komika Axis", "fonts/KomikaAxis.ttf"}, new String[]{"Overlock", "fonts/Overlock_Regular.ttf"}, new String[]{"Pacifico", "fonts/Pacifico.ttf"}, new String[]{"Rock Salt", "fonts/RockSalt.ttf"}, new String[]{"Top Secret", "fonts/Top_Secret.ttf"}, new String[]{"Market Deco", "fonts/Market_Deco.ttf"}, new String[]{"Cursive standard", "fonts/Cursivestandard.ttf"}, new String[]{"Lobster 1.4", "fonts/Lobster_1.4.otf"}};

    /* renamed from: com.snaperfect.style.daguerre.text.b$a */
    /* compiled from: TextFontAdapter */
    private class C1569a {

        /* renamed from: a */
        final String f2185a;

        /* renamed from: b */
        final Typeface f2186b;

        C1569a(String str, Typeface typeface) {
            this.f2185a = str;
            this.f2186b = typeface;
        }
    }

    /* renamed from: a */
    private void m2976a(Context context) {
        if (this.f2183c != null) {
            this.f2183c.clear();
            try {
                AssetManager assets = context.getAssets();
                this.f2183c.add(new C1569a("Robot", Typeface.DEFAULT));
                this.f2183c.add(new C1569a("Robot Bold", Typeface.DEFAULT_BOLD));
                this.f2183c.add(new C1569a("Monospace", Typeface.MONOSPACE));
                this.f2183c.add(new C1569a("Sans serif", Typeface.SANS_SERIF));
                this.f2183c.add(new C1569a("Serif", Typeface.SERIF));
                for (int i = 0; i < this.f2184d.length; i++) {
                    this.f2183c.add(new C1569a(this.f2184d[i][0], Typeface.createFromAsset(assets, this.f2184d[i][1])));
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCount() {
        return this.f2183c.size();
    }

    public Object getItem(int i) {
        return this.f2183c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    TextFontAdapter(Context context) {
        this.f2181a = context;
        this.f2182b = LayoutInflater.from(this.f2181a);
        m2976a(this.f2181a);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f2182b.inflate(R.layout.text_font_item, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.text_font);
        textView.setTypeface(this.f2183c.get(i).f2186b);
        textView.setText(this.f2183c.get(i).f2185a);
        view.setTag(Integer.valueOf(i));
        return view;
    }

    /* renamed from: a */
    public Typeface mo17218a(int i) {
        return this.f2183c.get(i).f2186b;
    }
}
