package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.apiutils.service.GeneralTools;
import ge.gov.tsu.studentmanagement.entity.User;
import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.pojo.UserPojo;
import ge.gov.tsu.studentmanagement.rest.request.RequestObject;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import ge.gov.tsu.studentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
public class UserController extends BaseController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/unsecured/api/user/register", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseObject registerUser(@RequestBody RequestObject<UserPojo> ro) throws TsuException {

        GeneralTools.checkRequiredProperties(ro, Arrays.asList("data", "actionPerformer"));
        GeneralTools.checkRequiredProperties(ro.getData(), Arrays.asList("email", "firstName", "lastName", "password"));
        return ResponseObject.createSuccessfulResponse(userService.registerUser(ro.getData(), ro.getActionPerformer()));
    }


    @RequestMapping(value = "/unsecured/api/user/active/{token}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseObject activateUser(@PathVariable String token) throws TsuException {
        return ResponseObject.createSuccessfulResponse(userService.activate(token));
    }


    @RequestMapping(value = {"/api/user/get", "/api/user/{id}/get"}, method = RequestMethod.GET)
    public ResponseObject getUserAccount(@PathVariable(required = false) Long id) throws TsuException {
        User resultttt = userService.userDetails(id);
        System.out.println("aqanee" + resultttt.getId());
        return ResponseObject.createSuccessfulResponse(resultttt);
    }

/*
    @RequestMapping(value = "/unsecured/api/user/get/student/session", method = RequestMethod.POST)
    public ResponseObject getStudentSession(@RequestBody RequestObject<UserPojo> ro) {
        return ResponseObject.createSuccessfulResponse(userService.getStudentSession(ro));
    }
*/

    @RequestMapping(value = "/api/user/edit", method = RequestMethod.POST)
    public ResponseObject editUser(@RequestBody RequestObject<UserPojo> requestBody) {
        requestBody.checkRequiredFields("edit");
        return ResponseObject.createSuccessfulResponse(userService.editUserInfo(requestBody.getData(), requestBody.getActionPerformer()));
    }
}
