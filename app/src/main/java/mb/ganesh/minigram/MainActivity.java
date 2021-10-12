package mb.ganesh.minigram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    ArrayList titleList = new ArrayList();
    ArrayList descriptionList = new ArrayList();
    ArrayList imageList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.27:1111/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostAPI api = retrofit.create(PostAPI.class);


        Call<List<PostModel>> call = api.getPost();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {

                if(response.code() != 200){
                    Toast.makeText(MainActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
                    return;
                }



                List<PostModel> posts = response.body();

                for(PostModel postModel : posts){
                    titleList.add(postModel.getTitle());
                    descriptionList.add(postModel.getDescription());
                    imageList.add(postModel.getImage());
                }


                adapter = new MyRecyclerViewAdapter(MainActivity.this , titleList, descriptionList , imageList);

                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Server down", Toast.LENGTH_SHORT).show();
            }
        });



    }
}