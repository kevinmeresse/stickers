package com.km.stickers.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.km.stickers.model.entity.Sticker;
import com.km.stickers.model.repository.StickerRepository;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    private StickerRepository repository;
    private MutableLiveData<Sticker[]> stickers;
    private Executor executor;

    public void init(Context context) {
        this.repository = StickerRepository.getInstance(context);
        this.stickers = new MutableLiveData<>();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<Sticker[]> getStickers() {
        return stickers;
    }

    public void search(String input) {
        Set<Sticker> stickerSet = repository.search(input);
        if (stickerSet == null) {
            stickers.setValue(null);
        } else {
            stickers.setValue(stickerSet.toArray(new Sticker[stickerSet.size()]));
        }
    }

    public void clearCaches(Context context) {
        // Clear Fresco caches
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();

        // Clear Glide caches
        Glide.get(context).clearMemory();
        executor.execute(() -> {
            Glide.get(context).clearDiskCache();
        });
    }
}
