package com.project.scanit.ui.scan;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.project.scanit.R;
import com.project.scanit.constants.Constants;
import com.project.scanit.ui.HomeActivity;
import com.project.scanit.ui.base.BaseActivity;
import com.project.scanit.ui.base.BaseFragment;
import com.project.scanit.ui.model.DetailModel;
import com.project.scanit.ui.model.ToolbarSettings;

import java.util.List;

import butterknife.BindView;

public class ScanFragment extends BaseFragment{
    private static final int CAMERA_PERMISSION_CODE = 101;

    @BindView(R.id.scan_view)
    DecoratedBarcodeView mDecoratedBarcodeView;
    private BeepManager mBeepManager;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_scan;
    }

    @Override
    protected void initViews(View view) {
        askForPermissions();
        mDecoratedBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                launchDetailScreen(result);
                mBeepManager.playBeepSound();
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
                Log.d(TAG , "barcode results points");
            }
        });
        ((HomeActivity)getActivity()).setDecoratedBarcodeView(mDecoratedBarcodeView);
        mBeepManager = new BeepManager(getActivity());
    }

    private void launchDetailScreen(BarcodeResult result) {
        DetailModel model = generateDetailModel(result);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_KEYS.DETAIL_MODEL, model);
        ((HomeActivity)getActivity()).launchDetailScreen(bundle);
    }

    private DetailModel generateDetailModel(BarcodeResult result) {
        DetailModel model = new DetailModel();
        model.setText(result.getText());
        model.setTime(result.getTimestamp());
        model.setFormat(result.getBarcodeFormat().name());
        return model;
    }

    @Override
    protected void initDagger() {

    }

    @Override
    protected void handleEvents(Object object) {

    }

    @Override
    public ToolbarSettings getToolBarSetting() {
        return new ToolbarSettings("", false);
    }

    /**
     * Permission for download
     */
    private void askForPermissions() {
        if (Build.VERSION.SDK_INT > 22 && !isCameraAllowed()) {
            requestCameraPermission();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean isCameraAllowed() {
        int result = getActivity().checkSelfPermission(Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        this.requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean permissionDenied = grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED;
        if (requestCode == CAMERA_PERMISSION_CODE && permissionDenied) {
            ((BaseActivity)getActivity()).showToastMessage(getString(R.string.provide_permissions));
        }
    }

    /*************************/

    @Override
    public void onResume() {
        super.onResume();
        mDecoratedBarcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mDecoratedBarcodeView.pause();
    }
}
