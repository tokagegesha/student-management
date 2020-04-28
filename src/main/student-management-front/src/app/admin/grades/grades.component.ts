import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SubjectService} from "../subject/subject.service";
import {SemesterService} from "../../public/semester/semester.service";
import {CandidatesService} from "../candidates/candidates.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html',
  styleUrls: ['./grades.component.css'],
  providers: [SubjectService, SemesterService]
})
export class GradesComponent implements OnInit {

  public gradeTypes: any[];
  public subjectGradeToAdd: any = {};
  public selectedSemester: any;
  public selectedSubject: any;
  public semesters: any[];
  public subjects: any[];
  public students: any[];
  public dataNotSubmitted = false;
  public changeList = {};
  public

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private modalService: NgbModal,
              private subjectService: SubjectService,
              private semesterService: SemesterService,
              private candidateService: CandidatesService) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    let currentDate = new Date().getTime();
    this.semesterService.getAllSemesters(this.toast).then(res => {
      if ( res.data.length > 0) {
        this.semesters = res.data;
        this.semesters.forEach(item => {
          if ((item.beginDate < currentDate ) && ( currentDate < item.endDate)) {
            this.selectedSemester = item;
            console.log(this.selectedSemester)
          }
          item.beginDate = new Date(item.beginDate);
          item.endDate = new Date(item.endDate);
        });
        this.subjectService.getSubjectsReleased({semesterId: this.selectedSemester.id},this.toast).then(response => {
          if (response.data && response.data.length > 0) {
            this.subjects = response.data;
            this.selectedSubject = response.data[0];
            this.subjectChanged();
          }
        });
      }
    })

  }

  addToChanges(student, grade) {
    console.log(student, this.changeList);
    if (grade.grade && grade.grade >= 0) {
      if (!this.changeList[student.id]) {
        this.changeList[student.id] = {};
      }
      this.changeList[student.id][grade.gradeId] = grade.grade;
    } else {
      delete this.changeList[student.id][grade.gradeId];
      if (Object.keys(this.changeList[student.id]).length == 0) {
        delete this.changeList[student.id];
      }
    }
    this.dataNotSubmitted = Object.keys(this.changeList).length > 0;
  }

  addSubjectGradeType() {
    let me = this;
    this.subjectGradeToAdd.subjectReleaseId = this.selectedSubject.id;
    this.subjectService.addSubjectGrade(this.subjectGradeToAdd,this.toast).then(response => {
      if (response.data) me.gradeTypes.push(response.data[0]);
    });
  }

  submitGrades() {
    console.log("change list", this.changeList, "keys", Object.keys(this.changeList));
    let dataToSubmit = [];
    for (let studentId in this.changeList) {
      for (let gradeId in this.changeList[studentId]) {
        dataToSubmit.push({studentId: studentId, gradeId: gradeId, grade: this.changeList[studentId][gradeId]})
      }
    }
    console.log("data to submit", dataToSubmit);
    this.candidateService.addStudentGradeRecords(dataToSubmit, this.toast).then(respone => {
      this.changeList = {};
      this.subjectChanged();
    });
  }

  semesterChanged() {
    this.updateSubjects(this.selectedSemester)

  }

  updateSubjects(semester) {
    this.subjectService.getSubjectsReleased({semesterId: semester.id},this.toast).then(response => {
      this.subjects = response.data
    });
  }

  subjectChange(subject) {

  }

  subjectChanged() {
    console.log('subjectChanged');
    this.students = [];
    this.gradeTypes = [];
    console.log(this.selectedSubject);

    this.subjectService.getSubjectGrades(this.selectedSubject.id, this.toast).then(res=>{
      console.log(res.data);
      this.gradeTypes = res.data;
    });

    this.candidateService.getStudentsSubjectRecord(this.selectedSubject.id, this.toast).then(response => {
      console.log(response);
      if (response.data.length > 0) {
        this.students = response.data;
        this.students.forEach(student => {
          student.grades.forEach(grade => {
            grade.submited = grade.grade !== null;
          })
        });
        //this.gradeTypes = this.students[0].grades;
      }
    })
  }

  fetchData() {

  }

  open(content) {
    //
    this.modalService.open(content).result.then((result) => {
    });
  }
}
