package com.project.scanit.ui.model;


public class ToolbarSettings {

    private String mTitle;
    private boolean showToolbar;
    private boolean showbackButton;

    public ToolbarSettings(String title, boolean show) {
        mTitle = title;
        showToolbar = show;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isShowToolbar() {
        return showToolbar;
    }

    public void setShowToolbar(boolean showToolbar) {
        this.showToolbar = showToolbar;
    }

    public boolean isShowbackButton() {
        return showbackButton;
    }

    public void setShowbackButton(boolean showbackButton) {
        this.showbackButton = showbackButton;
    }
}
