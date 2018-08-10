package com.example.gagan.resumebuilder.adapters;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.gagan.resumebuilder.R;
import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;
import com.example.gagan.resumebuilder.models.Header;
import com.example.gagan.resumebuilder.models.SkillsInfo;

import java.util.List;

public class Temp1SkillsAdapter extends RecyclerView.Adapter<Temp1SkillsAdapter.VH> {
    private final List<Temp1BaseModel> mDataset;

    public Temp1SkillsAdapter(List<Temp1BaseModel> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skill_row, null, false);
        return new Temp1SkillsAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.data_info;
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
        TextView textView;
        SeekBar seekBar;
        private SkillsInfo data;

        public VH(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_txt);
            seekBar = itemView.findViewById(R.id.seekBar);
            textView.setOnClickListener(this);
            seekBar.setOnSeekBarChangeListener(this);
        }

        public void bind(Temp1BaseModel temp1BaseModel) {
            this.data = (SkillsInfo) temp1BaseModel;
            textView.setText(data.getTitle());
            seekBar.setProgress(data.getPos());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                seekBar.getThumb().mutate().setAlpha(0);
            }
        }

        @Override
        public void onClick(View v) {
            data.setTitle(textView.getText().toString());
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            data.setPos(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
