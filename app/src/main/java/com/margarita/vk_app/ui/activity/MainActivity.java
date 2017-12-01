package com.margarita.vk_app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.mvp.presenter.MainPresenter;
import com.margarita.vk_app.mvp.view.MainView;
import com.margarita.vk_app.ui.fragment.NewsFeedFragment;
import com.mikepenz.google_material_typeface_library.GoogleMaterial.Icon;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends BaseActivity implements MainView {

    // Аннотация для управления жизненным циклом презентера
    @InjectPresenter
    MainPresenter mainPresenter;

    private Drawer drawer;

    /**
     * Sizes for different sections of navigation drawer
     */
    public static final int FIRST_SECTION_SIZE = 3;
    public static final int SECOND_SECTION_SIZE = 4;

    /**
     * All items of navigation drawer
     */
    private static final SparseArray<Icon> DRAWER_ITEMS = new SparseArray<>();

    /**
     * Item for a new section of navigation drawer
     */
    private static final SectionDrawerItem SECTION_ITEM = new SectionDrawerItem()
            .withName(R.string.drawer_item_section);

    static {
        DRAWER_ITEMS.append(R.string.drawer_item_news, Icon.gmd_home);
        DRAWER_ITEMS.append(R.string.drawer_item_my_posts, Icon.gmd_list);
        DRAWER_ITEMS.append(R.string.drawer_item_settings, Icon.gmd_settings);
        DRAWER_ITEMS.append(R.string.drawer_item_members, Icon.gmd_people);
        DRAWER_ITEMS.append(R.string.drawer_item_topics, Icon.gmd_record_voice_over);
        DRAWER_ITEMS.append(R.string.drawer_item_info, Icon.gmd_info);
        DRAWER_ITEMS.append(R.string.drawer_item_group_rules, Icon.gmd_assignment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VkApplication.getApplicationComponent().inject(this);
        mainPresenter.checkAuth();
        setupDrawer();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                mainPresenter.checkAuth();
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startSignIn() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE);
    }

    @Override
    public void signedIn() {
        setContent(new NewsFeedFragment());
    }

    private void setupDrawer() {
        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .build();

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(accountHeader)
                .addDrawerItems(getDrawerItems(0, FIRST_SECTION_SIZE))
                .addDrawerItems(SECTION_ITEM)
                .addDrawerItems(getDrawerItems(FIRST_SECTION_SIZE, SECOND_SECTION_SIZE))
                .build();
    }

    /**
     * Configure drawer items
     * @param offset Offset for DRAWER_ITEMS array
     * @param size Size of result array
     * @return Array of drawer items
     */
    private PrimaryDrawerItem[] getDrawerItems(int offset, int size) {
        PrimaryDrawerItem[] result = new PrimaryDrawerItem[size];

        for (int i = 0; i < size; i++) {
            int itemsOffset = i + offset;
            result[i] = new PrimaryDrawerItem()
                    .withIdentifier(itemsOffset + 1)
                    .withName(DRAWER_ITEMS.keyAt(itemsOffset))
                    .withIcon(DRAWER_ITEMS.valueAt(itemsOffset));
        }
        return result;
    }
}
