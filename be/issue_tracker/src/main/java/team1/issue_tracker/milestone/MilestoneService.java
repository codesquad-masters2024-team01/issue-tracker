package team1.issue_tracker.milestone;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.milestone.dto.MilestoneListResponse;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    public List<MilestoneListResponse> getList() {
        List<Milestone> milestones = (List<Milestone>) milestoneRepository.findAll();
        return milestones.stream().map(MilestoneListResponse::of).toList();
    }

    public void createMilestone(String name, String description, LocalDateTime deadline) {
        Milestone milestone = Milestone.builder().name(name).description(description).deadline(deadline).build();
        milestoneRepository.save(milestone);
    }

    public void updateMilestone(Long id) {
        Optional<Milestone> byId = milestoneRepository.findById(id);

    }

    public void deleteMilestone(Long id) {
    }

}