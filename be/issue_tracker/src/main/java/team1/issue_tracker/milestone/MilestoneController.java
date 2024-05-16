package team1.issue_tracker.milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team1.issue_tracker.milestone.dto.MilestoneListResponse;
import team1.issue_tracker.milestone.dto.MilestoneMakeRequest;

@RequestMapping("/milestone")
@Slf4j
@RestController
public class MilestoneController {

    private final MilestoneService milestoneService;

    @Autowired
    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping("/list")
    public List<MilestoneListResponse> milestoneList() {
        log.debug("List of Milestones");
        return milestoneService.getList();
    }

    @PostMapping
    public void createMilestone(@RequestBody MilestoneMakeRequest request) throws IllegalArgumentException {
        log.debug("Create Milestone.");
        LocalDateTime deadline = getDeadline(request);
        milestoneService.createMilestone(request.getName(), request.getDescription(), deadline);
    }

    @PatchMapping("/{id}")
    public void updateMilestone(@RequestBody MilestoneMakeRequest request, @PathVariable("id") Long id)
            throws IllegalArgumentException {
        log.debug("Update Milestone.{}", id);
        LocalDateTime deadline = getDeadline(request);
        milestoneService.updateMilestone(request.getName(), request.getDescription(), deadline, id);
    }

    private static LocalDateTime getDeadline(MilestoneMakeRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDateTime deadline;
        try {
            deadline = LocalDate.parse(request.getDeadline(), formatter).atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("날짜가 형식에 맞지 않습니다.");
        }
        return deadline;
    }

    @DeleteMapping("/{id}")
    public void deleteMilestone(@PathVariable("id") Long id) {
        log.debug("Delete Milestone.{}", id);
        milestoneService.deleteMilestone(id);
    }
}
