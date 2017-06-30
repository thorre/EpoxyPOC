package se.grep.epoxypoc;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity implements MainController.AdapterCallbacks {
    private MainController controller = new MainController(this);
    private List<Item> items = new ArrayList<>();
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(controller.getAdapter());
        controller.requestModelBuild();

        Observable.interval(1, TimeUnit.SECONDS)
                .skip(1)
                .take(6)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        controller.setLoadingMore(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(recyclerView, e.getMessage(), Snackbar.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onNext(Long aLong) {
                        addItem(items);
                        controller.setItems(items);
                    }
                });
    }

    private void addItem(List<Item> items) {
        itemId++;
        items.add(new Item(itemId, "Item no " + itemId));
    }

    @Override
    public void onItemClick(int position) {
        items.remove(position - 1);
        controller.setItems(items);
    }

    @Override
    public boolean onItemLongClick(int position) {
        itemId++;
        items.add(position - 1, new Item(itemId, "Item no " + itemId));
        controller.setItems(items);
        return false;
    }
}
