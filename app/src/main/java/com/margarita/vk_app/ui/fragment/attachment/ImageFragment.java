package com.margarita.vk_app.ui.fragment.attachment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.ui.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFragment extends BaseFragment {

    @BindView(R.id.webView)
    WebView webView;

    /**
     * Key for bundle
     */
    private static final String URL_KEY = "url";

    public static ImageFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(URL_KEY, url);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.image;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setBackgroundColor(getResources().getColor(R.color.colorDefaultWhite));

        webView.loadUrl(getArguments().getString(URL_KEY));
    }
}
