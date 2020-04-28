import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {UserService} from "../user.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css'],
  providers: [UserService]
})
export class ActivationComponent implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    public toast: ToastsManager,
    private vRef: ViewContainerRef) {
  }

  responseMessage: string;
  loadingMessage = true;

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.activatedRoute.queryParams.subscribe((params: Params) => {
      if (params['token']) {
        this.userService.activate(params['token'],this.toast).then(res => {
          setTimeout(() => {
            this.responseMessage = res;
            this.loadingMessage = false
          }, 2000);
        }, err => {
          this.loadingMessage = false
        });
      }
    });
  }

}
