package com.zq.glidedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String path2 = "/storage/emulated/legacy/Tencent/MobileQQ/qbiz/html5/165/s2.url.cn/"
            + "qqweb/m/relativegroup/css/image/a.png";
    private ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,img8;
    private ImageView iv41,imgn,imgng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView()
    {
        iv1= (ImageView) findViewById(R.id.img1);
        iv2= (ImageView) findViewById(R.id.img2);
        iv3= (ImageView) findViewById(R.id.img3);
        iv4= (ImageView) findViewById(R.id.img4);
        iv41= (ImageView) findViewById(R.id.img41);
        iv5= (ImageView) findViewById(R.id.img5);
        iv6= (ImageView) findViewById(R.id.img6);
        iv7= (ImageView) findViewById(R.id.img9);
        img8= (ImageView) findViewById(R.id.img8);
        imgn=(ImageView) findViewById(R.id.imgn);
        imgng=(ImageView) findViewById(R.id.imgng);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn41).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btnn).setOnClickListener(this);
        findViewById(R.id.btnng).setOnClickListener(this);
    }

    /***
     *
     * Gilde属性
     * 是否内存缓存
     * .skipMemoryCache(boolean);
     *  // 必须在UI线程中调用
     *  Glide.get(context).clearMemory();
     */

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn1: //加载本地图片
                int resourceId = R.mipmap.ic_launcher;
                Glide.with(this).load(resourceId).into(iv1);
                break;
            case R.id.btn2://加载SD卡图片iv
                File file = new File(path2);
                Glide.with(this).load(file).into(iv2);
               // Glide.with(this).load(path2).into(iv2);
                break;
            case R.id.btn3://网络基本图片加载
                Glide.with(this).load("http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg").into(iv3);
                break;
            case R.id.btn41:
                //图片自定义显示大小
                //Glide.with(this).load(Images.imageThumbUrls[2]).override(88, 88).into(iv41);
                //使用圆形变换，还可以使用其他的变换
                Glide.with(this)
                        .load(Images.imageThumbUrls[2])
                        .placeholder(R.mipmap.ic_launcher)
                        .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                        .into(iv41);
                break;
            case R.id.btn4:
                //定义 GlideCircleTransform     GlideRoundTransform   RoundedCornersTransformation
                //常规
                //Glide.with(this).load(Images.imageThumbUrls[3]).asBitmap().transform(new GlideRoundTransform(MainActivity.this)).into(iv4);
                //圆角弧度自定义大小
                // Glide.with(this).load(Images.imageThumbUrls[3]).transform(new GlideRoundTransform(MainActivity.this, 10)).into(iv4);
               //自定义圆形图片
               // Glide.with(this).load(Images.imageThumbUrls[3]).transform(new GlideCircleTransform(MainActivity.this)).into(iv4);

                //Glide.with(this).load(Images.imageThumbUrls[3]).bitmapTransform(new com.zq.glidedemo.RoundedCornersTransformation(this,10)).into(iv4);

                  //圆形裁剪
                //Glide.with(this).load(Images.imageThumbUrls[3]).bitmapTransform(new CropCircleTransformation(this)).into(iv4);

                /**
                 * 椭圆形
                 */
                Glide.with(this)
                        .load(Images.imageThumbUrls[2])
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存图片
                        .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                        .into(iv4);
                break;
            case R.id.btn5:
                //初始化图片和加载错误时的图片
                Glide.with(this).load(Images.imageThumbUrls[2])
                        .placeholder(R.mipmap.ic_launcher)
                        .priority(Priority.IMMEDIATE)//指定加载的优先级，优先级越高越优先加载
                        .error(R.mipmap.ic_launcher).centerCrop().into(iv5);
                break;
            case R.id.btn6:
                //用原图的1/10作为缩略图
                     Glide
                        .with(this)
                        .load(Images.imageThumbUrls[3])
                        .thumbnail(0.1f)
                        .into(iv6);
                //用其它图片作为缩略图
              /*  DrawableRequestBuilder<Integer> thumbnailRequest = Glide
                        .with(this)
                        .load(R.mipmap.ic_launcher);
                Glide.with(this)
                        .load(Images.imageThumbUrls[1])
                        .thumbnail(thumbnailRequest)
                        .into(iv6);*/
                break;
            case R.id.btnng:
                //灰度处理(做成老式照片  灰色的)
                 Glide.with(this).load(Images.imageThumbUrls[2]).bitmapTransform(new GrayscaleTransformation(this)).into(imgng);
                break;
            case R.id.btnn:
                //加载成Drawable的类型来加载
                Glide.with(this)
                        .load(Images.imageThumbUrls[3])
                        .placeholder(R.mipmap.ic_launcher)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                imgn.setImageDrawable(resource);
                            }
                        });
                break;
            case R.id.btn7:
                Glide.with(this).load("http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif").asGif().into(img8);
                break;
            case R.id.btn8:
                /**
                 使用centerCrop是利用图片图填充ImageView设置的大小，如果ImageView的Height是match_parent则图片就会被拉伸填充
                 */
                Glide.with(this).load(Images.imageThumbUrls[5]).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).centerCrop().into(iv7);
                /**
                 使用fitCenter即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围,该图像将会完全显示，但可能不会填满整个ImageView。
                 */
              //  Glide.with(context).load(imageUrl).fitCenter().into(imageView);


                //---------------------------------------------------相关属性---------------------------------------------------------------------


