# Android之Glide（非常好用的图片加载框架）
 <p><span style="color:#000000">谷歌开发者论坛上，谷歌为我们介绍了一个名叫&nbsp;Glide&nbsp;的图片加载库，作者是bumptech。 </span></p> 
<p>Glide<span style="color:#000000"><span style="background-color:rgb(255, 255, 255)">是一种快速、高效的开源媒体管理和Android的包裹mediadecoding图像加载框架，内存和磁盘缓存和资源集中到一个简单的和易于使用的界面。</span></span></p> 
<p><span style="color:#000000"><span style="background-color:rgb(255, 255, 255)"><img alt="" height="240" src="https://static.oschina.net/uploads/space/2017/0325/085809_5wvz_2945455.png" width="800"></span></span></p> 
<p><span style="color:#000000">&nbsp;Glide是一个开源的图片加载和缓存处理的第三方框架。使用Android的Glide和Android的Picasso库的方式几乎一模一样。</span></p> 
<p><span style="color:#000000">Glide</span>源码：https://github.com/bumptech/glide</p> 
<p><span style="color:#000000">案例中包含了以下功能：</span></p> 
<ul> 
 <li>加载drawable（mipmap）下的图片</li> 
 <li>加载SD卡图片</li> 
 <li>网络加载图片（可设置加载初始化和加载错误时的图片）</li> 
 <li>自定义图片大小</li> 
 <li>圆形图片（自定义弧度大小，自定义圆形图片）</li> 
 <li>圆形图片裁剪</li> 
 <li>椭圆形图片</li> 
 <li>可设置优先级记载</li> 
 <li>图片缩略为原图的10分之几</li> 
 <li>照片复古</li> 
 <li>加载成Drawable类型在显示</li> 
 <li>gif图片加载</li> 
 <li>图片填充</li> 
 <li>.................</li> 
</ul> 
<p>先看效果图：</p> 
<p><img alt="" height="557" src="https://static.oschina.net/uploads/space/2017/0325/091004_t9XZ_2945455.gif" width="507"></p> 
<p>1：项目本地</p> 
<pre><code class="language-java">    //加载本地图片
     int resourceId = R.mipmap.ic_launcher;
     Glide.with(this).load(resourceId).into(iv1);</code></pre> 
<p>2.SD卡</p> 
<pre><code class="language-java"> //加载SD卡图片iv
  File file = new File(path2);
  Glide.with(this).load(file).into(iv2);
  // Glide.with(this).load(path2).into(iv2);</code></pre> 
<p>3.网络加载</p> 
<pre><code class="language-java">//网络基本图片加载
Glide.with(this).load("http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg").into(iv3);</code></pre> 
<p>4.图片自定义大小和圆形图片</p> 
<pre><code class="language-java">     //图片自定义显示大小
     //Glide.with(this).load(Images.imageThumbUrls[2]).override(88, 88).into(iv41);
     //使用圆形变换，还可以使用其他的变换
      Glide.with(this).load(Images.imageThumbUrls[2])
                      .placeholder(R.mipmap.ic_launcher)
                      .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                      .into(iv41);</code></pre> 
<p>5.各种不同的方式实现圆形，椭圆图片</p> 
<pre><code class="language-java"> //定义 GlideCircleTransform.java     GlideRoundTransform.java    RoundedCornersTransformation.java 
 //常规
 //Glide.with(this).load(Images.imageThumbUrls[3]).asBitmap().transform(new        GlideRoundTransform(MainActivity.this)).into(iv4);
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
                        .into(iv4);</code></pre> 
<p>6.初始化默认图片和加载失败时的图片，图片加载优先级</p> 
<pre><code class="language-java">//初始化图片和加载错误时的图片
    Glide.with(this).load(Images.imageThumbUrls[2])
         .placeholder(R.mipmap.ic_launcher)
         .priority(Priority.IMMEDIATE)//指定加载的优先级，优先级越高越优先加载
         .error(R.mipmap.ic_launcher).centerCrop().into(iv5);</code></pre> 
<p>7.缩略图</p> 
<pre><code class="language-java"> //用原图的1/10作为缩略图
  Glide.with(this).load(Images.imageThumbUrls[3])
       .thumbnail(0.1f)
       .into(iv6);
  //用其它图片作为缩略图
    /*  DrawableRequestBuilder&lt;Integer&gt;   thumbnailRequest=Glide.with(this).load(R.mipmap.ic_launcher);
                         Glide.with(this)
                        .load(Images.imageThumbUrls[1])
                        .thumbnail(thumbnailRequest)
                        .into(iv6);*/</code></pre> 
<p>8.复古照片</p> 
<pre><code class="language-java">//灰度处理(做成老式照片  灰色的)
         Glide.with(this).load(Images.imageThumbUrls[2]).bitmapTransform(new GrayscaleTransformation(this)).into(imgng);</code></pre> 
<p>9.Drawable</p> 
<pre><code class="language-java">//加载成Drawable的类型来加载
   Glide.with(this)
         .load(Images.imageThumbUrls[3])
         .placeholder(R.mipmap.ic_launcher)
         .into(new SimpleTarget&lt;GlideDrawable&gt;() {
              @Override
               public void onResourceReady(GlideDrawable resource, GlideAnimation&lt;? super  GlideDrawable&gt; glideAnimation) {
                                imgn.setImageDrawable(resource);
                            }
                        });</code></pre> 
<p>10.&nbsp;&nbsp;&nbsp;&nbsp; .gif加载</p> 
<pre><code class="language-java">Glide.with(this).load("http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif").asGif().into(img8);</code></pre> 
<p>11. 图片填充</p> 
<pre><code class="language-java">  /**
       使用centerCrop是利用图片图填充ImageView设置的大小，如果ImageView的Height是match_parent则图片就会被拉伸填充
      */
                Glide.with(this).load(Images.imageThumbUrls[5]).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).centerCrop().into(iv7);</code></pre> 
<p>其他属性：</p> 
<pre><code class="language-java">//                 thumbnail(float sizeMultiplier). 请求给定系数的缩略图。如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示。系数sizeMultiplier必须在(0,1)之间，可以递归调用该方法。
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
//                 asGif().把资源作为GifDrawable对待。如果资源不是gif动画将会失败，会回调.error()。</code></pre> 
<p>&nbsp;</p> 
