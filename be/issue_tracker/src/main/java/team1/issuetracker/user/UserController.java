package team1.issuetracker.user;

import org.springframework.web.bind.annotation.*;
import team1.issuetracker.user.auth.JwtUtil;
import team1.issuetracker.user.dto.CheckDuplicateRequest;
import team1.issuetracker.user.dto.LoginInfo;
import team1.issuetracker.user.dto.RegisterInfo;
import team1.issuetracker.user.dto.UserInfoResponse;

import java.util.NoSuchElementException;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public void register(@RequestBody RegisterInfo registerInfo) throws IllegalArgumentException {
        userService.createUser(registerInfo);
    }

    @GetMapping("/duplicate")
    public boolean isDuplicate(@RequestBody CheckDuplicateRequest request) {
        if (request.id() != null) return userService.isDuplicateId(request.id());
        if (request.nickname() != null) return userService.isDuplicateNickName(request.nickname());

        else throw new IllegalArgumentException("중복 확인할 ID나 닉네임을 입력해 주세요");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginInfo loginInfo) {
        if (!userService.authentication(loginInfo)) throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다! ID와 비밀번호를 다시 확인해주세요!");
        return jwtUtil.generateToken(loginInfo.id());
    }

    @GetMapping("/{id}")
    public UserInfoResponse userInfo(@PathVariable String id) throws NoSuchElementException {
        return userService.getUserInfo(id);
    }

    @GetMapping("/test")
    public String tokenValidateTest(@RequestBody String key) {
        return jwtUtil.validateToken(key);
    }
}
