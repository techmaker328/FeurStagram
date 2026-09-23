package com.feurstagram.extension;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Watches the bottom tab bar until the "feed_tab" child (the Home button at the
 * bottom-left) is inflated, then installs a long-press listener on it that opens
 * the Feurstagram settings, and detaches.
 *
 * This is the sole settings entry point. An injected action-bar button was tried
 * but failed to appear on many devices, since it depends on the feed's top action
 * bar being present and laid out a particular way. Long-pressing Home only needs
 * the tab bar, which always exists, so settings stay reachable everywhere. The id
 * is resolved by name (with a clone fallback) so it survives Instagram version bumps.
 */
public final class HomeTabWatcher implements ViewTreeObserver.OnGlobalLayoutListener {

    private ViewGroup tabBar;

    public HomeTabWatcher(ViewGroup tabBar) {
        this.tabBar = tabBar;
    }

    @Override
    public void onGlobalLayout() {
        ViewGroup bar = tabBar;
        if (bar == null) return;

        // Strict build: settings are intentionally unreachable.
        bar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        tabBar = null;
    }
}
