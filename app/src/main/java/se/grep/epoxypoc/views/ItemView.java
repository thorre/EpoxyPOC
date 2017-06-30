package se.grep.epoxypoc.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelView;

import se.grep.epoxypoc.R;

@ModelView(defaultLayout = R.layout.item_view)
public class ItemView extends LinearLayout {

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @ModelProp
    public void setTitle(CharSequence title) {
        ((TextView) findViewById(R.id.itemTitle)).setText(title);
    }

    @ModelProp(options = ModelProp.Option.DoNotHash)
    public void setClickListener(@Nullable View.OnClickListener listener) {
        setOnClickListener(listener);
    }

    @ModelProp(options = ModelProp.Option.DoNotHash)
    public void setLongClickListener(@Nullable View.OnLongClickListener listener) {
        setOnLongClickListener(listener);
    }
}