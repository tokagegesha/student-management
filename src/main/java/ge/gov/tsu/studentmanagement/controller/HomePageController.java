package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomePageController {

    private UserService userService;

    @Autowired
    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    /*@RequestMapping(value = {"/", "public", "public*//**//**"})
    public String getHomepage() {
        System.out.println("public controller");
        return "public/index";
    }*/

    @ResponseBody
    @RequestMapping(value = "/unsecured/user/isAuthenticated", method = RequestMethod.GET)
    public ResponseObject isAuthenticated() {

        List<String> userRoles = userService.getUserRoles();
        if (userRoles != null) {
            return ResponseObject.createSuccessfulResponse(userRoles);
        }
        return ResponseObject.createFailedResponse();
    }
/*
    @ResponseBody
    @RequestMapping(value = "/unsecured/user/get", method = RequestMethod.GET)
    public ResponseObject isAuthenticated() {
        System.out.println("ar modis aq" + userService.getUserRoles());
        List<String> userRoles = userService.getUserRoles();
        if (userRoles != null) {
            return ResponseObject.createSuccessfulResponse(userRoles);
        }
        throw  new RuntimeException("SESSION_DOES_NOT_EXISTS");
    }
*/

    /*@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }*/


    @RequestMapping(value = {"/admin", "/admin/*"})
    public String getAdmin() {
        System.out.println("admin controller");
        return "admin/index";
    }


}
