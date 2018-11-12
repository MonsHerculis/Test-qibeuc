package skypcreative.com.cuebiq.view.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import skypcreative.com.cuebiq.R;
import skypcreative.com.cuebiq.model.Watcher;
import skypcreative.com.cuebiq.presenter.HomePresenter;
import skypcreative.com.cuebiq.presenter.WatcherAdapter;
import skypcreative.com.cuebiq.view.interfaces.HomeView;

public class MainActivity extends AppCompatActivity implements HomeView{
    HomePresenter homePresenter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    AppCompatEditText ownerEd, repoEd;
    AppCompatButton cercaBtn;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView errorTv;
    ConstraintLayout errorContainer;
    ImageView errorImg;
    CoordinatorLayout coordinatorLayout;
    WatcherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*permissionManager = new PermissionManager(MainActivity.this, coordinatorLayout);
        permissionManager.setPermission(Manifest.permission.INTERNET);
        permissionManager.setPermission(Manifest.permission.ACCESS_NETWORK_STATE);*/
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.caricamento));
        errorTv = findViewById(R.id.errorLabel);
        errorImg = findViewById(R.id.errorImg);
        errorContainer = findViewById(R.id.errorContainer);
        coordinatorLayout = findViewById(R.id.coordinator);

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ownerEd = findViewById(R.id.ownerEd);
        repoEd = findViewById(R.id.repoEd);
        cercaBtn = findViewById(R.id.cercaBtn);
        errorTv.setText(getResources().getString(R.string.cerca));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkConnectivity()){
            homePresenter = new HomePresenter(this);
            cercaBtn.setOnClickListener((View v)->{
                if(ownerEd.getText().toString().isEmpty() && repoEd.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.empty_field), Toast.LENGTH_LONG).show();
                }else{
                    homePresenter.getWatcher(ownerEd.getText().toString(), repoEd.getText().toString());
                    hideKeyboard(this);
                }
            });
        }else{
            errorImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_no_signal));
            errorTv.setText(getResources().getString(R.string.no_signal));
            showSnackBar(getResources().getString(R.string.no_signal),coordinatorLayout);
        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onWatcherGot(List<Watcher> watcherList) {
        if(watcherList.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            errorContainer.setVisibility(View.GONE);
            adapter = new WatcherAdapter(watcherList, this);
            recyclerView.setAdapter(adapter);

        }else{
            problemCallback(getResources().getString(R.string.lista_vuota));
            errorImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_empty));
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onEmptyWatcherGot() {
        problemCallback(getResources().getString(R.string.not_found));
        errorImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_search_problem));
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCallFailed(String error) {
        problemCallback(error);

    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    private void problemCallback(String message){
        recyclerView.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        errorTv.setText(message);
    }
    private boolean checkConnectivity(){
        ConnectivityManager cm = (ConnectivityManager)MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    private void showSnackBar(String message, View view){
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
