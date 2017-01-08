package com.commit451.gitlab.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.commit451.gitlab.R;
import com.commit451.gitlab.viewHolder.BreadcrumbViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Shows the current file path
 */
public class BreadcrumbAdapter extends RecyclerView.Adapter<BreadcrumbViewHolder> {
    private List<Breadcrumb> values;

    public BreadcrumbAdapter() {
        values = new ArrayList<>();
        notifyDataSetChanged();
    }

    private final View.OnClickListener onProjectClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag(R.id.list_position);
            Breadcrumb breadcrumb = getValueAt(position);
            if (breadcrumb != null && breadcrumb.getListener() != null) {
                breadcrumb.getListener().onClick();
            }
        }
    };

    @Override
    public BreadcrumbViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BreadcrumbViewHolder holder = BreadcrumbViewHolder.inflate(parent);
        holder.itemView.setOnClickListener(onProjectClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BreadcrumbViewHolder holder, int position) {
        String title = "";
        boolean showArrow = position != values.size() - 1;

        Breadcrumb breadcrumb = getValueAt(position);
        if (breadcrumb != null) {
            title = breadcrumb.getTitle();
        }

        holder.bind(title, showArrow);
        holder.itemView.setTag(R.id.list_position, position);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public void setData(Collection<Breadcrumb> breadcrumbs) {
        values.clear();
        if (breadcrumbs != null) {
            values.addAll(breadcrumbs);
            notifyItemRangeInserted(0, breadcrumbs.size());
        }
        notifyDataSetChanged();
    }

    public Breadcrumb getValueAt(int position) {
        if (position < 0 || position >= values.size()) {
            return null;
        }

        return values.get(position);
    }

    public static class Breadcrumb {
        private final String mTitle;
        private final Listener mListener;

        public Breadcrumb(String title, Listener listener) {
            mTitle = title;
            mListener = listener;
        }

        public String getTitle() {
            return mTitle;
        }

        public Listener getListener() {
            return mListener;
        }
    }

    public interface Listener {
        void onClick();
    }
}
