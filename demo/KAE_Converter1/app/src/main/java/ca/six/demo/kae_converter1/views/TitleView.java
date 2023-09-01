package ca.six.demo.kae_converter1.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ca.six.demo.kae_converter1.R;

public class TitleView extends FrameLayout {
    public TitleView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(@NonNull final Context context, @Nullable AttributeSet attrs) {
        final View view = inflate(context, R.layout.view_top_view, this);
    }

}
