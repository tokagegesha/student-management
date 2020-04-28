import {Inject, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {RequestHelperService} from "../../shared/services/request-helper.service";
import {ToastsManager} from "ng2-toastr";


@Injectable()
export class CandidatesService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http, @Inject(RequestHelperService) private requestHelper: RequestHelperService) {
  }

  /*  getUniversities(pojo: any) {

   return this.http.postToPromise("/api/university/search", {data: pojo}, this.headers)
   .toPromise()
   .then(res => res.json().result.data).catch(function () {
   console.log("error happened");
   })
   .catch(error => console.log(error));
   }*/

  getCandidates(params: any = null, paging: any = null, toast: ToastsManager) {
    /*return this.http.post("/api/student/search/extended", {
     data: params || {},
     paging: paging
     }, this.headers).map(res => {
     console.log('students',res.json().result);
     return res.json().result;
     });*/
    return this.requestHelper.postToPromise('student/search/extended', {
      data: params || {},
      paging: paging
    }, toast, false)
  }

  getStudent(userId: number, toast: ToastsManager) {
    /*return this.http.post("/api/student/get", {data: {userId: userId}}, this.headers).map(res => {
     return res.json();
     });*/
    return this.requestHelper.postToPromise('student/get', {data: {userId: userId}}, toast, false)
  }

  acceptCandidate(studentId: number, letterOfAcceptance: File, toast: ToastsManager) {

    let formData: FormData = new FormData();
    formData.append("studentId", studentId.toString());
    formData.append("letterOfAcceptance", letterOfAcceptance, letterOfAcceptance.name);

    return this.requestHelper.postToPromiseWithoutHeader('student/accept', formData, toast, true)
  }

  rejectCandidate(studentId: number, message: string, toast: ToastsManager) {
    return this.requestHelper.postToPromise('student/reject', {data: {id: studentId, message: message}}, toast, true);
  }

  pendCandidate(studentId: number, message: string, toast: ToastsManager) {
    return this.requestHelper.postToPromise('student/pend', {data: {id: studentId}}, toast, false)
  }

  getCandidateFile(id: number) {
    console.log(id);
    return this.http.get("/api/student/file/get/" + id);
  }

  addStudentSubject(studentId: number, subjectReleaseId: number, toast: ToastsManager) {
    let data = {
      studentId: studentId,
      subjectReleaseId: subjectReleaseId,
    };
    /*
     return this.http.post("/api/student/subject/add", {data: data}, this.headers).map(res => {
     return res.json();
     });*/
    return this.requestHelper.postToPromise('student/subject/add', {data: data}, toast, true)
  }

  getStudentSubject(params: any = null, toast: ToastsManager) {
    /* return this.http.post("/api/student/subject/search/extended", {
     data: params || {},
     paging: paging
     }, this.headers).map(res => {
     return res.json();
     });*/
    return this.requestHelper.postToPromise('student/subject/search/extended', {data: params || {}}, toast, false);

  }

  studentSubjectRemove(studentId: number, srId: number, toast: ToastsManager) {
    /*return this.http.post("/api/student/subject/remove", {
      data: {}
    }, this.headers).map(res => {
      return res.json();
    });*/
    return this.requestHelper.postToPromise('student/subject/remove', {
      data: {
        studentId: studentId,
        subjectReleaseId: srId
      }
    }, toast, true);

  }

  getStudentSubjectRecord(toast: ToastsManager) {
    /*return this.http.post("/api/student/subject/grade/search", {data: {}}, this.headers).toPromise().then(res => {
      return res.json();
    });*/
    return this.requestHelper.postToPromise('student/subject/grade/search', {data: {}}, toast, false);
  }

  getStudentsSubjectRecord(subjectReleaseId: number, toast: ToastsManager) {
    /*  return this.http.post("/api/student/subject/record/search", {data: {subjectReleaseId: subjectReleaseId}}, this.headers).toPromise().then(res => {
        return res.json();
      });*/
    return this.requestHelper.postToPromise('student/subject/record/search', {data: {subjectReleaseId: subjectReleaseId}}, toast, false);
  }

  addStudentGradeRecords(dataToSubmit: any[], toast: ToastsManager) {
    /* return this.http.post("/api/student/subject/records/add", {data: {studentGrades: dataToSubmit}}, this.headers).toPromise().then(res => {
       return res.json();
     });*/
    return this.requestHelper.postToPromise('student/subject/records/add', {data: {studentGrades: dataToSubmit}}, toast, true);
  }

}
