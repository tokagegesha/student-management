import {Inject, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/toPromise";
import {RequestHelperService} from "../services/request-helper.service";
import {ToastsManager} from "ng2-toastr";


@Injectable()
export class UniversitiesService {
  private headers = new Headers({'Content-Type': 'application/json'});

  private uniPaging = {
    sort: {
      orders: [
        {property: "orderNumber", direction: "ASC"}
      ]
    },
    size: 99999,
  };

  constructor(@Inject(Http) private http: Http, @Inject(RequestHelperService) private requestHelper: RequestHelperService) {
    /*config.showHint = true;*/
  }

  getUniversities(pojo: any, toast: ToastsManager) {


    return this.requestHelper.postToPromiseUnsecured('university/search/extended', {
      data: pojo,
      paging: this.uniPaging
    }, toast, false)
    /*return this.http.post("/unsecured/api/university/search/extended", {data: pojo}, this.headers)
     .toPromise()
     .then(res => {
     console.log(res.json().result.data);
     return res.json().result.data;
     }).catch(function () {
     console.log("error happened");
     })
     .catch(error => console.log(error));*/
  }


  getUniversities2(searchString: string, id: number = null, toast: ToastsManager): Observable<any> {

    return this.requestHelper.postUnsecured('university/search', {
      data: {
        name: searchString,
        id: id,
        visible: true
      }
    }, toast, false)


    /*console.log('servce call');
     return this.http.post("/unsecured/api/university/search", {
     data: {
     name: searchString,
     id: id,
     visible: true
     }
     }, this.headers).map(res => {
     console.log(res.json().result.data);
     return res.json().result.data
     });*/
  }

  editUniversity(id: number, name: string, address: string, countryId: number, info: string, orderNumber: number, toast: ToastsManager) {


    return this.requestHelper.postToPromise('university/edit', {
      data: {
        name: name,
        id: id,
        address: address,
        countryId: countryId,
        info: info,
        orderNumber: orderNumber
      }
    }, toast, true)


    /*
     return this.http.post("/api/university/edit", {
     data: {
     name: name,
     id: id,
     address: address,
     countryId: countryId,
     info: info
     }
     }, this.headers).map(res => {
     return res.json().result
     });*/
  }

  addUniversity(name: string, address: string, countryId: number, info: string, toast: ToastsManager) {


    return this.requestHelper.postToPromise('university/add', {
      data: {
        name: name,
        address: address,
        countryId: countryId,
        info: info
      }
    }, toast, true)

  }

  getStudentsType(toast: ToastsManager) {
    return this.requestHelper.postToPromiseUnsecured('student/degree/type/get', {
      data: {}
    }, toast, false)

  }

  /* return this.http.post("/api/university/add", {
   data: {
   name: name,
   address: address,
   countryId: countryId,
   info: info
   }
   }, this.headers).map(res => {
   return res.json().result
   });
   }*/


}
