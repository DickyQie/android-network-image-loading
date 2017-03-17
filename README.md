# Android之Fresco（facebook的强大Android图片加载的框架） 
 <div class="blog-abstract">
                    摘要: Fresco是Facebook最新推出的一款用于Android应用中展示图片的强大图片库，可以从网络、本地存储和本地资源中加载图片。其中的Drawees可以显示占位符，直到图片加载完成。而当图片从屏幕上消失时，会自动释放内存。
                </div>
 <p>Fresco是Facebook开源Android平台上一个强大的图片加载库，也是迄今为止Android平台上最强大的图片加载库。</p> 
<p>Fresco 是一个强大的图片加载组件。使用它之后，你不需要再去关心图片的加载和显示这些繁琐的事情！ 支持 Android 2.3 及以后的版本。</p> 
<p>Fresco源码：https://github.com/facebook/fresco</p> 
<p>Fresco官方文档（中文）：https://www.fresco-cn.org/</p> 
<p>Fresco源码在线查看：http://frescolib.org/javadoc/reference/packages.html</p> 
<p>目前最新版本是： <a href="http://frescolib.org/javadoc/reference/packages.html" rel="nofollow">Fresco 1.1.0</a></p> 
<p>gradle引入（当然你也可以引入 高版本）</p> 
<pre><code class="language-java">   compile 'com.facebook.fresco:fresco:0.12.0'
// 支持 GIF 动图，需要添加
   compile 'com.facebook.fresco:animated-gif:0.12.0'</code></pre> 
<p>运行效果图：（注：此案例只完成了图中相应功能，Fresco更多功能去官方查看，讲解详细）</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 　　<img alt="" src="http://images2015.cnblogs.com/blog/1041439/201703/1041439-20170317165346823-1762261669.gif"></p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> 
<span id="OSC_h2_1"></span>
<h2>特性</h2> 
<p>1：内存管理</p> 
<p>解压后的图片，即Android中的Bitmap，占用大量的内存。大的内存占用势必引发更加频繁的GC。在5.0以下，GC将会显著地引发界面卡顿。</p> 
<p>在5.0以下系统，Fresco将图片放到一个特别的内存区域。当然，在图片不显示的时候，占用的内存会自动被释放。这会使得APP更加流畅，减少因图片内存占用而引发的OOM。</p> 
<p>Fresco 在低端机器上表现一样出色，你再也不用因图片内存占用而思前想后。</p> 
<p>2：图片加载</p> 
<p>Fresco的<strong>Image Pipeline</strong>允许你用很多种方式来自定义图片加载过程，比如：</p> 
<ul> 
 <li>为同一个图片指定不同的远程路径，或者使用已经存在本地缓存中的图片</li> 
 <li>先显示一个低清晰度的图片，等高清图下载完之后再显示高清图</li> 
 <li>加载完成回调通知</li> 
 <li>对于本地图，如有EXIF缩略图，在大图加载完成之前，可先显示缩略图</li> 
 <li>缩放或者旋转图片</li> 
 <li>对已下载的图片再次处理</li> 
 <li>支持WebP解码，即使在早先对WebP支持不完善的Android系统上也能正常使用！</li> 
</ul> 
<p>3：图片绘制</p> 
<p>Fresco 的 Drawees 设计，带来一些有用的特性：</p> 
<ul> 
 <li>自定义居中焦点</li> 
 <li>圆角图，当然圆圈也行</li> 
 <li>下载失败之后，点击重现下载</li> 
 <li>自定义占位图，自定义overlay, 或者进度条</li> 
 <li>指定用户按压时的overlay</li> 
