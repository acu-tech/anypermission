package per.goweii.android.anypermission;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import per.goweii.anylayer.AnyLayer;
import per.goweii.anypermission.AnyPermission;
import per.goweii.anypermission.RequestInterceptor;
import per.goweii.anypermission.RequestListener;
import per.goweii.anypermission.RuntimeRequester;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private RuntimeRequester mRuntimeRequester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_runtime).setOnClickListener(this);
        findViewById(R.id.btn_install).setOnClickListener(this);
        findViewById(R.id.btn_overlay).setOnClickListener(this);
        findViewById(R.id.btn_setting).setOnClickListener(this);
        findViewById(R.id.btn_notification_show).setOnClickListener(this);
        findViewById(R.id.btn_notification_access).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_runtime:
                requestRuntime();
                break;
            case R.id.btn_install:
                requestInstall();
                break;
            case R.id.btn_overlay:
                requestOverlay();
                break;
            case R.id.btn_setting:
                requestSetting();
                break;
            case R.id.btn_notification_show:
                requestNotificationShow();
                break;
            case R.id.btn_notification_access:
                requestNotificationAccess();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mRuntimeRequester != null) {
            mRuntimeRequester.onActivityResult(requestCode);
        }
    }

    private void requestRuntime() {
        mRuntimeRequester = AnyPermission.with(this)
                .runtime(1)
                .permissions(Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.SEND_SMS)
                .onBeforeRequest(new RequestInterceptor<String>() {
                    @Override
                    public void intercept(@NonNull final String permission, @NonNull final Executor executor) {
                        AnyLayer.with(TestActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new AnyLayer.IDataBinder() {
                                    @Override
                                    public void bind(AnyLayer anyLayer) {
                                        TextView tvTitle = anyLayer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = anyLayer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = anyLayer.getView(R.id.tv_dialog_permission_next);

                                        tvNext.setText("?????????");
                                        tvTitle.setText(AnyPermission.with(TestActivity.this).name(permission));
                                        tvDescription.setText("?????????????????????\"" + AnyPermission.with(TestActivity.this).name(permission) + "\"??????");
                                    }
                                })
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();
                    }
                })
                .onBeenDenied(new RequestInterceptor<String>() {
                    @Override
                    public void intercept(@NonNull final String permission, @NonNull final Executor executor) {
                        AnyLayer.with(TestActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new AnyLayer.IDataBinder() {
                                    @Override
                                    public void bind(AnyLayer anyLayer) {
                                        TextView tvTitle = anyLayer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = anyLayer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = anyLayer.getView(R.id.tv_dialog_permission_next);

                                        tvNext.setText("????????????");
                                        tvTitle.setText(AnyPermission.with(TestActivity.this).name(permission));
                                        tvDescription.setText("?????????\"" + AnyPermission.with(TestActivity.this).name(permission) + "\"???????????????");
                                    }
                                })
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();
                    }
                })
                .onGoSetting(new RequestInterceptor<String>() {
                    @Override
                    public void intercept(@NonNull final String permission, @NonNull final Executor executor) {
                        AnyLayer.with(TestActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new AnyLayer.IDataBinder() {
                                    @Override
                                    public void bind(AnyLayer anyLayer) {
                                        TextView tvTitle = anyLayer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = anyLayer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = anyLayer.getView(R.id.tv_dialog_permission_next);

                                        tvNext.setText("?????????");
                                        tvTitle.setText(AnyPermission.with(TestActivity.this).name(permission));
                                        tvDescription.setText("????????????\"" + AnyPermission.with(TestActivity.this).name(permission) + "\"??????");
                                    }
                                })
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();
                    }
                })
                .request(new RequestListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void requestInstall() {
        AnyPermission.with(this).install(new File(TestActivity.this.getCacheDir(), "1.apk"))
                .onWithoutPermission(new RequestInterceptor<File>() {
                    @Override
                    public void intercept(@NonNull final File data, @NonNull final Executor executor) {
                        AnyLayer.with(TestActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new AnyLayer.IDataBinder() {
                                    @Override
                                    public void bind(AnyLayer anyLayer) {
                                        TextView tvTitle = anyLayer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = anyLayer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = anyLayer.getView(R.id.tv_dialog_permission_next);

                                        tvNext.setText("?????????");
                                        tvTitle.setText("????????????");
                                        tvDescription.setText("???????????????????????????????????????");
                                    }
                                })
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();
                    }
                })
                .request(new RequestListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void requestOverlay() {
        AnyPermission.with(this).overlay()
                .onWithoutPermission(new RequestInterceptor<Void>() {
                    @Override
                    public void intercept(@NonNull final Void data, @NonNull final Executor executor) {
                        AnyLayer.with(TestActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new AnyLayer.IDataBinder() {
                                    @Override
                                    public void bind(AnyLayer anyLayer) {
                                        TextView tvTitle = anyLayer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = anyLayer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = anyLayer.getView(R.id.tv_dialog_permission_next);

                                        tvNext.setText("?????????");
                                        tvTitle.setText("?????????");
                                        tvDescription.setText("????????????????????????????????????");
                                    }
                                })
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();
                    }
                })
                .request(new RequestListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void requestSetting() {
        AnyPermission.with(this).setting()
                .onWithoutPermission(new RequestInterceptor<Void>() {
                    @Override
                    public void intercept(@NonNull final Void data, @NonNull final Executor executor) {
                        AnyLayer.with(TestActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new AnyLayer.IDataBinder() {
                                    @Override
                                    public void bind(AnyLayer anyLayer) {
                                        TextView tvTitle = anyLayer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = anyLayer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = anyLayer.getView(R.id.tv_dialog_permission_next);

                                        tvNext.setText("?????????");
                                        tvTitle.setText("????????????");
                                        tvDescription.setText("???????????????????????????????????????");
                                    }
                                })
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();
                    }
                })
                .request(new RequestListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void requestNotificationShow() {
        AnyPermission.with(this).notificationShow()
                .onWithoutPermission(new RequestInterceptor<Void>() {
                    @Override
                    public void intercept(@NonNull final Void data, @NonNull final Executor executor) {
                        AnyLayer.with(TestActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new AnyLayer.IDataBinder() {
                                    @Override
                                    public void bind(AnyLayer anyLayer) {
                                        TextView tvTitle = anyLayer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = anyLayer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = anyLayer.getView(R.id.tv_dialog_permission_next);

                                        tvNext.setText("?????????");
                                        tvTitle.setText("????????????");
                                        tvDescription.setText("???????????????????????????????????????");
                                    }
                                })
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();
                    }
                })
                .request(new RequestListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void requestNotificationAccess() {
        AnyPermission.with(this).notificationAccess()
                .onWithoutPermission(new RequestInterceptor<Void>() {
                    @Override
                    public void intercept(@NonNull final Void data, @NonNull final Executor executor) {
                        AnyLayer.with(TestActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new AnyLayer.IDataBinder() {
                                    @Override
                                    public void bind(AnyLayer anyLayer) {
                                        TextView tvTitle = anyLayer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = anyLayer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = anyLayer.getView(R.id.tv_dialog_permission_next);

                                        tvNext.setText("?????????");
                                        tvTitle.setText("????????????");
                                        tvDescription.setText("???????????????????????????????????????");
                                    }
                                })
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new AnyLayer.OnLayerClickListener() {
                                    @Override
                                    public void onClick(AnyLayer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();
                    }
                })
                .request(new RequestListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(TestActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
