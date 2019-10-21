package com.example.androidproject.activities;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.androidproject.models.Business;
import com.example.androidproject.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Perfume_Fragment extends Fragment {

    @BindView(R.id.fragmentView) ImageView mImageView;
    @BindView(R.id.perfumName) TextView mText;
    @BindView(R.id.ratingText) TextView mRatingText;
    @BindView(R.id.location) TextView mLocation;
    @BindView(R.id.phoneText) TextView mNumber;
    @BindView(R.id.addressText) TextView mAdresses;
    @BindView(R.id.button) Button mButton;

    private Business mSpray;

    public Perfume_Fragment() {

    }

    public static Perfume_Fragment newInstance(Business perfume) {
        Perfume_Fragment bodySpray = new Perfume_Fragment();
        Bundle args = new Bundle();
        args.putParcelable("perfume", Parcels.wrap(perfume));
        bodySpray.setArguments(args);
        return bodySpray;
    }

    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        mSpray = Parcels.unwrap(getArguments().getParcelable("perfume"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View create = inflater.inflate(R.layout.fragment_perfume_, container, false);
        ButterKnife.bind(this, create);
        Picasso.get().load(mSpray.getUrl()).into(mImageView);
        List<String> more = new ArrayList<>();


        for (Category category : mSpray.getCategories()) {
            more.add(category.getTitle());
        }
        mText.setText(mSpray.getName());
        mLocation.setText(android.text.TextUtils.join(" , ", more));
        mNumber.setText(mSpray.getPhone());
        mAdresses.setText(mSpray.getLocation().toString());

        return create;
    }

}
