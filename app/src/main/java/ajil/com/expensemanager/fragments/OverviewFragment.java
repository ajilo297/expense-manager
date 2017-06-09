package ajil.com.expensemanager.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ajil.com.expensemanager.R;
import ajil.com.expensemanager.adapters.OverviewAdapter;
import ajil.com.expensemanager.helpers.NotifyEditState;
import ajil.com.expensemanager.models.BudgetModel;
import io.codetail.animation.ViewAnimationUtils;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

/**
 * Created by ajilo on 25-05-2017.
 */

public class OverviewFragment extends Fragment implements NotifyEditState, View.OnClickListener {
    View v;
    RecyclerView overviewRecycler;
    FloatingActionButton ovAddCategoryBtn;
    ArrayList<BudgetModel> budgetModels = new ArrayList<>();
    OverviewAdapter overviewAdapter;
    Boolean currentEditFlag = false;
    View revealFrame;
    CardView revealCard;
    private ImageView ovEditButton;
    private TextView ovCancelButton, ovSaveButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_overview, container, false);

        Context mContext = getContext();

        overviewRecycler = (RecyclerView) v.findViewById(R.id.ovRecycler);
        ovAddCategoryBtn = (FloatingActionButton) v.findViewById(R.id.ovAddCategoryBtn);
        revealFrame = v.findViewById(R.id.revealFrame);
        ovEditButton = (ImageView) v.findViewById(R.id.ovEditButton);
        revealCard = (CardView) v.findViewById(R.id.revealCard);
        ovCancelButton = (TextView) v.findViewById(R.id.ovCancelButton);
        ovSaveButton = (TextView) v.findViewById(R.id.ovSaveButton);

        overviewAdapter = new OverviewAdapter(budgetModels, getContext(), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        overviewRecycler.setLayoutManager(layoutManager);
        overviewRecycler.setItemAnimator(new FadeInDownAnimator(new AccelerateInterpolator()));
        overviewRecycler.setAdapter(overviewAdapter);

        ovAddCategoryBtn.setOnClickListener(this);
        ovEditButton.setOnClickListener(this);
        ovCancelButton.setOnClickListener(this);
        ovSaveButton.setOnClickListener(this);

        return v;
    }

    @Override
    public boolean isEditing(boolean state) {
        currentEditFlag = state;
        return state;
    }

    @Override
    public void onClick(View v) {
        if (v == ovAddCategoryBtn) {
            addCategory();
        } else if (v == ovEditButton) {
            editBudget();
        } else if (v == ovCancelButton) {
            cancelBudget();
        } else if (v == ovSaveButton)
            saveBudget();
    }

    private void saveBudget() {
        int cx = revealCard.getWidth() / 2;
        int cy = revealCard.getHeight() / 2;

        revealFrame.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        // get the final radius for the clipping circle
        int dx = Math.max(cx, revealFrame.getWidth() - cx);
        int dy = Math.max(cy, revealFrame.getHeight() - cy);
        float initialRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator = ViewAnimationUtils.createCircularReveal(revealFrame, cx, cy, initialRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(400);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealFrame.setVisibility(View.GONE);
                revealCard.setVisibility(View.GONE);
                revealFrame.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray));
            }
        });
        animator.start();
        revealCard.animate().alpha(0).setDuration(300).setInterpolator(new AccelerateInterpolator());
    }

    private void cancelBudget() {
        int cx = revealCard.getWidth() / 2;
        int cy = revealCard.getHeight() / 2;

        // get the final radius for the clipping circle
        int dx = Math.max(cx, revealFrame.getWidth() - cx);
        int dy = Math.max(cy, revealFrame.getHeight() - cy);
        float initialRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator = ViewAnimationUtils.createCircularReveal(revealFrame, cx, cy, initialRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(400);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealFrame.setVisibility(View.GONE);
                revealCard.setVisibility(View.GONE);
            }
        });
        animator.start();
        revealCard.animate().alpha(0).setDuration(300).setInterpolator(new AccelerateInterpolator());
    }

    private void addCategory() {
        if (!currentEditFlag) {
            BudgetModel model = new BudgetModel("", "", "");
            budgetModels.add(model);
            overviewAdapter.notifyItemInserted(budgetModels.size() - 1);
            currentEditFlag = true;
        }
    }

    private void editBudget() {

        revealFrame.setVisibility(View.VISIBLE);
        revealCard.setVisibility(View.VISIBLE);
        revealCard.setAlpha(0);
        revealCard.animate().alpha(1).setDuration(700).setInterpolator(new AccelerateInterpolator());

        // get the center for the clipping circle
//        int cx = (ovEditButton.getLeft() + ovEditButton.getRight()) / 2;
//        int cy = (ovEditButton.getTop() + ovEditButton.getBottom()) / 2;

        int cx = ovEditButton.getLeft() + (ovEditButton.getWidth());
        int cy = ovEditButton.getTop() + (ovEditButton.getHeight());

        // get the final radius for the clipping circle
        int dx = Math.max(cx, revealFrame.getWidth() - cx);
        int dy = Math.max(cy, revealFrame.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator = ViewAnimationUtils.createCircularReveal(revealFrame, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(400);
        animator.start();

    }
}

