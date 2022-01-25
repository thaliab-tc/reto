package com.taliabarrientos.demo.service;

import com.taliabarrientos.demo.dto.SimplifiedUserResponse;
import io.reactivex.Maybe;

public interface UserService {
  Maybe<SimplifiedUserResponse> getSimplifiedUser();
}
