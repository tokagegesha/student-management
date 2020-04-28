import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {SubjectService} from "./subject.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ToastsManager} from "ng2-toastr";
import {Pagination} from "../../shared/Pagination";

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  providers: [SubjectService]
})
export class SubjectComponent implements OnInit {
  public subjects: any[];
  public subjectLanguages: any[];
  public subject: Subject;
  public selectedSubject: any;
  public selectedSubjectLanguage = 'ENG';
  public subCredits = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  public selectedCredits = 1;
  page = 1;
  totalCount = 0;
  pageLimit = 20;


  public newSubject: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };

  public selectedSubjectEdit: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };


  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private subjectService: SubjectService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);
    this.loadData();
    this.subject = {
      id: null,
      name: '',
      credits: null,
      language: ''
    }

  }

  open(content) {
    this.newSubject = {
      working: false,
      result: null
    };
    this.selectedSubjectEdit = {
      working: false,
      result: null
    };
    this.modalService.open(content).result.then((result) => {
    });
  }

  addNewSubject(name: string, credits: number, language: string) {
    this.newSubject.working = true;
    this.subjectService.addSubject(name, credits, language, this.toast).then(res => {
      if (res.data && res.data.length == 1) {
        this.newSubject.result = {success: true, data: res.data[0]};
        this.subjects.push(res.data[0]);
      } else {
        this.newSubject.result = {success: false, data: null};
      }
      this.newSubject.working = false;
    }, error2 => this.newSubject.working = false);
  }

  editSubject(id: number, name: string, credits: number, language: string) {
    this.selectedSubjectEdit.working = true;
    this.subjectService.editSubject(id, name, credits, language, this.toast).then(res => {
      if (res.data && res.data.length == 1) {
        this.selectedSubjectEdit.result = {success: true, data: res.data[0]};
        this.loadData();
      } else {
        this.selectedSubjectEdit.result = {success: false, data: null};
      }
      this.selectedSubjectEdit.working = false;
    }, error2 => this.selectedSubjectEdit.working = false);

  }

  loadPage(page: number) {
    this.loadData();
  }


  loadData() {
    this.subjectService.getSubjects({}, this.getPaging(this.pageLimit, this.page), this.toast).then(response => {
        this.subjects = response.data;
        this.totalCount = response.totalCount;
      }
    );
    this.subjectService.getSubjectLanguages().then(languagesRes => {
      if (languagesRes && languagesRes.data) {
        this.subjectLanguages = languagesRes.data[0]
      }
      console.log(this.subjectLanguages);
    });

  }

  deleteSubject(subject) {
    this.subjectService.deleteSubject(subject, this.toast).then(resp => {
      if (resp.data) {
        this.loadData();
      }
    });
  }

  getPaging(pageLimit: number, page: number) {
    return {
      page: page - 1,
      size: pageLimit,
      sort: {
        orders: [{property: "name", direction: "ASC"}]
      }
    }
  }
}


interface Subject {
  id: number;
  name: string;
  credits: number;
  language: string;
}

