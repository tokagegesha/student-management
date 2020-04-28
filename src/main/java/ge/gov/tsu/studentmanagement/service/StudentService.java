package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.apiutils.file.FileUtil;
import ge.gov.tsu.studentmanagement.entity.*;
import ge.gov.tsu.studentmanagement.entity.view.*;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.StudentPojo;
import ge.gov.tsu.studentmanagement.pojo.StudentRecordPojo;
import ge.gov.tsu.studentmanagement.pojo.StudentSubjectPojo;
import ge.gov.tsu.studentmanagement.pojo.StudentSubjectRecordPojo;
import ge.gov.tsu.studentmanagement.repository.*;
import ge.gov.tsu.studentmanagement.rest.request.ActionPerformer;
import ge.gov.tsu.studentmanagement.specification.view.StudentExtendedSpecification;
import ge.gov.tsu.studentmanagement.specification.view.StudentSubjectExtendedSpecification;
import ge.gov.tsu.studentmanagement.specification.view.StudentSubjectRecordSpecification;
import ge.gov.tsu.studentmanagement.util.DocxGenerator;
import ge.gov.tsu.studentmanagement.util.MailStaticData;
import ge.gov.tsu.studentmanagement.util.PersonalInfo;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {

    public enum UserStatus {
        ACTIVE(1),
        INACTIVE(2);

        private Integer value;

        UserStatus(Integer i) {
            this.value = i;
        }

        public Integer value() {
            return value;
        }

    }

    public enum StudentStatus {
        PENDING(1),
        ACCEPTED(2),
        REJECTED(3),
        ACTIVE(4),
        CANCELED(5),
        PAUSED(6),
        FINISHED(7);


        private Integer value;
        private static Map<Integer, StudentStatus> map;

        static {
            map = new HashMap<>();
            for (StudentStatus c : StudentStatus.class.getEnumConstants()) {
                map.put(c.value(), c);
            }
        }

        public static StudentStatus getInstatnce(int status) {
            return map.get(status);
        }

        StudentStatus(Integer i) {
            this.value = i;
        }

        public Integer value() {
            return value;
        }

    }

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;

    @Value("${mail.from}")
    private String mailFrom;

    private StudentRepository studentRepository;
    private UserService userService;
    private UserRoleService userRoleService;
    private StudentExtendedRepository studentExtendedRepository;
    private SecurityTokenService securityTokenService;
    private SettingService settingService;
    private StudentSemestersRepository studentSemestersRepository;
    private StudentSubjectRepository studentSubjectRepository;
    private StudentSubjectExtendedRepository studentSubjectExtendedRepository;
    private StudentSubjectRecordRepository studentSubjectRecordRepository;
    private StudentSubjectGradeRepository studentSubjectGradeRepository;
    private ExchangeProgrammeRepository exchangeProgrammeRepository;
    private StudentSemesterExtendedRepository studentSemesterExtendedRepository;
    private UniversityService universityService;
    private SemesterRepository semesterRepository;

    @Autowired
    public StudentService(
            StudentSubjectRecordRepository studentSubjectRecordRepository,
            StudentRepository studentRepository,
            UserService userService,
            UserRoleService userRoleService,
            StudentExtendedRepository studentExtendedRepository,
            SecurityTokenService securityTokenService,
            SettingService settingService,
            StudentSemestersRepository studentSemestersRepository,
            StudentSubjectRepository studentSubjectRepository,
            StudentSubjectExtendedRepository studentSubjectExtendedRepository,
            StudentSubjectGradeRepository studentSubjectGradeRepository,
            ExchangeProgrammeRepository exchangeProgrammeRepository,
            StudentSemesterExtendedRepository studentSemesterExtendedRepository,
            UniversityService universityService,
            SemesterRepository semesterRepository) {
        this.studentSubjectRecordRepository = studentSubjectRecordRepository;


        this.studentRepository = studentRepository;
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.studentExtendedRepository = studentExtendedRepository;
        this.securityTokenService = securityTokenService;
        this.settingService = settingService;
        this.studentSemestersRepository = studentSemestersRepository;
        this.studentSubjectRepository = studentSubjectRepository;
        this.studentSubjectExtendedRepository = studentSubjectExtendedRepository;
        this.studentSubjectGradeRepository = studentSubjectGradeRepository;
        this.exchangeProgrammeRepository = exchangeProgrammeRepository;
        this.studentSemesterExtendedRepository = studentSemesterExtendedRepository;
        this.universityService = universityService;
        this.semesterRepository = semesterRepository;
    }

    @Transactional
    public Student registerStudent(
            StudentPojo studentPojo,
            MultipartFile letterOfNomination,
            MultipartFile cv,
            MultipartFile diploma,
            MultipartFile learningAgreement,
            MultipartFile universityRecord,
            MultipartFile picture,
            MultipartFile passport,
            MultipartFile proofOfEnglishSkill,
            MultipartFile healthInsurance
    ) throws TsuException {
        Student student = new Student();

        student.setUniversityId(studentPojo.getUniversityId());
        student.setFirstName(studentPojo.getFirstName());
        student.setLastName(studentPojo.getLastName());
        student.setGender(studentPojo.getGender());
        student.setCitizenship(studentPojo.getCitizenship());
        student.setCountryOfResidence(studentPojo.getCountryOfResidence());
        student.setBirthDate(studentPojo.getBirthDate());
        student.setAddress(studentPojo.getAddress());
        student.setCity(studentPojo.getCity());
        student.setZipCode(studentPojo.getZipCode());
        student.setCountry(studentPojo.getCountry());
        student.setEmail(studentPojo.getEmail());
        student.setPhone(studentPojo.getPhone());
        student.setContactPersonName(studentPojo.getContactPersonName());
        student.setContactPersonAddress(studentPojo.getContactPersonAddress());
        student.setContactPersonPhone(studentPojo.getContactPersonPhone());
        student.setContactPersonRelationship(studentPojo.getContactPersonRelationship());
        student.setAccommodation(studentPojo.getAccommodation());
        student.setStatus(StudentStatus.PENDING.value());
        student.setPassportNumber(studentPojo.getPassportNumber());
        student.setUserId(studentPojo.getUserId());
        student.setDegree(studentPojo.getDegree());

        ExchangeProgramme selectedExchangeProgramme = exchangeProgrammeRepository.findOne(studentPojo.getExchangeProgrammeId());
        if (selectedExchangeProgramme == null) throw new TsuException("Invalid ExchangeProgrammeId");
        student.setExchangeProgramme(selectedExchangeProgramme);


        String letterOfNominationPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "letterOfNomination", letterOfNomination);
        String cvPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "cv", cv);
        String diplomaPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "diploma", diploma);
        String learningAgreementPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "learningAgreement", learningAgreement);
        String universityRecordPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "universityRecord", universityRecord);
        String picturePath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "picture", picture);
        String passportPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "passport", passport);
        String proofOfEnglishSkillPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "proofOfEnglishSkill", proofOfEnglishSkill);
        String healthInsurancePah = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentPojo.getPassportNumber(), "healthInsurance", healthInsurance);

        student.setLetterOfNominationPath(letterOfNominationPath);
        student.setCvPath(cvPath);
        student.setDiplomaPath(diplomaPath);
        student.setLearningAgreementPath(learningAgreementPath);
        student.setUniversityRecordPath(universityRecordPath);
        student.setPicturePath(picturePath);
        student.setPassportPath(passportPath);
        student.setProofOfEnglishSkillPath(proofOfEnglishSkillPath);
        student.setHealthInsurancePath(healthInsurancePah);

        student.setRegistrationDate(new Date());
        Student saveStudent = studentRepository.save(student);

        if (studentPojo.getSemesterIds().size() == 0) {
            throw new TsuException("Semester Is Not Chosen");
        }
        studentPojo.getSemesterIds().forEach(aLong -> {
            StudentSemester studentSemester = new StudentSemester();
            studentSemester.setStudentId(saveStudent.getId());
            studentSemester.setSemesterId(aLong);
            studentSemestersRepository.save(studentSemester );
        });

        return saveStudent;
    }


    @Transactional
    public Student updateStudentDocumentInfo(
            MultipartFile letterOfNomination,
            MultipartFile cv,
            MultipartFile diploma,
            MultipartFile learningAgreement,
            MultipartFile universityRecord,
            MultipartFile picture,
            MultipartFile passport,
            MultipartFile proofOfEnglishSkill,
            MultipartFile healthInsurance
    ) throws TsuException {

        User user = this.userService.getAuthorisedUser();
        if (user == null) {
            throw new TsuException("User Not Found");
        }
        Student studentExtended = studentRepository.getStudentByUserId(user.getId());

        if (studentExtended == null || studentExtended.getPassportNumber() == null) {
            throw new TsuException("Student Not Found");
        }

        if (letterOfNomination != null && !letterOfNomination.isEmpty() && studentExtended.getLetterOfNominationPath().isEmpty()) {
            String letterOfNominationPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "letterOfNomination", letterOfNomination);
            if (letterOfNominationPath == null || letterOfNominationPath.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setLetterOfNominationPath(letterOfNominationPath);
        }

        if (cv != null && !cv.isEmpty() && studentExtended.getCvPath().isEmpty()) {
            String cvPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "cv", cv);
            if (cvPath == null || cvPath.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setCvPath(cvPath);
        }

        if (diploma != null && !diploma.isEmpty() && studentExtended.getDiplomaPath().isEmpty()) {
            String diplomaPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "diploma", diploma);
            if (diplomaPath == null || diplomaPath.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setDiplomaPath(diplomaPath);
        }

        if (learningAgreement != null && !learningAgreement.isEmpty() && studentExtended.getLearningAgreementPath().isEmpty()) {
            String learningAgreementPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "learningAgreement", learningAgreement);
            if (learningAgreementPath == null || learningAgreementPath.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setLearningAgreementPath(learningAgreementPath);
        }

        if (universityRecord != null && !universityRecord.isEmpty() && studentExtended.getUniversityRecordPath().isEmpty()) {
            String universityRecordPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "universityRecord", universityRecord);
            if (universityRecordPath == null || universityRecordPath.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setUniversityRecordPath(universityRecordPath);
        }

        if (picture != null && !picture.isEmpty() && studentExtended.getPicturePath().isEmpty()) {
            String picturePath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "picture", picture);
            if (picturePath == null || picturePath.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setPicturePath(picturePath);
        }

        if (passport != null && !passport.isEmpty() && studentExtended.getPassportPath().isEmpty()) {
            String passportPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "passport", passport);
            if (passportPath == null || passportPath.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setPassportPath(passportPath);
        }

        if (proofOfEnglishSkill != null && !proofOfEnglishSkill.isEmpty() && studentExtended.getProofOfEnglishSkillPath().isEmpty()) {
            String proofOfEnglishSkillPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "proofOfEnglishSkill", proofOfEnglishSkill);
            if (proofOfEnglishSkillPath == null || proofOfEnglishSkillPath.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setProofOfEnglishSkillPath(proofOfEnglishSkillPath);
        }

        if (healthInsurance != null && !healthInsurance.isEmpty() && studentExtended.getHealthInsurancePath().isEmpty()) {
            String healthInsurancePah = FileUtil.saveFile(FileUtil.studentDataBaseDirName, studentExtended.getPassportNumber(), "healthInsurance", healthInsurance);
            if (healthInsurancePah == null || healthInsurancePah.isEmpty()) {
                throw new TsuException("Unable to save files");
            }
            studentExtended.setHealthInsurancePath(healthInsurancePah);
        }


        return studentRepository.save(studentExtended);
    }

    public Page<StudentExtended> searchExtended(StudentPojo data, Pageable pageable) throws TsuException {
        Specifications<StudentExtended> spec = Specifications.where(StudentExtendedSpecification.hasRecord());
        if (data.getId() != null) {
            spec = spec.and(StudentExtendedSpecification.hasId(data.getId()));
        }
        if (data.getUniversityId() != null) {
            spec = spec.and(StudentExtendedSpecification.hasUniversityId(data.getUniversityId()));
        }
        if (data.getUserId() != null) {
            spec = spec.and(StudentExtendedSpecification.hasUserId(data.getUserId()));
        }
        if (data.getEmail() != null) {
            spec = spec.and(StudentExtendedSpecification.hasEmail(data.getEmail()));
        }
        if (data.getStatuses() != null) {
            spec = spec.and(StudentExtendedSpecification.hasStatuses(data.getStatuses()));
        }
        if (data.getSearchString() != null) {
            spec = spec.and(StudentExtendedSpecification.hasSearchString(data.getSearchString()));
        }
        if (data.getSemesterIds() != null && data.getSemesterIds().size() > 0) {
            spec = spec.and(StudentExtendedSpecification.hasSemesterIds(data.getSemesterIds()));
        }
        return studentExtendedRepository.findAll(
                spec,
                pageable);
    }

    /*@PersonalInfo*/
    public StudentExtended getStudent(Long id) throws TsuException {
        System.out.println("<<<<<<<<<<<<<<<<" + id);
        User userExtended = userService.userDetails(id);
        if (userExtended == null) {
            throw new TsuException("Invalid Sesion");
        }
        return studentExtendedRepository.getStudentByUserId(userExtended.getId());
    }

    @PersonalInfo
    public StudentExtended getStudentByUserId(Long userId) {
        // TODO: 9/17/2017 check performer permission
        return studentExtendedRepository.getStudentByUserId(userId);
    }
  /*  public Page<Candidate> search(CandidatePojo data, Pageable pageable) {
        return candidateRepository.search(data.getId(), data.getProgrammeReleaseId(), data.getId(), data.getEmail(), new Date(), pageable);
    }

    */


    @Transactional
    public Student accept(Long studentId, MultipartFile letterOfAcceptance) throws TsuException {
        Student newStudent = null;
        Student student = studentRepository.getCandidateById(studentId);
        if (student != null) {
            //კანდიდანტის გამოგზავნილი აპლიკაციის დადასტურება
            student.setStatus(StudentStatus.ACCEPTED.value());
            String letterOfNominationPath = FileUtil.saveFile(FileUtil.studentDataBaseDirName, student.getPassportNumber(), "letterOfAcceptance", letterOfAcceptance);
            student.setLetterOfAcceptancePath(letterOfNominationPath);

            newStudent = studentRepository.save(student);


            User user = userService.getUser(student.getUserId());
            if (user == null) {
                throw new TsuException("User Not Found");
            }
            user.setStatus(User.Type.ACCEPTED_STUDENT.getValue());
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            MailService.sendMail(mailUsername, mailPassword, mailFrom, user.getEmail(), "tsu", MailStaticData.CANDIDATE_APPROVED_TEXT);
                            return 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 1;
                    });

            userService.saveUser(user);

        } else {
            throw new TsuException("Candidate Not Found");
        }
        return newStudent;

    }

    @Transactional
    public Student reject(StudentPojo data) throws TsuException {
        Student newStudent = null;
        Student student = studentRepository.getCandidateById(data.getId());
        if (student != null) {
            //კანდიდანტის გამოგზავნილი აპლიკაციის დადასტურება
            student.setStatus(StudentStatus.REJECTED.value());
            newStudent = studentRepository.save(student);
            User user = userService.getUser(student.getUserId());
            if (user == null) {
                throw new TsuException("User Not Found");
            }
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            MailService.sendMail(mailUsername, mailPassword, mailFrom, user.getEmail(), "tsu", MailStaticData.CANDIDATE_REJECT_TEXT + "Reason: " + data.getMessage());
                            return 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 1;
                    });

            userService.saveUser(user);

        } else {
            throw new TsuException("Candidate Not Found");
        }
        return newStudent;

    }

    @Transactional
    public Student pend(StudentPojo data) throws TsuException {
        Student newStudent = null;
        Student student = studentRepository.getCandidateById(data.getId());
        if (student != null) {
            //კანდიდანტის გამოგზავნილი აპლიკაციის დადასტურება
            student.setStatus(StudentStatus.PENDING.value());
            newStudent = studentRepository.save(student);
        } else {
            throw new TsuException("Candidate Not Found");
        }
        return newStudent;

    }


    public StudentSubject studentSubjectAdd(StudentSubjectPojo data) throws TsuException {
        StudentSubject ss = studentSubjectRepository.findByStudentIdAndSubjectId(data.getStudentId(), data.getSubjectReleaseId());
        if (ss != null) {
            throw new TsuException("Student Already Has This Subject");
        }
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setStudentId(data.getStudentId());
        studentSubject.setSubjectReleaseId(data.getSubjectReleaseId());

        return studentSubjectRepository.save(studentSubject);

    }

    public Page<StudentSubjectExtended> studentSubjectSearch(StudentSubjectPojo data, PageRequest pageRequest) {

        Specifications<StudentSubjectExtended> spec = Specifications.where(StudentSubjectExtendedSpecification.hasRecord());
        if (data.getId() != null) {
            spec = spec.and(StudentSubjectExtendedSpecification.hasId(data.getId()));
        }
        if (data.getStudentId() != null) {
            spec = spec.and(StudentSubjectExtendedSpecification.hasStudentid(data.getStudentId()));
        }

        if (data.getSemesterId() != null) {
            spec = spec.and(StudentSubjectExtendedSpecification.hasSemesterId(data.getSemesterId()));
        }

        if (data.getSubjectReleaseId() != null) {
            spec = spec.and(StudentSubjectExtendedSpecification.hasSubjectReleaseId(data.getSubjectReleaseId()));
        }

        if (data.getSubjectId() != null) {
            spec = spec.and(StudentSubjectExtendedSpecification.hasSubjectId(data.getSubjectId()));
        }

        return studentSubjectExtendedRepository.findAll(spec, pageRequest);
    }

    public boolean studentSubjectRemove(StudentSubjectPojo data) throws TsuException {
        StudentSubject ss = studentSubjectRepository.findByStudentIdAndSubjectId(
                data.getStudentId(),
                data.getSubjectReleaseId());
        if (ss == null) {
            throw new TsuException("Student Does Not Have This Subject");
        }
        studentSubjectRepository.delete(ss);
        return true;
    }


    public ByteArrayOutputStream getStudentFile(Long studentId) {
        StudentExtended studentExtended = studentExtendedRepository.findOne(studentId);
        if (studentExtended == null) {
            //todo error must be thrown
            return null;
        }
        try {
            return FileUtil.createZip(studentExtended);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteArrayOutputStream getStudentAcceptanceFile(Long studentId) throws TsuException, IOException {
        StudentExtended studentExtended = studentExtendedRepository.findOne(studentId);
        if (studentExtended == null) {
            //todo error must be thrown
            return null;
        }

        University universityExtended = universityService.getUniversity(studentExtended.getUniversityId());
        if (universityExtended == null) {
            throw new TsuException("university Semester Not Found");
        }
        University tsu = universityService.getByName("tsu");
        if (tsu == null) {
            throw new TsuException("Tsu Semester Not Found");
        }


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XWPFDocument doc = DocxGenerator.generateStudentAcceptanceDocument(
                studentExtended.getGender(),
                studentExtended.getFirstName(),
                studentExtended.getLastName(),
                new SimpleDateFormat("MMM dd yyyy").format(studentExtended.getBirthDate()),
                studentExtended.getSemesters(),
                universityExtended.getName(),
                universityExtended.getCountry().getName(),
                universityExtended.getDepartmentHeadName(),
                tsu.getName(),
                tsu.getRector());
        doc.write(out);
        out.close();
        return out;
    }

    public byte[] getStudentAcceptanceFileForDownload(Long studentId) throws TsuException, IOException {
        StudentExtended studentExtended = studentExtendedRepository.findOne(studentId);
        if (studentExtended == null) {
            //todo error must be thrown
            return null;
        }


        File file = new File(FileUtil.studentDataBaseDir + studentExtended.getLetterOfAcceptancePath());
        System.out.println(file.getAbsolutePath());
        Path path = Paths.get(file.getAbsolutePath());
        return Files.readAllBytes(path);

    }

    public ByteArrayOutputStream getStudentDocument(Long studentId, Long semesterId) throws Exception {

        Student student = studentRepository.getCandidateById(studentId);
        if (student == null) {
            throw new TsuException("Student Not Found");
        }

        StudentSemesterExtended studentSemester = studentSemesterExtendedRepository.getStudentByIdAndSemesterId(studentId, semesterId);
        if (studentSemester == null) {
            throw new TsuException("Student Semester Not Found");
        }

        University universityExtended = universityService.getUniversity(student.getUniversityId());
        if (universityExtended == null) {
            throw new TsuException("university Semester Not Found");
        }
        University tsu = universityService.getByName("tsu");
        if (tsu == null) {
            throw new TsuException("Tsu Semester Not Found");
        }

        List<StudentSubjectRecord> record = studentSubjectRecordRepository.getByStudentIdAndSemesterId(studentId, semesterId);


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XWPFDocument doc = DocxGenerator.generateStudentRecordDocument(
                studentSemester.getYear(),
                studentSemester.getSeason(),
                tsu.getName(),
                tsu.getTelephone(),
                tsu.getEmail(),
                student.getFirstName() + " " + student.getLastName(),
                new SimpleDateFormat("yyyy-MM-dd").format(student.getBirthDate()),
                student.getGender(),
                student.getPassportNumber(),
                universityExtended.getName(),
                universityExtended.getTelephone(),
                universityExtended.getEmail(),
                universityExtended.getDepartmentHeadName(),
                record,
                tsu.getRector());
        doc.write(out);
        out.close();
        return out;
    }

    public ByteArrayOutputStream getStudentGridData(StudentPojo data) throws Exception {
        List<StudentExtended> students = searchExtended(data, null).getContent();
        List<Semester> semesters = semesterRepository.getByIds(data.getSemesterIds());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XWPFDocument doc = DocxGenerator.generateStudentGridData(semesters, students);
        doc.write(out);
        out.close();
        return out;
    }


    @Transactional
    public Student editStudentInfo(StudentPojo data, ActionPerformer actionPerformer) {
        // TODO: 18-Sep-17 required permission annotation need
        Student oldUser = studentRepository.getCandidateById(data.getId());

        if (data.getCitizenship() != null && !data.getCitizenship().trim().equals("")) {
            oldUser.setCitizenship(data.getCitizenship());
        }
        if (data.getCountryOfResidence() != null && !data.getCountryOfResidence().trim().equals("")) {
            oldUser.setCountryOfResidence(data.getCountryOfResidence());
        }
        if (data.getAddress() != null && !data.getAddress().trim().equals("")) {
            oldUser.setAddress(data.getAddress());
        }
        if (data.getCity() != null && !data.getCity().trim().equals("")) {
            oldUser.setCity(data.getCity());
        }
        if (data.getBirthDate() != null) {
            oldUser.setBirthDate(data.getBirthDate());
        }
        if (data.getGender() != null) {
            oldUser.setGender(data.getGender());
        }
        if (data.getContactPersonAddress() != null && !data.getContactPersonAddress().trim().equals("")) {
            oldUser.setContactPersonAddress(data.getContactPersonAddress());
        }
        if (data.getContactPersonName() != null && !data.getContactPersonName().trim().equals("")) {
            oldUser.setContactPersonName(data.getContactPersonName());
        }
        if (data.getContactPersonPhone() != null && !data.getContactPersonPhone().trim().equals("")) {
            oldUser.setContactPersonPhone(data.getContactPersonPhone());
        }
        if (data.getContactPersonRelationship() != null && !data.getContactPersonRelationship().trim().equals("")) {
            oldUser.setContactPersonRelationship(data.getContactPersonRelationship());
        }
        if (data.getSemesterIds() != null && data.getSemesterIds().size() > 0) {
            updateStudentChosenSemester(oldUser.getId(), data.getSemesterIds());
        }


        return studentRepository.save(oldUser);
    }

    private void updateStudentChosenSemester(Long studentId, List<Long> semesterIds) {
        List<StudentSemester> semesters = studentSemestersRepository.getByStudentId(studentId);
        semesters.forEach(studentSemester -> {
            studentSemestersRepository.delete(studentSemester);
        });

        semesterIds.forEach(aLong -> {
            StudentSemester studentSemester = new StudentSemester();
            studentSemester.setStudentId(studentId);
            studentSemester.setSemesterId(aLong);
            studentSemestersRepository.save(studentSemester);
        });
    }

    public List<StudentSemester> getStudentSemesters(Long studentId) {
        return studentSemestersRepository.getByStudentId(studentId);
    }


    public Object getStudentSubjects() {
        return null;
        //UserExtended userExtended = userService.userDetails(null);

    }

    public List<StudentSubjectRecord> getStudentSubjectsGrades(Long userId, Long semesterId, Long subjectReleaseId, ActionPerformer actionPerformer) throws TsuException {

        // TODO: 12/5/2017 პოტენციური ბაგი ძებნის პარამეტრს ქვია studentId შესამოწმებელია რა მნიშვნელობა იდება
        User userExtended = userService.userDetails(userId);
        StudentExtended studentByUserId = studentExtendedRepository.getStudentByUserId(userExtended.getId());

        Specifications<StudentSubjectRecord> spec = Specifications.where(StudentSubjectRecordSpecification.hasRecord());
        if (studentByUserId != null) {
            spec = spec.and(StudentSubjectRecordSpecification.hasStudentid(studentByUserId.getId()));
        }
        if (semesterId != null) {
            spec = spec.and(StudentSubjectRecordSpecification.hasSemesterId(semesterId));
        }
        if (subjectReleaseId != null) {
            spec = spec.and(StudentSubjectRecordSpecification.hasSubjectReleaseId(subjectReleaseId));
        }

        return studentSubjectRecordRepository.findAll(spec);
    }


    @Transactional
    public Boolean addStudentSubjectRecords(StudentSubjectRecordPojo data, ActionPerformer actionPerformer) throws TsuException {
        if (data.getStudentGrades() != null && data.getStudentGrades().size() > 0) {
            List<StudentSubjectGrade> studentSubjectGrades = new ArrayList<>();

            for (StudentRecordPojo p : data.getStudentGrades()) {

                if (studentSubjectGradeRepository.getByStudentIdAndGradeId(p.getStudentId(), p.getGradeId()).size() > 0) {
                    throw new TsuException("Duplicate Grade");
                }

                StudentSubjectGrade studentSubjectGrade = new StudentSubjectGrade();
                studentSubjectGrade.setStudentId(p.getStudentId());
                studentSubjectGrade.setSubjectGradeId(p.getGradeId());
                studentSubjectGrade.setGrade(p.getGrade());

                studentSubjectGrades.add(studentSubjectGrade);
            }
            studentSubjectGradeRepository.save(studentSubjectGrades);

        }
        return true;
    }

/*
    public SecurityToken initiateUserEmailVerification(User userData, String link) throws ResponseObject {
        SecurityToken securityToken = securityTokenService.generateNewSecurityToken(userData.getId(), userData.getEmail(), SecurityToken.ActionType.EMAIL_VERIFICATION, 3, 0, 0);
        String template = settingService.getApplicationSettingValue("UM.USER_EMAIL_VERIFICATION_TEMPLATE", String.class);
        Object[] params = new Object[]{link, securityToken.getToken()};
        String body = MessageFormat.format(template, params);
       // MailServiceClient.send(userData.getEmail(), "MSDA email verification", body, true);
        return securityToken;
    }*/

   /* public University add(RequestObject<UniversityPojo> ro) {
        University university = new University();
        UniversityPojo data = ro.getData();
        university.setId(data.getId());
        university.setAddress(data.getAddress());
        university.setInfo(data.getInfo());
        university.setName(data.getName());
        university.setCountryId(data.getCountryId());
        return entityWithArchiveService.save(university, new ActionPerformer(), programmeRepository);
    }
*/

   /* public boolean delete(RequestObject<UniversityPojo> ro) {
        UniversityPojo data = ro.getData();
        entityWithArchiveService.delete(data.getId(), new ActionPerformer(), programmeRepository);
        return true;
    }*/
}
