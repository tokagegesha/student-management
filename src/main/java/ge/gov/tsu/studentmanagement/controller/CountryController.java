package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.pojo.CountryPojo;
import ge.gov.tsu.studentmanagement.pojo.UniversityPojo;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.CountryService;
import ge.gov.tsu.studentmanagement.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/api/country/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject search(@RequestBody RequestObject<CountryPojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(countryService.search(ro));
    }


}