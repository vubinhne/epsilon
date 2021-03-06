package com.epsilon.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epsilon.R;
import com.epsilon.commons.GenericRetainedFragment;
import com.epsilon.screens.main.MainActivity;
import com.epsilon.screens.register.RegisterActivity;
import com.epsilon.utils.Utils;

import butterknife.Bind;
import butterknife.OnClick;
import utils.Injection;

/**
 * Created by Dandoh on 4/9/16.
 */
public class LoginFragment extends GenericRetainedFragment implements LoginContract.View{

    @Bind(R.id.login_btn_login)
    Button mLoginButton;
    @Bind(R.id.login_edt_username)
    EditText mUsernameEditText;
    @Bind(R.id.login_edt_password)
    EditText mPasswwordEditText;
    @Bind(R.id.login_tv_gotoregister)
    TextView mGotoRegisterTextView;

    private LoginContract.UserActionListener mUserActionListener;


    public static Fragment getInstace() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserActionListener = new LoginPresenter(this, Injection.provideUserRepository());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @OnClick(R.id.login_btn_login)
    void login() {
        Utils.log(TAG, "on login");
        mUserActionListener.login(
                mUsernameEditText.getText().toString(),
                mPasswwordEditText.getText().toString()
        );
    }
    @OnClick(R.id.login_tv_gotoregister)
    void toRegister() {
        mUserActionListener.toRegister();
    }


    @Override
    public void displayErrorUsername() {
        Toast.makeText(getActivity(), "Username is invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayErrorPassword() {
        Toast.makeText(getActivity(), "Password is invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToRegisterScreen() {
        Intent intent = RegisterActivity.makeIntent(getActivity());
        startActivity(intent);
    }

    @Override
    public void displayLoginSucceed() {
        // TODO
    }

    @Override
    public void displayLoginError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMainScreen() {
        // TODO
        Intent intent = MainActivity.makeIntent(getActivity(), MainActivity.COURSES_TAB_POSITION);
        startActivity(intent);
    }
}
