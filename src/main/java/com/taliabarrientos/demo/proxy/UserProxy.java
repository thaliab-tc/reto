package com.taliabarrientos.demo.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name  = "servicio-usuario", url= "https://reqres.in" , decode404 = true)
public interface UserProxy {

  @GetMapping("/api/users")
  UserResponse getUsers();

}
