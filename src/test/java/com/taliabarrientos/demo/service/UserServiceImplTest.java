package com.taliabarrientos.demo.service;

import com.taliabarrientos.demo.dto.SimplifiedUserResponse;
import com.taliabarrientos.demo.proxy.Support;
import com.taliabarrientos.demo.proxy.UserData;
import com.taliabarrientos.demo.proxy.UserProxy;
import com.taliabarrientos.demo.proxy.UserResponse;
import com.taliabarrientos.demo.service.impl.UserServiceImpl;
import io.reactivex.Maybe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final int NUMBER = 1;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserProxy userProxy;



    private UserResponse mockGetUsers(){
        UserResponse userResponse = new UserResponse();
        userResponse.setPage(NUMBER);
        userResponse.setPer_page(NUMBER);
        userResponse.setTotal(NUMBER);
        userResponse.setTotal_pages(NUMBER);
        userResponse.setData(Arrays.asList(mock(UserData.class)));
        userResponse.setSupport(mock(Support.class));
        return userResponse;
    }

    @Test
    public void getUsersSuccess() {
        SimplifiedUserResponse simplifiedUserResponse = new SimplifiedUserResponse();
        simplifiedUserResponse.setData(Arrays.asList("5 | Morris | charles.morris@reqres.in",
            "6 | Ramos | tracey.ramos@reqres.in"));

        when(userProxy.getUsers()).thenReturn(mockGetUsers());
        Maybe<SimplifiedUserResponse> simplifiedUserValue = userServiceImpl.getSimplifiedUser();
        TestObserver<SimplifiedUserResponse> testSimplifiedUserValue = simplifiedUserValue.test();

        testSimplifiedUserValue.awaitTerminalEvent();
        testSimplifiedUserValue.assertSubscribed();
        testSimplifiedUserValue.assertComplete();
        testSimplifiedUserValue.assertNoErrors();
        testSimplifiedUserValue.assertValue(predicate(t -> {
            Assert.assertEquals("Result value is equals", t, simplifiedUserResponse);
        }));
    }


    private <T>Predicate<T> predicate(Consumer<T> consumer){
        return t -> {
            consumer.accept(t);
            return true;
        };
    }
}
