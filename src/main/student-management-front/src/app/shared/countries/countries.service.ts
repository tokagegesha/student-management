import {Inject, Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {ToastsManager} from "ng2-toastr";
import {RequestHelperService} from "../services/request-helper.service";

@Injectable()
export class CountriesService {
  private headers = new Headers({'Content-Type': 'application/json'});


  constructor(@Inject(Http) private http: Http, @Inject(RequestHelperService) private requestHelper: RequestHelperService) {
  }


  getCountries(pojo: any, toastManager: ToastsManager) {

    return this.requestHelper.postToPromise('country/search', {data: pojo}, toastManager, false);
/*    return this.http.postToPromise("/api/country/search", {data: pojo}, this.headers)
      .toPromise()
      .then(res => {
        let response = res.json();
        if (response.error == null) {
          return response.result;
        } else {
          console.error('Exception in request', response.error);
          toastManager.error(response.error, 'შეცდომა');
        }
      })
      .catch(error => toastManager.error(error, 'გაუთვალისწინებელი შეცდომა'));*/
  }

}
