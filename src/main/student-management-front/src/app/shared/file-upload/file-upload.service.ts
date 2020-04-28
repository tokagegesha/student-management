import {Injectable} from '@angular/core';
import {ToastsManager} from "ng2-toastr";
import {RequestHelperService} from "../services/request-helper.service";
import {Http} from "@angular/http";

@Injectable()
export class FileUploadService {

  constructor(private http: Http, private requestHelper:RequestHelperService) { }

  uploadFiles(files: File[], toastManager: ToastsManager){
    let formData: FormData = new FormData();
    for (var x = 0; x < files.length; x++) formData.append("attachments[]", files[x]);
    return this.requestHelper.postToPromiseWithoutHeader('/file/upload', formData, toastManager, true);
  }
}
