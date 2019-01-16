package com.example.arslan.chocolife.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arslan.chocolife.R;
import com.example.arslan.chocolife.data.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> categories;
    private SubcategoryAdapter subcategoryAdapter;

    public CategoryAdapter() {
        this.categories = new ArrayList<>();
        getSubcategoryAdapter();
    }

    public SubcategoryAdapter getSubcategoryAdapter() {
        if(subcategoryAdapter == null){
            subcategoryAdapter = new SubcategoryAdapter();
        }
        return subcategoryAdapter;

    }

    public void setCategories(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        this.notifyDataSetChanged();
    }

    public void addCategories(List<Category> categories) {
        this.categories.addAll(categories);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_navigation_item, viewGroup, false);
        return new CategoryViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        Category category = categories.get(i);
        categoryViewHolder.textViewTitle.setText(category.getTitle());
        RecyclerView recyclerViewChild = categoryViewHolder.recyclerViewChild;
        if(category.isHasSubcategories()){
            recyclerViewChild.setAdapter(subcategoryAdapter);
            subcategoryAdapter.setSubcategories(categories.get(i).getSub_categories());
            categoryViewHolder.textViewCountStocks.setVisibility(View.GONE);
            categoryViewHolder.imageViewArrow.setVisibility(View.VISIBLE);
        }else{
            categoryViewHolder.textViewCountStocks.setText(Integer.toString(category.getDeals_number()));
            categoryViewHolder.textViewCountStocks.setVisibility(View.VISIBLE);
            categoryViewHolder.imageViewArrow.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }




    /**
     * Слушатель для клика по категории
     */
    private OnCategoryClickListener onCategoryClickListener;

    public interface OnCategoryClickListener{
        void onCategoryClick(int position);
    }

    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private ImageView imageViewArrow;
        private TextView textViewCountStocks;
        private RecyclerView recyclerViewChild;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewCategoryTitle);
            imageViewArrow = itemView.findViewById(R.id.imageViewArrow);
            textViewCountStocks = itemView.findViewById(R.id.textViewCountStocks);

            recyclerViewChild = itemView.findViewById(R.id.recyclerViewChild);
            recyclerViewChild.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onCategoryClickListener != null){
                        onCategoryClickListener.onCategoryClick(getAdapterPosition());
                    }
                }
            });
        }


    }


    class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder> {
        private List<Category> subcategories;

        public SubcategoryAdapter() {
            this.subcategories = new ArrayList<>();
        }



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



}
