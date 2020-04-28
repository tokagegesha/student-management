package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.statics.Country;
import ge.gov.tsu.studentmanagement.pojo.CountryPojo;
import ge.gov.tsu.studentmanagement.repository.security.CountryRepository;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.specification.view.CountrySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Page<Country> search(RequestObject<CountryPojo> ro) {
        CountryPojo countryPojo = ro.getData();

        Specifications<Country> spec = Specifications.where(CountrySpecification.hasRecord());
        if (countryPojo.getId() != null) {
            spec = spec.and(CountrySpecification.hasId(countryPojo.getId()));
        }

        return countryRepository.findAll(
                spec,
                ro.getPaging()
        );

    }

    public Country getCountry(Long id) {
        return countryRepository.findOne(id);
    }


}
