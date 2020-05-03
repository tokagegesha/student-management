package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.apiutils.file.FileUtil;
import ge.gov.tsu.studentmanagement.dto.SemesterUniversityDTO;
import ge.gov.tsu.studentmanagement.dto.SubjectReleaseDTO;
import ge.gov.tsu.studentmanagement.entity.ProgrammeSubject;
import ge.gov.tsu.studentmanagement.entity.Subject;
import ge.gov.tsu.studentmanagement.entity.SubjectGrade;
import ge.gov.tsu.studentmanagement.entity.SubjectReleased;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.ProgrammeSubjectPojo;
import ge.gov.tsu.studentmanagement.pojo.SubjectPojo;
import ge.gov.tsu.studentmanagement.pojo.SubjectReleasedPojo;
import ge.gov.tsu.studentmanagement.repository.*;
import ge.gov.tsu.studentmanagement.rest.request.ActionPerformer;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.specification.view.SubjectReleasedSpecification;
import ge.gov.tsu.studentmanagement.specification.view.SubjectSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private SemesterRepository semesterRepository;
    private SubjectGradeRepository subjectGradeRepository;
    private ProgrammeSubjectRepository programmeSubjectRepository;
    private SubjectReleaseRepository subjectReleaseRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, SubjectGradeRepository subjectGradeRepository,
                          SubjectReleaseRepository subjectReleaseRepository,
                          ProgrammeSubjectRepository programmeSubjectRepository,
                          SemesterRepository semesterRepository) {
        this.subjectRepository = subjectRepository;
        this.subjectGradeRepository = subjectGradeRepository;
        this.programmeSubjectRepository = programmeSubjectRepository;
        this.subjectReleaseRepository = subjectReleaseRepository;
        this.semesterRepository = semesterRepository;
    }

    public Page<Subject> search(RequestObject<SubjectPojo> ro) {
        SubjectPojo subjectPojo = ro.getData();

        Specifications<Subject> spec = Specifications.where(SubjectSpecification.hasRecord());
        if (subjectPojo.getId() != null) {
            spec = spec.and(SubjectSpecification.hasId(subjectPojo.getId()));
        }
        if (subjectPojo.getName() != null) {
            spec = spec.and(SubjectSpecification.hasName(subjectPojo.getName().toLowerCase()));
        }

        return subjectRepository.findAll(
                spec,
                ro.getPaging()
        );
    }

    public Page<Subject> getSubjectExceptSomeValues(RequestObject<ProgrammeSubjectPojo> ro) {
        ProgrammeSubjectPojo data = ro.getData();
        List<ProgrammeSubject> buProgrammeId = programmeSubjectRepository.getByProgrammeId(data.getProgrammeId());
        List<Long> ids = new ArrayList<>();
        buProgrammeId.forEach(subjectInProgramme -> {
            ids.add(subjectInProgramme.getSubject().getId());
        });


        Specifications<Subject> spec = Specifications.where(SubjectSpecification.hasRecord());
        if (ids.size() > 0) {
            spec = spec.and(SubjectSpecification.notInSubjects(ids));
        }
        if (data.getSubjectName() != null) {
            spec = spec.and(SubjectSpecification.hasName(data.getSubjectName().toLowerCase()));
        }


        return subjectRepository.findAll(
                spec,
                ro.getPaging()
        );
    }

    public Subject add(RequestObject<SubjectPojo> ro) {
        Subject subject = new Subject();
        SubjectPojo data = ro.getData();
        subject.setId(data.getId());
        subject.setName(data.getName());
        subject.setCredits(data.getCredits());
        subject.setCreatedAt(new Date());
        subject.setExecutiveUserId(data.getExecutiveUserId());
        return subjectRepository.save(subject);
    }

    public Subject edit(RequestObject<SubjectPojo> ro) throws TsuException {
        SubjectPojo subjectPojo = ro.getData();
        Subject u = subjectRepository.getSubject(subjectPojo.getId());
        if (u == null) {
            throw new TsuException("Subject Not Found");
        }
        if (subjectPojo.getName() != null) {
            u.setName(subjectPojo.getName());
        }
        if (subjectPojo.getCredits() != null) {
            u.setCredits(subjectPojo.getCredits());
        }
        if (subjectPojo.getExecutiveUserId() != null) {
            u.setExecutiveUserId(subjectPojo.getExecutiveUserId());
        }
        if (subjectPojo.getLanguage() != null) {
            u.setLanguage(subjectPojo.getLanguage());
        }
        return subjectRepository.save(u);
    }

    public boolean delete(RequestObject<SubjectPojo> ro) {
        SubjectPojo data = ro.getData();
        subjectRepository.delete(data.getId());
        return true;
    }

    @Transactional
    public SubjectReleased addReleasedSubjects(RequestObject<SubjectReleasedPojo> ro) throws TsuException {
        Long semesterId = ro.getData().getSemesterId();
        if (semesterId == null) {
            throw new TsuException("Semester_Id Not Found");
        }
        SubjectReleasedPojo subjects = ro.getData();
        SubjectReleased subjectReleased = new SubjectReleased();
        subjectReleased.setMaxStudents(subjects.getMaxStudent());
        subjectReleased.setMinStudents(subjects.getMinStudent());
        subjectReleased.setSubject(subjectRepository.findOne(subjects.getSubjectId()));
        subjectReleased.setSemester(semesterRepository.findOne(semesterId));
        subjectReleased.setCreatedAt(new Date());
        if (subjects.getSyllabus() != null) {
            String syllPath = saveSyllabus(subjects.getSyllabus());
            subjectReleased.setSyllabusPath(syllPath);
        }

        subjectReleaseRepository.save(subjectReleased);

        return subjectReleased;
    }

    private String saveSyllabus(MultipartFile syllabus) {
        String studentDirectory = FileUtil.creteCandidateDirectory();
        String syllabuses = FileUtil.createCandidateDocumentDirectory(studentDirectory + File.separator + "syllabuses");
        return FileUtil.saveFileInDirectory(syllabuses, UUID.randomUUID().toString(), syllabus).getAbsolutePath();
    }

    public Page<SubjectReleaseDTO> searchSubjectRelease(RequestObject<SubjectReleasedPojo> ro) {
        SubjectReleasedPojo data = ro.getData();

        Specifications<SubjectReleased> spec = Specifications.where(SubjectReleasedSpecification.hasRecord());
        if (data.getId() != null) {
            spec = spec.and(SubjectReleasedSpecification.hasId(data.getId()));
        }
        if (data.getSemesterId() != null) {
            spec = spec.and(SubjectReleasedSpecification.hasSemesterId(data.getSemesterId()));
        }
        Page<SubjectReleased> releaseds = subjectReleaseRepository.findAll(spec, ro.getPaging());


        return releaseds.map(item ->
                new SubjectReleaseDTO(item.getId(), item.getSubject().getName(), item.getSubject().getCredits(), item.getSubject().getId(),
                        item.getSubject().getLanguage() != null ? item.getSubject().getLanguage().toString() : "", item.getSemester().getId(), item.getMaxStudents(), item.getMinStudents(),
                        item.getSemester().getAdministrationRegBegin(), item.getSemester().getAdministrationRegEnd(),
                        item.getSemester().getAcademicRegBegin(), item.getSemester().getAcademicRegEnd(), item.getSemester().getBeginDate(),
                        item.getSemester().getEndDate(), item.getSemester().getSeason(), item.getSemester().getYear())

        );

    }

    public Page<Subject> searchUnreleasedSubjects(RequestObject<SubjectReleasedPojo> ro) {
        SubjectReleasedPojo data = ro.getData();
        return subjectRepository.getSubjectExpectReleasedData(data.getSemesterId(), ro.getPaging());
    }

    public void deleteSubjectRelease(RequestObject<SubjectReleasedPojo> ro) throws TsuException {
        SubjectReleasedPojo data = ro.getData();
        SubjectReleased subjectRelease = subjectReleaseRepository.getSubjectRelease(data.getId());
        if (subjectRelease == null) {
            throw new TsuException("Subject_Release Not Found");
        }
        subjectReleaseRepository.delete(subjectRelease);
    }

    public SubjectGrade addSubjectGradeType(Long subjectReleasedId, Integer max, String name, Integer type, ActionPerformer actionPerformer) {
        SubjectGrade data = new SubjectGrade();
        data.setSubjectReleaseId(subjectReleasedId);
        data.setMax(max);
        data.setType(type);
        data.setName(name);
        return subjectGradeRepository.save(data);
    }

    public List<SubjectGrade> subjectGradeSearch(Long subjectReleasedId, ActionPerformer actionPerformer) {
        return subjectGradeRepository.getSubjectGrades(subjectReleasedId);
    }
}
