package com.android.newcrime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.newcrime.databases.CrimeItem;
import com.android.newcrime.databases.CrimeProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwb on 2017/3/11.
 */

public class ListActivity extends AppCompatActivity {
    private Context context = null;
    private CrimeProvider mCrimeProvider;
    private List<CrimeItem> mItemslist;
    private ListView mListV;
    private ListAdapter mAdapter;

    ImageView mLeftButton;

    final int LIST_DELETE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list);

        context = this.getApplicationContext();
        mCrimeProvider = new CrimeProvider(context);
        mItemslist = new ArrayList<CrimeItem>();
        mItemslist = mCrimeProvider.getAll();

        mListV=(ListView)findViewById(R.id.listView);
        mAdapter = new ListAdapter(ListActivity.this,mItemslist);
        mListV.setAdapter(mAdapter);
        mListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                CrimeItem item = (CrimeItem) mAdapter.getItem(position);
                Intent intent = new Intent(ListActivity.this, CreateActivity.class);
                intent.putExtra("CrimeItem", item);
                startActivity(intent);
            }
        });

        mLeftButton = (ImageView)findViewById(R.id.list_toolbar_left_button);
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        registerForContextMenu(mListV);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String delete = context.getResources().getString(R.string.list_delete);
        if (v.getId()==R.id.listView) {
            menu.add(0, LIST_DELETE, 0, delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case LIST_DELETE:
                mCrimeProvider.delete(mItemslist.get(info.position).getId());
                mItemslist.remove(info.position);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.case_delete_done),Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

}
