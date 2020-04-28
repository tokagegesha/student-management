import {Component, ViewContainerRef} from '@angular/core';
import {Router} from "@angular/router";
import {SignInService} from "./sign-in.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css'],
  providers: [SignInService]
})
export class SignInComponent {

  public user: User;

  constructor(
    private loginService: SignInService,
    private router: Router,
    public toast: ToastsManager,
    private vRef: ViewContainerRef,) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    // initialize model here
    this.user = {
      username: '',
      password: ''
    }
  }

  login(user: User) {
    console.log("login started")
    this.loginService.login(user.username, user.password,this.toast).then(data => {
      this.loginService.getCurrentUserDetails().then(data => {
        if (data && data.length > 0) {
          const role = data[0].userRoles.role.name;
          console.log(role)
          if (role == "ADMIN") {
            this.router.navigateByUrl("/admin")
          } else if (role == "STUDENT") {
            this.router.navigateByUrl("/user")
          } else {
            this.router.navigateByUrl("/")
          }
        }
      });
    }, error => {
      console.log(error)
    });
  }
}

interface User {
  username: string;
  password: string;
}
