import {Injectable} from '@angular/core';
import {ToastsManager} from "ng2-toastr";
import {Observable} from "rxjs/Observable";
import {Headers, Http, RequestOptionsArgs} from "@angular/http";

@Injectable()
export class RequestHelperService {

  private basicOptions: RequestOptionsArgs = {
    headers: new Headers({'Content-Type': 'application/json'})
  };

  constructor(private http: Http) {
  }

  postToPromise(method: string, body: any, toastManager: ToastsManager, showSuccessMessage?: boolean) {
    if (method[0] === '/') method = method.substr(1);
    return this.http.post('/api/' + method, body, this.basicOptions)
      .toPromise()
      .then(res => {
        let response = res.json();
        if (response.error == null && response.success === true) {
          if (showSuccessMessage) toastManager.success('ქმედება ასახულია', 'წარმატება');
          return response.result;
        } else {
          console.error('Exception in request', response.error);
          toastManager.error(response.error, 'შეცდომა');
          return null;
        }
      })
      .catch(error => {
        console.log(1111, error);
        let errorResponse = error.json();
        if (errorResponse.message != null) {
          toastManager.error(errorResponse.message);
          return null;
        } else {
          toastManager.error('გაუთვალისწინებელი შეცდომა');
          return null;
        }
      });
  }

  getToPromise(method: string) {
    if (method[0] === '/') method = method.substr(1);
    return this.http.get('/api/' + method, this.basicOptions)
      .toPromise()
      .then(res => {
        let response = res.json();
        if (response.error == null && response.success === true) {
          return response.result;
        } else {
          console.error('Exception in request', response.error);
          return null;
        }
      })
      .catch(error => {
        let errorResponse = error.json();
        if (errorResponse.message != null) {
          return null;
        } else {
          return null;
        }
      });
  }

  postToPromiseUnsecured(method: string, body: any, toastManager: ToastsManager, showSuccessMessage?: boolean) {
    if (method[0] === '/') method = method.substr(1);
    return this.http.post('/unsecured/api/' + method, body, this.basicOptions)
      .toPromise()
      .then(res => {
        let response = res.json();
        if (response.error == null && response.success === true) {
          if (showSuccessMessage) toastManager.success('ქმედება ასახულია', 'წარმატება');
          return response.result;
        } else {
          console.error('Exception in request', response.error);
          toastManager.error(response.error, 'შეცდომა');
          return null;
        }
      })
      .catch(error => {
        let errorResponse = error.json();
        if (errorResponse.message != null) {
          toastManager.error(errorResponse.message);
          return null;
        } else {
          toastManager.error('გაუთვალისწინებელი შეცდომა');
          return null;
        }
      });
  }

  post(method: string, body: any, toastManager: ToastsManager, showSuccessMessage?: boolean): Observable<any> {
    if (method[0] === '/') method = method.substr(1);
    return this.http.post('/api/' + method, body, this.basicOptions)
      .map(res => {
        let response = res.json();
        if (response.error == null && response.success === true) {
          if (showSuccessMessage) toastManager.success('ქმედება ასახულია', 'წარმატება');
          return response.result;
        } else {
          console.error('Exception in request', response.error);
          toastManager.error(response.error, 'შეცდომა');
          return null;
        }
      })
      .catch(error => {
        let errorResponse = error.json();
        if (errorResponse.message != null) {
          toastManager.error(errorResponse.message);
          return null;
        } else {
          toastManager.error('გაუთვალისწინებელი შეცდომა');
          return null;
        }
      });
  }

  postUnsecured(method: string, body: any, toastManager: ToastsManager, showSuccessMessage?: boolean): Observable<any> {
    if (method[0] === '/') method = method.substr(1);
    return this.http.post('/unsecured/api/' + method, body, this.basicOptions)
      .map(res => {
        let response = res.json();
        if (response.error == null && response.success === true) {
          if (showSuccessMessage) toastManager.success('ქმედება ასახულია', 'წარმატება');
          return response.result;
        } else {
          console.error('Exception in request', response.error);
          toastManager.error(response.error, 'შეცდომა');
          return null;
        }
      })
      .catch(error => {
        let errorResponse = error.json();
        if (errorResponse.message != null) {
          toastManager.error(errorResponse.message);
          return null;
        } else {
          toastManager.error('გაუთვალისწინებელი შეცდომა');
          return null;
        }
      });
  }

  postToPromiseWithoutHeader(method: string, body: any, toastManager: ToastsManager, showSuccessMessage?: boolean) {
    if (method[0] === '/') method = method.substr(1);
    return this.http.post('/api/' + method, body)
      .toPromise()
      .then(res => {
        let response = res.json();
        if (response.error == null && response.success === true) {
          if (showSuccessMessage) toastManager.success('ქმედება ასახულია', 'წარმატება');
          return response.result;
        } else {
          toastManager.error(response.error, 'შეცდომა');
          return null;
        }
      })
      .catch(error => {
        let errorResponse = error.json();
        if (errorResponse.message != null) {
          toastManager.error(errorResponse.message);
          return null;
        } else {
          toastManager.error('გაუთვალისწინებელი შეცდომა');
          return null;
        }
      });
  }

}
