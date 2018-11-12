package skypcreative.com.cuebiq.presenter;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import skypcreative.com.cuebiq.R;
import skypcreative.com.cuebiq.model.Watcher;


public class WatcherAdapter extends RecyclerView.Adapter<WatcherAdapter.ViewHolder> {

    private List<Watcher> watcherList;
    private OnItemSelected onItemSelected;
    Context context;

    public WatcherAdapter(List<Watcher> watcherList, Context context) {
        this.watcherList = watcherList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.watcher_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Watcher watcher = watcherList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(watcher.getAvatarUrl())

                .into(holder.avatar);

        holder.username.setText(watcher.getLogin());


    }

    @Override
    public int getItemCount() {
        return watcherList.size();
    }

    public interface OnItemSelected {
        void onItemSelected(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.username);


        }
    }

}
