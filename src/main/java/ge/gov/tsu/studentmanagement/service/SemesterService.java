package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.Semester;
import ge.gov.tsu.studentmanagement.entity.SemesterUniversity;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.SemesterPojo;
import ge.gov.tsu.studentmanagement.pojo.SemesterUniversityPojo;
import ge.gov.tsu.studentmanagement.pojo.SemesterVisibilityPojo;
import ge.gov.tsu.studentmanagement.repository.SemesterRepository;
import ge.gov.tsu.studentmanagement.repository.SemesterUniversityRepository;
import ge.gov.tsu.studentmanagement.repository.UniversityRepository;
import ge.gov.tsu.studentmanagement.rest.request.ActionPerformer;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.specification.view.SemesterSpecification;
import ge.gov.tsu.studentmanagement.specification.view.SemesterUniversitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SemesterService {

    private SemesterUniversityRepository semesterUniversityRepository;
    private SemesterRepository semesterRepository;
    private UniversityRepository universityRepository;


    @Autowired
    public SemesterService(
            SemesterUniversityRepository semesterUniversityRepository,
            UniversityRepository universityRepository,
            SemesterRepository semesterRepository) {
        this.semesterUniversityRepository = semesterUniversityRepository;
        this.universityRepository = universityRepository;
        this.semesterRepository = semesterRepository;
    }

    public List<SemesterUniversity> searchSemesterUniversity(SemesterUniversityPojo s) {
        Specifications<SemesterUniversity> spec = Specifications.where(SemesterUniversitySpecification.hasRecord());
        if (s.getUniversityId() != null) {
            spec = spec.and(SemesterUniversitySpecification.hasUniversityId(s.getUniversityId()));
        }
        if (s.getVisible() != null) {
            spec = spec.and(SemesterUniversitySpecification.visible(s.getVisible()));
        }
        if (s.getSemesterVisibility() != null) {
            spec = spec.and(SemesterUniversitySpecification.semesterVisible(s.getSemesterVisibility()));
        }

        return semesterUniversityRepository.findAll(spec);

    }

    public Page<Semester> search(Long id, Boolean visible, Pageable pageable, ActionPerformer actionPerformer) {
        Specifications<Semester> spec = Specifications.where(SemesterSpecification.hasRecord());
        if (id != null) {
            spec = spec.and(SemesterSpecification.hasId(id));
        }
        if (visible != null) {
            spec = spec.and(SemesterSpecification.visible(visible));
        }

        return semesterRepository.findAll(spec, pageable);
    }

    public Semester addSemester(RequestObject<SemesterPojo> ro) throws TsuException {
        SemesterPojo data = ro.getData();
        if (data.getAcademicRegBegin() == null ||
                data.getAcademicRegEnd() == null ||
                data.getAdministrationRegBegin() == null ||
                data.getAdministrationRegEnd() == null ||
                data.getBeginDate() == null ||
                data.getEndDate() == null ||
                data.getSeason() == null ||
                data.getYear() == null) {
            throw new TsuException("Parameters Required");
        }

        Semester semester = new Semester();
        semester.setSeason(data.getSeason());
        semester.setYear(data.getYear());
        semester.setAcademicRegBegin(data.getAcademicRegBegin());
        semester.setAcademicRegEnd(data.getAcademicRegEnd());
        semester.setAdministrationRegBegin(data.getAdministrationRegBegin());
        semester.setAdministrationRegEnd(data.getAdministrationRegEnd());
        semester.setEndDate(data.getEndDate());
        semester.setBeginDate(data.getBeginDate());
        semester.setVisible(false);
        semester.setCreatedAt(new Date());

        return semesterRepository.save(semester);

    }

    public Semester editSemester(RequestObject<SemesterPojo> ro) throws TsuException {
        SemesterPojo data = ro.getData();
        Semester byId = semesterRepository.getById(data.getId());
        if (byId == null) {
            throw new TsuException("Invalid Semester Id");
        }
        if (data.getYear() != null) {
            byId.setYear(data.getYear());
        }
        if (data.getSeason() != null) {
            byId.setSeason(data.getSeason());
        }
        if (data.getBeginDate() != null) {
            byId.setBeginDate(data.getBeginDate());
        }
        if (data.getEndDate() != null) {
            byId.setEndDate(data.getEndDate());
        }
        if (data.getAdministrationRegEnd() != null) {
            byId.setAdministrationRegEnd(data.getAdministrationRegEnd());
        }
        if (data.getAdministrationRegBegin() != null) {
            byId.setAdministrationRegBegin(data.getAdministrationRegBegin());
        }
        if (data.getAcademicRegEnd() != null) {
            byId.setAcademicRegEnd(data.getAcademicRegEnd());
        }
        if (data.getAcademicRegBegin() != null) {
            byId.setAcademicRegBegin(data.getAcademicRegBegin());
        }
        if (data.getVisible() != null) {
            byId.setVisible(data.getVisible());
        }

        return semesterRepository.save(byId);

    }

    public Semester changeSemesterVisibility(SemesterVisibilityPojo data) throws TsuException {
        Semester semester = semesterRepository.getById(data.getId());
        if (semester == null) {
            throw new TsuException("Semester Not Found");
        }
        semester.setVisible(data.getVisible());
        return semesterRepository.save(semester);
    }

    public Boolean removeUniversityInSemester(SemesterUniversityPojo data) throws TsuException {
        SemesterUniversity semUni = semesterUniversityRepository.findBySemesterIdAndUniversityId(data.getUniversityId(), data.getSemesterId());
        if (semUni == null) {
            throw new TsuException("Semester_University Not Found");
        }
        semesterUniversityRepository.delete(semUni);
        return true;
    }

    public SemesterUniversity changeSemesterUniversityVisibility(SemesterUniversityPojo data) throws TsuException {
        SemesterUniversity semUni = semesterUniversityRepository.findBySemesterIdAndUniversityId(data.getUniversityId(), data.getSemesterId());
        if (semUni == null) {
            throw new TsuException("Semester_University Not Found");
        }
        semUni.setVisible(data.getVisible());

        return semesterUniversityRepository.save(semUni);


    }

    public SemesterUniversity addUniversityInSemester(SemesterUniversityPojo data, ActionPerformer actionPerformer) {
        SemesterUniversity semesterUniversity = new SemesterUniversity();
        semesterUniversity.setSemester(semesterRepository.findOne(data.getSemesterId()));
        semesterUniversity.setUniversity(universityRepository.findOne(data.getUniversityId()));
        semesterUniversity.setVisible(false);
        semesterUniversity.setStatus(1);
        semesterUniversity.setCreatedAt(new Date());
        return semesterUniversityRepository.save(semesterUniversity);
    }
}
