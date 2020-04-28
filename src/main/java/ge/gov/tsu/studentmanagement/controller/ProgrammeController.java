package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.pojo.ProgrammePojo;
import ge.gov.tsu.studentmanagement.pojo.ProgrammeSubjectPojo;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.ProgrammeService;
import ge.gov.tsu.studentmanagement.service.ProgrammeSubjectService;
import ge.gov.tsu.studentmanagement.service.ProgrammeUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgrammeController {

    @Autowired
    ProgrammeUniversityService programmeUniversityService;

    @Autowired
    ProgrammeService programmeService;

    @Autowired
    ProgrammeSubjectService programmeSubjectService;

    /*@RequestMapping(value = "/university/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchProgrammeUniversity(@RequestBody RequestObject<ProgrammeReleasedPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeUniversityService.search(ro.getData(), ro.getPaging()));
    }*/

    @RequestMapping(value = "/unsecured/api/programme/subject/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchProgrammeSubject(@RequestBody RequestObject<ProgrammeSubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeSubjectService.search(ro.getData(), ro.getPaging()));
    }

    @RequestMapping(value = "/api/programme/subjects/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchProgrammeSubjects(@RequestBody RequestObject<ProgrammeSubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeSubjectService.searchProgrammeSubject(ro.getData(), ro.getPaging()));
    }

    @RequestMapping(value = "/unsecured/api/programme/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject search(@RequestBody RequestObject<ProgrammePojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeService.search(ro.getData(), ro.getPaging()));
    }


    @RequestMapping(value = "/api/programme/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject add(@RequestBody RequestObject<ProgrammePojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeService.add(ro));
    }

    @RequestMapping(value = "/api/programme/edit", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject edit(@RequestBody RequestObject<ProgrammePojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeService.edit(ro));
    }

    @RequestMapping(value = "/api/programme/delete", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject delete(@RequestBody RequestObject<ProgrammePojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeService.delete(ro));
    }

    @RequestMapping(value = "/api/programme/subject/delete", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject programmeSubjectDelete(@RequestBody RequestObject<ProgrammeSubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeService.programmeSubjectDelete(ro));
    }

    @RequestMapping(value = "/api/programme/subject/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject addProgrammeSubject(@RequestBody RequestObject<ProgrammeSubjectPojo> ro) throws Exception {
        ro.checkRequiredFields("programme-subject");
        return ResponseObject.createSuccessfulResponse(programmeSubjectService.addProgrammeSubject(ro.getData()));
    }

    @RequestMapping(value = "/unsecured/api/programme/subject/cut/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject searchCutProgrammeSubject(@RequestBody RequestObject<ProgrammeSubjectPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(programmeSubjectService.getCutProgrammeSubjects(ro.getData(), ro.getPaging()));
    }
}
