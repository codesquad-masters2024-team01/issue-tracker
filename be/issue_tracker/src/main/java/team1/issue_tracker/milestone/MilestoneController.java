package team1.issue_tracker.milestone;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team1.issue_tracker.milestone.dto.MilestoneListResponse;

@RequestMapping("/milestone")
@Slf4j
@RestController
public class MilestoneController {

    private final MilestoneService milestoneService;

    @Autowired
    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    // 마일스톤 전체 목록 조회
    @GetMapping("/list")
    public List<MilestoneListResponse> milestoneList() {
        log.debug("List of Milestones");
        return milestoneService.getList();
    }

    // 마일스톤 생성
    @PostMapping
    public void createMilestone() {
    }

    // 마일스톤 수정
    @PatchMapping("/{id}")
    public void updateMilestone(@PathVariable("id") Long id) {
        log.debug("Update Milestone.{}", id);
    }

    // 마일스톤 삭제
    @DeleteMapping("/{id}")
    public void deleteMilestone(@PathVariable("id") Long id) {
        log.debug("Delete Milestone.{}", id);
    }
}
