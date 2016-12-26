package com.yeungkc.itemdelegate.sample.item_delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.yeungkc.itemdelegate.AbsListItemItemDelegate;
import com.yeungkc.itemdelegate.sample.R;
import com.yeungkc.itemdelegate.sample.bean.Company;

import java.util.List;

public class CompanyItemDelegate extends AbsListItemItemDelegate<Company, Company, CompanyItemDelegate.CompanyViewHolder> {
    private int mCurrentPosition = 0;

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    @Override
    protected boolean isForViewType(@NonNull Company data, @NonNull List<Company> dataSets, int position) {
        return true;
    }

    @NonNull
    @Override
    protected CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new CompanyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Company data, @NonNull CompanyViewHolder viewHolder, @NonNull List payloads) {
        viewHolder.bind(data);
    }

    @Override
    protected long getItemId(@NonNull Company data, @NonNull List<Company> dataSets, int position) {
        return data.hashCode();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder {

        private final RadioButton mRadioButton;

        public CompanyViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int oldPosition = mCurrentPosition;
                    mCurrentPosition = getAdapterPosition();

                    RecyclerView.Adapter adapter = ((RecyclerView) itemView.getParent()).getAdapter();

                    adapter.notifyItemChanged(mCurrentPosition);

                    if (oldPosition == mCurrentPosition) {
                        return;
                    }

                    adapter.notifyItemChanged(oldPosition);
                }
            });

            mRadioButton = (RadioButton) itemView;
        }

        public void bind(Company item) {
            mRadioButton.setChecked(getAdapterPosition() == mCurrentPosition);
            mRadioButton.setText(item.getName());
        }
    }
}
