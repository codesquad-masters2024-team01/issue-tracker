package team1.issuetracker.user;

import org.springframework.stereotype.Service;
import team1.issuetracker.Issue.Issue;
import team1.issuetracker.user.dto.RegisterInfo;
import team1.issuetracker.user.dto.UserInfoResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getNameById(String userId) {
        Optional<User> byId = userRepository.findById(userId);
        return byId.get().getName();
    }

    public boolean isDuplicateId(String id) {
        return userRepository.existsById(id);
    }

    public boolean isDuplicateNickName(String nickname) {
        return userRepository.existsByName(nickname);
    }

    public void createUser(RegisterInfo registerInfo) {
        if (isDuplicateId(registerInfo.id())) throw new IllegalArgumentException("이미 등록된 ID 입니다");
        if (isDuplicateId(registerInfo.nickname())) throw new IllegalArgumentException("이미 등록된 닉네임 입니다");

        User newUser = User.normalUSerOf(registerInfo);
        userRepository.insert(newUser);
    }

    public UserInfoResponse getUserInfo(String id) {
        Optional<User> byId = userRepository.findById(id);
        byId.orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원 ID 입니다"));

        return UserInfoResponse.of(byId.get());
    }

    public List<String> getAssigneesAtIssue(Issue issue) {
        Set<UserRef> issueAssignees = issue.getIssueAssignees();
        return issueAssignees.stream()
                .map(UserRef::getUserId)
                .map(this::getNameById).toList();
    }
}
