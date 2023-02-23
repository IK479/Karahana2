package com.example.karahana.Adapters;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karahana.Fragments.PlaylistFragment;
import com.example.karahana.R;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.Models.SongCard;
import com.example.karahana.managers.Models.appManager;
import com.example.karahana.managers.PartyManager;

import java.io.IOException;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MultiViewHolder> {

    private PlaylistFragment context;
    private ArrayList<SongCard> playlist;
    private PartyCard partyCard = PartyManager.getInstance().getNewParty();

    public SongAdapter(PlaylistFragment context, ArrayList<SongCard> playlist) {
        this.context = context;
        this.playlist = playlist;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPlaylist(ArrayList<SongCard> playlist) {
        this.playlist = playlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MultiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_card, parent, false);
        return new MultiViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }


    @Override
    public void onBindViewHolder(@NonNull SongAdapter.MultiViewHolder holder, int position) {
        holder.bind(playlist.get(position));
        holder.playSong(playlist.get(position));
        holder.pauseSong();
        SongCard songCard = playlist.get(position);
        holder.artistImg.setImageResource(songCard.getArtistImg());
        holder.artistName.setText(songCard.getArtist());
        holder.songName.setText(songCard.getSongName());

        holder.alpha.setVisibility(playlist.get(position).isChecked() ? View.VISIBLE : View.INVISIBLE);
    }

    public static class MultiViewHolder extends RecyclerView.ViewHolder {
        appManager appManager = new appManager();
        boolean play = false;
        private final String CAN_NOT_PLAY_THE_SONG = "Another song has already playing";
        private ImageView artistImg;
        private ImageView alpha;
        private TextView artistName;
        private TextView songName;
        private Button playBtn;
        private Button pauseBtn;


        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            artistImg = itemView.findViewById(R.id.sCard_songViewImg);
            alpha = itemView.findViewById(R.id.sCard_alpa);
            artistName = itemView.findViewById(R.id.sCard_artistName);
            songName = itemView.findViewById(R.id.sCard_songName);
            playBtn = itemView.findViewById(R.id.sCard_BTN_play);
            pauseBtn = itemView.findViewById(R.id.sCard_BTN_pause);
        }

        private void bind(final SongCard song) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    song.setChecked(!song.isChecked());
                    alpha.setVisibility(song.isChecked() ? View.VISIBLE : View.INVISIBLE);
                }
            });
        }


        public void playSong(final SongCard song) {
            playBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appManager.Player(song);
                    playBtn.setVisibility(View.INVISIBLE);
                    pauseBtn.setVisibility(View.VISIBLE);

                }
            });
        }

        public void pauseSong() {
            pauseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appManager.stop();
                    playBtn.setVisibility(View.VISIBLE);
                    pauseBtn.setVisibility(View.INVISIBLE);

                }
            });
        }
    }

    public ArrayList<SongCard> getSelectedSongs() {
        ArrayList<SongCard> selected = new ArrayList<>();
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).isChecked()) {
                selected.add(playlist.get(i));
            }
        }
        return selected;
    }
}

