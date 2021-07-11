package com.lee.book.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.lee.book.R;

public class SearchRecentAdapter extends CursorAdapter {

    private static final String LOG_TAG =
            SearchRecentAdapter.class.getSimpleName();

    public SearchRecentAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_recent_search, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mTitle.setText(
                cursor.getString(cursor.
                        getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1)));
    }

    public String getSuggestionText(int position) {
        if (position >= 0 && position < getCursor().getCount()) {
            Cursor cursor = getCursor();
            cursor.moveToPosition(position);
            return cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
        }
        return null;
    }

    public static class ViewHolder {
        public TextView mTitle;

        public ViewHolder(View view) {
            // view of your custom layout
            mTitle = view.findViewById(R.id.text);
        }
    }
}
