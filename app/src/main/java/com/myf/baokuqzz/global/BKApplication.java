package com.myf.baokuqzz.global;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by myf on 2017/8/17.
 */

public class BKApplication extends Application {
    private static BKApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        SDKInitializer.initialize(getApplicationContext());
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(getApplicationContext().getCacheDir())//缓存图片基路径
                //.setMaxCacheSize(1000 * ByteConstants.MB)//默认缓存的最大大小100M。
                //.setMaxCacheSizeOnLowDiskSpace(60 * ByteConstants.MB)//缓存的最大大小,使用设备时低磁盘空间60M。
                //.setMaxCacheSizeOnVeryLowDiskSpace(20 * ByteConstants.MB)//缓存的最大大小,当设备极低磁盘空间
                .setDiskTrimmableRegistry(NoOpDiskTrimmableRegistry.getInstance())
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
//                .setBitmapMemoryCacheParamsSupplier(bitmapCacheParamsSupplier)
//                .setCacheKeyFactory(cacheKeyFactory)
//                .setEncodedMemoryCacheParamsSupplier(encodedCacheParamsSupplier)
//                .setExecutorSupplier(executorSupplier)
//                .setImageCacheStatsTracker(imageCacheStatsTracker)
                .setMainDiskCacheConfig(diskCacheConfig)
//                .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
//                .setNetworkFetchProducer(networkFetchProducer)
//                .setPoolFactory(poolFactory)
//                .setProgressiveJpegConfig(progressiveJpegConfig)
//                .setRequestListeners(requestListeners)
//                .setSmallImageDiskCacheConfig(smallImageDiskCacheConfig)
                .build();
        Fresco.initialize(this, config);
    }

    public static Application getInstance(){
        return application;
    }
}
