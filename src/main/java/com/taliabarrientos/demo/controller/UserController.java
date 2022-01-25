package com.taliabarrientos.demo.controller;

import com.taliabarrientos.demo.dto.SimplifiedUserResponse;
import com.taliabarrientos.demo.service.UserService;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/users")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

  private UserService userService;

  @PostMapping
  public Maybe<SimplifiedUserResponse> getSimplifiedUser() {
    return userService.getSimplifiedUser();
  }

}
