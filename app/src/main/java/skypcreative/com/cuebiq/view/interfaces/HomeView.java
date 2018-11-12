package skypcreative.com.cuebiq.view.interfaces;


import java.util.List;

import skypcreative.com.cuebiq.model.Watcher;

public interface HomeView {

    void showProgress();

    void hideProgress();

    void onWatcherGot(List<Watcher> watcherList);

    void onEmptyWatcherGot();

    void onCallFailed(String error);

}
