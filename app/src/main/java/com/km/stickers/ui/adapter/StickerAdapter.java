package com.km.stickers.ui.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.km.stickers.R;
import com.km.stickers.model.entity.Sticker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {

    private Sticker[] stickers;

    public void setStickers(Sticker[] stickers) {
        this.stickers = stickers;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image) SimpleDraweeView image;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_sticker, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (stickers == null || position < 0 || position >= getItemCount()) {
            return;
        }

        final Sticker sticker = stickers[position];
        if (sticker != null) {
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(sticker.getThumbnailUrl()))
                    .setAutoPlayAnimations(true)
                    .build();
            holder.image.setController(controller);
        }
    }

    @Override
    public int getItemCount() {
        if (stickers != null) {
            return stickers.length;
        }
        return 0;
    }
}
