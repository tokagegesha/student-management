package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.ProgrammeSubject;
import ge.gov.tsu.studentmanagement.entity.StudentSubject;
import ge.gov.tsu.studentmanagement.entity.SubjectReleased;
import ge.gov.tsu.studentmanagement.entity.view.ProgrammeSubjectExtended;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.ProgrammeSubjectPojo;
import ge.gov.tsu.studentmanagement.repository.*;
import ge.gov.tsu.studentmanagement.specification.view.ProgrammeSubjectExtendedSpecification;
import ge.gov.tsu.studentmanagement.specification.view.ProgrammeSubjectSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProgrammeSubjectService {


    private ProgrammeSubjectExtendedRepository programmeSubjectExtendedRepository;
    private ProgrammeSubjectRepository programmeSubjectRepository;
    private ProgrammeRepository programmeRepository;
    private SemesterRepository semesterRepository;
    private SubjectRepository subjectRepository;
    private SubjectReleaseRepository subjectReleaseRepository;
    private StudentSubjectRepository studentSubjectRepository;

    @Autowired
    public ProgrammeSubjectService(ProgrammeSubjectExtendedRepository programmeSubjectExtendedRepository,
                                   ProgrammeSubjectRepository programmeSubjectRepository,
                                   ProgrammeRepository programmeRepository,
                                   SemesterRepository semesterRepository,
                                   SubjectReleaseRepository subjectReleaseRepository,
                                   SubjectRepository subjectRepository,
                                   StudentSubjectRepository studentSubjectRepository) {
        this.programmeSubjectExtendedRepository = programmeSubjectExtendedRepository;
        this.programmeSubjectRepository = programmeSubjectRepository;
        this.subjectRepository = subjectRepository;
        this.subjectReleaseRepository = subjectReleaseRepository;
        this.programmeRepository = programmeRepository;
        this.semesterRepository = semesterRepository;
        this.studentSubjectRepository = studentSubjectRepository;
    }

    public Page<ProgrammeSubjectExtended> search(ProgrammeSubjectPojo data, Pageable pageable) {
        Specifications<ProgrammeSubjectExtended> spec = Specifications.where(ProgrammeSubjectExtendedSpecification.hasRecord());
        if (data.getId() != null) {
            spec = spec.and(ProgrammeSubjectExtendedSpecification.hasId(data.getId()));
        }

        if (data.getSubjectId() != null) {
            spec = spec.and(ProgrammeSubjectExtendedSpecification.hasSubjectId(data.getSubjectId()));
        }
        if (data.getSemesterId() != null) {
            spec = spec.and(ProgrammeSubjectExtendedSpecification.hasSemesterId(data.getSemesterId()));
        }

        if (data.getSemesterYear() != null) {
            spec = spec.and(ProgrammeSubjectExtendedSpecification.hasSemesterYear(data.getSemesterYear()));
        }

        if (data.getProgrammeId() != null) {
            spec = spec.and(ProgrammeSubjectExtendedSpecification.hasProgrammeId(data.getProgrammeId()));
        }

        return programmeSubjectExtendedRepository.findAll(
                spec,
                pageable
        );

    }

    public Page<SubjectReleased> getCutProgrammeSubjects(ProgrammeSubjectPojo data, Pageable pageable) {

        List<Long> ids = new ArrayList<>();
        List<StudentSubject> studentId = studentSubjectRepository.findByStudentId(data.getStudentId());
        if (studentId != null) {
            if (studentId.size() > 0) {
                studentId.forEach(studentSubject ->
                        ids.add(studentSubject.getSubjectReleaseId()));
            } else {
                ids.add(-1L);
            }
        }
        return subjectReleaseRepository.getStudentSubjectExpectAlreadyChosenSubjects(
                ids,
                data.getSemesterId(),
                pageable
        );

    }

    public Page<ProgrammeSubject> searchProgrammeSubject(ProgrammeSubjectPojo data, Pageable pageable) {
        Specifications<ProgrammeSubject> spec = Specifications.where(ProgrammeSubjectSpecification.hasRecord());
        if (data.getId() != null) {
            spec = spec.and(ProgrammeSubjectSpecification.hasId(data.getId()));
        }

        if (data.getSubjectId() != null) {
            spec = spec.and(ProgrammeSubjectSpecification.hasSubjectId(data.getSubjectId()));
        }
        if (data.getSemesterId() != null) {
            spec = spec.and(ProgrammeSubjectSpecification.hasSemesterId(data.getSemesterId()));
        }

        if (data.getProgrammeId() != null) {
            spec = spec.and(ProgrammeSubjectSpecification.hasProgrammeId(data.getProgrammeId()));
        }

        return programmeSubjectRepository.findAll(spec, pageable);

    }

    public ProgrammeSubject addProgrammeSubject(ProgrammeSubjectPojo data) throws TsuException {
        ProgrammeSubject obj = programmeSubjectRepository.getByProgrammeIdAndSemesterIdAndSubjectId(data.getSemesterId(), data.getSubjectId(), data.getProgrammeId());
        if (obj != null) {
            throw new TsuException("Subject Programme Exists");
        }
        ProgrammeSubject programmeSubject = new ProgrammeSubject();
        programmeSubject.setProgramme(programmeRepository.findOne(data.getProgrammeId()));
        programmeSubject.setSemester(semesterRepository.findOne(data.getSemesterId()));
        programmeSubject.setSubject(subjectRepository.findOne(data.getSubjectId()));
        programmeSubject.setCreatedAt(new Date());
        return programmeSubjectRepository.save(programmeSubject);
    }
}
