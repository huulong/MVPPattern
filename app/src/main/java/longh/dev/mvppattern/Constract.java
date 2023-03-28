package longh.dev.mvppattern;

import android.widget.Adapter;

public interface Constract {
    interface IView{
        void showloginsuccess();
        void showloginFailed();
    }
    interface IPresenter{
        void dologin(String email, String pass);
        void forgotpass();
        Adapter list();
    }
}
