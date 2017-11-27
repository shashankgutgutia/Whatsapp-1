package com.example.adity.whatsup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
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


public class Tab2Chats extends Fragment implements SearchView.OnQueryTextListener{

    private String[] title={"I'm the Mechanic","I'm the Captain","I'm a Ghost","Very Funny","Lets Run",
            "Not today","Lets put a smile on that face","Got you","Superman go to hell"};

    private String[] details={"Iron Man","Cap America","Batman","Black Widow","Flash","Witch","Joker","Hawkeye","Wonderwoman"};

    private int[] icons={R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,
            R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a};

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CardAdapter adapter;
    ArrayList<Item> arrayList = new ArrayList<>();

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
                adapter.setFilter(arrayList);
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.activity_card_demo,container,false);
        recyclerView= (RecyclerView) rootview.findViewById(R.id.recyclerid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setHasOptionsMenu(true);

        int count = 0;
        for(String Name: title)
        {
            arrayList.add(new Item(icons[count],Name,details[count]));
            count++;
        }
        adapter= new CardAdapter(arrayList,getActivity());
        recyclerView.setAdapter(adapter);
        return rootview;
        /*View rootView = inflater.inflate(R.layout.tab2chats, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list2);
        adapter = new ReAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;*/
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Item> filter=filter(arrayList,newText);
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



    /*public static List<Item> getData(){
        List<Item> data=new ArrayList<>();
        int[] icons={R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,
                R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a};
        String[] titles={"I'm the Mechanic","I'm the Captain","I'm a Ghost","Very Funny","Lets Run",
                "Not today","Lets put a smile on that face","Got you","Superman go to hell"};
        String[] description={"Iron Man","Cap America","Batman","Black Widow","Flash","Witch","Joker","Hawkeye","Wonderwoman"};
        for(int i=0;i<titles.length && i<icons.length && i<description.length;i++){
            Item current=new Item();
            current.iconid=icons[i];
            current.title=titles[i];
            current.descript=description[i];
            data.add(current);
        }

        return data;
    }*/

}
