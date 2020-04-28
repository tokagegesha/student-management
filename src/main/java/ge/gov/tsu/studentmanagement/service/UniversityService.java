package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.dto.SemesterUniversityDTO;
import ge.gov.tsu.studentmanagement.entity.SemesterUniversity;
import ge.gov.tsu.studentmanagement.entity.University;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.SemesterUniversityPojo;
import ge.gov.tsu.studentmanagement.pojo.UniversityPojo;
import ge.gov.tsu.studentmanagement.repository.SemesterUniversityRepository;
import ge.gov.tsu.studentmanagement.repository.UniversityRepository;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.specification.view.SemesterUniversitySpecification;
import ge.gov.tsu.studentmanagement.specification.view.UniversityExtendedSpecification;
import ge.gov.tsu.studentmanagement.specification.view.UniversitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {

    private UniversityRepository universityRepository;
    private CountryService countryService;
    private SemesterUniversityRepository semesterUniversityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository,
                             CountryService countryService,
                             SemesterUniversityRepository semesterUniversityRepository) {
        this.universityRepository = universityRepository;
        this.semesterUniversityRepository = semesterUniversityRepository;
        this.countryService = countryService;
    }

    public Page<University> search(RequestObject<UniversityPojo> ro) {
        UniversityPojo universityPojo = ro.getData();

        Specifications<University> spec = Specifications.where(UniversitySpecification.hasRecord());
        if (universityPojo.getId() != null) {
            spec = spec.and(UniversitySpecification.hasId(universityPojo.getId()));
        }
        if (universityPojo.getCountryId() != null) {
            spec = spec.and(UniversitySpecification.hasCountryId(universityPojo.getCountryId()));
        }
        if (universityPojo.getName() != null) {
            spec = spec.and(UniversitySpecification.hasName(universityPojo.getName()));
        }

        return universityRepository.findAll(
                spec,
                ro.getPaging()
        );

    }

    public Page<University> searchExtended(RequestObject<UniversityPojo> ro) {
        UniversityPojo universityPojo = ro.getData();

        Specifications<University> spec = Specifications.where(UniversityExtendedSpecification.hasRecord());
        if (universityPojo.getId() != null) {
            spec = spec.and(UniversityExtendedSpecification.hasId(universityPojo.getId()));
        }

        if (universityPojo.getCountryId() != null) {
            spec = spec.and(UniversityExtendedSpecification.hasCountryId(universityPojo.getCountryId()));
        }
        if (universityPojo.getName() != null) {
            spec = spec.and(UniversityExtendedSpecification.hasName(universityPojo.getName()));
        }
        return universityRepository.findAll(spec, ro.getPaging());
    }

    public University add(RequestObject<UniversityPojo> ro) {
        University university = new University();
        UniversityPojo data = ro.getData();
        university.setId(data.getId());
        university.setAddress(data.getAddress());
        university.setInfo(data.getInfo());
        university.setName(data.getName());
        university.setCountry(countryService.getCountry(ro.getData().getCountryId()));
        return universityRepository.save(university);
    }

    public University edit(UniversityPojo ro) throws TsuException {

        University edited = null;
        List<University> allUniversities = findAll(new Sort(Sort.Direction.ASC, "orderNumber"));


        Integer correctOrderNumber = ro.getOrderNumber();
        if (correctOrderNumber > allUniversities.size()) correctOrderNumber = allUniversities.size();
        if (correctOrderNumber < 1) correctOrderNumber = 1;
        for (int i = 0; i < allUniversities.size(); i++) {
            University university = allUniversities.get(i);
            if (university.getId().equals(ro.getId())) {
                edited = university;
                if (ro.getName() != null) university.setName(ro.getName());
                if (ro.getAddress() != null) university.setAddress(ro.getAddress());
                if (ro.getCountryId() != null) university.setCountry(countryService.getCountry(ro.getCountryId()));
                if (ro.getInfo() != null) university.setInfo(ro.getInfo());
                university.setOrderNumber(correctOrderNumber);
                allUniversities.remove(i);
                allUniversities.add(correctOrderNumber - 1, university);
            }
        }
        for (int i = 0; i < allUniversities.size(); i++) {
            allUniversities.get(i).setOrderNumber(i + 1);
        }
        if (edited == null) throw new TsuException("Programme Not Found");
        universityRepository.save(allUniversities);
        return edited;

    }


    public List<University> findAll(Sort sort) {
        Specifications<University> spec = Specifications.where(UniversitySpecification.hasRecord());
        return universityRepository.findAll(spec, sort);
    }


    public boolean delete(RequestObject<UniversityPojo> ro) {
        UniversityPojo data = ro.getData();
        universityRepository.delete(data.getId());
        return true;
    }

    public Page<SemesterUniversityDTO> searchUnselectedUniversitiesForSemester(RequestObject<SemesterUniversityPojo> ro) {
        SemesterUniversityPojo data = ro.getData();
        Page<University> list = universityRepository.getUnactivatedUniversitiesForSemester(ro.getData().getSemesterId(), ro.getPaging());
        List<SemesterUniversityDTO> sms = new ArrayList<>();
        list.getContent().forEach(item -> {
            sms.add(new SemesterUniversityDTO(null, item.getId(), null, null, item.getCountry().getId(), item.getName(), item.getAddress(), item.getCountry().getName(), item.getInfo()));
        });


        return list.map(item -> {
            SemesterUniversityDTO dto = new SemesterUniversityDTO(null, item.getId(), null, null, item.getCountry().getId(),
                    item.getName(), item.getAddress(), item.getCountry().getName(), item.getInfo());
            // Conversion logic

            return dto;
        });

    }

    public Page<SemesterUniversityDTO> searchSelectedUniversitiesForSemester(RequestObject<SemesterUniversityPojo> ro) {
        SemesterUniversityPojo data = ro.getData();
        Specifications<SemesterUniversity> spec = Specifications.where(SemesterUniversitySpecification.hasRecord());
        if (data.getSemesterId() != null) {
            spec = spec.and(SemesterUniversitySpecification.hasSemesterId(data.getSemesterId()));
        }

        Page<SemesterUniversity> list = semesterUniversityRepository.findAll(spec, ro.getPaging());

        return list.map(item -> {
            SemesterUniversityDTO dto = new SemesterUniversityDTO(item.getId(), item.getUniversity().getId(), item.getVisible(),
                    item.getSemester().getId(), item.getUniversity().getCountry().getId(),
                    item.getUniversity().getName(), item.getUniversity().getAddress(), item.getUniversity().getCountry().getName(), item.getUniversity().getInfo());
            // Conversion logic

            return dto;
        });
    }

    public University getUniversity(Long id) {
        return universityRepository.findOne(id);
    }

    public University getByName(String name) {
        return universityRepository.getByName(name);
    }


}
