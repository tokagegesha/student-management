import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {SignInService} from "../../../shared/user/account/sign-in/sign-in.service";
import {CandidatesService} from "../../../admin/candidates/candidates.service";
import {SemesterService} from "../../semester/semester.service";
import {Semester} from "../../../shared/SemesterModified";
import {ProgrammeService} from "../../programme/programme.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.css'],
  providers: [SignInService, CandidatesService, SemesterService, ProgrammeService]

})
export class SubjectsComponent implements OnInit {
  private currentUser: any = {};
  private candidateInfo: any = {};
  showSubjects = false;
  semesters: Semester[];
  selectOptions: any[];
  selectedSemester: Semester;
  subjects: any[];
  studentSubjects: any[];
  selectedSubject: any;
  selectedStudentSubject: any;
  subjectAddErrorMessage = false;
  studentSubjectRemoveErrorMessage = false;
  public statusFilter: any = {
    '1': false,
    '2': false,
    '3': false
  };
  public searchParams: any = {paging: undefined};

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private singInService: SignInService,
              private candidateService: CandidatesService,
              private semesterService: SemesterService,
              private programmeService: ProgrammeService) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.studentSubjects = [];
    this.singInService.getCurrentUserDetails().then(data => {
      if (data && data.length > 0) {
        this.currentUser = data[0];
        this.selectOptions = [];
        this.candidateService.getCandidates({
          "userId": this.currentUser.id,
          statuses: [2]
        }, null, this.toast).then(res => {
          if (res && res.data && res.data.length == 1) {
            this.showSubjects = true;
            this.candidateInfo = res.data[0];
            this.semesters = this.candidateInfo.semesters;
            this.selectedSemester = this.semesters[0];
            this.loadSubjects(this.selectedSemester.semesterId);
            this.candidateService.getStudentSubject({
              studentId: this.candidateInfo.id, semesterId: this.selectedSemester.semesterId
            }, this.toast).then(response => {
              this.studentSubjects = response.data;
            })
          }
        })
      }
    })
  }

  loadSubjects(semesterId: number) {
    this.programmeService.getCutProgrammeSubjects(semesterId, this.candidateInfo.id, this.toast).then(data => {
      if (data.data) {
        this.subjects = data.data;
        console.log(1212, this.subjects);
      }
    });
  }

  addSubject(subject: any, i: number) {
    this.subjectAddErrorMessage = false;
    this.candidateService.addStudentSubject(this.candidateInfo.id, subject.id, this.toast).then(data => {
      this.subjects.splice(i, 1);
      this.candidateService.getStudentSubject({
        studentId: this.candidateInfo.id,
        semesterId: this.selectedSemester.semesterId
      }, this.toast).then(subjects => {
        this.studentSubjects = subjects.data;
      })
    });

  }

  removeStudentSubject(studentSubject: any, index: number) {
    this.candidateService.studentSubjectRemove(this.candidateInfo.id, studentSubject.subjectReleaseId, this.toast).then(data => {
      this.studentSubjects.splice(index, 1);
      if (this.selectedSemester) {
        this.loadSubjects(this.selectedSemester.id);
      }
    });
  }

}
