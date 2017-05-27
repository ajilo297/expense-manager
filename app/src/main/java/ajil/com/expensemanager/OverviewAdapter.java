package ajil.com.expensemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by ajilo on 26-05-2017.
 */

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.CustomViewHolder>{
    private ArrayList<BudgetModel> budgetModels;
    private Context context;
    public OverviewAdapter(ArrayList<BudgetModel> budgetModels, Context context) {
        this.budgetModels = budgetModels;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_budget_category, parent, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        holder.ovCatEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ovCatText.setVisibility(VISIBLE);
                holder.ovCatLabel.setVisibility(GONE);
                holder.ovCatEdit.setVisibility(GONE);
                holder.ovCatDone.setVisibility(VISIBLE);
            }
        });

        holder.ovCatDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ovCatText.setVisibility(GONE);
                holder.ovCatLabel.setVisibility(VISIBLE);
                holder.ovCatEdit.setVisibility(VISIBLE);
                holder.ovCatDone.setVisibility(GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return budgetModels.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView ovCatEdit , ovBudgetPlus, ovBudgetMinus , ovCatDone;
        TextView ovCatLabel , ovCatText , ovBudgetText;
        public CustomViewHolder(View itemView) {
            super(itemView);
            ovCatEdit = (ImageView) itemView.findViewById(R.id.ovCategoryEdit);
            ovCatLabel = (TextView) itemView.findViewById(R.id.ovCategoryLabel);
            ovBudgetPlus = (ImageView) itemView.findViewById(R.id.ovBudgetPlus);
            ovBudgetMinus = (ImageView) itemView.findViewById(R.id.ovBudgetMinus);
            ovCatText = (TextView) itemView.findViewById(R.id.ovCategoryText);
            ovBudgetText = (TextView) itemView.findViewById(R.id.ovBudgetText);
            ovCatDone = (ImageView) itemView.findViewById(R.id.ovCategoryDone);
        }
    }
}
