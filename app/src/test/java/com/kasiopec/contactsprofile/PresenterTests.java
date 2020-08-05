package com.kasiopec.contactsprofile;

import android.database.sqlite.SQLiteException;

import com.kasiopec.contactsprofile.database.User;
import com.kasiopec.contactsprofile.presenter.Presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;


import io.reactivex.Flowable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;


/**
 * Class that test presenter class and its method
 * Two tests are written, one to check if data is actually displayed when received from a database
 * and second to check if error message toast is displayed when Room database throws and exception
 */

@RunWith(MockitoJUnitRunner.class)
public class PresenterTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private Contract.View view;
    @Mock
    private Contract.Repository repo;

    private Presenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter(view, repo);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void checkThat_DataLoaded_FromDB_And_Displayed_OnTheScreen(){

        ArrayList<User> arrayList = new ArrayList<>();
        User u = new User();
        u.setId(1);
        u.setImageUrl("");
        u.setEmail("kasiclysm@gmail.com");
        u.setName("Test");
        u.setPhoneNumber("123");
        u.setStreet("Test");
        u.setCity("Test");

        arrayList.add(u);

        Mockito.when(repo.getAllUsersFromDb()).thenReturn(Flowable.just(arrayList));
        presenter.startFetching();
        Mockito.verify(view, Mockito.times(1)).displayUserData(arrayList);
    }


    @Test(expected = SQLiteException.class)
    public void checkThat_ErrorMessageToast_Displayed_OnRoomException(){
        Mockito.doThrow(new SQLiteException()).when(repo).getAllUsersFromDb();
        presenter.startFetching();
        Mockito.verify(view, Mockito.times(1)).displayErrorToast("test");
    }
}


