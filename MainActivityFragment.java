package com.example.a46406163y.listamagic;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.os.AsyncTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    private int num;

    public MainActivityFragment() {
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                        setHasOptionsMenu(true);
           }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCards = (ListView) view.findViewById(R.id.lvCards);

        items = new ArrayList<>();

        String[] data = {
                                "Golem arcano",
                                "Dragon de fuego",
                                "Goblin de las montañas",
                                "Angel guardian",
                                "Fuego eterno",
                                };

                        items = new ArrayList<>(Arrays.asList(data));

               adapter = new ArrayAdapter<>(
                               getContext(),
                               R.layout.ls_cards_rows,
                               R.id.txtNameCard,
                              items
                           );
                lvCards.setAdapter(adapter);

        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
                super.onCreateOptionsMenu(menu, inflater);
                inflater.inflate(R.menu.menu_cartes_fragment, menu);
            }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                                        int id = item.getItemId();
                                if (id == R.id.action_refresh) {
            refresh();
                        return true;
                   }
                        return super.onOptionsItemSelected(item);
            }
                private void refresh() {
                    RefreshDataTask task = new RefreshDataTask();
                    task.execute();

            }
        private class RefreshDataTask extends AsyncTask<Void, Object, ArrayList<Card>> {
            @Override
            protected ArrayList<Card> doInBackground(Void... voids) {
                Cards2016 api = new Cards2016();
                ArrayList<Card> result = api.getAllCards();

                Log.d("DEBUG", result.toString());

                return result;
            }

            @Override
            protected void onPostExecute(ArrayList<Card> carta) {

                super.onPostExecute(carta);

                adapter.clear();
                    for (int i = 0; i < carta.size(); i++) {
                        adapter.add(carta.get(i).getName());
                    }


            }
        }
}
