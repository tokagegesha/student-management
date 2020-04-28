package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.SecurityToken;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.repository.SecurityTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@Service
public class SecurityTokenService {

    private SecurityTokenRepository securityTokenRepository;

    @Autowired
    public SecurityTokenService(SecurityTokenRepository securityTokenRepository) {
        this.securityTokenRepository = securityTokenRepository;
    }

    @Transactional
    public SecurityToken generateNewSecurityToken(Long userId, SecurityToken.ActionType actionType, long expireDays) {
        String userToken = this.getRandomUserToken(actionType);
        SecurityToken token = new SecurityToken();
        token.setToken(userToken);
        token.setActionType(actionType);
        token.setUserId(userId);
        token.setCreationDate(new Date());
        long duration = expireDays * 24 * 60 * 60 * 1000;
        token.setExpirationDate(new Date(token.getCreationDate().getTime() + duration));
        token.setStatus(SecurityToken.Status.ACTIVE);

        securityTokenRepository.expireOldTokens(userId, actionType);
        return securityTokenRepository.save(token);
    }

    public SecurityToken findActiveSecurityToken(String tokenString) throws TsuException {
        SecurityToken token = securityTokenRepository.search(tokenString, null, null, SecurityToken.ActionType.EMAIL_VERIFICATION);
        if (token == null) {
            throw new TsuException("Invalid Token");
        }
        if (token.getStatus().equals(SecurityToken.Status.USED)) {
            throw new TsuException("Token Used");
        }
        if (!token.getStatus().equals(SecurityToken.Status.ACTIVE)) {
            throw new TsuException("Invalid OR Expired Token");
        }

        return token;
    }

    //<editor-fold desc="TOOLS">
    private String getRandomUserToken(SecurityToken.ActionType actionType) {
        Long nextValue = securityTokenRepository.getNextval();
        String userString = this.transformLongToStringWithZeros(nextValue, 12) + this.transformLongToStringWithZeros(actionType.getValue(), 4);

        String randomUserToken = this.getRandomString(2);
        for (int i = 0; i < 16; i += 1) {
            randomUserToken += Character.toString(userString.charAt(i));
            randomUserToken += this.getRandomString(2);
        }
        return randomUserToken;
    }

    private String getRandomString(Integer length) {
        String str = "";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            str += (rand.nextInt((9) + 1));
        }
        return str;
    }

    private String transformLongToStringWithZeros(Integer number, Integer length) {
        return transformLongToStringWithZeros(new Long(number), length);
    }

    private String transformLongToStringWithZeros(Long number, Integer length) {
        String str = number.toString();
        String strWithZeros = "";
        int stringLength = str.length();

        for (int i = length; i > stringLength; i--) {
            strWithZeros += "0";
        }
        str = strWithZeros + str;
        return str;
    }
    //</editor-fold>

}
