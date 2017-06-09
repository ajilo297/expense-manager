package ajil.com.expensemanager.models;

/**
 * Created by ajilo on 26-05-2017.
 */

public class BudgetModel {
    String id;
    String category;
    String budget;

    public BudgetModel(String id, String category, String budget) {
        this.id = id;
        this.category = category;
        this.budget = budget;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
