package longh.dev.mvppattern.data.impl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import longh.dev.mvppattern.Constract;
import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.driver.SQLiteDb;
import longh.dev.mvppattern.data.model.User;

public class UserDaoImpl implements UserDao {
    Context context;
    String DB_NAME = "mvp.db";
    Constract.IView mIView;

    SQLiteDatabase mDatabase;

    public UserDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean insert(User user) {
        mDatabase = SQLiteDb.initDatabase((Activity) context, DB_NAME);
        Cursor cursor = mDatabase.rawQuery("select * from Accout",null);
        for (int i = 0 ; i < cursor.getCount(); i++ ){
            cursor.moveToPosition(i);
            if(!user.getEmail().equals(cursor.getString(0))){
                ContentValues contentValues = new ContentValues();
                contentValues.put("user",user.getEmail());
                contentValues.put("password",user.getPassword());
                mDatabase.insert("Accout",null,contentValues);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        mDatabase = SQLiteDb.initDatabase((Activity) context,DB_NAME);
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",user.getPassword());
        mDatabase.update("Accout", contentValues,"user =?", new String[]{user.getEmail()});
        return true;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public User find(String email, String password) {
        mDatabase = SQLiteDb.initDatabase((Activity) context,DB_NAME);
        Cursor mCursor = mDatabase.rawQuery("select * from Accout",null);
        for(int i = 0; i < mCursor.getCount(); i++){
            mCursor.moveToPosition(i);
            if(email.equals(mCursor.getString(0)) && password.equals(mCursor.getString(1))){
                return new User(email,password);
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        mDatabase = SQLiteDb.initDatabase((Activity) context, DB_NAME);
        List<User> lstUsers = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from Accout",null);
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            lstUsers.add(new User( cursor.getString(0), cursor.getString(1)));
        }
        return lstUsers;
    }
}
