package com.bignerdranch.android.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.beatbox.databinding.FragmentBeatBoxBinding;
import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding;

import java.util.List;


public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox = new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding fragmentBeatBoxBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_beat_box, container, false);

        fragmentBeatBoxBinding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),
                                                                                        3));
        fragmentBeatBoxBinding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSoundList()));

        return fragmentBeatBoxBinding.getRoot();
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding mListItemSoundBinding;

        private SoundHolder(ListItemSoundBinding listItemSoundBinding) {
            super(listItemSoundBinding.getRoot());
            mListItemSoundBinding = listItemSoundBinding;
            mListItemSoundBinding.setViewModel(new SoundViewModel(mBeatBox));
        }

        public void bind(Sound sound) {
            mListItemSoundBinding.getViewModel().setSound(sound);
            mListItemSoundBinding.executePendingBindings();
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding listItemSoundBinding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(listItemSoundBinding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
