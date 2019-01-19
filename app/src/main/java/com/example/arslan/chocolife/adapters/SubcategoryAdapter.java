package com.example.arslan.chocolife.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arslan.chocolife.R;
import com.example.arslan.chocolife.data.Category;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder> {

    public SubcategoryAdapter() {
        this.subcategories = new ArrayList<>();
    }

    private List<Category> subcategories;

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories.clear();
        this.subcategories.addAll(subcategories);
        this.notifyDataSetChanged();
    }

    public void addSubategories(List<Category> subcategories) {
        this.subcategories.addAll(subcategories);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subcategory_navigation_item, viewGroup, false);
        return new SubcategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryViewHolder subcategoryViewHolder, int i) {
        Category subcategory = subcategories.get(i);
        subcategoryViewHolder.textViewSubcategoryTitle.setText(subcategory.getTitle());
    }

    @Override
    public int getItemCount() {
        return subcategories.size();
    }

    class SubcategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewSubcategoryTitle;

        public SubcategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSubcategoryTitle = itemView.findViewById(R.id.textViewSubcategoryTitle);
        }
    }
}
