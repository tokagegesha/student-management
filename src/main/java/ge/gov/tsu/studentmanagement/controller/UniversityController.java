package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.pojo.SemesterUniversityPojo;
import ge.gov.tsu.studentmanagement.pojo.UniversityPojo;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @RequestMapping(value = "/unsecured/api/university/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject search(@RequestBody RequestObject<UniversityPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(universityService.search(ro));
    }

    @RequestMapping(value = "/unsecured/api/university/search/extended", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchExtended(@RequestBody RequestObject<UniversityPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(universityService.searchExtended(ro));
    }

    @RequestMapping(value = "/unsecured/api/university/semester/unselected/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchUnselectedUniversities(@RequestBody RequestObject<SemesterUniversityPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(universityService.searchUnselectedUniversitiesForSemester(ro));
    }

    @RequestMapping(value = "/unsecured/api/university/semester/selected/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchSelectedUniversities(@RequestBody RequestObject<SemesterUniversityPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(universityService.searchSelectedUniversitiesForSemester(ro));
    }


    @RequestMapping(value = "/api/university/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject add(@RequestBody RequestObject<UniversityPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(universityService.add(ro));
    }

    @RequestMapping(value = "/api/university/edit", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject edit(@RequestBody RequestObject<UniversityPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(universityService.edit(ro.getData()));
    }

    @RequestMapping(value = "/api/university/delete", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject delete(@RequestBody RequestObject<UniversityPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(universityService.delete(ro));
    }
}