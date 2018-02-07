package semana1.curso3.coursera;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import semana1.curso3.coursera.adapter.PagerAdapter;
import semana1.curso3.coursera.fragment.HomeFragment;
import semana1.curso3.coursera.fragment.PetProfileFragment;
import semana1.curso3.coursera.restApi.Endpoints;
import semana1.curso3.coursera.restApi.adapter.RestApiAdapter;
import semana1.curso3.coursera.restApi.model.UserResponse;
import semana1.curso3.coursera.utils.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tab_layout;
    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new PetProfileFragment());
        view_pager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments));
        tab_layout.setupWithViewPager(view_pager);
        tab_layout.getTabAt(0).setIcon(R.mipmap.ic_home);
        tab_layout.getTabAt(1).setIcon(R.mipmap.ic_pet_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_star:
                Intent secondActivity = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(secondActivity);
                break;
            case R.id.mi_contact:
                Intent contactActivity = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contactActivity);
                break;
            case R.id.mi_about:
                Intent aboutActivity = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutActivity);
                break;
            case R.id.mi_set_up_account:
                Intent setUpAccountActivity = new Intent(MainActivity.this, SetUpAccountActivity.class);
                startActivity(setUpAccountActivity);
                finish();
                break;
            case R.id.mi_receive_notifications:
                postRegistrarUsuario();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void postRegistrarUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiServer();
        Call<UserResponse> userResponseCall = endpoints.postRegistrarUsuario(FirebaseInstanceId.getInstance().getToken(), SharedPreferencesManager.getUserId(MainActivity.this));
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                Toast.makeText(MainActivity.this, R.string.registered_successfully, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

}