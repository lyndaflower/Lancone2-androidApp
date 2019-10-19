package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.DnsResolver;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalActivity extends AppCompatActivity {

    @BindView(R.id.view1) TextView mView;
    @BindView(R.id.view2) TextView mMake;
    @BindView(R.id.view3) TextView mView1;
    @BindView(R.id.view4) TextView mMake2;
    @BindView(R.id.listProgress) ListView mListprogress;
    @BindView(R.id.progressBar) ProgressBar mProgressText;
    @BindView(R.id.errorView) TextView mErrorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        ButterKnife.bind(this);


        Intent choice = getIntent();
        String name = choice.getStringExtra("name");
        String email = choice.getStringExtra("email");
        String location = choice.getStringExtra("location");
        String type = choice.getStringExtra("type");

//        mView.setText("Name : " + name );
//        mMake.setText("Email : " + email);
//        mView1.setText("Location : " + location);
//        mMake2.setText("Type : " +type);

        ParfumeInter client = PerfumeRetro.getClient();

        Call<YelpPurabella> call = client.getParfumes(location, "Body spray");
        call.enqueue(new Callback<YelpPurabella>() {
            @Override
            public void onResponse(Call<YelpPurabella> call, Response<YelpPurabella> response) {

                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Business> perfumesList = response.body().getBusinesses();
                    String[] perfumes = new String[perfumesList.size()];
                    String[] categories = new String[perfumesList.size()];

                    for (int i = 0; i < perfumes.length; i++) {
                        perfumes[i] = perfumesList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category spray = perfumesList.get(i).getCategories().get(0);
                        categories[i] = spray.getTitle();
                    }
//                    ArrayAdapter bodySpray = new ParfumAdapter(FinalActivity.this, android.R.layout.simple_list_item_1, perfumes, categories);

                }
            }

            @Override
            public void onFailure(Call<YelpPurabella> call, Throwable t) {
            hideProgressBar();
            showUnsuccessfulMessage();

            }
            private void showUnsuccessfulMessage(){
                mErrorView.setText("Something went wrong. Please try again later");
                mErrorView.setVisibility(View.VISIBLE);
            }
            private void ShowBodySpray(){
                mListprogress.setVisibility(View.VISIBLE);
                mView.setVisibility(View.VISIBLE);
                mView1.setVisibility(View.VISIBLE);
                mMake2.setVisibility(View.VISIBLE);
                mMake.setVisibility(View.VISIBLE);
            }

            private void hideProgressBar(){
                mProgressText.setVisibility(View.GONE);
            }
        });
    }

}
