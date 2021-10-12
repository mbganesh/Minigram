package mb.ganesh.minigram;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostAPI {



    @GET("api/show-post")
    Call<List<PostModel>> getPost();

}
