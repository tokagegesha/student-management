import {Inject, Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {ToastsManager} from "ng2-toastr";

@Injectable()
export class SignInService {

  constructor(@Inject(Http) private http: Http) {
  }

  login(username: string, password: string, toast: ToastsManager) {
    //let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' });
    //let options = new RequestOptions({ headers: headers });
    let fd = new FormData();
    fd.append('username', username);
    fd.append('password', password);
    return this.http.post("/api/j_spring_security_check", fd).toPromise()
      .then(res => res)
      .catch(res => {
        toast.error('ავტორიზაცია წარუმატებელია. ' + JSON.parse(res._body).message, 'შეცდომა');
      });
  }

  logout() {
    return this.http.post("logout", {})
      .map(res => {
        console.log('logout response:', res);
        return res;
      });
  }

  getCurrentUserDetails(userId: any = null) {
    userId = userId ? '/' + userId : '';
    return this.http.get(`/api/user${userId}/get`).toPromise()
      .then(res => res.json().result.data).catch(function () {
        console.log("error happened");
      })
      .catch(error => console.log(error));
  }


  /*  getStudentSession(userId:number) {
      return this.http.get(`/api/user/${userId}/get`).toPromise()
        .then(res => res.json().result.data).catch(function () {
          console.log("error happened");
        })
        .catch(error => console.log(error));
    }*/

  getAuthToken(): Observable<string[]> {
    return this.http.get("/unsecured/user/isAuthenticated").map(res => {
      var response = res.json();
      if (response.success && response.result) {
        return response.result.data;
      }
      return [];
    }).catch(() => []);
  }
}
