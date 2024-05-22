package team1.issuetracker.domain.label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team1.issuetracker.domain.label.dto.LabelListResponse;
import team1.issuetracker.domain.label.dto.LabelMakeRequest;
import team1.issuetracker.domain.user.auth.annotation.Authenticate;
import team1.issuetracker.domain.user.auth.annotation.AuthenticatedUserId;

import java.util.List;

@RequestMapping("/label")
@RestController
public class LabelController {

    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping("/list")
    public List<LabelListResponse> labelList(){
        return labelService.getList();
    }

    @Authenticate
    @PostMapping
    public LabelListResponse createLabel(@RequestBody LabelMakeRequest labelMakeRequest, @AuthenticatedUserId String userId){
        return LabelListResponse.of(labelService.saveLabel(labelMakeRequest, userId));
    }

    @Authenticate
    @PatchMapping("/{id}")
    public Label updateLabel(@PathVariable long id, @RequestBody LabelMakeRequest labelMakeRequest, @AuthenticatedUserId String userId){
        return labelService.updateLabel(id, labelMakeRequest, userId);
    }

    @Authenticate
    @DeleteMapping("/{id}")
    public void deleteLabel(@PathVariable long id,  @AuthenticatedUserId String userId) {
        labelService.deleteLabel(id, userId);
    }
}
