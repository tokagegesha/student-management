package ge.gov.tsu.studentmanagement.service;

import ge.gov.tsu.studentmanagement.entity.User;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.repository.security.UserRepository;
import ge.gov.tsu.studentmanagement.util.PersonalInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PermissionAspect {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Around("execution(* ge.gov.tsu..*(..)) && @annotation(personalInfo) && args(.., userId)")
    public Object initElements(final ProceedingJoinPoint pjp, PersonalInfo personalInfo, Long userId) throws TsuException {
        System.out.println("userId in aspect " + userId);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        User targetUser = null;
        if (principal instanceof User) {
            String email = ((User) principal).getUsername();
            User curUser = userRepository.getUser(email);
            //თუ სტუდენტია დალოგინებული დავუბრუნოთ პირდაპირ
            if (userId == null || userId.equals(curUser.getId())) targetUser = curUser;
                //უნდა შევამოწმოთ სესსიის იუზერს აქვს თუ არა ადმინის როლი, რადგან ნახულობს სხვა პიროვნების პროფილს
            else if (curUser.getUserRoles().getRole().getName().equals("ADMIN")) targetUser = userRepository.getUser(userId);
        }
        if (targetUser == null) throw new TsuException("User Not Found");

        Object[] args = pjp.getArgs();
        args[args.length - 1] = targetUser.getId();
        System.out.println("targetUser" + targetUser.getId());
        try {
            return pjp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}