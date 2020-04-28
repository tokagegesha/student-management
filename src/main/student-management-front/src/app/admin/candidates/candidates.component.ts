import {Component, ElementRef, OnInit, ViewContainerRef} from '@angular/core';
import {CandidatesService} from "./candidates.service";
import {Pagination} from "../../shared/Pagination";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ToastsManager} from "ng2-toastr";

declare var jQuery: any;

@Component({
  selector: 'app-candidates',
  templateUrl: './candidates.component.html',
  styleUrls: ['./candidates.component.css']
})
export class CandidatesComponent implements OnInit {
  public selectedCandidate: any = null;
  public selectedCandidateStatus: number;
  public rejectMessage: string = null;
  public letterOfAcceptance;
  letterOfAcceptanceRequired = false;

  public selectedCandidateEdit: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };

  candidatesList: any[] = [];
  candidatesPaging: Pagination = new Pagination(20, 3, 3);

  typeDict = {
    1: {name: "მომლოდინე", cssClass: 'glyphicon-hourglass', style: {color: 'gold'}},
    2: {name: "დადასტურებული", cssClass: 'glyphicon-ok-circle', style: {color: 'green'}},
    3: {name: "უარყოფილი", cssClass: 'glyphicon-ban-circle', style: {color: 'red'}},
  };

  accommodationDict = {
    1: {name: "კი",  style: {color: 'green'}},
    2: {name: "არა",  style: {color: 'red'}},
  };

  public statusFilter: any = {
    '1': false,
    '2': false,
    '3': false
  };
  public searchParams: any = {paging: undefined};

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private candidateService: CandidatesService,
              private domElement: ElementRef,
              private modalService: NgbModal) {
  }

  open(content) {
    console.log("here comes");
    this.modalService.open(content).result.then((result) => {
    });
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.candidateService.getCandidates(null, null, this.toast).then(data => {
      this.candidatesList = data.data;
      this.candidatesPaging.setPaging(data);
    });
  }

  updateStatusFilter(status: number) {
    this.searchParams.statuses = [];
    this.statusFilter['' + status] = !this.statusFilter['' + status];
    Object.keys(this.statusFilter).forEach(key => {
      if (this.statusFilter[key] == true) this.searchParams.statuses.push(+key);
    });
    this.updateCandidatesTable();
  }

  updateCandidatesTable() {
    this.candidateService.getCandidates(this.searchParams, this.candidatesPaging.getPaging(), this.toast).then(res => {
      this.candidatesList = res.data;
      this.candidatesPaging.setPaging(res);
    });
  }

  editCandidateStatus(studentId: number, status: number, message: string, letterOfAcceptance: any) {
    console.log(letterOfAcceptance);
    if (status == 2) {
      if (!letterOfAcceptance) {
        this.letterOfAcceptanceRequired = true;
      } else {
        this.candidateService.acceptCandidate(studentId,letterOfAcceptance, this.toast).then(res => {
          this.clearData()
        });
      }
    } else if (status == 3) {
      this.candidateService.rejectCandidate(studentId, message, this.toast).then(res => {
        this.clearData()
      });
    } else if (status == 1) {
      this.candidateService.pendCandidate(studentId, message, this.toast).then(res => {
        this.clearData()
      });
    }
  }

  clearData() {
    this.letterOfAcceptance = null;
    this.letterOfAcceptanceRequired = false;
  }

  chooseFile(event) {
    this.letterOfAcceptance = event.target.files[0];
  }

}
