import {Component, OnInit} from '@angular/core';
import {SignInService} from "./shared/user/account/sign-in/sign-in.service";
import {Router} from "@angular/router";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './app.fonts.css', './app.sidebar.css'],
  providers: [SignInService]
})
export class AppComponent implements OnInit{
  title = 'app';
  public user = null;
  constructor(private signInService: SignInService, private router: Router) {
  }

  ngOnInit(){
    this.signInService.getCurrentUserDetails().then(user=>{
      this.user = (user && user[0]) || null;
    })
  }
}
