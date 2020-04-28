import {Inject, Injectable} from '@angular/core';
import {Headers, Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {ToastsManager} from "ng2-toastr";
import {RequestHelperService} from "../../shared/services/request-helper.service";


@Injectable()
export class ProgrammeService {
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(@Inject(Http) private http: Http, @Inject(RequestHelperService) private requestHelper: RequestHelperService) {
  }

  getProgrammes(pojo: any, toast: ToastsManager) {
    /*  return this.http.post("/unsecured/api/programme/search", {data: pojo}, this.headers)
     .toPromise()
     .then(res => {
     return res.json().result.data;
     }).catch(function () {
     console.log("error happened");
     })
     .catch(error => console.log(error));*/

    return this.requestHelper.postToPromiseUnsecured('programme/search', {data: {},
      paging: {
        sort: {
          orders: [{property: "orderNumber", direction: "ASC"}]
        }
      }}, toast, false)


  }

  getSubjectsInProgramme(pojo: any, toast: ToastsManager) {
    /* return this.http.post("/api/programme/subjects/search", {data: pojo}, this.headers)
     .toPromise()
     .then(res => {
     return res.json().result.data;
     }).catch(function () {
     console.log("error happened");
     })
     .catch(error => console.log(error));
     */
    return this.requestHelper.postToPromise('programme/subjects/search', {data: pojo}, toast, false)
  }

  deleteProgramme(pojo: any, toast: ToastsManager) {
    return this.requestHelper.postToPromise('programme/delete', {data: pojo}, toast, true)
  }


  deleteProgrammeSubject(id, toast: ToastsManager) {
    /*return this.http.post("/api/programme/subject/delete", {data: {id: id}}, this.headers)
     .toPromise()
     .then(res => {
     return res.json().result.data;
     })*/
    /*.catch(function () {
     console.log("error happened");
     })
     .catch(error => console.log(error));*/

    return this.requestHelper.postToPromise('programme/subject/delete', {data: {id: id}}, toast, true)

  }

  addProgrammeSubject(pojo: any, toast: ToastsManager) {
    /*return this.http.post("/api/programme/subject/add", {data: pojo}, this.headers)
     .toPromise()
     .then(res => {
     return res.json().result;
     })*/

    return this.requestHelper.postToPromise('programme/subject/add', {data: pojo}, toast, true)
  }

  editProgramme(id: number, name: string, degree: number, orderNumber: number, toast: ToastsManager) {
    /* return this.http.post("/api/programme/edit", {
     data: {
     name: name,
     id: id,
     degree: degree
     }
     }, this.headers).map(res => {
     return res.json().result
     });*/

    return this.requestHelper.postToPromise('programme/edit', {
      data: {
        name: name,
        id: id,
        degree: degree,
        orderNumber: orderNumber
      }
    }, toast, true)

  }

  addProgramme(name: string, degree: number, toast: ToastsManager) {
    /* return this.http.post("/api/programme/add", {
     data: {
     name: name,
     degree: degree
     }
     }, this.headers).map(res => {
     return res.json().result
     });*/

    return this.requestHelper.postToPromise('programme/add', {
      data: {
        name: name,
        degree: degree
      }
    }, toast, true)
  }
}
