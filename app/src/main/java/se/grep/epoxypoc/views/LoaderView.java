package se.grep.epoxypoc.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;

import se.grep.epoxypoc.R;

@ModelView(defaultLayout = R.layout.loader_view)
public class LoaderView extends LinearLayout {
    public LoaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @ModelProp(options = ModelProp.Option.GenerateStringOverloads)
    public void setTitle(CharSequence title) {
        ((TextView) findViewById(R.id.itemTitle)).setText(title);
    }
}