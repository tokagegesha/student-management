import {Inject, Injectable} from '@angular/core';
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {ToastsManager} from "ng2-toastr";
import {RequestHelperService} from "../services/request-helper.service";

@Injectable()
export class ExchangeProgrammeService {
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(@Inject(Http) private http: Http,@Inject(RequestHelperService) private requestHelper: RequestHelperService) {
  }


  getExchangeProgrammes(query: any): Observable<any[]> {
    return this.http.post("/api/exchangeProgramme/search", {
      data: {name: query}, paging: {
        sort: {
          orders: [{property: "orderNumber", direction: "ASC"}]
        }
      }
    }, this.headers)
      .map(res => {
        return res.json().result.data;
      })
  }


  addEditExchangeProgramme(pojo: any) {
    return this.http.post("/api/exchangeProgramme/" + (pojo.id ? "edit" : "add"), {
      data: pojo
    }, this.headers).toPromise().then(res => {
      return res.json()
    });
  }


  deleteSubject(pojo: any, toast: ToastsManager) {
    return this.requestHelper.postToPromise('exchangeProgramme/delete', {data: pojo}, toast, true)
  }


}
