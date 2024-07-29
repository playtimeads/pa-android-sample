package com.zodiacscope.zenith.astroverse.Custom_Views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;
import java.util.HashMap;
import java.util.Map;


public class Advance_3d_DrawerLayout extends Advance_Drawer_Layout {

    private static final String TAG = Advance_3d_DrawerLayout.class.getSimpleName();
    private final Map<Integer, Setting> settings = new HashMap<>();

    public Advance_3d_DrawerLayout(Context context) {
        super(context);
    }

    public Advance_3d_DrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Advance_3d_DrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void updateSlideOffset(CardView child, Advance_Drawer_Layout.Setting setting, float width, float slideOffset, boolean isLeftDrawer) {
        updateSlideOffset(child, (Setting) setting, width, slideOffset, isLeftDrawer);
    }

    private void updateSlideOffset(CardView child, Setting setting, float width, float slideOffset, boolean isLeftDrawer) {
        if (setting.degree > 0) {
            float percentage = setting.degree / 90f;
            child.setX(width * slideOffset - child.getWidth() / 2.0f * percentage * slideOffset);
            child.setRotationY((isLeftDrawer ? -1 : 1) * setting.degree * slideOffset);
        } else {
            super.updateSlideOffset(child, setting, width, slideOffset, isLeftDrawer);
        }
    }

    @Override
    public Advance_Drawer_Layout.Setting createSetting() {
        return new Setting();
    }

    public void setViewRotation(int gravity, float degree) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = (Setting) createSetting();
            settings.put(absGravity, setting);
        } else {
            setting = settings.get(absGravity);
        }

        if (setting != null) {
            setting.degree = Math.min(degree, 45f);
            setting.scrimColor = Color.TRANSPARENT;
            setting.drawerElevation = 0f;
        }
    }

    public class Setting extends Advance_Drawer_Layout.Setting {
        public float degree = 0f;
    }
}
