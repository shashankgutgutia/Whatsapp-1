package com.example.adity.whatsup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Tab1Contacts extends Fragment implements SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private ReAdapter adapter;
    public List<Item> data=getData();
/*

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1contacts, container, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.list1);
        adapter=new ReAdapter(getActivity(),getData(),1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        setHasOptionsMenu(true);
        return rootView;
    }

    public static List<Item> getData(){
        List<Item> data=new ArrayList<>();
        int[] icons={R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,
                R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a};
        String[] titles={"Jarvis i want a burger","Grow Up","I'm a Billionaire","You ready?","Fasten Your Seatbelts",
                "I'm a Witch","Why so serious?","Bullseye","I'm wonderful"};
        String[] description={"Iron Man","Cap America","Batman","Black Widow","Flash","Witch","Joker","Hawkeye","Wonderwoman"};
        for(int i=0;i<titles.length && i<icons.length && i<description.length;i++){
            Item current=new Item(icons[i],titles[i],description[i]);
            current.setIconid(icons[i]);
            current.setTitle(titles[i]);
            current.setDescript(description[i]);
            data.add(current);
        }
        return data;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        final MenuItem menuItem=menu.findItem(R.id.action_search);
        final SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapter.setFilter(data);
                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Item> filter=filter(data,newText);
        adapter.setFilter(filter);
        return true;
    }


    private List<Item> filter(List<Item> items,String query){
        query=query.toLowerCase();
        final List<Item> filterList=new ArrayList<>();
        for(Item item:items){
            final String text=item.getTitle().toLowerCase();
            if(text.contains(query)){
                filterList.add(item);
            }
        }
        return filterList;
    }
}