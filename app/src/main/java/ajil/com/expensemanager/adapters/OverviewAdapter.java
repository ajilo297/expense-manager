package ajil.com.expensemanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ajil.com.expensemanager.R;
import ajil.com.expensemanager.fragments.OverviewFragment;
import ajil.com.expensemanager.helpers.NotifyEditState;
import ajil.com.expensemanager.models.BudgetModel;

/**
 * Created by ajilo on 26-05-2017.
 */

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.CustomViewHolder>{
    private ArrayList<BudgetModel> budgetModels;
    private Context context;
    private NotifyEditState editState;

    public OverviewAdapter(ArrayList<BudgetModel> budgetModels, Context context, OverviewFragment overviewFragment) {
        this.budgetModels = budgetModels;
        this.context = context;
        editState = overviewFragment;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_budget_category, parent, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return budgetModels.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(View itemView) {
            super(itemView);

        }
    }
}
