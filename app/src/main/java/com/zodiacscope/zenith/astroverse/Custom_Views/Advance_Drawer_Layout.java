package com.zodiacscope.zenith.astroverse.Custom_Views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.zodiacscope.zenith.astroverse.R;

import java.util.HashMap;
import java.util.Map;

public class Advance_Drawer_Layout extends DrawerLayout {
    private static final String TAG = Advance_Drawer_Layout.class.getSimpleName();
    private Map<Integer, Setting> settings = new HashMap<>();
    private int defaultScrimColor = -0x67000000;
    private float defaultDrawerElevation = 0f;
    private FrameLayout frameLayout;
    private View drawerView;
    private int statusBarColor = 0;
    private boolean defaultFitsSystemWindows = false;
    private float contrastThreshold = 3f;
    private int cardBackgroundColor = 0;

    public Advance_Drawer_Layout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public Advance_Drawer_Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public Advance_Drawer_Layout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    @SuppressLint("CustomViewStyleable")
    private void init(Context context, AttributeSet attrs, int defStyle) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.advDrawerLayout);
        cardBackgroundColor = a.getColor(R.styleable.advDrawerLayout_adl_cardBackgroundColor, 0);
        a.recycle();
        defaultDrawerElevation = getDrawerElevation();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            defaultFitsSystemWindows = getFitsSystemWindows();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !isInEditMode()) {
            statusBarColor = getActivity().getWindow().getStatusBarColor();
        }
        addDrawerListener(new DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Advance_Drawer_Layout.this.drawerView = drawerView;
                updateSlideOffset(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {}

            @Override
            public void onDrawerClosed(View drawerView) {}

            @Override
            public void onDrawerStateChanged(int newState) {}
        });
        frameLayout = new FrameLayout(context);
        frameLayout.setPadding(0, 0, 0, 0);
        super.addView(frameLayout);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        child.setLayoutParams(params);
        addView(child);
    }

    @Override
    public void addView(View child) {
        if (child instanceof NavigationView) {
            super.addView(child);
        } else {
            CardView cardView = new CardView(getContext());
            cardView.setRadius(0f);
            cardView.addView(child);
            cardView.setCardElevation(0f);
            cardView.setCardBackgroundColor(cardBackgroundColor);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                cardView.setContentPadding(-6, -9, -6, -9);
            }
            frameLayout.addView(cardView);
        }
    }

    public void setViewScale(int gravity, float percentage) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
            settings.put(absGravity, setting);
        } else {
            setting = settings.get(absGravity);
        }
        if (setting != null) {
            setting.percentage = percentage;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && percentage < 1) {
            setStatusBarBackground(null);
            setSystemUiVisibility(0);
        }
        if (setting != null) {
            setting.scrimColor = Color.TRANSPARENT;
            setting.drawerElevation = 0f;
        }
    }

    public void setViewElevation(int gravity, float elevation) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
            settings.put(absGravity, setting);
        } else {
            setting = settings.get(absGravity);
        }
        if (setting != null) {
            setting.scrimColor = Color.TRANSPARENT;
            setting.drawerElevation = 0f;
            setting.elevation = elevation;
        }
    }

    public void setViewScrimColor(int gravity, @ColorInt int scrimColor) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
            settings.put(absGravity, setting);
        } else {
            setting = settings.get(absGravity);
        }
        if (setting != null) {
            setting.scrimColor = scrimColor;
        }
    }

    public void setCardBackgroundColor(int gravity, @ColorInt int color) {
        cardBackgroundColor = color;
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            CardView child = (CardView) frameLayout.getChildAt(i);
            child.setCardBackgroundColor(cardBackgroundColor);
        }
    }

    public void setRadius(int gravity, float radius) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
            settings.put(absGravity, setting);
        } else {
            setting = settings.get(absGravity);
        }
        if (setting != null) {
            setting.radius = radius;
        }
    }

    public Setting getSetting(int gravity) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        return settings.get(absGravity);
    }

    @Override
    public void setDrawerElevation(float elevation) {
        defaultDrawerElevation = elevation;
        super.setDrawerElevation(elevation);
    }

    @Override
    public void setScrimColor(@ColorInt int color) {
        defaultScrimColor = color;
        super.setScrimColor(color);
    }

    public void useCustomBehavior(int gravity) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        if (!settings.containsKey(absGravity)) {
            Setting setting = createSetting();
            settings.put(absGravity, setting);
        }
    }

    public void removeCustomBehavior(int gravity) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        if (settings.containsKey(absGravity)) {
            settings.remove(absGravity);
        }
    }

    @Override
    public void openDrawer(View drawerView, boolean animate) {
        super.openDrawer(drawerView, animate);
        post(() -> updateSlideOffset(drawerView, isDrawerOpen(drawerView) ? 1f : 0f));
    }

    private void updateSlideOffset(View drawerView, float slideOffset) {
        int absHorizGravity = getDrawerViewAbsoluteGravity(GravityCompat.START);
        int childAbsGravity = getDrawerViewAbsoluteGravity(drawerView);
        Activity activity = getActivity();
        if (activity == null) return;
        boolean isRtl = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            isRtl = getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        }
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            CardView child = (CardView) frameLayout.getChildAt(i);
            Setting setting = settings.get(childAbsGravity);
            if (setting != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && setting.percentage < 1.0) {
                    if (drawerView.getBackground() instanceof ColorDrawable) {
                        int color = ColorUtils.setAlphaComponent(statusBarColor, (int) (255 - 255 * slideOffset));
                        activity.getWindow().setStatusBarColor(color);
                        int bgColor = ((ColorDrawable) drawerView.getBackground()).getColor();
                        activity.getWindow().getDecorView().setBackgroundColor(bgColor);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            int contrastColor = ColorUtils.calculateContrast(Color.WHITE, bgColor) < contrastThreshold && slideOffset > 0.4 ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : 0;
                            setSystemUiVisibility(contrastColor);
                        }
                    } else if (drawerView.getBackground() instanceof MaterialShapeDrawable) {
                        MaterialShapeDrawable background = (MaterialShapeDrawable) drawerView.getBackground();
                        if (background.getFillColor() != null) {
                            int color = ColorUtils.setAlphaComponent(statusBarColor, (int) (255 - 255 * slideOffset));
                            activity.getWindow().setStatusBarColor(color);
                            int bgColor = background.getFillColor().getDefaultColor();
                            activity.getWindow().getDecorView().setBackgroundColor(bgColor);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                int contrastColor = ColorUtils.calculateContrast(Color.WHITE, bgColor) < contrastThreshold && slideOffset > 0.4 ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : 0;
                                setSystemUiVisibility(contrastColor);
                            }
                        }
                    }
                }
                child.setRadius(Math.round(setting.radius * slideOffset * 2.0) / 2f);
                super.setScrimColor(setting.scrimColor);
                super.setDrawerElevation(setting.drawerElevation);
                float percentage = 1f - setting.percentage;
                ViewCompat.setScaleY(child, 1f - percentage * slideOffset);
                child.setCardElevation(setting.elevation * slideOffset);
                float adjust = setting.elevation;
                boolean isLeftDrawer = isRtl ? childAbsGravity != absHorizGravity : childAbsGravity == absHorizGravity;
                float width = isLeftDrawer ? drawerView.getWidth() + adjust : -drawerView.getWidth() - adjust;
                updateSlideOffset(child, setting, width, slideOffset, isLeftDrawer);
            } else {
                super.setScrimColor(defaultScrimColor);
                super.setDrawerElevation(defaultDrawerElevation);
            }
        }
    }

    public void setContrastThreshold(float contrastThreshold) {
        this.contrastThreshold = contrastThreshold;
    }

    public Activity getActivity() {
        return getActivity(getContext());
    }

    private Activity getActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) return (Activity) context;
        if (context instanceof ContextWrapper) return getActivity(((ContextWrapper) context).getBaseContext());
        return null;
    }

    public void updateSlideOffset(CardView child, Setting setting, float width, float slideOffset, boolean isLeftDrawer) {
        child.setX(width * slideOffset);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (drawerView != null) updateSlideOffset(drawerView, isDrawerOpen(drawerView) ? 1f : 0f);
    }

    int getDrawerViewAbsoluteGravity(int gravity) {
        return GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this)) & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
    }

    private int getDrawerViewAbsoluteGravity(View drawerView) {
        int gravity = ((LayoutParams) drawerView.getLayoutParams()).gravity;
        return getDrawerViewAbsoluteGravity(gravity);
    }

    public Setting createSetting() {
        return new Setting();
    }

    public class Setting {
        public boolean fitsSystemWindows = false;
        public float percentage = 1f;
        public int scrimColor = defaultScrimColor;
        public float elevation = 0f;
        public float drawerElevation = defaultDrawerElevation;
        public float radius = 0f;
    }
}
