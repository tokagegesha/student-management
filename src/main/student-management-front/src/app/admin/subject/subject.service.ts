import {Inject, Injectable} from '@angular/core';
import {Headers, Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {RequestHelperService} from "../../shared/services/request-helper.service";
import {ToastsManager} from "ng2-toastr";


@Injectable()
export class SubjectService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(@Inject(Http) private http: Http, @Inject(RequestHelperService) private requestHelper: RequestHelperService) {
  }

  getSubjects(pojo: any, paging: any = null, toast: ToastsManager) {
    return this.requestHelper.postToPromise('subject/search', {data: pojo, paging: paging}, toast, false)
  }

  getSubjectsReleased(pojo: any, toast: ToastsManager) {
    return this.requestHelper.postToPromise('subject/released/search', {data: pojo}, toast, false)
  }

  deleteSubject(pojo: any, toast: ToastsManager) {
    return this.requestHelper.postToPromise('subject/delete', {data: pojo}, toast, true)
  }


  getSubjectsExceptData(pojo: any, toast: ToastsManager) {
    /*return this.http.post("/api/subject/except/search", {data: pojo}, this.headers)
     .toPromise()
     .then(res => {
     console.log(res.json().result.data);
     return res.json().result.data;
     }).catch(function () {
     console.log("error happened");
     })
     .catch(error => console.log(error));*/

    return this.requestHelper.postToPromise('subject/except/search', {data: pojo}, toast, false)
  }

  editSubject(id: number, name: string,defaultMaxStudent: number,defaultMinStudent: number, credits: number, language: string, toast: ToastsManager) {
    /* return this.http.post("/api/subject/edit", {
     data: {
     id: id,
     name: name,
     credits: credits
     }
     }, this.headers).map(res => {
     return res.json().result
     });*/
    return this.requestHelper.postToPromise('subject/edit', {
      data: {
        id: id,
        name: name,
        credits: credits,
        language: language,
        defaultMaxStudent: defaultMaxStudent,
        defaultMinStudent: defaultMinStudent
      }
    }, toast, true)
  }

  editSetting(keyword, value, toast: ToastsManager) {
    return this.requestHelper.postToPromise('setting/edit', {
      data: {
        keyword: keyword,
        value: value
      }
    }, toast, true)
  }

  getSettingValue(keyword, toast: ToastsManager) {
    return this.requestHelper.postToPromiseUnsecured('setting/search', {
      data: {
        keyword: keyword,
      }
    }, toast, false)
  }

  addSubject(name: string, defaultMaxStudent: number, defaultMinStudent: number, credits: number, language: string, toast: ToastsManager) {
    /*return this.http.post("/api/subject/add", {
     data: {
     name: name,
     credits: credits
     }
     }, this.headers).map(res => {
     return res.json().result
     });*/

    return this.requestHelper.postToPromise('subject/add', {
      data: {
        name: name,
        credits: credits,
        language: language,
        defaultMaxStudent: defaultMaxStudent,
        defaultMinStudent: defaultMinStudent
      }
    }, toast, true)

  }

  addSubjectGrade(data: any, toast: ToastsManager) {
    /*return this.http.post("/api/subject/released/grade/add", {
     data: {
     name: data.name,
     max: data.maxGrade,
     type: data.gradeType,
     subjectReleaseId: data.subjectReleaseId
     }
     }, this.headers).toPromise()
     .then(res => {
     console.log(res.json());
     return res.json();
     });*/

    return this.requestHelper.postToPromise('subject/released/grade/add', {
      data: {
        name: data.name,
        max: data.maxGrade,
        type: data.gradeType,
        subjectReleaseId: data.subjectReleaseId
      }
    }, toast, true)


  }


  activateSubject(subject: any, semesterId: number, toast: ToastsManager) {
    let a = Object.assign({semesterId: semesterId}, subject);
    a.subjectId = a.id;
    delete a.id;

    //   return this.http.post("/api/subject/release/add", {
    //     data: a
    //   }, this.headers)
    //     .toPromise()
    //     .then(res => {
    //       console.log(res.json());
    //       return res.json();
    //     }).catch(function () {
    //       console.log("error happened");
    //     })
    //     .catch(error => console.log(error));
    // }

    return this.requestHelper.postToPromise('subject/release/add', {
      data: a
    }, toast, true)
  }

  activateAllSubject(semesterId: number, toast: ToastsManager) {
    let a = Object.assign({semesterId: semesterId});
    return this.requestHelper.postToPromise('subject/release/addAll', {
      data: a
    }, toast, true)
  }

  deleteAllRelease(semesterId: number, toast: ToastsManager) {
    let a = Object.assign({semesterId: semesterId});
    return this.requestHelper.postToPromise('subject/release/deleteAll', {
      data: a
    }, toast, true)
  }

  getSubjectGrades(subjectReleasedId: number, toast: ToastsManager) {
    return this.requestHelper.postToPromise('subject/released/grade/search', {
      data: {
        id: subjectReleasedId
      }
    }, toast, false)
  }


  getSubjectLanguages() {
    return this.requestHelper.getToPromise('/subject/language/get')
  }
}
