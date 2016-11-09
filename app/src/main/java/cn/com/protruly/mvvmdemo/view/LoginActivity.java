package cn.com.protruly.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.protruly.mvvmdemo.R;
import cn.com.protruly.mvvmdemo.databinding.ActivityLoginBinding;
import cn.com.protruly.mvvmdemo.viewmodel.LoginViewModel;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_login);

        mLoginViewModel = new LoginViewModel(this);
        binding.setModel(mLoginViewModel);
    }

    @Override
    protected void onDestroy() {
        mLoginViewModel.cancelAction();
        super.onDestroy();
    }
}

