package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.Programme;
import ge.gov.tsu.studentmanagement.entity.ProgrammeSubject;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.ProgrammePojo;
import ge.gov.tsu.studentmanagement.pojo.ProgrammeSubjectPojo;
import ge.gov.tsu.studentmanagement.repository.ProgrammeRepository;
import ge.gov.tsu.studentmanagement.repository.ProgrammeSubjectRepository;
import ge.gov.tsu.studentmanagement.rest.request.ActionPerformer;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.specification.view.ProgrammeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ProgrammeService {
    private ProgrammeRepository programmeRepository;
    private ProgrammeSubjectRepository programmeSubjectRepository;

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository,
                            ProgrammeSubjectRepository programmeSubjectRepository) {
        this.programmeRepository = programmeRepository;
        this.programmeSubjectRepository = programmeSubjectRepository;
    }

    public Page<Programme> search(ProgrammePojo programmePojo, Pageable pageable) {
        Specifications<Programme> spec = Specifications.where(ProgrammeSpecification.hasRecord());
        if (programmePojo.getId() != null) spec = spec.and(ProgrammeSpecification.hasId(programmePojo.getId()));
        if (programmePojo.getName() != null) spec = spec.and(ProgrammeSpecification.hasName(programmePojo.getName()));
        if (programmePojo.getDegree() != null) spec = spec.and(ProgrammeSpecification.hasDegree(programmePojo.getDegree()));

        return programmeRepository.findAll(spec, pageable);
    }

    public Programme add(RequestObject<ProgrammePojo> ro) {
        Programme subject = new Programme();
        ProgrammePojo data = ro.getData();
        subject.setId(data.getId());
        subject.setName(data.getName());
        subject.setDegree(data.getDegree());
        subject.setCreatedAt(new Date());
        return programmeRepository.save(subject);
    }

    public boolean delete(RequestObject<ProgrammePojo> ro) {
         programmeRepository.delete(ro.getData().getId());
         return true;
    }

    public Programme edit(RequestObject<ProgrammePojo> ro) throws TsuException {
        ProgrammePojo programmePojo = ro.getData();

        Programme edited = null;
        List<Programme> allProgrammes = findAll(new Sort(Sort.Direction.ASC, "orderNumber"));


        Integer correctOrderNumber = programmePojo.getOrderNumber();
        if (correctOrderNumber > allProgrammes.size()) correctOrderNumber = allProgrammes.size();
        if (correctOrderNumber < 1) correctOrderNumber = 1;
        for (int i = 0; i < allProgrammes.size(); i++) {
            Programme programme = allProgrammes.get(i);
            if (programme.getId().equals(programmePojo.getId())) {
                edited = programme;
                if (programmePojo.getName() != null) programme.setName(programmePojo.getName());
                if (programmePojo.getDegree() != null) programme.setDegree(programmePojo.getDegree());
                programme.setOrderNumber(correctOrderNumber);
                allProgrammes.remove(i);
                allProgrammes.add(correctOrderNumber - 1, programme);
            }
        }
        for (int i = 0; i < allProgrammes.size(); i++) {
            allProgrammes.get(i).setOrderNumber(i + 1);
        }
        if (edited == null) throw new TsuException("Programme Not Found");
        programmeRepository.save(allProgrammes);
        return edited;

    }

    public List<Programme> findAll(Sort sort) {
        Specifications<Programme> spec = Specifications.where(ProgrammeSpecification.hasRecord());
        return programmeRepository.findAll(spec, sort);
    }

    @Transactional
    public ProgrammeSubject programmeSubjectDelete(RequestObject<ProgrammeSubjectPojo> ro) throws TsuException {
        ProgrammeSubject byProgrammeIdAndSemesterIdAndSubjectId = programmeSubjectRepository.findOne(ro.getData().getId());
        if (byProgrammeIdAndSemesterIdAndSubjectId == null) throw new TsuException("Programme_Subject Not Found");

        programmeSubjectRepository.delete(byProgrammeIdAndSemesterIdAndSubjectId);
        return byProgrammeIdAndSemesterIdAndSubjectId;
    }



   /* public boolean delete(RequestObject<UniversityPojo> ro) {
        UniversityPojo data = ro.getData();
        entityWithArchiveService.delete(data.getId(), new ActionPerformer(), programmeRepository);
        return true;
    }*/
}
