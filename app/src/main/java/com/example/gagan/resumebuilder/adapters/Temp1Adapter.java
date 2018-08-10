package com.example.gagan.resumebuilder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.resumebuilder.R;
import com.example.gagan.resumebuilder.decorations.DividerItemDecoration;
import com.example.gagan.resumebuilder.decorations.ItemOffsetDecoration;
import com.example.gagan.resumebuilder.decorations.SpacesItemDecoration;
import com.example.gagan.resumebuilder.decorations.VerticalSpaceItemDecoration;
import com.example.gagan.resumebuilder.fragments.Teamplate1Fragment;
import com.example.gagan.resumebuilder.interfaces.EditCallBack;
import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;
import com.example.gagan.resumebuilder.models.Header;
import com.example.gagan.resumebuilder.models.Information;
import com.example.gagan.resumebuilder.models.ItemList;
import com.example.gagan.resumebuilder.utils.Utils;
import com.example.gagan.resumebuilder.views.HelperLayout;

import java.util.List;

public class Temp1Adapter extends RecyclerView.Adapter<Temp1Adapter.Holder> {
    private final List<Temp1BaseModel> mData;
    private final Context mContext;
    private String addString = "+ Add";

    public Temp1Adapter(@NonNull List<Temp1BaseModel> list, Context context) {
        this.mData = list;
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setIsRecyclable(true);
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position) instanceof Header ? R.layout.header_layout :
                (mData.get(position) instanceof Information) ?
                        R.layout.info_layout : R.layout.recycler_view;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements EditCallBack {

        private List<String> listOfItem;

        public Holder(View itemView) {
            super(itemView);
        }

        public void bind(Temp1BaseModel temp1BaseModel) {
            if (temp1BaseModel instanceof Header) {
                ((TextView) itemView.findViewById(R.id.tv_header_info))
                        .setText(((Header) temp1BaseModel).getTitle());
            } else if (temp1BaseModel instanceof Information) {
                Information information = (Information) temp1BaseModel;
                TextView header = itemView.findViewById(R.id.tv_header);
                TextView tv_info = itemView.findViewById(R.id.info_item);
                ImageView imageView = itemView.findViewById(R.id.iv_icon);
                header.setText(information.getHeaders());
                tv_info.setText(information.getInfo());
                imageView.setBackgroundResource(information.getIv_icons());
            } else if (temp1BaseModel instanceof ItemList) {

                HelperLayout selected_filter_layout = ((HelperLayout) itemView);

                listOfItem = ((ItemList) temp1BaseModel).getData();
                selected_filter_layout.clearViews();
                while (listOfItem.contains(addString))
                    listOfItem.remove(addString);

                listOfItem.add(addString);
                for (String item : listOfItem)
                    selected_filter_layout.add(1,item);


                selected_filter_layout.setCloseClickedListener(new HelperLayout.CloseClickedHelper() {
                    @Override
                    public void CloseClicked(Object id, String title) {
                        if (title.equalsIgnoreCase(addString)) {
                            Utils.displayDialogue(Temp1Adapter.Holder.this);
                        } else {
                            listOfItem.remove(title);
                            notifyItemChanged(getAdapterPosition());
                        }
                    }
                });
            }
        }

        @Override
        public Context getContext() {
            return itemView.getContext();
        }

        @Override
        public void setTextFromDilogue(String text) {
            listOfItem.add(text);
            notifyItemChanged(getAdapterPosition());
        }

        @Override
        public String getHintText() {
            return "Enter your skill here";
        }
    }
}
