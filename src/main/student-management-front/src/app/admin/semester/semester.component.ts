import {Component, OnInit, ViewEncapsulation, ViewContainerRef} from '@angular/core';
import {SemesterService} from "../../public/semester/semester.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {IMyDpOptions} from 'mydatepicker';
import {SubjectService} from "../subject/subject.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-semester',
  templateUrl: './semester.component.html',
  styleUrls: ['./semester.component.css'],
  providers: [SemesterService, SubjectService],
  encapsulation: ViewEncapsulation.None,
  styles: [`
    .big-modal-window .modal-dialog {
      width: 900px
    }
    .modal-title {
      margin: 0;
      line-height: 0;
    }

    .subject-auto-complete-result-button {
      text-align: end;
    }

    .list-group-item {
      display: table-cell;
    }

    .center-title {
      text-align: center;
    }

    .no-padding {
      padding: 0px 0px 8px;
      display: inline-flex;
    }

    .selector-margin {
      margin-left: 12px;
    }
    .modal-body {
      padding: 14px!important;
    }
    .modal-header {
      padding: 15px!important;
    }
  `]
})
export class SemesterComponent implements OnInit {
  public semesters: any[];
  public selectedSemester: any = null;
  public semester: SemesterInterface;
  public date: Date;
  public yearList: {};
  public dataModal: any;
  public ssFilterState = true;
  public suFilterState = true;

  public semesterUniversitiesSelected: any[];
  public semesterUniversitiesNotSelected: any[];
  public semesterSubjectsSelected: any[];
  public semesterUniversities: any[];
  public semesterSubjectsNotSelected: any[];
  public semesterSubjects: any[];
  private dateProps = ['beginDate', 'endDate', 'academicRegEnd', 'academicRegBegin', 'administrationRegBegin', 'administrationRegEnd'];


  validationMessage: boolean;
  successMessage: boolean;
  errorMessage: boolean;

