package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.pojo.ExchangeProgrammePojo;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.ExchangeProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeProgrammeController {

    private ExchangeProgrammeService exchangeProgrammeService;

    @Autowired
    public ExchangeProgrammeController(ExchangeProgrammeService exchangeProgrammeService) {
        this.exchangeProgrammeService = exchangeProgrammeService;
    }

    @RequestMapping(value = "/api/exchangeProgramme/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject search(@RequestBody RequestObject<ExchangeProgrammePojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(exchangeProgrammeService.search(ro));
    }

    @RequestMapping(value = "/api/exchangeProgramme/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject add(@RequestBody RequestObject<ExchangeProgrammePojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(exchangeProgrammeService.add(ro));
    }

    @RequestMapping(value = "/api/exchangeProgramme/edit", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject edit(@RequestBody RequestObject<ExchangeProgrammePojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(exchangeProgrammeService.edit(ro));
    }


    @RequestMapping(value = "/api/exchangeProgramme/delete", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject delete(@RequestBody RequestObject<ExchangeProgrammePojo> ro) throws Exception {
        return ResponseObject.createSuccessfulResponse(exchangeProgrammeService.delete(ro.getData().getId()));
    }


}