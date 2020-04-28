package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.SemesterPojo;
import ge.gov.tsu.studentmanagement.pojo.SemesterUniversityPojo;
import ge.gov.tsu.studentmanagement.pojo.SemesterVisibilityPojo;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SemesterController {

    private SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @RequestMapping(value = "/api/semester/search", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject search(@RequestBody RequestObject<SemesterPojo> ro) {
        return ResponseObject.createSuccessfulResponse(semesterService.search(ro.getData().getId(),ro.getData().getVisible(),ro.getPaging(),ro.getActionPerformer()));
    }

    @RequestMapping(value = "/api/semester/add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject add(@RequestBody RequestObject<SemesterPojo> ro) throws TsuException {
        return ResponseObject.createSuccessfulResponse(semesterService.addSemester(ro));
    }

    @RequestMapping(value = "/api/semester/edit", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject edit(@RequestBody RequestObject<SemesterPojo> ro) throws TsuException {
        return ResponseObject.createSuccessfulResponse(semesterService.editSemester(ro));
    }

    @RequestMapping(value = "/unsecured/api/semester/university/search", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject searchUniversitySemester(@RequestBody RequestObject<SemesterUniversityPojo> ro) {
        return ResponseObject.createSuccessfulResponse(semesterService.searchSemesterUniversity(ro.getData()));
    }

    @RequestMapping(value = "/unsecured/api/semester/visibility/change", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject changeSemesterVisibility(@RequestBody RequestObject<SemesterVisibilityPojo> ro) throws TsuException {
        return ResponseObject.createSuccessfulResponse(semesterService.changeSemesterVisibility(ro.getData()));
    }


    @RequestMapping(value = "/unsecured/api/semester/university/visibility/change", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject changeSemesterUniversityVisibility(@RequestBody RequestObject<SemesterUniversityPojo> ro) throws TsuException {
        return ResponseObject.createSuccessfulResponse(semesterService.changeSemesterUniversityVisibility(ro.getData()));
    }

    @RequestMapping(value = "/unsecured/api/semester/university/remove", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject removeUniversityInSemester(@RequestBody RequestObject<SemesterUniversityPojo> ro) throws TsuException {
        return ResponseObject.createSuccessfulResponse(semesterService.removeUniversityInSemester(ro.getData()));
    }

    @RequestMapping(value = "/unsecured/api/semester/university/add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject addUniversityInSemester(@RequestBody RequestObject<SemesterUniversityPojo> ro) {
        return ResponseObject.createSuccessfulResponse(semesterService.addUniversityInSemester(ro.getData(),ro.getActionPerformer()));
    }

}
