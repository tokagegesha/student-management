import {AfterViewInit, Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SignInService} from "../shared/user/account/sign-in/sign-in.service";
import {Http} from "@angular/http";

@Component({
  selector: 'app-public',
  templateUrl: './public.component.html',
  styleUrls: ['./public.component.css']
})
export class PublicComponent implements AfterViewInit, OnDestroy, OnInit {
  public activationMessage: string;
  public user:any;

  constructor(private signInService: SignInService, private activatedRoute: ActivatedRoute, @Inject(Http) private http: Http, private router:Router) {
  }

  private onScroll = () => {
    let scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
    if (scrollTop > 0) {
      document.body.classList.add('scrolled-down');
    } else {
      document.body.classList.remove('scrolled-down');
    }
  };

  ngOnInit(){
    this.signInService.getCurrentUserDetails().then(user=>{
      this.user = (user && user[0]) || null;
    })
  }

  ngAfterViewInit() {
    window.addEventListener('scroll', this.onScroll);
  }

  ngOnDestroy() {
    window.removeEventListener('scroll', this.onScroll);
  }

  logout() {
    console.log('PublicComponent logout');
    this.http.get('/api/logout').finally(()=> {

      //this.signInService.authenticated = false;
      this.router.navigateByUrl('/login');
    }).subscribe(res=>{
      console.log(res);
    });
  }

}
