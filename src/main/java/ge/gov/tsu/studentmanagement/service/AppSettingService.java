package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.AppSetting;
import ge.gov.tsu.studentmanagement.exception.TsuRuntimeException;
import ge.gov.tsu.studentmanagement.repository.AppSettingRepository;
import ge.gov.tsu.studentmanagement.rest.request.ActionPerformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppSettingService {

    @Autowired
    AppSettingRepository appSettingRepository;



    public AppSetting edit(String keyword, String value, ActionPerformer actionPerformer) {
        List<AppSetting> result = appSettingRepository.findByKeyword(keyword);
        if (result.size() != 1) throw new TsuRuntimeException("Cant ge setting");
        AppSetting appSetting = result.get(0);
        appSetting.setValue(value);
        return appSettingRepository.save(appSetting);
    }
}
