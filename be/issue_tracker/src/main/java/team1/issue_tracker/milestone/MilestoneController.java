package team1.issue_tracker.milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createMilestone(@RequestBody MilestoneMakeRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        try {
            LocalDateTime deadline = LocalDate.parse(request.getDeadline(), formatter).atStartOfDay();
            milestoneService.createMilestone(request.getName(), request.getDescription(), deadline);
            return ResponseEntity.ok("Milestone created successfully");
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use YYYY.MM.DD.");
        }
    }

    @PatchMapping("/{id}")
    public void updateMilestone(@PathVariable("id") Long id) {
        log.debug("Update Milestone.{}", id);
        milestoneService.updateMilestone(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMilestone(@PathVariable("id") Long id) {
        log.debug("Delete Milestone.{}", id);
        milestoneService.deleteMilestone(id);
    }
}
