package com.zq.frescodemo;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {



    private SimpleDraweeView simpleDraweeView1, simpleDraweeView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //方式一
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDraweeView sdv = (SimpleDraweeView) findViewById(R.id.id_main_sdv_sdv);
                Uri uri = Uri.parse("http://image5.tuku.cn/pic/wallpaper/fengjing/menghuandaziranmeijingbizhi/009.jpg");
                sdv.setImageURI(uri);
            }
        });

        //方式二
        SimpleDraweeView sdv2 = (SimpleDraweeView) findViewById(R.id.id_main_sdv_sdv2);
        sdv2.setImageURI("http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg");
        showImg1();
        showImg2();
    }

    //方式三
    private void showImg1() {
        simpleDraweeView1 = (SimpleDraweeView) findViewById(R.id.user_avator);
        simpleDraweeView1.setController(Fresco.newDraweeControllerBuilder()
                .setImageRequest(
                        ImageRequestBuilder.newBuilderWithSource(
                                Uri.parse("http://avatar.csdn.net/8/6/0/1_dickyqie.jpg"))
                                .setProgressiveRenderingEnabled(true)
                                .build())
                .setOldController(simpleDraweeView1.getController())
                .build());
    }

    /***
     * 加载   .gif 图片
     */
    private void showImg2() {
        Uri uri = Uri.parse("http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif");
        simpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.user_avator2);
        DraweeController draweeController1 = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true).build();
        simpleDraweeView2.setController(draweeController1);
        simpleDraweeView2.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                simpleDraweeView2.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                return true;
            case MotionEvent.ACTION_UP:
                simpleDraweeView2.clearColorFilter();
                return true;
        }
        return super.onTouchEvent(event);
    }


    /****
     * 设置相关属性
     */
    private void show() {
        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setFadeDuration(3000)
                .setPlaceholderImage(R.mipmap.ic_launcher)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setProgressBarImage(new ProgressBarDrawable()) // 显示进度条（Fresco自带的进度条）
                .build();
        // 设置图片圆角
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
        roundingParams.setCornersRadius(200);
        hierarchy.setRoundingParams(roundingParams);
        // 为SimpleDraweeView设置属性
        //sdv.setHierarchy(hierarchy);
    }



}
