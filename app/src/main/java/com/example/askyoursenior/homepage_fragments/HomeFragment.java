package com.example.askyoursenior.homepage_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.askyoursenior.R;
import com.example.askyoursenior.adapter.BookListRecyclerviewAdapter;
import com.example.askyoursenior.databinding.FragmentHomeBinding;
import com.example.askyoursenior.model.BookDetailModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding fragmentHomeBinding;
    ArrayList<BookDetailModel> bookDetailModelArrayList;
    BookListRecyclerviewAdapter bookListRecyclerviewAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BookDetailModel book1 = new BookDetailModel("Software engineering", "Techneo", "by vijay4145", "fourth semester", "computer engineering", "440");
        bookDetailModelArrayList = new ArrayList<>();
        bookDetailModelArrayList.add(book1);
        BookDetailModel book2 = new BookDetailModel("Computer network", "Techneo", "by sahil07", "fourth semester", "computer engineering", "440");
        bookDetailModelArrayList.add(book2);
        BookDetailModel book3 = new BookDetailModel("data warehousing and mining", "Techneo", "by gapte02", "fourth semester", "computer engineering", "440");
        bookDetailModelArrayList.add(book3);
        BookDetailModel book4 = new BookDetailModel("Theoretical computer science", "Techneo", "by vijay4145", "fourth semester", "computer engineering", "440");
        bookDetailModelArrayList.add(book4);


        bookListRecyclerviewAdapter = new BookListRecyclerviewAdapter(bookDetailModelArrayList);
        fragmentHomeBinding.bookListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentHomeBinding.bookListRecyclerView.setAdapter(bookListRecyclerviewAdapter);


        fragmentHomeBinding.booksSearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
    }

    private void filterList(String newText) {
        ArrayList<BookDetailModel> newList = new ArrayList<>();
        for(BookDetailModel bookDetail: bookDetailModelArrayList){
            if(bookDetail.getBook_name().toLowerCase().contains(newText)) newList.add(bookDetail);
        }
        if(newList.isEmpty())
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        else
            bookListRecyclerviewAdapter.setfilteredList(newList);
    }
}