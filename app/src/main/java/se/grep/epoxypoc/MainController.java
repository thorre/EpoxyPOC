package se.grep.epoxypoc;

import android.view.View;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelLongClickListener;

import java.util.ArrayList;
import java.util.List;

import se.grep.epoxypoc.views.HeaderViewModel_;
import se.grep.epoxypoc.views.ItemView;
import se.grep.epoxypoc.views.ItemViewModel_;
import se.grep.epoxypoc.views.LoaderViewModel_;

public class MainController extends EpoxyController {
    private AdapterCallbacks callbacks;

    public interface AdapterCallbacks {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    public MainController(AdapterCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    private boolean loadingMore = true;
    private List<Item> items = new ArrayList<>();

    @AutoModel
    HeaderViewModel_ header;
    @AutoModel
    LoaderViewModel_ loader;

    public void setLoadingMore(boolean loadingMore) {
        this.loadingMore = loadingMore;
        requestModelBuild();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        requestModelBuild();
    }

    @Override
    protected void buildModels() {
        header
                .title(R.string.header_title)
                .caption(R.string.header_caption)
                .addTo(this);

        for (Item item : items) {
            new ItemViewModel_()
                    .id(item.getId())
                    .title(item.getTitle())
                    .clickListener_OnClickListener(new OnModelClickListener<ItemViewModel_, ItemView>() {
                        @Override
                        public void onClick(ItemViewModel_ model, ItemView parentView, View clickedView, int position) {
                            callbacks.onItemClick(position);
                        }
                    })
                    .longClickListener_OnLongClickListener(new OnModelLongClickListener<ItemViewModel_, ItemView>() {
                        @Override
                        public boolean onLongClick(ItemViewModel_ model, ItemView parentView, View clickedView, int position) {
                            return callbacks.onItemLongClick(position);
                        }
                    })
                    .addTo(this);
        }

        loader
                .title(R.string.loader_text)
                .addIf(loadingMore, this);

    }

    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        throw exception;
    }
}
