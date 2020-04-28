import {Inject, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {RequestHelperService} from "../../shared/services/request-helper.service";
import {ToastsManager} from "ng2-toastr";


@Injectable()
export class ProgrammeService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private paging = {
    sort: {
      orders: [
        {property: "orderNumber", direction: "ASC"}
      ]
    }
  };

  constructor(@Inject(Http) private http: Http,
              @Inject(RequestHelperService) private requestHelper: RequestHelperService) {
  }

  getUniversityProgrammes(universityId: number, semesterId: number, toast: ToastsManager) {
    /* return this.http.post("/unsecured/api/programme/search", {
     data: {
     universityId: universityId,
     semesterId: semesterId
     }
     }, this.headers)
     .toPromise()
     .then(res => res.json()).catch(function () {
     console.log("error happened");
     })
     .catch(error => console.log(error));*/


    return this.requestHelper.postToPromiseUnsecured('programme/search', {
      data: {
        universityId: universityId,
        semesterId: semesterId
      },
      paging: this.paging
    }, toast, false)
  }

  getUniversityProgrammes2(universityId: number, searchString: string, programmeReleaseId: number = null, toast: ToastsManager) {
    /*  return this.http.post("/api/programme/university/search", {
     data: {
     universityId: universityId,
     programmeName: searchString,
     id: programmeReleaseId
     }
     }, this.headers).map(res => {
     console.log("searched for", universityId, searchString);
     console.log("uni programmes from api: ", res.json());
     return res.json().result.data;
     });

     */
    return this.requestHelper.postToPromise('programme/university/search', {
      data: {
        universityId: universityId,
        programmeName: searchString,
        id: programmeReleaseId
      }
    }, toast, false)

  }

  getProgrammeSubjects(programmeId: number, semesterId: number, toast: ToastsManager) {
    /* return this.http.post("/unsecured/api/programme/subject/search", {
     data: {
     programmeId: programmeId,
     semesterId: semesterId
     }
     }, this.headers)
     .toPromise()
     .then(res => res.json()).catch(function () {
     console.log("error happened");
     })
     .catch(error => console.log(error));
     */

    return this.requestHelper.postToPromiseUnsecured('programme/subject/search', {
      data: {
        programmeId: programmeId,
        semesterId: semesterId
      }, paging: {
        sort: {
          orders: [{property: "subjectName", direction: "ASC"}]
        }
      }
    }, toast, false)

  }

  getCutProgrammeSubjects(semesterId: number, studentId: number, toast: ToastsManager) {
    /*return this.http.post("/unsecured/api/programme/subject/cut/search", {
      data: {
        semesterId: semesterId,
        studentId: studentId
      }
    }, this.headers)
      .toPromise()
      .then(res => res.json()).catch(function () {
        console.log("error happened");
      })
      .catch(error => console.log(error));
*/
    return this.requestHelper.postToPromiseUnsecured('programme/subject/cut/search', {
      data: {
        semesterId: semesterId,
        studentId: studentId
      }
    }, toast, false)
  }
}