//
//                 thumbnail(float sizeMultiplier). 请求给定系数的缩略图。如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示。系数sizeMultiplier必须在(0,1)之间，可以递归调用该方法。
//                 sizeMultiplier(float sizeMultiplier). 在加载资源之前给Target大小设置系数。
//                 diskCacheStrategy(DiskCacheStrategy strategy).设置缓存策略。DiskCacheStrategy.SOURCE：缓存原始数据，DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据，DiskCacheStrategy.NONE：什么都不缓存，DiskCacheStrategy.ALL：缓存SOURC和RESULT。默认采用DiskCacheStrategy.RESULT策略，对于download only操作要使用DiskCacheStrategy.SOURCE。
//                 priority(Priority priority). 指定加载的优先级，优先级越高越优先加载，但不保证所有图片都按序加载。枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW。默认为Priority.NORMAL。
//                 dontAnimate(). 移除所有的动画。
//                 animate(int animationId). 在异步加载资源完成时会执行该动画。
//                 animate(ViewPropertyAnimation.Animator animator). 在异步加载资源完成时会执行该动画。
//                 placeholder(int resourceId). 设置资源加载过程中的占位Drawable。
//                 placeholder(Drawable drawable). 设置资源加载过程中的占位Drawable。
//                 fallback(int resourceId). 设置model为空时要显示的Drawable。如果没设置fallback，model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
//                 fallback(Drawable drawable).设置model为空时显示的Drawable。
//                 error(int resourceId).设置load失败时显示的Drawable。
//                 error(Drawable drawable).设置load失败时显示的Drawable。
//
//                 skipMemoryCache(boolean skip). 设置是否跳过内存缓存，但不保证一定不被缓存（比如请求已经在加载资源且没设置跳过内存缓存，这个资源就会被缓存在内存中）。
//
//                 override(int width, int height). 重新设置Target的宽高值（单位为pixel）。
//                 into(Y target).设置资源将被加载到的Target。
//                 into(ImageView view). 设置资源将被加载到的ImageView。取消该ImageView之前所有的加载并释放资源。
//                 into(int width, int height). 后台线程加载时要加载资源的宽高值（单位为pixel）。
//                 preload(int width, int height). 预加载resource到缓存中（单位为pixel）。
//                 asBitmap(). 无论资源是不是gif动画，都作为Bitmap对待。如果是gif动画会停在第一帧。
//                 asGif().把资源作为GifDrawable对待。如果资源不是gif动画将会失败，会回调.error()。
                break;
        }
    }
}
