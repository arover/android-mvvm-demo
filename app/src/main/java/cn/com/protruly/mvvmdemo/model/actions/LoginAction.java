package cn.com.protruly.mvvmdemo.model.actions;

import java.util.HashMap;

import cn.com.protruly.mvvmdemo.model.entity.Session;
import rx.Observable;
import rx.Subscriber;


public class LoginAction implements Observable.OnSubscribe<Session>{
    private static final HashMap<String,String> DUMMY_CREDENTIALS = new HashMap<>();

    static {
        DUMMY_CREDENTIALS.put("foo@example.com","hello");
        DUMMY_CREDENTIALS.put("bar@example.com","world");
    }

    private final String mEmail;
    private final String mPassword;

    public LoginAction(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    @Override
    public void call(Subscriber<? super Session> subscriber) {
        if(DUMMY_CREDENTIALS.containsKey(mEmail)
                && mPassword.equals(DUMMY_CREDENTIALS.get(mEmail))){

            subscriber.onNext(new Session(mEmail));
            subscriber.onCompleted();

        }else{
            subscriber.onError(new RuntimeException("bad email or password"));
        }
    }
}
