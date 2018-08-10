package com.example.gagan.resumebuilder.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.resumebuilder.R;
import com.example.gagan.resumebuilder.adapters.Temp1Adapter;
import com.example.gagan.resumebuilder.adapters.Temp1DataAdapter;
import com.example.gagan.resumebuilder.adapters.Temp1SkillsAdapter;
import com.example.gagan.resumebuilder.decorations.DividerItemDecoration;
import com.example.gagan.resumebuilder.decorations.SpacesItemDecoration;
import com.example.gagan.resumebuilder.interfaces.Temp1BaseModel;
import com.example.gagan.resumebuilder.models.DataInfo;
import com.example.gagan.resumebuilder.models.Header;
import com.example.gagan.resumebuilder.models.Information;
import com.example.gagan.resumebuilder.models.ItemList;
import com.example.gagan.resumebuilder.models.SkillsInfo;
import com.example.gagan.resumebuilder.picassohelper.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

public class Teamplate1Fragment extends Fragment implements View.OnClickListener {

    private static final int PICTURE_STATUS = 12;
    RecyclerView rv_user_details, rv_data, rv_data2, rv_data3;
    View layout;
    ImageView profile, iv_edit;
    private final List<Temp1BaseModel> modelList = new ArrayList<>();
    private final List<Temp1BaseModel> infoList = new ArrayList<>();
    private final List<Temp1BaseModel> infoList2 = new ArrayList<>();
    private final List<Temp1BaseModel> infoList3 = new ArrayList<>();
    private Temp1Adapter adapter;
    private File imageFile;
    private Temp1DataAdapter adapter2;
    private Temp1DataAdapter adapter1;
    private Temp1SkillsAdapter adapter3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_teamplate1, container, false);
        rv_user_details = view.findViewById(R.id.rv_user_details);
        layout = view.findViewById(R.id.iv_layout);
        profile = layout.findViewById(R.id.iv_profile);
        iv_edit = layout.findViewById(R.id.btn_edit);
        rv_data = view.findViewById(R.id.rv_data);
        rv_data2 = view.findViewById(R.id.rv_data2);
        rv_data3 = view.findViewById(R.id.rv_data3);
        View skillsLayout = view.findViewById(R.id.header_info);
        initSkillsLayout(skillsLayout);
//        https://2.bp.blogspot.com/-8ytYF7cfPkQ/WkPe1-rtrcI/AAAAAAAAGqU/FGfTDVgkcIwmOTtjLka51vineFBExJuSACLcBGAs/s320/31.jpg
        Picasso.get()
                .load("https://2.bp.blogspot.com/-8ytYF7cfPkQ/WkPe1-rtrcI/AAAAAAAAGqU/FGfTDVgkcIwmOTtjLka51vineFBExJuSACLcBGAs/s320/31.jpg")
                .transform(new CircleTransform()).into(profile);

        initListners();
        initDatas();
        initInfo();
        initRecyclerView();
        return view;
    }

    private void initSkillsLayout(View skillsLayout) {
        ImageView image = skillsLayout.findViewById(R.id.image);
        TextView tv_header_info = skillsLayout.findViewById(R.id.tv_header_info);
        FloatingActionButton ic_add = skillsLayout.findViewById(R.id.ic_add);

        image.setImageDrawable(getContext().getResources().getDrawable(R.drawable.brain));
        tv_header_info.setText("SKILLS");
        ic_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoList3.add(new SkillsInfo());
                adapter3.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onClick(View v) {
        startImageSelectorActivity();
    }


    private void initListners() {
        iv_edit.setOnClickListener(this);
    }

    private void startImageSelectorActivity() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), PICTURE_STATUS);
    }


    private void initRecyclerView() {
        adapter = new Temp1Adapter(modelList, getContext());
        rv_user_details.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_user_details.addItemDecoration(new SpacesItemDecoration(10));
        rv_user_details.setAdapter(adapter);


        adapter1 = new Temp1DataAdapter(infoList);
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_data.addItemDecoration(new SpacesItemDecoration(10));
        rv_data.setAdapter(adapter1);


        adapter2 = new Temp1DataAdapter(infoList2);
        rv_data2.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_data2.addItemDecoration(new SpacesItemDecoration(10));
        rv_data2.setAdapter(adapter2);

        adapter3 = new Temp1SkillsAdapter(infoList3);
        rv_data3.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_data3.setAdapter(adapter3);
    }

    private void initInfo() {
        infoList.add(new Header("WORK EXPERIENCE", R.drawable.work));
        infoList2.add(new Header("EDUCATION", R.drawable.education));
    }

    private void initDatas() {
        modelList.add(new Header("INFO"));
        modelList.add(new Information("Name",
                "Tap to enter name", R.drawable.profile));
        modelList.add(new Information("Address",
                "Tap to enter Address", R.drawable.address));
        modelList.add(new Information("Phone",
                "Tap to enter Phone", R.drawable.phone));
        modelList.add(new Information("Email",
                "Tap to enter email", R.drawable.email));
        modelList.add(new Information("Website",
                "Tap to enter Website", R.drawable.website));

        modelList.add(new Header("Skills"));
        modelList.add(new ItemList());

        modelList.add(new Header("Social"));
        modelList.add(new Information("Skype",
                "Tap to enter Skype link", R.drawable.skype));
        modelList.add(new Information("Twitter",
                "Tap to enter Twitter link", R.drawable.twitter));
        modelList.add(new Information("Linkdin",
                "Tap to enter Linkdin profile", R.drawable.linkdin));
        modelList.add(new Information("Facebook",
                "Tap to enter Facebook profile link",
                R.drawable.facebook));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICTURE_STATUS:
                    selectIamge(data);
                    break;
            }
        }
    }

    private void selectIamge(Intent data) {
        Uri uri = data.getData();
        String path = getRealPathFromDocumentUri(uri);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        bmOptions.inSampleSize = 1;
        bmOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bmOptions.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        imageFile = new File(path);
        Picasso.get().load(uri).transform(new CircleTransform()).into(profile);
    }

    private String getRealPathFromDocumentUri(Uri uri) {
        String filePath = "";

        Pattern p = Pattern.compile("(\\d+)$");
        Matcher m = p.matcher(uri.toString());
        if (!m.find()) {
            Log.e("asas", "ID for requested image not found: " + uri.toString());
            return filePath;
        }
        String imgId = m.group();

        String[] column = {MediaStore.Images.Media.DATA};
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{imgId}, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();

        return filePath;
    }

}