</ul> 
<p>4：图片的渐进式呈现</p> 
<p>渐进式的JPEG图片格式已经流行数年了，渐进式图片格式先呈现大致的图片轮廓，然后随着图片下载的继续，呈现逐渐清晰的图片，这对于移动设备，尤其是慢网络有极大的利好，可带来更好的用户体验。</p> 
<p>Android 本身的图片库不支持此格式，但是Fresco支持。使用时，和往常一样，仅仅需要提供一个图片的URI即可，剩下的事情，Fresco会处理</p> 
<p>5：动图加载</p> 
<p>加载Gif图和WebP动图在任何一个Android开发者眼里看来都是一件非常头疼的事情。每一帧都是一张很大的Bitmap，每一个动画都有很多帧。Fresco让你没有这些烦恼，它处理好每一帧并管理好你的内存。</p> 
<p>&nbsp;</p> 
<p>代码：（属性可根据需要设置&nbsp; 注：<span style="color:#800000">android:layout_height="200dp" 的属性值不能为：wrap_content</span>）</p> 
<pre><code class="language-html"> &lt;!--  fresco:actualImageScaleType：实际加载的图片的伸缩样式
        fresco:backgroundImage：底层图片资源
        fresco:fadeDuration：进度条和占位符图片逐渐消失、加载的图片逐渐显示的时间间隔
        fresco:failureImage：加载失败时显示的图片资源
        fresco:failureImageScaleType：加载失败时加载的图片的伸缩样式
        fresco:overlayImage：在显示的图片表层覆盖一张图片的图片资源
        fresco:placeholderImage：占位符图片资源
        fresco:placeholderImageScaleType：占位符图片的伸缩样式
        fresco:progressBarAutoRotateInterval：进度条图片转动周期
        fresco:progressBarImage：进度条图片资源
        fresco:progressBarImageScaleType：进度条图片的伸缩样式
        fresco:retryImage：提示重新加载的图片资源
        fresco:retryImageScaleType：提示重新加载的图片的伸缩样式
        fresco:roundAsCircle：将图片剪切成圆形
        fresco:viewAspectRatio：图片宽高比--&gt;

    &lt;com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/id_main_sdv_sdv"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        fresco:actualImageScaleType="focusCrop"
        fresco:fadeDuration="3000"
        fresco:failureImage="@mipmap/ic_launcher"
        fresco:failureImageScaleType="centerInside"
        fresco:placeholderImage="@mipmap/ic_launcher"
        fresco:placeholderImageScaleType="fitCenter"
        fresco:progressBarAutoRotateInterval="1000"
        fresco:progressBarImage="@drawable/aa"
        fresco:progressBarImageScaleType="centerInside"
        fresco:retryImage="@mipmap/ic_launcher"
        fresco:retryImageScaleType="centerCrop"
        fresco:roundAsCircle="false"
        fresco:viewAspectRatio="1.6" /&gt;</code></pre> 
<p>加载方式1：</p> 
<pre><code class="language-java">  SimpleDraweeView sdv = (SimpleDraweeView) findViewById(R.id.id_main_sdv_sdv);
  Uri uri = Uri.parse("http://image5.tuku.cn/pic/wallpaper/fengjing/menghuandaziranmeijingbizhi/009.jpg");
                sdv.setImageURI(uri);</code></pre> 
<p>加载方式2：</p> 
<pre><code class="language-java"> SimpleDraweeView sdv2 = (SimpleDraweeView) findViewById(R.id.id_main_sdv_sdv2);
 sdv2.setImageURI("http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg");</code></pre> 
<p>加载方式3：</p> 
<pre><code class="language-java"> simpleDraweeView1 = (SimpleDraweeView) findViewById(R.id.user_avator);
 simpleDraweeView1.setController(Fresco.newDraweeControllerBuilder()
                 .setImageRequest(
                  ImageRequestBuilder.newBuilderWithSource(
                  Uri.parse("http://avatar.csdn.net/8/6/0/1_dickyqie.jpg"))
                  .setProgressiveRenderingEnabled(true)
                  .build())
                 .setOldController(simpleDraweeView1.getController())
                 .build());</code></pre> 
<p>加载方式<span style="color:#B22222">gif图片</span>：</p> 
<pre><code class="language-java"> Uri uri = Uri.parse("http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif");
        simpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.user_avator2);
        DraweeController draweeController1 = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true).build();
        simpleDraweeView2.setController(draweeController1);
        simpleDraweeView2.setOnTouchListener(this);</code></pre> 
<p>代码设置属性：</p> 
<pre><code class="language-java">  // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
 SimpleDraweeView sdv = (SimpleDraweeView) findViewById(R.id.id_main_sdv_sdv);        
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
 sdv.setHierarchy(hierarchy);</code></pre> 
<p>&nbsp;</p> 
<p>&nbsp;</p>
