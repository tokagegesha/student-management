import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {CandidatesService} from "../candidates/candidates.service";
import {SignInService} from "../../shared/user/account/sign-in/sign-in.service";
import {Router} from "@angular/router";
import {ToastsManager} from "ng2-toastr";
import {IMultiSelectOption} from "angular-2-dropdown-multiselect";
import {SemesterService} from "../../public/semester/semester.service";
import {Semester} from "../../shared/SemesterModified";
import {FormatSemesterNamePipe} from "../../pipes/format.semester.name.pipe";
import {SemesterEntity} from "../../shared/SemesterEntity";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class AdminHomeComponent implements OnInit {
  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private candidateService: CandidatesService,
              private loginsService: SignInService,
              private router: Router,
              private semesterService: SemesterService) {
  }

  private selectedStudent: any;
  private students: any[];
  semesters: SemesterEntity[];
  semesterIds: number[];
  semesterOptions: IMultiSelectOption[];
  selectOptions: any[];
  private semesterNamePipe = new FormatSemesterNamePipe();

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);
    this.selectOptions = [];
    this.semesterIds = [];

    this.candidateService.getCandidates({statuses: [2]}, null, this.toast).then(data => {
      this.students = data.data;
    });

    this.semesterService.getAllSemesters(this.toast).then(response => {
      this.semesters = response.data.map(item => new SemesterEntity(item));
      console.log(this.semesters);
      this.semesters.forEach((sem) => {
        this.selectOptions.push(
          {
            id: sem.id, name: this.semesterNamePipe.transform(sem, 'en')
          }
        );
      });
      this.semesterOptions = this.selectOptions;
    });
  }

  onChange() {
    this.updateStudentGrid();
  }

  updateStudentGrid() {
    this.candidateService.getCandidates({statuses: [2], semesterIds: this.semesterIds}, null, this.toast).then(data => {
      console.log(data)
      this.students = data.data;
    });
  }


  getSemesterIdsJson() {
    let semJson = "";

    if (this.semesterIds.length > 0) {
      this.semesterIds.forEach(value => {
        semJson = semJson.concat(value + ",");
      });
      return semJson.slice(0, -1);
    }
    return semJson;
  }

  /*  openStudentProfile(student: any) {
   console.log("student", student);
   this.loginsService.getStudentSession(student.userId).then(data => {
   if (data.data) {
   console.log("navigatiob will start");
   this.router.navigateByUrl("/user/profile")
   }
   })
   }*/

}
