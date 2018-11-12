package skypcreative.com.cuebiq.utils;

import java.util.ArrayList;
import java.util.List;

import skypcreative.com.cuebiq.model.Watcher;

public class MainSingleton {

    private static MainSingleton singletonInstance = null;
    public List<Watcher> watcherList = new ArrayList<>();


    private MainSingleton() {
    }


    public static MainSingleton getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new MainSingleton();
        }
        return singletonInstance;
    }



}
