package semana1.curso3.coursera;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import semana1.curso3.coursera.mail.Mail;

public class ContactActivity extends AppCompatActivity {

    private TextInputLayout til_name, til_email, til_message;
    private TextInputEditText tiet_name, tiet_email, tiet_message;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);
        til_name = (TextInputLayout) findViewById(R.id.til_name);
        tiet_name = (TextInputEditText) findViewById(R.id.tiet_name);
        tiet_name.addTextChangedListener(new MyTextWatcher(tiet_name));
        til_email = (TextInputLayout) findViewById(R.id.til_email);
        tiet_email = (TextInputEditText) findViewById(R.id.tiet_email);
        tiet_email.addTextChangedListener(new MyTextWatcher(tiet_email));
        til_message = (TextInputLayout) findViewById(R.id.til_message);
        tiet_message = (TextInputEditText) findViewById(R.id.tiet_message);
        tiet_message.addTextChangedListener(new MyTextWatcher(tiet_message));
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidations()) {
                    String[] recipients = { tiet_email.getText().toString().trim() };
                    SendEmailAsyncTask sendEmailAsyncTask = new SendEmailAsyncTask();
                    sendEmailAsyncTask.view = view;
                    sendEmailAsyncTask.mail = new Mail("coursera.danny@gmail.com", "coursera123456");
                    sendEmailAsyncTask.mail.set_from(tiet_email.getText().toString().trim());
                    sendEmailAsyncTask.mail.setBody(tiet_message.getText().toString().trim());
                    sendEmailAsyncTask.mail.set_to(recipients);
                    sendEmailAsyncTask.mail.set_subject(tiet_name.getText().toString().trim());
                    sendEmailAsyncTask.execute();
                }
            }
        });
    }

    class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {

        View view;
        Mail mail;

        public SendEmailAsyncTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (mail.send()) {
                    Snackbar.make(view, getString(R.string.email_sent), Snackbar.LENGTH_LONG).show();
                }
                else {
                    Snackbar.make(view, getString(R.string.email_failed_send), Snackbar.LENGTH_LONG).show();
                }
                return true;
            }
            catch (AuthenticationFailedException error) {
                Snackbar.make(view, getString(R.string.authentication_failed), Snackbar.LENGTH_LONG).show();
                return false;
            }
            catch (MessagingException error) {
                Snackbar.make(view, getString(R.string.email_failed_send), Snackbar.LENGTH_LONG).show();
                return false;
            }
            catch (Exception error) {
                Snackbar.make(view, getString(R.string.unexpected_error_occured), Snackbar.LENGTH_LONG).show();
                return false;
            }
        }
    }

    public boolean checkValidations(){
        if (!validateFullName()) {
            return false;
        }
        if (!validateEmail()) {
            return false;
        }
        if (!validateMessage()) {
            return false;
        }
        return true;
    }

    private boolean validateFullName() {
        if (tiet_name.getText().toString().trim().isEmpty()) {
            til_name.setError(getString(R.string.required_name));
            requestFocus(tiet_name);
            return false;
        }
        else {
            til_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        if (tiet_email.getText().toString().trim().isEmpty()) {
            til_email.setError(getString(R.string.required_email));
            requestFocus(tiet_email);
            return false;
        }
        else if (!isValidEmail(tiet_email.getText().toString().trim())){
            til_email.setError(getString(R.string.incorrect_email));
            requestFocus(tiet_email);
            return false;
        }
        else {
            til_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMessage() {
        if (tiet_message.getText().toString().trim().isEmpty()) {
            til_message.setError(getString(R.string.required_message));
            requestFocus(tiet_message);
            return false;
        }
        else {
            til_message.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
                case R.id.tiet_name:
                    validateFullName();
                    break;
                case R.id.tiet_email:
                    validateEmail();
                    break;
                case R.id.tiet_message:
                    validateMessage();
                    break;
            }
        }

    }

}