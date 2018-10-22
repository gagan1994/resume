package com.example.gagan.resumebuilder.fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gagan.resumebuilder.R;
import com.example.gagan.resumebuilder.adapters.Temp1Adapter;
import com.example.gagan.resumebuilder.adapters.Temp1DataAdapter;
import com.example.gagan.resumebuilder.adapters.Temp1SkillsAdapter;
import com.example.gagan.resumebuilder.decorations.SpacesItemDecoration;
import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;
import com.example.gagan.resumebuilder.pdfconverter.ViewToPdfConverter;
import com.example.gagan.resumebuilder.picassohelper.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Template1ZoomOutFragment extends Fragment {

    public static Template1ZoomOutFragment getInstance(List<Temp1BaseModel> modelList,
                                                       List<Temp1BaseModel> infoList,
                                                       List<Temp1BaseModel> infoList2,
                                                       List<Temp1BaseModel> infoList3) {
        Template1ZoomOutFragment fragment = new Template1ZoomOutFragment();
        fragment.modelList = modelList;
        fragment.infoList = infoList;
        fragment.infoList2 = infoList2;
        fragment.infoList3 = infoList3;
        return fragment;
    }

    private List<Temp1BaseModel> modelList;
    private List<Temp1BaseModel> infoList;
    private List<Temp1BaseModel> infoList2;
    private List<Temp1BaseModel> infoList3;
    private Temp1Adapter adapter;
    private File imageFile;
    private Temp1DataAdapter adapter2;
    private Temp1DataAdapter adapter1;
    private Temp1SkillsAdapter adapter3;
    private View mView;
    ImageView profile;
    RecyclerView rv_user_details, rv_data, rv_data2, rv_data3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_template1_zoom_out, container, false);
        rv_user_details = view.findViewById(R.id.rv_user_details);
        profile = view.findViewById(R.id.iv_profile);
        rv_data = view.findViewById(R.id.rv_data);
        rv_data2 = view.findViewById(R.id.rv_data2);
        rv_data3 = view.findViewById(R.id.rv_data3);
        this.mView = view.findViewById(R.id.view_complete);

        Picasso.get()
                .load("https://2.bp.blogspot.com/-8ytYF7cfPkQ/WkPe1-rtrcI/AAAAAAAAGqU/FGfTDVgkcIwmOTtjLka51vineFBExJuSACLcBGAs/s320/31.jpg")
                .transform(new CircleTransform()).into(profile);
        initRecyclers();
        return view;
    }

    private void initRecyclers() {
        adapter = new Temp1Adapter(modelList, getContext());
        rv_user_details.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_user_details.addItemDecoration(new SpacesItemDecoration(10));
        rv_user_details.setAdapter(adapter);


        adapter1 = new Temp1DataAdapter(infoList, false);
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_data.addItemDecoration(new SpacesItemDecoration(10));
        rv_data.setAdapter(adapter1);


        adapter2 = new Temp1DataAdapter(infoList2, false);
        rv_data2.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_data2.addItemDecoration(new SpacesItemDecoration(10));
        rv_data2.setAdapter(adapter2);

        adapter3 = new Temp1SkillsAdapter(infoList3);
        rv_data3.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_data3.setAdapter(adapter3);
    }


    public void generatePDF() {
        ViewToPdfConverter viewToPdfConverter = new ViewToPdfConverter
                .ViewToPdfBuilder().setPathToStore("Resume")
                .setFileNameWithoutExt("resume").build();

        viewToPdfConverter.generatePdf(getView());
    }
}
