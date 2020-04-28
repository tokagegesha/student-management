package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.Setting;
import ge.gov.tsu.studentmanagement.repository.SettingValueRepository;
import ge.gov.tsu.studentmanagement.repository.security.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SettingService {

    @Autowired
    SettingValueRepository settingValueRepository;

    @Autowired
    SettingRepository settingRepository;


   /* public SettingValue editApplicationSettingValue(SettingPojo pojo, ActionPerformer actionPerformer) {
        //TODO permission check depending on application
        SettingValue settingValue = settingValueRepository.getAppSettingValue(pojo.getSettingId(), actionPerformer.getDate());
        if (settingValue != null) {
            settingValue.setValue(pojo.getValue());
            return entityWithArchiveService.save(settingValue, actionPerformer, settingValueRepository);
        } else {
            settingValue = new SettingValue();
            settingValue.setValue(pojo.getValue());
            settingValue.setSettingId(pojo.getSettingId());
            settingValue.setClientId(null);
            settingValue.setUserId(null);
            return entityWithArchiveService.save(settingValue, actionPerformer, settingValueRepository);
        }
    }*/

   /* @RequiresPermission("UM.CLIENT_SETTING.EDIT")
    public SettingValue editClientSettingValue(SettingPojo pojo, ActionPerformer actionPerformer) {
        //TODO permission check depending on application
        SettingValue settingValue = settingValueRepository.getClientSettingValue(pojo.getSettingId(), pojo.getClientId(), actionPerformer.getDate());
        if (settingValue != null) {
            settingValue.setValue(pojo.getValue());
            return entityWithArchiveService.save(settingValue, actionPerformer, settingValueRepository);
        } else {
            settingValue = new SettingValue();
            settingValue.setValue(pojo.getValue());
            settingValue.setSettingId(pojo.getSettingId());
            settingValue.setClientId(pojo.getClientId());
            settingValue.setUserId(null);
            return entityWithArchiveService.save(settingValue, actionPerformer, settingValueRepository);
        }
    }
*/
/*
    @RequiresPermission("UM.CLIENT_USER_SETTING.EDIT")
    public SettingValue editClientUserSettingValue(SettingPojo pojo, ActionPerformer actionPerformer) {
        //TODO permission check depending on application
        SettingValue settingValue = settingValueRepository.getClientUserSettingValue(pojo.getSettingId(), pojo.getClientId(), pojo.getUserId(), actionPerformer.getDate());
        if (settingValue != null) {
            settingValue.setValue(pojo.getValue());
            return entityWithArchiveService.save(settingValue, actionPerformer, settingValueRepository);
        } else {
            settingValue = new SettingValue();
            settingValue.setValue(pojo.getValue());
            settingValue.setSettingId(pojo.getSettingId());
            settingValue.setClientId(pojo.getClientId());
            settingValue.setUserId(pojo.getUserId());
            return entityWithArchiveService.save(settingValue, actionPerformer, settingValueRepository);
        }
    }
*/


/*    public Setting addSetting(SettingPojo pojo, ActionPerformer actionPerformer) {
        //TODO dynamic permission check
        if (settingRepository.search(pojo.getApplicationId(), pojo.getName(), actionPerformer.getDate()) != null) {
            throw ErrorService.initializeErrorResponse("DUPLICATE_EXISTS");
        }
        Application app = applicationRepository.findCurrentVersion(pojo.getApplicationId(), actionPerformer.getDate());
        if (app == null) {
            throw ErrorService.initializeErrorResponse("INVALID_ID", "applicationId is invalid");
        }
        Setting setting = new Setting();
        setting.setName(pojo.getName());
        setting.setApplicationId(pojo.getApplicationId());
        setting.setDescription(pojo.getDescription());
        setting.setValueType(pojo.getValueType());
        setting.setDefaultValue(pojo.getDefaultValue());
        setting.setForApplication(pojo.getForApplication());
        setting.setForClient(pojo.getForClient());
        setting.setForUser(pojo.getForUser());
        setting.setMeta(pojo.getMeta());
        return entityWithArchiveService.save(setting, actionPerformer, settingRepository);
    }*/

    public <T> T getApplicationSettingValue(String s, Class<T> clazz) {
        Setting realValue = settingRepository.search(s);
        realValue.getName();
        if (Integer.class == clazz) {
            return (T) Integer.valueOf(realValue.getDefaultValue());
        }
        if (Boolean.class == clazz) {
            return (T) Boolean.valueOf(realValue.getDefaultValue());
        }
        if (Double.class == clazz) {
            return (T) Double.valueOf(realValue.getDefaultValue());
        }
        if (Date.class == clazz) {
            return (T) new Date(Long.valueOf(realValue.getDefaultValue()));
        }
        if (String.class == clazz) {
            return (T) realValue.getDefaultValue();
        }
        throw new RuntimeException("unknown");
    }

   /* public ApplicationSettings getApplicationSetting(String s) {
        return applicationSettingRepository.search(s);
    }*/
}
