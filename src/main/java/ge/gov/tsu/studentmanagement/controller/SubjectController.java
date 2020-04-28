package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.entity.SubjectGrade;
import ge.gov.tsu.studentmanagement.entity.statics.DegreeType;
import ge.gov.tsu.studentmanagement.entity.statics.LanguageType;
import ge.gov.tsu.studentmanagement.pojo.GradeTypePojo;
import ge.gov.tsu.studentmanagement.pojo.ProgrammeSubjectPojo;
import ge.gov.tsu.studentmanagement.pojo.SubjectPojo;
import ge.gov.tsu.studentmanagement.pojo.SubjectReleasedPojo;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/api/subject/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject search(@RequestBody RequestObject<SubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(subjectService.search(ro));
    }

    @RequestMapping(value = "/api/subject/except/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchExcept(@RequestBody RequestObject<ProgrammeSubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(subjectService.getSubjectExceptSomeValues(ro));
    }


    @RequestMapping(value = "/api/subject/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject add(@RequestBody RequestObject<SubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(subjectService.add(ro));
    }

    @RequestMapping(value = "/api/subject/edit", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject edit(@RequestBody RequestObject<SubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(subjectService.edit(ro));
    }

    @RequestMapping(value = "/api/subject/delete", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject delete(@RequestBody RequestObject<SubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(subjectService.delete(ro));
    }

    @RequestMapping(value = "/api/subject/release/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject addReleasedSubjects(@RequestBody RequestObject<SubjectReleasedPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(subjectService.addReleasedSubjects(ro));
    }

    @RequestMapping(value = "/api/subject/release/delete", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject deleteReleasedSubjects(@RequestBody RequestObject<SubjectReleasedPojo> ro) throws Exception {
        subjectService.deleteSubjectRelease(ro);
        return ResponseObject.createSuccessfulResponse();
    }

    @RequestMapping(value = "/api/subject/released/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchSubjectRelease(@RequestBody RequestObject<SubjectReleasedPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(subjectService.searchSubjectRelease(ro));
    }

    @RequestMapping(value = "/api/subject/unreleased/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchUnreleasedSubjects(@RequestBody RequestObject<SubjectReleasedPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(subjectService.searchUnreleasedSubjects(ro));
    }

    @RequestMapping(value = "/api/subject/released/delete", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject deleteSubjectRelease(@RequestBody RequestObject<SubjectReleasedPojo> ro) throws Exception {
        subjectService.deleteSubjectRelease(ro);
        return ResponseObject.createSuccessfulResponse();
    }

    @RequestMapping(value = "/api/subject/released/grade/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject addSubjectGradeType(@RequestBody RequestObject<GradeTypePojo> ro) {
        SubjectGrade result = subjectService.addSubjectGradeType(ro.getData().getSubjectReleaseId(), ro.getData().getMax(), ro.getData().getName(), ro.getData().getType(), ro.getActionPerformer());
        return ResponseObject.createSuccessfulResponse(result);
    }

    @RequestMapping(value = "/api/subject/released/grade/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchSubjectGrades(@RequestBody RequestObject<SubjectReleasedPojo> ro) {
        List<SubjectGrade> result = subjectService.subjectGradeSearch(ro.getData().getId(), ro.getActionPerformer());
        return ResponseObject.createSuccessfulResponse(result);
    }

    @RequestMapping(value = "/api/subject/language/get", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseObject getLanguages() {
        return ResponseObject.createSuccessfulResponse(LanguageType.values());
    }

}