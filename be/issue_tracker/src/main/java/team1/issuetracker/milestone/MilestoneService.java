package team1.issuetracker.milestone;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issuetracker.Issue.Issue;
import team1.issuetracker.milestone.dto.MilestoneListResponse;
import team1.issuetracker.milestone.dto.MilestoneShowResponse;

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

    public MilestoneShowResponse getMilestoneAtIssue(Issue issue) {
        Long milestoneId = issue.getMilestoneId();
        if (milestoneId == null) {
            return null;
        }

        Milestone milestone = milestoneRepository.findById(milestoneId).get();
        return new MilestoneShowResponse(milestoneId, milestone.getName());
    }

    public void createMilestone(String name, String description, LocalDateTime deadline) {
        Milestone milestone = Milestone.builder().name(name).description(description).deadline(deadline).build();
        milestoneRepository.save(milestone);
    }

    public void deleteMilestone(Long id) {
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(id);
        if (optionalMilestone.isEmpty()) {
            throw new NoSuchElementException(id + "번 마일스톤이 존재하지 않습니다!");
        }
        Milestone milestone = optionalMilestone.get();
        milestoneRepository.delete(milestone);
    } 

    public void updateMilestone(String name, String description, LocalDateTime deadline, Long id) {
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(id);
        if (optionalMilestone.isEmpty()) {
            throw new NoSuchElementException(id + "번 마일스톤이 존재하지 않습니다!");
        }

        Milestone milestone = Milestone.builder().id(id).name(name).description(description).deadline(deadline).build();
        milestoneRepository.save(milestone);
    }
}