package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.view.ProgrammeReleasedExtended;
import ge.gov.tsu.studentmanagement.pojo.ProgrammeReleasedPojo;
import ge.gov.tsu.studentmanagement.repository.ProgrammeUniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeUniversityService {

 /*   @Autowired
    ProgrammeUniversityRepository programmeUniversityRepository;

    public Page<ProgrammeReleasedExtended> search(ProgrammeReleasedPojo data, Pageable pageable) {
        return programmeUniversityRepository.search(
                data.getId(),
                data.getId(),
                data.getProgramId(),
                pageable
        );

    }*/
}
