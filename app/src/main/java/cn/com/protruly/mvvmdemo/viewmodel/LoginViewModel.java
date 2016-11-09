package cn.com.protruly.mvvmdemo.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.com.protruly.mvvmdemo.model.actions.LoginAction;
import cn.com.protruly.mvvmdemo.model.entity.Session;
import cn.com.protruly.mvvmdemo.view.LoginActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginViewModel {

    public final ObservableField<String> email = new ObservableField<>("foo@example.com");
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableBoolean isLogging = new ObservableBoolean();

    private static final String TAG = "LoginVM";
    private final LoginActivity mActivity;
    private Subscription mSubscription;

    public LoginViewModel(LoginActivity loginActivity) {
        mActivity = loginActivity;
    }

    public void onClickLogin(View view) {

        if (!isEmailValid(email.get()) || !isPasswordValid(password.get())) {
            return;
        }

        cancelAction();
        isLogging.set(true);
        mSubscription = Observable.create(new LoginAction(email.get(), password.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSignInSubscriber());
    }

    public void cancelAction() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }

    private Observer<? super Session> getSignInSubscriber() {
        return new Subscriber<Session>() {
            @Override
            public void onCompleted() {
                isLogging.set(false);
                new AlertDialog.Builder(mActivity)
                        .setMessage("Welcome!")
                        .create().show();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"login onError",e);

                new AlertDialog.Builder(mActivity)
                        .setMessage("Something is Wrong:" + e.getMessage())
                        .create().show();
            }

            @Override
            public void onNext(Session session) {
                Log.d(TAG, "log in success session" + session);
            }
        };
    }


}
