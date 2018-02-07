package semana1.curso3.coursera;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import semana1.curso3.coursera.pojo.User;
import semana1.curso3.coursera.restApi.Constants;
import semana1.curso3.coursera.restApi.Endpoints;
import semana1.curso3.coursera.restApi.adapter.RestApiAdapter;
import semana1.curso3.coursera.restApi.model.PetProfileResponse;
import semana1.curso3.coursera.utils.SharedPreferencesManager;

public class SetUpAccountActivity extends AppCompatActivity {

    private TextInputLayout til_user;
    private TextInputEditText tiet_user;
    private Button btn_save_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_account);
        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        til_user = (TextInputLayout) findViewById(R.id.til_user);
        tiet_user = (TextInputEditText) findViewById(R.id.tiet_user);
        tiet_user.addTextChangedListener(new MyTextWatcher(tiet_user));
        btn_save_account = (Button) findViewById(R.id.btn_save_account);
        btn_save_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidations()) {
                    getUserId();
                }
            }
        });
    }

    public void getUserId() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonDeserializerSearch();
        Endpoints endpoints = restApiAdapter.establishConnectionRestApiInstagram(gson);
        Call<PetProfileResponse> petProfileResponseCall = endpoints.getSearch(tiet_user.getText().toString().trim(), Constants.INSTAGRAM_ACCESS_TOKEN);
        petProfileResponseCall.enqueue(new Callback<PetProfileResponse>() {
            @Override
            public void onResponse(Call<PetProfileResponse> call, Response<PetProfileResponse> response) {
                PetProfileResponse petProfileResponse = response.body();
                User user = petProfileResponse.getUser();
                SharedPreferencesManager.setUserId(SetUpAccountActivity.this, user.getId());
                goToMainActivity();
            }

            @Override
            public void onFailure(Call<PetProfileResponse> call, Throwable throwable) {
                Toast.makeText(SetUpAccountActivity.this, R.string.unexpected_error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkValidations(){
        if (!validateUser()) {
            return false;
        }
        return true;
    }

    private boolean validateUser() {
        if (tiet_user.getText().toString().trim().isEmpty()) {
            til_user.setError(getString(R.string.required_user));
            requestFocus(tiet_user);
            return false;
        }
        else {
            til_user.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tiet_user:
                    validateUser();
                    break;
            }
        }

    }

    private void goToMainActivity() {
        Intent setUpAccountActivity = new Intent(SetUpAccountActivity.this, MainActivity.class);
        startActivity(setUpAccountActivity);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        goToMainActivity();
    }
}