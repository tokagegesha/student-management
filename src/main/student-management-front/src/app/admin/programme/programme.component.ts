import {AfterViewInit, Component, OnInit, QueryList, ViewChildren, ViewContainerRef, ViewEncapsulation} from '@angular/core';
import {ProgrammeService} from "./programme.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Observable} from "rxjs/Observable";
import {SubjectService} from "../subject/subject.service";
import {SemesterService} from "../../public/semester/semester.service";
import {SemesterEntity} from "../../shared/SemesterEntity";
import {ToastsManager} from "ng2-toastr";
import {CreateNewAutocompleteGroup, NgAutocompleteComponent} from "ng-auto-complete";

@Component({
  selector: 'app-programme',
  templateUrl: './programme.component.html',
  styleUrls: ['./programme.component.css'],
  providers: [ProgrammeService, SubjectService, SemesterService],
  encapsulation: ViewEncapsulation.None,
  styles: [`
    .big-modal-window .modal-dialog {
      width: 900px
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
  `]
})
export class ProgrammeComponent implements OnInit, AfterViewInit {
  @ViewChildren(NgAutocompleteComponent) public subjectSearches: QueryList<NgAutocompleteComponent>;

  //@ViewChild(NgAutocompleteComponent) public subjectSearch: NgAutocompleteComponent;
  public group = [CreateNewAutocompleteGroup('Choose programme', 'subjectSearch', [], {titleKey: 'name', childrenKey: null})];


  public programmes: any[];
  public selectedProgramme: any;
  public programmeSubjects: any[];
  public isCollapsed = true;
  public selectedSubjectForAdd: any;
  public selectedProgrammeSubject: any;
  public semesters: SemesterEntity[];
  public selectedSemester: SemesterEntity;
  public programme: Programme;
  successMessage: boolean;
  errorMessage: boolean;
  public newProgrammeSubject: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };
  public editProgrammeSelected: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };
  public newProgramme: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private programmeService: ProgrammeService,
              private subjectService: SubjectService,
              private modalService: NgbModal,
              private semesterService: SemesterService) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);
    this.loadData();
    this.programme = {
      id: null,
      name: '',
      degree: null
    }

  }

  ngAfterViewInit(): void {
  }

  loadData() {
    this.programmeService.getProgrammes({}, this.toast).then(response => this.programmes = response.data);
  }

  selectedSubjectChanged(subject) {
    this.selectedSubjectForAdd = subject;
  }

  getDegreeName(degree: number) {
    if (degree == 1) {
      return "ბაკალავრიატი";
    }
    if (degree == 2) {
      return "მაგისტრატურა";
    }
  }

  open(content) {
    this.editProgrammeSelected = {
      working: false,
      result: null
    };
    this.newProgrammeSubject = {
      working: false,
      result: null
    };
    this.selectedSubjectForAdd = null;
    this.programme = {
      id: null,
      name: '',
      degree: null
    };
    this.newProgramme = {
      working: false,
      result: null
    };
    this.modalService.open(content, {windowClass: 'big-modal-window'}).result.then((result) => {
    });
  }

  getSemesterList() {
    this.semesterService.getVisibleSemesters(this.toast).subscribe(response => {
      this.semesters = response.data.map(item => new SemesterEntity(item));
      this.selectedSemester = this.semesters[0];
    });
  }

  getProgrammeSubjects(programme: any) {
    setTimeout(() => {
      var subjectSearch = NgAutocompleteComponent.FindCompleter('subjectSearch', this.subjectSearches);
      //completer.SelectItem('subjectSearch', '1');
      //this.subjectSearch =
      this.subjectService.getSubjectsExceptData({programmeId: programme.id}, this.toast).then(data => {
        //subjectSearch.SetValues('subjectSearch', data);
        this.group = [CreateNewAutocompleteGroup('აირჩიე საგანი', 'subjectSearch', data.data, {titleKey: 'name', childrenKey: null})];

      });
    }, 1000);

    console.log("selected programme", programme);
    this.programmeService.getSubjectsInProgramme({programmeId: programme.id}, this.toast).then(response => {
      this.programmeSubjects = response.data;
    });
  }


  deleteProgramme(programme) {
    this.programmeService.deleteProgramme(programme, this.toast).then(response => {
      if (response.data) {
        this.loadData();
      }
    });
  }

  addProgramme(name: string, degree: number) {
    if (degree == null) {
      degree = 1;
    }
    this.programmeService.addProgramme(name, degree, this.toast).then(res => {
      if (res.data && res.data.length == 1) {
        this.newProgramme.result = {success: true, data: res.data[0]};
        this.programmes.push(res.data[0]);
      } else {
        this.newProgramme.result = {success: false, data: null};
      }
      this.newProgramme.working = false;
    }, error2 => this.newProgramme.working = false);
  }

  addProgrammeSubject(programmeId: any, semesterId: any, subjectId: any) {
    var data = {
      programmeId: programmeId,
      semesterId: semesterId,
      subjectId: subjectId
    };
    this.programmeService.addProgrammeSubject(data, this.toast).then(res => {
      if (res.data && res.data.length == 1) {
        this.newProgrammeSubject.result = {success: true, data: res.data[0]};
        this.getProgrammeSubjects(this.selectedProgramme);
      } else {
        this.newProgrammeSubject.result = {success: false, data: null};
      }
    });
  }


  editProgramme(id: number, name: string, degree: number, orderNumber: number) {
    console.log('ddd', orderNumber);
    this.editProgrammeSelected.working = true;
    this.programmeService.editProgramme(id, name, degree, orderNumber, this.toast).then(res => {
      if (res.data && res.data.length == 1) {
        this.editProgrammeSelected.result = {success: true, data: res.data[0]};
        this.programmeService.getProgrammes({}, this.toast).then(response => this.programmes = response.data);
      } else {
        this.editProgrammeSelected.result = {success: false, data: null};
      }
      this.editProgrammeSelected.working = false;
    }, error2 => this.editProgrammeSelected.working = false);

  }

  deleteProgrammeSubject(id: number) {
    this.programmeService.deleteProgrammeSubject(id, this.toast).then(response => {
      this.programmeSubjects = null;
      this.successMessage = true;
      this.getProgrammeSubjects(this.selectedProgramme);
      setTimeout(() => {
        this.successMessage = null
      }, 4000);
    });

  }

  selectedItem(item) {
    this.selectedSubjectForAdd = item.item;
    console.log("selectedItem", item);
  }

  searchSubject = (text$: Observable<string>) =>
    text$
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(inputString =>
        this.subjectService.getSubjectsExceptData({
          subjectName: inputString,
          programmeId: this.selectedProgramme.id
        }, this.toast)
          .catch(() => {
            return Observable.of([])
          }));

  subjectFormatter = (x: { name: string }) => x.name;

}


interface Programme {
  id: number;
  name: string;
  degree: number;
}
