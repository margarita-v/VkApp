package com.margarita.vk_app.ui.activity.drawer;

/**
 * All types of drawer items
 */
public enum DrawerItemType {
    News(1), Posts(2), Settings(3), Members(4), Topics(5), Info(6), GroupRules(7);

    private final int id;

    DrawerItemType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DrawerItemType getTypeById(int id) {
        for (DrawerItemType itemType: values()) {
            if (itemType.getId() == id)
                return itemType;
        }
        return null;
    }
}
