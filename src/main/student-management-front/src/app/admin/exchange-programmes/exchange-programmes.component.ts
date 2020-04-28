import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ExchangeProgrammeService} from "../../shared/exchange-programme/exchange-programme.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-exchange-programmes',
  templateUrl: './exchange-programmes.component.html',
  styleUrls: ['./exchange-programmes.component.css']
})
export class ExchangeProgrammesComponent implements OnInit {

  public dataModal: any = {};
  public exchangeProgrammes: any[];
  public newExchangeProgramme: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };


  constructor(private exchangeProgrammesService: ExchangeProgrammeService,
              public toast: ToastsManager, private vRef: ViewContainerRef,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);
    this.reloadGrid();

  }

  open(content) {
    this.newExchangeProgramme = {
      working: false,
      result: null
    };
    this.modalService.open(content).result.then((result) => {
    });
  }


  reloadGrid() {
    this.exchangeProgrammesService.getExchangeProgrammes(null).subscribe(data => {
      this.exchangeProgrammes = data;
    })
  }

  addEditExchangeProgramme(exchangeProgramme) {
    this.newExchangeProgramme.working = true;
    this.exchangeProgrammesService.addEditExchangeProgramme(exchangeProgramme).then(data => {
        if (data.success) {
          this.newExchangeProgramme.result = {success: true, data: data.result.data[0]};
          this.reloadGrid();
        } else {
          this.newExchangeProgramme.result = {success: false, data: null};
        }
        this.newExchangeProgramme.working = false;
      }, error => {
        this.newExchangeProgramme.working = false;
        this.newExchangeProgramme.result = {success: false, data: null}

      }
    )
  }

  deleteExchPro(data) {
    this.exchangeProgrammesService.deleteSubject(data, this.toast).then(resp => {
      if (resp.data) {
        this.reloadGrid();
      }
    })
  }

}
