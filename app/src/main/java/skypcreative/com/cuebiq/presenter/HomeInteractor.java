package skypcreative.com.cuebiq.presenter;


import java.util.List;

import skypcreative.com.cuebiq.model.Watcher;
import skypcreative.com.cuebiq.utils.network.RetrofitHelper;

public class HomeInteractor {

    public interface OnLoadFinishedListener {

        void onWatcherGot(List<Watcher> watcherList);
        void onEmptyWatcherGot();

        void onError(String msg);

    }


    public void getWatcher(String owner, String repo, OnLoadFinishedListener listener) {
        new RetrofitHelper().getWatcher(owner, repo, listener);
    }


}
