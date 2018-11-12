package skypcreative.com.cuebiq.presenter;


import java.util.List;

import skypcreative.com.cuebiq.model.Watcher;
import skypcreative.com.cuebiq.view.interfaces.HomeView;

public class HomePresenter implements HomeInteractor.OnLoadFinishedListener {

    private HomeView homeView;
    private HomeInteractor homeInteractor;


    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        this.homeInteractor = new HomeInteractor();
    }


    public void getWatcher(String owner, String repo) {
        homeView.showProgress();
        homeInteractor.getWatcher(owner, repo, this);
    }


    @Override
    public void onWatcherGot(List<Watcher> watcherList) {

            if (homeView != null) {
                homeView.hideProgress();
                homeView.onWatcherGot(watcherList);
            }

    }

    @Override
    public void onEmptyWatcherGot() {
        if (homeView != null) {
            homeView.hideProgress();
            homeView.onEmptyWatcherGot();
        }
    }

    @Override
    public void onError(String msg) {
        if (homeView != null) {
            homeView.hideProgress();
            homeView.onCallFailed(msg);
        }
    }
    }
