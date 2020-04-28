package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.entity.AppSetting;
import ge.gov.tsu.studentmanagement.pojo.AppSettingPojo;
import ge.gov.tsu.studentmanagement.repository.AppSettingRepository;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.AppSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppSettingController {

    @Autowired
    AppSettingRepository appSettingRepository;

    @Autowired
    AppSettingService appSettingService;

    @RequestMapping(value = "/unsecured/api/setting/search", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject search(@RequestBody RequestObject<AppSettingPojo> ro) {
        return ResponseObject.createSuccessfulResponse(appSettingRepository.findByKeyword(ro.getData().getKeyword()));
    }

    @RequestMapping(value = "/api/setting/edit", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject delete(@RequestBody RequestObject<AppSettingPojo> ro) {
        ro.checkRequiredFields("edit");
        AppSetting result = appSettingService.edit(ro.getData().getKeyword(), ro.getData().getValue(), ro.getActionPerformer());
        return ResponseObject.createSuccessfulResponse(result);
    }
}