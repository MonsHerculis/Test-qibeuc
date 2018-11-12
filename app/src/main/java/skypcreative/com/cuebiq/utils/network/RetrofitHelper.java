package skypcreative.com.cuebiq.utils.network;


import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import skypcreative.com.cuebiq.model.Watcher;
import skypcreative.com.cuebiq.presenter.HomeInteractor;


public class RetrofitHelper {

    public void getWatcher(String owner, String repo, final HomeInteractor.OnLoadFinishedListener listener) {
        ApiInterface apiInterface = ApiClient.getRequestsRetrofit().create(ApiInterface.class);
        Call<List<Watcher>> call = apiInterface.getWatcher(owner, repo);
        call.enqueue(new AbstractCallback<List<Watcher>>() {
            @Override
            protected void onResultOK(Response<List<Watcher>> response) {
                if ((response.code() == HttpURLConnection.HTTP_OK || response.code() == HttpURLConnection.HTTP_CREATED)) {
                    listener.onWatcherGot(response.body());
                }else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                    listener.onEmptyWatcherGot();
                }
                else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    listener.onError(response.message());
                }
            }

            @Override
            protected void onFail(String msg) {
                listener.onError(msg);
            }
        });
    }


}