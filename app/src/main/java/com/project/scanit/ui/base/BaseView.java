/*
 * Copyright © 2017, Eenadu Television Pvt. Ltd.
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 */

package com.project.scanit.ui.base;

public interface BaseView {

    void showProgressIndicator();

    void hideProgressIndicator();

    void displayApiError(Error error);
}
