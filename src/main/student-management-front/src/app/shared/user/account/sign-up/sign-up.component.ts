import {Component, ViewContainerRef} from '@angular/core';
import {ProgrammeService} from "../../../../public/programme/programme.service";
import {UniversitiesService} from "../../../universities/universities.service";
import {UserService} from "../../user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {SignInService} from "../sign-in/sign-in.service";
import {NgForm} from "@angular/forms";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
  providers: [ProgrammeService, UniversitiesService, SignInService, UserService]
})

export class SignUpComponent {
  public user: User;
  private registrationResponse: any;
  public resultMessage: string;
  public resultSuccess: boolean;
  public submited: boolean;
  public test = 'sasa';
  public mode = 1;



  constructor(
    private userService: UserService,
    private loginService: SignInService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public toast: ToastsManager,
    private vRef: ViewContainerRef) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);
    // initialize model here
    this.user = {
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      confirmPassword: ''
    }
  }


  registerUser(user: User, isValid: boolean, form: NgForm) {
    console.log(user);
    this.userService.registerUser(user,this.toast).then(response => {
      if ( response && response.error) {
        this.submited = true;
        this.resultSuccess = false;
        this.resultMessage = response.error[0].message;
        this.registrationResponse = response.error;
        console.log('response from server', response.error);
      }
      if (response && response.result && response.result.data && response.result.data[0]) {
        //this.router.navigateByUrl("/public", { queryParams: { am: response.result.data[0].email } });
        form.resetForm();
        this.submited = true;
        this.resultSuccess = true;
        this.resultMessage = "Verification email was sent on " + response.result.data[0].email;
        this.registrationResponse = response.result.data[0];
        console.log('response from server', response.result.data[0]);
      }
    });
  }


  /*  login(user: User) {
   console.log('us', user.email);
   this.loginService.login(user.email, user.password).subscribe(data => {
   this.router.navigateByUrl("/user/home")
   });
   }*/
}


interface User {
  email: string;
  firstName: string;
  lastName: string;
  password: string,
  confirmPassword: string,
}
