import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {SignInService} from "../shared/user/account/sign-in/sign-in.service";

@Injectable()
export class StudentLoginGuard implements CanActivateChild {

  private requiredRoles: string[] = ['STUDENT', 'ADMIN'];

  constructor(private signInService: SignInService, public router: Router) {
  }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    return this.signInService.getAuthToken().map(roles => {
      console.log(roles);
      if (roles && roles.length && this.requiredRoles.indexOf(roles[0]) !== -1) return true;
      this.router.navigateByUrl('/login');
      return false;
    });
  }
}
