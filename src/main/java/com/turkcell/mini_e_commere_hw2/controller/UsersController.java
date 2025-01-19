package com.turkcell.mini_e_commere_hw2.controller;

import com.turkcell.mini_e_commere_hw2.dto.user.AuthUserDto;
import com.turkcell.mini_e_commere_hw2.dto.user.LoginDto;
import com.turkcell.mini_e_commere_hw2.dto.user.RegisterDto;
import com.turkcell.mini_e_commere_hw2.entity.User;
import com.turkcell.mini_e_commere_hw2.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
  private final UserService userService;

    public UsersController(UserService userService)
    {
        this.userService = userService;
    }

  @PostMapping("register")
  public AuthUserDto add(@RequestBody @Valid RegisterDto registerDto)
  {
    return userService.add(registerDto);
  }

  @PostMapping("login")
  public AuthUserDto login(@RequestBody @Valid LoginDto loginDto)
  {
    return userService.login(loginDto);
  }
}
