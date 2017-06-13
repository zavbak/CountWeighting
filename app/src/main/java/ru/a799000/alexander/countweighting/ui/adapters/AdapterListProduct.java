package ru.a799000.alexander.countweighting.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.model.intities.Barcode;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;


public class AdapterListProduct extends RecyclerView.Adapter<AdapterListProduct.ViewHolder> implements RealmChangeListener {

    private final RealmResults<Product> mList;

    private CallBackClickItem mCallBackClickItem;


    public AdapterListProduct(RealmResults<Product> list, CallBackClickItem CallBackClickItem) {
        mList = list;
        mCallBackClickItem = CallBackClickItem;
        mList.addChangeListener(this);

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        holder.setProduct((Product) mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public void onChange(Object element) {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvId)
        TextView tvId;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvWeight)
        TextView tvWeight;
        @BindView(R.id.tvUnit)
        TextView tvUnit;
        @BindView(R.id.tvSites)
        TextView tvSites;

        Product mProduct;
        float mWeight;
        int mSites;




        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            view.setOnClickListener(this);
        }

        private void init(){
            RealmList<Barcode> barcodes = mProduct.getBarcodes();

            for(Barcode barcode: barcodes){
                mSites = mSites + barcode.getPlaces();
                mWeight = mWeight + barcode.getWeight();
            }
        }

        public void setProduct(Product product) {
            mProduct = product;
            init();
            tvId.setText(Long.toString(product.getId()));
            tvName.setText(product.getName()==null?"": product.getName());
            tvUnit.setText(product.getUnit()==null?"": product.getUnit());
            tvSites.setText(mSites==0?"":Integer.toString(mSites));
            tvWeight.setText(mWeight==0?"":Float.toString(mWeight));
        }


        @Override
        public void onClick(View v) {
            mCallBackClickItem.click(Long.toString(mProduct.getId()));
        }
    }

    public interface CallBackClickItem {
        void click(String id);
    }

}
