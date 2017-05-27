package ajil.com.expensemanager;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

/**
 * Created by ajilo on 25-05-2017.
 */

public class OverviewFragment extends Fragment {
    View v;
    RecyclerView overviewRecycler;
    FloatingActionButton ovAddCategoryBtn;
    ArrayList<BudgetModel> budgetModels = new ArrayList<>();
    OverviewAdapter overviewAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_overview, container, false);

        overviewRecycler = (RecyclerView) v.findViewById(R.id.ovRecycler);
        ovAddCategoryBtn = (FloatingActionButton) v.findViewById(R.id.ovAddCategoryBtn);

        overviewAdapter = new OverviewAdapter(budgetModels, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        overviewRecycler.setLayoutManager(layoutManager);
        overviewRecycler.setItemAnimator(new FadeInDownAnimator(new AccelerateInterpolator()));
        overviewRecycler.setAdapter(overviewAdapter);

        ovAddCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategoryMethod();
            }
        });

        return v;
    }

    private void addCategoryMethod() {
        BudgetModel model = new BudgetModel("", "", "");
        budgetModels.add(model);
        overviewAdapter.notifyItemInserted(budgetModels.size() - 1);
    }


}