  public myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'dd.mm.yyyy',
  };

  public newSemester: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private semesterService: SemesterService,
              private subjectService: SubjectService,
              private modalService: NgbModal) {
  }


  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.dataModal = {};
    this.yearList = this.yearRange(2000, 2030);
    this.dataModal.year = this.yearList[0];
    this.dataModal.season = 1;

    this.refreshSemesterList();

  }

  refreshSemesterList() {
    this.semesterService.getAllSemesters(this.toast).then(res => {
      if (res.data) {
        res.data.forEach(semester => {
          this.getSemesterObjectFromRest(semester);
        });
        this.semesters = res.data;
      }
    })
  }

  activateSubject(subject) {
    delete subject.editMode;
    this.subjectService.activateSubject(subject, this.selectedSemester.id, this.toast).then(res => {
      if (res.data) {
        this.syncSubjectListNew(this.selectedSemester);
      }
    })
  }

  moveToActivated(semester) {
    this.ssFilterState = true;
    semester.editMode = true;
    this.semesterSubjectsSelected = [semester].concat(this.semesterSubjectsSelected);
  }

  deactivateSubject(subject) {
    this.semesterService.deactivateSubject(subject.id, this.toast).then(res => {
      if (res.data) {
        this.syncSubjectListNew(this.selectedSemester)
      }
    });
    console.log(subject);
  }

  addEditSubjects(semester) {
    this.selectedSemester = semester;
    this.syncSubjectListNew(semester);
  }

  syncSubjectList() {
    this.semesterSubjects = this.semesterSubjectsSelected.map(subject => Object.assign({selected: true}, subject))
      .concat(this.semesterSubjectsNotSelected.map(subject => Object.assign({selected: false}, subject)))
  }

  syncSubjectListNew(semester) {
    this.semesterService.getSelectedSubjects(
      {semesterId: semester.id}, this.toast
    ).then(res => {
      this.semesterSubjectsSelected = res.data;
    });

    this.semesterService.getNotSelectedSubjects(
      {semesterId: semester.id}, this.toast
    ).then(res => {
      this.semesterSubjectsNotSelected = res.data;
    });
  }

  syncUniversityListNew(semester) {
    this.semesterService.getSelectedUniversities(
      {semesterId: semester.id}, this.toast
    ).then(res => {
      this.semesterUniversitiesSelected = res.data;
    });

    this.semesterService.getNotSelectedUniversities(
      {semesterId: semester.id}, this.toast
    ).then(res => {
      this.semesterUniversitiesNotSelected = res.data;
    });
  }

  yearRange(min, max) {
    return Array.from(Array(max - min + 1).keys()).map(n => n + min);
  }


  getSeasonString(season: number) {
    if (season == 1) {
      return "შემოდგომა";
    }
    return "გაზაფხული"
  }

  open(content) {
    this.newSemester = {
      working: false,
      result: null
    };
    this.modalService.open(content, {windowClass: 'big-modal-window'}).result.then((result) => {
    });
  }


  getSemesterObjectForRest(semester) {
    if (semester) {
      //semester.beginDate = new Date(semester.beginDate);
      let dataModalTemp = Object.assign({}, semester);
      dataModalTemp.beginDate = dataModalTemp.beginDate.jsdate;
      dataModalTemp.endDate = dataModalTemp.endDate.jsdate;
      dataModalTemp.academicRegBegin = dataModalTemp.academicRegBegin.jsdate;
      dataModalTemp.academicRegEnd = dataModalTemp.academicRegEnd.jsdate;
      dataModalTemp.administrationRegBegin = dataModalTemp.administrationRegBegin.jsdate;
      dataModalTemp.administrationRegEnd = dataModalTemp.administrationRegEnd.jsdate;
      return dataModalTemp;
    }
  }

  getSemesterObjectFromRest(semester) {
    this.dateProps.forEach(prop => {
      semester[prop] = new PluginDate(semester[prop]);
    });
  }

  showEditSemesterUniversities(semester) {
    this.selectedSemester = semester;
    this.syncUniversityListNew(semester);
  }

  getSubjectsReleased(semester: any) {
    console.log("selected semester", semester);
    this.subjectService.getSubjectsReleased({semesterId: semester.id}, this.toast).then(response => {
      this.semesterSubjects = response.data;
    }, error => console.log('error', error));
  }

  addEditSemester(semester: any) {
    if (semester.season && semester.year) {
      this.validationMessage = null;
      /*this.semester = {
       id: null,
       year: semester.year,
       season: semester.season,
       beginDate: semester.beginDate.jsdate,
       endDate: semester.endDate.jsdate,
       academicRegEnd: semester.academicRegEnd.jsdate,
       academicRegBegin: semester.academicRegBegin.jsdate,
       administrationRegBegin: semester.administrationRegBegin.jsdate,
       administrationRegEnd: semester.administrationRegEnd.jsdate,
       visible: null
       };*/


      this.newSemester.working = true;
      this.semesterService.addEditSemester(this.getSemesterObjectForRest(semester), this.toast).then(res => {
        if (res.data) {
          this.getSemesterObjectFromRest(res.data[0]);
          this.newSemester.result = {success: true, data: res.data[0]};
          this.refreshSemesterList();
        } else {
          this.newSemester.result = {success: false, data: null};
        }
        this.newSemester.working = false;
      }, error2 => {
        this.newSemester.working = false;
        this.newSemester.result = {success: false, data: null}
      })
      ;
    } else {
      this.validationMessage = true;
    }
  }

  changeSemesterVisibility(event, id) {
    this.semesterService.changeSemesterVisibility(event.currentValue, id, this.toast).then(res => {
    });
  }

  deactivateUniversity(item) {
    this.semesterService.removeUniversityInSemester(item.universityId, item.semesterId, this.toast).then(res => {
      if (res.data) {
        this.syncUniversityListNew(this.selectedSemester);
      }
      //
    });
  }

  addSemesterUniversity(item) {
    console.log(item, this.selectedSemester);
    this.semesterService.addSemesterUniversity(item.id, this.selectedSemester.id, this.toast).then(res => {
      if (res.data) {
        this.syncUniversityListNew(this.selectedSemester);
      }
      //
    });
  }

  toggleVisibility(item) {
    console.log(item);
    this.semesterService.changeSemesterUniversityVisibility(!item.visible, item.universityId, item.semesterId, this.toast).then(res => {
      if (res.data) {
        this.syncUniversityListNew(this.selectedSemester);
      }
    });
  }

}

function PluginDate(milliseconds) {
  let d = new Date(milliseconds);
  this.jsdate = milliseconds;
  this.date = {
    year: d.getFullYear(),
    month: d.getMonth() + 1,
    day: d.getDate()
  };
}

PluginDate.prototype.toString = function () {
  return this.date.year + "--" + this.date.month + "--" + this.date.day;
};

interface SemesterInterface {
  id: number,
  year: number,
  season: number,
  beginDate: Date,
  endDate: Date,
  academicRegEnd: Date,
  academicRegBegin: Date,
  administrationRegBegin: Date,
  administrationRegEnd: Date,
  visible: boolean
}
