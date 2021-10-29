package com.example.meowsic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meowsic.R;
import com.example.meowsic.bean.MusicBean;

import java.util.List;

/**
 * @author : 12453
 * @since : 2021/3/8
 * 作用:
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private List<MusicBean> beans;
    private Context context;

    public MusicAdapter(List<MusicBean> beans, Context context) {
        this.beans = beans;
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_list, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        MusicBean bean = beans.get(position);
        holder.idTv.setText(bean.getId());
        holder.titleTv.setText(bean.getTitle());
        holder.singerMoreTv.setText(bean.getArtist()+" - "+bean.getAlbum());
    }

    @Override
    public int getItemCount() {
        if (beans != null && beans.size() > 0) return beans.size();
        return 0;
    }

    class MusicViewHolder extends RecyclerView.ViewHolder{
        private TextView idTv,titleTv,singerMoreTv;
        private MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            idTv = itemView.findViewById(R.id.item_local_music_number);
            titleTv = itemView.findViewById(R.id.item_local_music_song);
            singerMoreTv = itemView.findViewById(R.id.item_local_music_singer_and_album);
        }
    }
}
