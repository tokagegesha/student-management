import {Component, ViewContainerRef} from '@angular/core';
import {Router} from "@angular/router";
import {SignInService} from "../../shared/user/account/sign-in/sign-in.service";
import {SubjectService} from "../../admin/subject/subject.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [SignInService, SubjectService]
})
export class HomeComponent {

  public user: User;
  public homepageHtml;

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private router: Router,
              private loginService: SignInService,
              private subjectService: SubjectService) {


    loginService.getCurrentUserDetails().then(obj => {
      console.log(obj);
      if (obj && obj.length > 0) {
        this.user = {
          userFullName: obj
        };
      } else {
        console.log('არ არის დალოგინებული');
      }
    }, error => console.log('error', error))
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.subjectService.getSettingValue('homepage', this.toast).then(res=>{
      this.homepageHtml = res.data[0].value;
    });

    // initialize model here
    this.user = {
      userFullName: ''
    }
  }
}

interface User {
  userFullName: string;
}
