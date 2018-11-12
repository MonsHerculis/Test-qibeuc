package skypcreative.com.cuebiq.utils.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import skypcreative.com.cuebiq.model.Watcher;


public interface ApiInterface {

    @GET("/repos/{owner}/{repo}/subscribers")
    Call<List<Watcher>> getWatcher(
            @Path("owner") String owner,
            @Path("repo") String repo);

}