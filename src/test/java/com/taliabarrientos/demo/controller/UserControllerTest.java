package com.taliabarrientos.demo.controller;

import com.taliabarrientos.demo.dto.SimplifiedUserResponse;
import com.taliabarrientos.demo.service.UserService;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    @Test
    public void getUsersSuccess() {
        SimplifiedUserResponse simplifiedUserResponse = new SimplifiedUserResponse();
        simplifiedUserResponse.setData(Arrays.asList("5 | Morris | charles.morris@reqres.in",
            "6 | Ramos | tracey.ramos@reqres.in"));

        when(userService.getSimplifiedUser()).thenReturn(Maybe.fromCallable(() -> simplifiedUserResponse));

        Maybe<SimplifiedUserResponse> simplifiedUserValue = userController.getSimplifiedUser();
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
