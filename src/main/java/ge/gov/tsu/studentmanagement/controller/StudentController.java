package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.apiutils.service.GeneralTools;
import ge.gov.tsu.studentmanagement.entity.statics.DegreeType;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.StudentPojo;
import ge.gov.tsu.studentmanagement.pojo.StudentSubjectPojo;
import ge.gov.tsu.studentmanagement.pojo.StudentSubjectRecordPojo;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.StudentService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class StudentController {


    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/unsecured/api/student/register", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"multipart/form-data"})
    public ResponseObject registerCandidate(
            @RequestPart("student") StudentPojo studentPojo,
            @RequestParam(value = "letterOfNomination") MultipartFile letterOfNomination,
            @RequestParam(value = "cv") MultipartFile cv,
            @RequestParam(value = "diploma") MultipartFile diploma,
            @RequestParam(value = "learningAgreement") MultipartFile learningAgreement,
            @RequestParam(value = "universityRecord") MultipartFile universityRecord,
            @RequestParam(value = "picture") MultipartFile picture,
            @RequestParam(value = "passport") MultipartFile passport,
            @RequestParam(value = "proofOfEnglishSkill") MultipartFile proofOfEnglishSkill,
            @RequestParam(value = "healthInsurance") MultipartFile healthInsurance) throws Exception {
        // GeneralTools.checkRequiredFields(studentPojo, "candidate-registration");
        GeneralTools.checkRequiredProperties(studentPojo, Arrays.asList(
                "universityId",
                "firstName",
                "lastName",
                "gender",
                "citizenship",
                "countryOfResidence",
                "birthDate",
                "address",
                "city",
                "country",
                "email",
                "phone",
                "semesterIds"));
        return ResponseObject.createSuccessfulResponse(studentService.registerStudent(
                studentPojo,
                letterOfNomination,
                cv,
                diploma,
                learningAgreement,
                universityRecord,
                picture,
                passport,
                proofOfEnglishSkill,
                healthInsurance
        ));


    }

    @RequestMapping(value = "/api/student/updateStudentDocumentInfo", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"multipart/form-data"})
    public ResponseObject updateStudentDocumentInfo(
            @RequestParam(value = "letterOfNomination", required = false) MultipartFile letterOfNomination,
            @RequestParam(value = "cv", required = false) MultipartFile cv,
            @RequestParam(value = "diploma", required = false) MultipartFile diploma,
            @RequestParam(value = "learningAgreement", required = false) MultipartFile learningAgreement,
            @RequestParam(value = "universityRecord", required = false) MultipartFile universityRecord,
            @RequestParam(value = "picture", required = false) MultipartFile picture,
            @RequestParam(value = "passport", required = false) MultipartFile passport,
            @RequestParam(value = "proofOfEnglishSkill", required = false) MultipartFile proofOfEnglishSkill,
            @RequestParam(value = "healthInsurance", required = false) MultipartFile healthInsurance) throws Exception {

        return ResponseObject.createSuccessfulResponse(studentService.updateStudentDocumentInfo(
                letterOfNomination,
                cv,
                diploma,
                learningAgreement,
                universityRecord,
                picture,
                passport,
                proofOfEnglishSkill,
                healthInsurance
        ));


    }

    @RequestMapping(value = "/api/student/search/extended", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject searchExtended(@RequestBody RequestObject<StudentPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(studentService.searchExtended(ro.getData(), ro.getPaging()));
    }

    @RequestMapping(value = {"/api/student/get"}, method = RequestMethod.POST)
    public ResponseObject getStudent(@RequestBody RequestObject<StudentPojo> ro) {
        return ResponseObject.createSuccessfulResponse(studentService.getStudentByUserId(ro.getData().getUserId()));
    }

    @RequestMapping(value = {"/unsecured/api/student/degree/type/get"}, method = RequestMethod.POST)
    public ResponseObject getStudentDegreeType(@RequestBody RequestObject<StudentPojo> ro) {
        return ResponseObject.createSuccessfulResponse(DegreeType.values());
    }
/*

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject search(@RequestBody RequestObject<CandidatePojo> ro) {
       return ResponseObject.createSuccessfulResponse(studentService.search(ro.getData(), ro.getPaging()));
    }

   */

    @RequestMapping(value = "/api/student/accept", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"multipart/form-data"})
    public ResponseObject accept(@RequestParam(value = "studentId") String studentId,
                                 @RequestParam(value = "letterOfAcceptance") MultipartFile letterOfAccepnatce) throws TsuException {
        return ResponseObject.createSuccessfulResponse(studentService.accept(Long.valueOf(studentId), letterOfAccepnatce));
    }

    @RequestMapping(value = "/api/student/reject", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject reject(@RequestBody RequestObject<StudentPojo> ro) throws TsuException {
        return ResponseObject.createSuccessfulResponse(studentService.reject(ro.getData()));
    }

    @RequestMapping(value = "/api/student/pend", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject pend(@RequestBody RequestObject<StudentPojo> ro) throws TsuException {
        return ResponseObject.createSuccessfulResponse(studentService.pend(ro.getData()));
    }

    @RequestMapping(value = "/api/student/file/get/{studentId}", method = RequestMethod.GET)
    public void getStudentFile(HttpServletResponse response, @PathVariable("studentId") Long studentId) throws IOException {
        response.addHeader("Content-disposition", "attachment;filename=" + "file-" + UUID.randomUUID() + ".zip");
        response.setContentType("application/zip");

        ByteArrayOutputStream stream = studentService.getStudentFile(studentId);
        InputStream myStream = new ByteArrayInputStream(stream.toByteArray());
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/api/student/acceptance/generate/{studentId}", method = RequestMethod.GET)
    public void getStudentAcceptanceFile(HttpServletResponse response, @PathVariable("studentId") Long studentId) throws IOException, TsuException {
        response.addHeader("Content-disposition", "attachment;filename=" + "file-" + UUID.randomUUID() + ".docx");
        response.setContentType("application/msword");

        ByteArrayOutputStream stream = studentService.getStudentAcceptanceFile(studentId);
        InputStream myStream = new ByteArrayInputStream(stream.toByteArray());
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/api/student/acceptance/download/{studentId}", method = RequestMethod.GET)
    public void downloadStudentAcceptanceFile(HttpServletResponse response, @PathVariable("studentId") Long studentId) throws IOException, TsuException {
        response.addHeader("Content-disposition", "attachment;filename=" + "file-" + UUID.randomUUID() + ".docx");
        response.setContentType("application/msword");

        byte[] stream = studentService.getStudentAcceptanceFileForDownload(studentId);
        InputStream myStream = new ByteArrayInputStream(stream);
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/api/student/document/generate/{studentId}/{semesterId}", method = RequestMethod.GET)
    public void getStudentDocument(HttpServletResponse response, @PathVariable("studentId") Long studentId, @PathVariable("semesterId") Long semesterId) throws Exception {
        response.addHeader("Content-disposition", "attachment;filename=" + "file-" + UUID.randomUUID() + ".docx");
        response.setContentType("application/msword");

        ByteArrayOutputStream stream = studentService.getStudentDocument(studentId, semesterId);
        InputStream myStream = new ByteArrayInputStream(stream.toByteArray());
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/api/student/grid/export/{semesterJson}", method = RequestMethod.GET)
    public void getStudentDocument(@PathVariable("semesterJson") String semesterJson, HttpServletResponse response) throws Exception {
        response.addHeader("Content-disposition", "attachment;filename=" + "file-" + UUID.randomUUID() + ".docx");
        response.setContentType("application/msword");

        StudentPojo studentPojo = new StudentPojo();
        List<Long> semesterIdsLong = new ArrayList<>();
        String[] semesterids = semesterJson.split(",");
        for (int i = 0; i < semesterids.length; i++) {
            semesterIdsLong.add(Long.valueOf(semesterids[i]));
        }
        studentPojo.setSemesterIds(semesterIdsLong);
        ByteArrayOutputStream stream = studentService.getStudentGridData(studentPojo);
        InputStream myStream = new ByteArrayInputStream(stream.toByteArray());
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/api/student/subject/add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject studentSubjectAdd(@RequestBody RequestObject<StudentSubjectPojo> ro) throws TsuException {
        return ResponseObject.createSuccessfulResponse(studentService.studentSubjectAdd(ro.getData()));
    }

    @RequestMapping(value = "/api/student/subject/remove", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject studentSubjectRemove(@RequestBody RequestObject<StudentSubjectPojo> ro) throws TsuException {
        GeneralTools.checkRequiredProperties(ro.getData(), Arrays.asList("studentId", "subjectReleaseId"));
        return ResponseObject.createSuccessfulResponse(studentService.studentSubjectRemove(ro.getData()));
    }

    @RequestMapping(value = "/api/student/subject/search/extended", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseObject studentSubjectSearch(@RequestBody RequestObject<StudentSubjectPojo> ro) {
        return ResponseObject.createSuccessfulResponse(studentService.studentSubjectSearch(ro.getData(), ro.getPaging()));
    }

    @RequestMapping(value = "/api/student/edit", method = RequestMethod.POST)
    public ResponseObject editStudentInfo(@RequestBody RequestObject<StudentPojo> requestBody) {
        return ResponseObject.createSuccessfulResponse(studentService.editStudentInfo(requestBody.getData(), requestBody.getActionPerformer()));
    }

    @RequestMapping(value = "/api/student/semester/get", method = RequestMethod.POST)
    public ResponseObject getStudentSemesters(@RequestBody RequestObject<StudentSubjectPojo> requestBody) {
        return ResponseObject.createSuccessfulResponse(studentService.getStudentSemesters(requestBody.getData().getStudentId()));
    }


    @RequestMapping(value = "/api/student/subject/get", method = RequestMethod.POST)
    public ResponseObject getStudentSubjects(@RequestBody RequestObject<StudentSubjectPojo> requestBody) {
        return ResponseObject.createSuccessfulResponse(studentService.getStudentSubjects());
    }

    @RequestMapping(value = "/api/student/subject/grade/search", method = RequestMethod.POST)
    public ResponseObject getStudentSubjectGrades(@RequestBody RequestObject<StudentSubjectPojo> requestBody) throws TsuException {
        return ResponseObject.createSuccessfulResponse(studentService.getStudentSubjectsGrades(requestBody.getData().getStudentId(), requestBody.getData().getSemesterId(), null, requestBody.getActionPerformer()));
    }

    @RequestMapping(value = "/api/student/subject/record/search", method = RequestMethod.POST)
    public ResponseObject getStudentSubjectsGrades(@RequestBody RequestObject<StudentSubjectPojo> requestBody) throws TsuException {
        return ResponseObject.createSuccessfulResponse(studentService.getStudentSubjectsGrades(requestBody.getData().getStudentId(), requestBody.getData().getSemesterId(), requestBody.getData().getSubjectReleaseId(), requestBody.getActionPerformer()));
    }

    @RequestMapping(value = "/api/student/subject/records/add", method = RequestMethod.POST)
    public ResponseObject addStudentSubjectRecords(@RequestBody RequestObject<StudentSubjectRecordPojo> requestBody) throws TsuException {
        return ResponseObject.createSuccessfulResponse(studentService.addStudentSubjectRecords(requestBody.getData(), requestBody.getActionPerformer()));
    }

}
