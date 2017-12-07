package com.margarita.vk_app.ui.activity.drawer;

import com.mikepenz.google_material_typeface_library.GoogleMaterial.Icon;

public class DrawerItem {

    /**
     * ID of string resource for item's name
     */
    private int nameResourceId;

    /**
     * Item's icon
     */
    private Icon icon;

    public DrawerItem(int nameResourceId, Icon icon) {
        this.nameResourceId = nameResourceId;
        this.icon = icon;
    }

    public int getNameResourceId() {
        return nameResourceId;
    }

    public Icon getIcon() {
        return icon;
    }
}
