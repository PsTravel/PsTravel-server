package com.sideproject.pstravel.Controllter;


import com.sideproject.pstravel.Dto.LocationDto;
import com.sideproject.pstravel.Entity.User;
import com.sideproject.pstravel.Repository.JPA.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @PostMapping("/create")
    public void saveLocation(@RequestBody User user) throws IllegalAccessException {
        User createUser = User.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build();
        userRepo.save(createUser);
    }

}
