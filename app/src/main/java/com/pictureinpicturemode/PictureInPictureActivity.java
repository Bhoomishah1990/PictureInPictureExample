package com.pictureinpicturemode;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PictureInPictureActivity extends AppCompatActivity {

    private VideoView vv;

    private Button pip;
    private Uri videoUri;
    private final PictureInPictureParams.Builder pictureInPictureParamsBuilder =
            new PictureInPictureParams.Builder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videolayout);


        vv = findViewById(R.id.videoView);
        setVideoView();

        findViewById(R.id.play).setOnClickListener(onClickListener);
        findViewById(R.id.pause).setOnClickListener(onClickListener);
        pip = findViewById(R.id.pip);
        pip.setOnClickListener(onClickListener);
    }

    private final View.OnClickListener onClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.play: {
                            vv.start();
                            break;
                        }
                        case R.id.pause: {
                            vv.stopPlayback();
                            break;
                        }
                        case R.id.pip:
                            pictureInPictureMode();
                            break;
                    }
                }
            };

    private void setVideoView() {

        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lionsample));


    }

    private void pictureInPictureMode() {
        Rational aspectRatio = new Rational(vv.getWidth(), vv.getHeight());
        pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
        enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
    }

    @Override
    public void onUserLeaveHint() {
        if (!isInPictureInPictureMode()) {
            Rational aspectRatio = new Rational(vv.getWidth(), vv.getHeight());
            pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
            enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode,
                                              Configuration newConfig) {
        if (isInPictureInPictureMode) {
            pip.setVisibility(View.GONE);
        } else {
            pip.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNewIntent(Intent i) {
        setVideoView();
    }

    @Override
    public void onStop() {
        if (vv.isPlaying()) {
            vv.stopPlayback();
        }
        super.onStop();
    }
}