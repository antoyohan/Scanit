package com.project.scanit.ui;


import android.os.Bundle;

public interface Navigator {

    void launchHomeScreen(Bundle args);

    void launchSplashScreen(Bundle args);

    void launchScanningScreen(Bundle args);

    void launchDetailScreen(Bundle args);
}
