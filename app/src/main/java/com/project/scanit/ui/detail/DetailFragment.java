package com.project.scanit.ui.detail;


import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.scanit.R;
import com.project.scanit.constants.Constants;
import com.project.scanit.database.entity.DetailEntity;
import com.project.scanit.io.manager.OfflineQRManager;
import com.project.scanit.ui.HomeActivity;
import com.project.scanit.ui.base.BaseFragment;
import com.project.scanit.ui.model.DetailModel;
import com.project.scanit.ui.model.ToolbarSettings;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailFragment extends BaseFragment {
    private DetailModel mDetailModel;

    @BindView(R.id.tv_text)
    TextView mQrText;
    @BindView(R.id.tv_format)
    TextView mFormat;
    @BindView(R.id.tv_time)
    TextView mTime;
    @BindView(R.id.rl_save)
    RelativeLayout mSaveLayout;

    private boolean mShowSaveLayout = true;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void initViews(View view) {
        if (mDetailModel != null) {
            mQrText.setText(mDetailModel.getText());
            mFormat.setText(mDetailModel.getFormat());
            mTime.setText(mDetailModel.getTime());
        }

        mSaveLayout.setVisibility(mShowSaveLayout ? View.VISIBLE : View.GONE);

    }

    @Override
    protected void initDagger() {

    }

    @Override
    protected void handleEvents(Object object) {

    }

    @Override
    public ToolbarSettings getToolBarSetting() {
        ToolbarSettings settings = new ToolbarSettings(getString(R.string.txt_qr_code), true);
        settings.setShowbackButton(true);
        return settings;
    }

    @Override
    protected void getBundleData() {
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(Constants.BUNDLE_KEYS.DETAIL_MODEL)) {
                mDetailModel = (DetailModel) args.getParcelable(Constants.BUNDLE_KEYS.DETAIL_MODEL);
            }
            if (args.containsKey(Constants.BUNDLE_KEYS.SHOW_SAVE_LAYOUT)) {
                mShowSaveLayout = args.getBoolean(Constants.BUNDLE_KEYS.SHOW_SAVE_LAYOUT);
            }
        }
    }

    @OnClick(R.id.rl_save)
    void onSaveClick() {
        DetailEntity entity = new DetailEntity(getTimeStamp());
        entity.setTime(mDetailModel.getTime());
        entity.setFormat(mDetailModel.getFormat());
        entity.setTitle(mDetailModel.getText());
        new OfflineQRManager().insertToDataBase(entity);
        ((HomeActivity) getActivity()).launchHomeScreen(null);
    }

    /**
     * To get unique id
     *
     * @return current timestamp
     */
    public long getTimeStamp() {
        return Long.parseLong(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
    }
}
