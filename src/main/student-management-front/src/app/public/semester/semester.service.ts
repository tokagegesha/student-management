import {Inject, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Semester} from "../../shared/SemesterModified";
import {ToastsManager} from "ng2-toastr";
import {RequestHelperService} from "../../shared/services/request-helper.service";

@Injectable()
export class SemesterService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private paging = {
    sort: {
      orders: [
        {property: "year", direction: "ASC"},
        {property: "season", direction: "ASC"},
      ]
    }
  };

  constructor(@Inject(Http) private http: Http,
              @Inject(RequestHelperService) private requestHelper: RequestHelperService) {
  }

  getAccessibleSemesters(universityId: number, toast: ToastsManager): Observable<any> {
    /*return this.http.post("/unsecured/api/semester/university/search", {
     data: {universityId: universityId, visible: true, semesterVisibility: true},
     paging: this.paging
     }, this.headers).map(res => {
     return res.json().result.data;
     });
     */

    return this.requestHelper.postUnsecured('semester/university/search', {
      data: {universityId: universityId, visible: true, semesterVisibility: true}, paging: this.paging
    }, toast, false)
  }

  getSemesterUniversities(universityId: any, semesterId: any, toast: ToastsManager): Observable<Semester[]> {
    /* return this.http.post("/unsecured/api/semester/university/search", {
     data: {universityId: universityId, semesterId: semesterId},
     paging: this.paging
     }, this.headers).map(res => {
     return res.json().result.data;
     });
     */
    return this.requestHelper.postUnsecured('semester/university/search', {
      data: {universityId: universityId, semesterId: semesterId}, paging: this.paging
    }, toast, false)

  }

  getVisibleSemesters(toast: ToastsManager): Observable<any> {
    /*  return this.http.post("/api/semester/search", {data: {visible: true}, paging: this.paging}, this.headers)
     .map(res => res.json().result.data);*/

    return this.requestHelper.post('semester/search', {
      data: {visible: true}, paging: this.paging
    }, toast, false)

  }

  getAllSemesters(toast: ToastsManager) {
    /* return this.http.post("/api/semester/search", {data: {}, paging: this.paging}, this.headers)
     .toPromise().then(res => res.json());*/

    return this.requestHelper.postToPromise('semester/search', {
      data: {}, paging: this.paging
    }, toast, false)
  }

  getCurrentSemester(toast: ToastsManager) {
    /*return this.http.post("/api/semester/search", {
     data: {currentDate: new Date().getTime()},
     paging: this.paging
     }, this.headers)
     .toPromise().then(res => res.json());*/

    return this.requestHelper.postToPromise('semester/search', {
      data: {currentDate: new Date().getTime()}, paging: this.paging
    }, toast, false)

  }

  addEditSemester(param: any, toast: ToastsManager) {
    /* console.log('სერვისი add/edit', param.id ? "edit" : "add", param.id);
     return this.http.post("/api/semester/" + ( param.id ? "edit" : "add"), {data: param}, this.headers)
     .toPromise().then(res => res.json());

     */
    return this.requestHelper.postToPromise("/semester/" + ( param.id ? "edit" : "add"), {
      data: param, paging: this.paging
    }, toast, true)
  }

  getStudentSemesters(param: {studentId}, toast: ToastsManager) {
    /* return this.http.post("/api/student/semester/get", {data: param,}, this.headers)
     .map(res => res.json());*/

    return this.requestHelper.postToPromise("student/semester/get", {
      data: param
    }, toast, false)
  }

  /* convertToSemester(responseObject) {
   if (responseObject && responseObject.success && responseObject.result && responseObject.result.data) {
   responseObject.result.data = responseObject.result.data.map(semester => new Semester(semester))
   }
   return responseObject;
   }*/

  getNotSelectedSubjects(param: {semesterId}, toast: ToastsManager) {
    //return this.http.post("/api/subject/unreleased/search", {data: param}, this.headers).map(res => res.json());

    return this.requestHelper.postToPromise("subject/unreleased/search", {
      data: param
    }, toast, false)
  }

  getSelectedSubjects(param: {semesterId}, toast: ToastsManager) {
    //return this.http.post("/api/subject/released/search", {data: param}, this.headers).map(res => res.json());

    return this.requestHelper.postToPromise("subject/released/search", {
      data: param
    }, toast, false)
  }

  getSelectedUniversities(param: {semesterId}, toast: ToastsManager) {
    //return this.http.post("/unsecured/api/university/semester/selected/search", {data: param}, this.headers).map(res => res.json());

    return this.requestHelper.postToPromiseUnsecured("university/semester/selected/search", {
      data: param
    }, toast, false)
  }

  getNotSelectedUniversities(param: {semesterId}, toast: ToastsManager) {
    //return this.http.post("/unsecured/api/university/semester/unselected/search", {data: param}, this.headers).map(res => res.json());

    return this.requestHelper.postToPromiseUnsecured("university/semester/unselected/search", {
      data: param
    }, toast, false)
  }

  deactivateSubject(id: number, toast: ToastsManager) {
    /*return this.http.post("/api/subject/release/delete", {data: {"id": id}}, this.headers).toPromise().then(res => {
     return res.json();
     });*/

    return this.requestHelper.postToPromise("subject/release/delete", {
      data: {"id": id}
    }, toast, true)
  }

  changeSemesterVisibility(state: boolean, id: number, toast: ToastsManager) {
    /*return this.http.post("/unsecured/api/semester/visibility/change", {
     data: {
     "id": id,
     "visible": state
     }
     }, this.headers).toPromise().then(res => {
     return res.json();
     });
     */

    return this.requestHelper.postToPromiseUnsecured("semester/visibility/change", {
      data: {
        "id": id,
        "visible": state
      }
    }, toast, true)
  }

  changeSemesterUniversityVisibility(state: boolean, universityId: number, semesterId: number, toast: ToastsManager) {
    /*return this.http.post("/unsecured/api/semester/university/visibility/change", {
     data: {
     "universityId": universityId,
     "semesterId": semesterId,
     "visible": state
     }
     }, this.headers).toPromise().then(res => {
     return res.json();
     });
     */
    return this.requestHelper.postToPromiseUnsecured("semester/university/visibility/change", {
      data: {
        "universityId": universityId,
        "semesterId": semesterId,
        "visible": state
      }
    }, toast, true)

  }

  addSemesterUniversity(universityId: number, semesterId: number, toast: ToastsManager) {
    /* return this.http.post("/unsecured/api/semester/university/add", {
     data: {
     "universityId": universityId,
     "semesterId": semesterId
     }
     }, this.headers).toPromise().then(res => {
     return res.json();
     });*/

    return this.requestHelper.postToPromiseUnsecured("semester/university/add", {
      data: {
        "universityId": universityId,
        "semesterId": semesterId
      }
    }, toast, true)

  }

  removeUniversityInSemester(universityId: number, semesterId: number,toast:ToastsManager) {
   /* return this.http.post("/unsecured/api/semester/university/remove", {
      data: {
        "universityId": universityId,
        "semesterId": semesterId
      }
    }, this.headers).toPromise().then(res => {
      return res.json();
    });
*/
    return this.requestHelper.postToPromiseUnsecured("semester/university/remove", {
      data: {
        "universityId": universityId,
        "semesterId": semesterId
      }
    }, toast, true)

  }
}
