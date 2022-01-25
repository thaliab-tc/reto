package com.taliabarrientos.demo.service.impl;

import com.taliabarrientos.demo.dto.SimplifiedUserResponse;
import com.taliabarrientos.demo.proxy.UserProxy;
import com.taliabarrientos.demo.service.UserService;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserProxy userProxy;
  private static final String SEPARATOR = " | ";

  @Override
  public Maybe<SimplifiedUserResponse> getSimplifiedUser() {
    return Maybe.fromCallable(() -> getUsers());
  }

  private SimplifiedUserResponse getUsers() {
    List<String> dataList = userProxy.getUsers().getData()
        .stream()
        .map(userData -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(userData.getId()).append(SEPARATOR).append(userData.getLast_name())
                .append(SEPARATOR).append(userData.getEmail());
            return stringBuilder.toString();
        })
        .collect(Collectors.toList());

    SimplifiedUserResponse simplifiedUserResponse = new SimplifiedUserResponse();
    simplifiedUserResponse.setData(dataList);

    return simplifiedUserResponse;
  }
}
