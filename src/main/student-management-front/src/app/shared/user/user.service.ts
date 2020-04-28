import {Inject, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {ToastsManager} from "ng2-toastr";

@Injectable()
export class UserService {
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(@Inject(Http) private http: Http) {
  }

  registerUser(user: any, toast: ToastsManager): Promise<any> {
    return this.http.post("/unsecured/api/user/register", {data: user}, this.headers).toPromise().then(res => {
      const response = res.json();
      if (response.error) {
        toast.error(response.error, 'შეცდომა');
        return null;
      }
      return res.json()
    }).catch(err => {
      let errorResponse = err.json();
      toast.error(errorResponse.message);
      return null;
    });
  }

  activate(token: string, toast: ToastsManager): Promise<string> {
    return this.http.get("/unsecured/api/user/active/" + token).toPromise().then(res => {
      const response = res.json();
      if (response.error) {
        toast.error(response.error, 'შეცდომა');
        return null;
      }
      if (response.success) {
        return "Your account was successfully activated";
      }

      return "token expired or user already activated";
    }, err => {
      let errorResponse = err.json();
      toast.error(errorResponse.message);
      return null;
    });
  }

  updateStudentDocumentsData(formData: FormData, toast: ToastsManager): Promise<any> {
    return this.http.post("/api/student/updateStudentDocumentInfo",formData).toPromise().then(res => {
      const response = res.json();
      if (response.error) {
        toast.error(response.error, 'შეცდომა');
        return null;
      }
      return response;
    }, err => {
      let errorResponse = err.json();
      toast.error(errorResponse.message);
      return null;
    });
  }

  /*activate(user: any): Observable<any> {
    return this.http.postToPromise("/unsecured/api/user/register", {data: user}, this.headers).map(res =>{
      console.log(res.json());
      return res.json()});
  }*/
  changeUserInfo(userInfo: any): Observable<any> {
    return this.http.post("/api/user/edit", {data: userInfo}, this.headers).map(res => {
      console.log(res.json());
      return res.json()
    });
  }

  changeStudentInfo(studentInfo: any): Observable<any> {
    return this.http.post("/api/student/edit", {data: studentInfo}, this.headers).map(res => {
      console.log(res.json());
      return res.json()
    });
  }




}
