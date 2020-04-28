import {Component, Inject, OnInit} from '@angular/core';
import {SignInService} from "../shared/user/account/sign-in/sign-in.service";
import {Router} from "@angular/router";
import {Http} from "@angular/http";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: [/*'./admin.component.css'*/],
  //providers: [SignInService]
})
export class AdminComponent implements OnInit {
  public user = null;

  constructor(private signInService: SignInService, @Inject(Http) private http: Http, private router: Router) {
  }


  ngOnInit() {
    this.signInService.getCurrentUserDetails().then(user => {
      this.user = (user && user[0]) || null;
    })
  }


  logout() {
    console.log('PublicComponent logout');
    this.http.get('/api/logout').finally(() => {
      this.router.navigateByUrl('/login');
    }).subscribe(res => {
      console.log(res);
    });
  }

}
