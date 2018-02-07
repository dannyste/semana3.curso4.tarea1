package semana1.curso3.coursera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import semana1.curso3.coursera.adapter.PetAdapter;
import semana1.curso3.coursera.pojo.Pet;
import semana1.curso3.coursera.pojo.PetConstructor;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView rv_pet;

    private ArrayList<Pet> pets;
    private PetAdapter petAdapter;

    private PetConstructor petConstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv_pet = (RecyclerView) findViewById(R.id.rv_pet);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SecondActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_pet.setLayoutManager(linearLayoutManager);
        petConstructor = new PetConstructor(SecondActivity.this);
        pets = petConstructor.getFiveFavoritePets();
        petAdapter = new PetAdapter(SecondActivity.this, pets);
        rv_pet.setAdapter(petAdapter);
    }

}