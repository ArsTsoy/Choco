package com.example.arslan.chocolife.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arslan.chocolife.R;
import com.example.arslan.chocolife.data.Stock;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static String IMAGE_KIND_GENERAL = "general";
    private static String IMAGE_KIND_WIDE_PLATE = "wide_plate";

    private static int ITEM_TYPE_GENERAL = 1;
    private static int ITEM_TYPE_WIDE_PLATE = 2;

    private List<Stock> stocks;

    public StockAdapter() {
        this.stocks = new ArrayList<>();
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks.clear();
        this.stocks.addAll(stocks);
        this.notifyDataSetChanged();
    }

    public void addStocks(List<Stock> stocks) {
        this.stocks.addAll(stocks);
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = null;
        if(i == ITEM_TYPE_GENERAL){
            view = layoutInflater.inflate(R.layout.stock_item, viewGroup, false);
            return new GeneralStockViewHolder(view);
        }else {
            view = layoutInflater.inflate(R.layout.special_stock_item, viewGroup, false);
            return new SpecialStockViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(stocks.size()>=20 && i>stocks.size()-2 && onReachEndListener != null){
            onReachEndListener.onReachEnd();
        }

        Stock stock = stocks.get(i);
//        private ImageView imageViewStockImage;
//        private TextView textViewShortTitle;
//        private TextView textViewTitle;
//        private TextView textViewBought;
//        private TextView textViewPrice;
//        private TextView textViewRating;
        if(viewHolder instanceof GeneralStockViewHolder){
            Picasso.get().load(stock.getImageUrlWide()).into(((GeneralStockViewHolder) viewHolder).imageViewStockImage);
            ((GeneralStockViewHolder) viewHolder).textViewShortTitle.setText(stock.getTitleShort());
            ((GeneralStockViewHolder) viewHolder).textViewTitle.setText(stock.getTitle());
            ((GeneralStockViewHolder) viewHolder).textViewBought.setText(Integer.toString(stock.getBought()));
//            ((GeneralStockViewHolder) viewHolder).textViewPrice.setText(String.format(R.string));
            ((GeneralStockViewHolder) viewHolder).textViewPrice.setText(Integer.toString(stock.getPrice()));
            ((GeneralStockViewHolder) viewHolder).textViewRating.setText(Double.toString(stock.getReviewsRate()));
        }else {
            Picasso.get().load(stock.getImageUrl()).into(((SpecialStockViewHolder)viewHolder).imageViewWideImage);
        }
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }


    @Override
    public int getItemViewType(int position) {
        if(stocks.get(position).getImageKind().equals(IMAGE_KIND_GENERAL)){
            return ITEM_TYPE_GENERAL;
        }else {
            return ITEM_TYPE_WIDE_PLATE;
        }
    }

    /**
     * Создаем слушателя что юзер долистал до конца списка
     */


    private OnReachEndListener onReachEndListener;


    public interface OnReachEndListener{
        void onReachEnd();
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener){
        this.onReachEndListener = onReachEndListener;
    }


    /**
     * Слушатель для клика по акции
     */
    private OnStockClickLstener onStockClickLstener;

    public interface OnStockClickLstener{
        void onStockClick(int position);
    }

    public void setOnStockClickLstener(OnStockClickLstener onStockClickLstener) {
        this.onStockClickLstener = onStockClickLstener;
    }

    private class GeneralStockViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewStockImage;
        private TextView textViewShortTitle;
        private TextView textViewTitle;
        private TextView textViewBought;
        private TextView textViewPrice;
        private TextView textViewRating;



        public GeneralStockViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewStockImage = itemView.findViewById(R.id.imageViewStockImage);
            textViewShortTitle = itemView.findViewById(R.id.textViewShortTitle);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewBought = itemView.findViewById(R.id.textViewBought);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewRating = itemView.findViewById(R.id.textViewRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onStockClickLstener != null){
                        onStockClickLstener.onStockClick(getAdapterPosition());
                    }
                }
            });
;
        }
    }
    private class SpecialStockViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewWideImage;

        public SpecialStockViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewWideImage = itemView.findViewById(R.id.imageViewWideImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onStockClickLstener != null){
                        onStockClickLstener.onStockClick(getAdapterPosition());
                    }
                }
            });

        }
    }

}
