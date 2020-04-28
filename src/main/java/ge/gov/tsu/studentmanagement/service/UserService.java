package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.SecurityToken;
import ge.gov.tsu.studentmanagement.entity.Student;
import ge.gov.tsu.studentmanagement.entity.User;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.UserPojo;
import ge.gov.tsu.studentmanagement.repository.SecurityTokenRepository;
import ge.gov.tsu.studentmanagement.repository.security.UserRepository;
import ge.gov.tsu.studentmanagement.rest.request.ActionPerformer;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.util.PersonalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Value("${token.expire.day}")
    private Integer expireDays;

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;

    @Value("${mail.from}")
    private String mailFrom;

    @Value("${user.activation.link}")
    private String link;


    private UserRepository userRepository;
    private SecurityTokenService securityTokenService;
    private SecurityTokenRepository securityTokenRepository;
    private SettingService settingService;
    private UserRoleService userRoleService;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public UserService(
            UserRepository userRepository,
            SecurityTokenService securityTokenService,
            SecurityTokenRepository securityTokenRepository,
            SettingService settingService,
            UserRoleService userRoleService, MyUserDetailsService myUserDetailsService) {
        this.userRepository = userRepository;
        this.securityTokenService = securityTokenService;
        this.securityTokenRepository = securityTokenRepository;
        this.settingService = settingService;
        this.userRoleService = userRoleService;
        this.myUserDetailsService = myUserDetailsService;
    }

    public User createUserForCandidate(Student student, StudentService.UserStatus status) {
        User user = new User();
        user.setPassword("12345");
        user.setFirstName(student.getFirstName());
        user.setLastName(student.getLastName());
        user.setEmail(student.getEmail());
        user.setStatus(status.value());
        return userRepository.save(user);
    }

    @Transactional
    public User registerUser(UserPojo data, ActionPerformer actionPerformer) throws TsuException {
        User curUser = userRepository.getUser(data.getEmail());
        if (curUser != null) {
            throw new TsuException("User With This Email Exists");
        }
        User user = new User();
        user.setEmail(data.getEmail());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setPassword(data.getPassword());
        user.setStatus(User.Type.ACTIVE.getValue());
        User newUser = userRepository.save(user);

        userRoleService.addRoleToUser(newUser.getId(), 2L);

        SecurityToken token = securityTokenService.generateNewSecurityToken(
                newUser.getId(),
                SecurityToken.ActionType.EMAIL_VERIFICATION, expireDays);
        String template = settingService.getApplicationSettingValue("USER_EMAIL_VERIFICATION_TEMPLATE", String.class);
        Object[] params = new Object[]{link, "?token=" + token.getToken()};
        String body = MessageFormat.format(template, params);
        MailService.sendMail(mailUsername, mailPassword, mailFrom, user.getEmail(), "ACTIVATION", body);

        return newUser;
    }

    public User activate(String token) throws TsuException {

        SecurityToken securityToken = securityTokenService.findActiveSecurityToken(token);
        User u = userRepository.getUser(securityToken.getUserId());
        if (u == null) {
            throw ResponseObject.createFailedResponse("01", "USER_EXISTS", "მომხმარებელი ამ მეილით არსებობს");
        }
        securityToken.setStatus(SecurityToken.Status.USED);
        securityToken.setUsageDate(new Date());
        securityTokenRepository.save(securityToken);

        u.setStatus(User.Type.VERIFIED.getValue());
        return userRepository.save(u);

    }

/*    public UserExtended userDetails() {
        return userDetails(null);
    }*/

    @PersonalInfo
    public User userDetails(Long userId) throws TsuException {
        System.out.println("in user det  " + userId);
        User targetUser = userRepository.getUser(userId);
        if (targetUser == null) throw new TsuException("User Not Found");
        return targetUser;
    }


    public Boolean isAuthorised() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                String email = ((UserDetails) principal).getUsername();
                User curUser = userRepository.getUser(email);
                if (curUser == null) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public User getAuthorisedUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                String email = ((UserDetails) principal).getUsername();
                User curUser = userRepository.getUser(email);
                if (curUser == null) {
                    return null;
                }
                return curUser;
            }
            return null;
        }
        return null;
    }

    public User getUser(Long userId) {
        return userRepository.getUser(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<String> getUserRoles() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                String email = ((User) principal).getUsername();
                User curUser = userRepository.getUser(email);
                if (curUser == null) {
                    return null;
                }
                return Collections.singletonList(curUser.getUserRoles().getRole().getName());
            }
            return null;
        }
        return null;
    }


    public User editUserInfo(UserPojo data, ActionPerformer actionPerformer) {
        User oldUser = getUser(data.getId());
        if (data.getEmail() != null && !data.getEmail().trim().equals("")) {
            oldUser.setEmail(data.getEmail());
        }
        if (data.getFirstName() != null && !data.getFirstName().trim().equals("")) {
            oldUser.setFirstName(data.getFirstName());
        }
        if (data.getLastName() != null && !data.getLastName().trim().equals("")) {
            oldUser.setLastName(data.getLastName());
        }
        return userRepository.save(oldUser);
    }

/*    public UserExtended getStudentSession(RequestObject<UserPojo> ro) {
        System.out.println("gamoidzaxa");
        UserExtended userExtended = userDetails();
        if (userExtended == null) {
            throw new RuntimeException("SESSION_DOES_NOT_EXISTS");
        }
        if (!userExtended.getRole().equals("ADMIN")) {
            throw new RuntimeException("NOT_ADMIN");
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User details = myUserDetailsService.loadUserByUsername(ro.getData().getEmail());
        details.setLastName("aaaaaaaaaaaaaaaaaaaaaaaaa");
        Executor executor = new Executor();
        executor.setUserId(userExtended.getId());
        executor.setRole(userExtended.getRole());
        details.setExecutor(executor);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details, details.getPassword());
        securityContext.setAuthentication(token);

        return userDetails();
    }*/
}
