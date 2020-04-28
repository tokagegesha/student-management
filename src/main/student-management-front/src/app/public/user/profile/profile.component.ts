import {AfterViewInit, Component, OnInit, ViewContainerRef} from '@angular/core';
import {SignInService} from "../../../shared/user/account/sign-in/sign-in.service";
import {CandidatesService} from "../../../admin/candidates/candidates.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {UserService} from "../../../shared/user/user.service";
import {Observable} from "rxjs/Observable";
import {IMultiSelectOption} from "angular-2-dropdown-multiselect";
import {Semester} from "../../../shared/SemesterModified";
import {SemesterService} from "../../semester/semester.service";
import {UniversitiesService} from "../../../shared/universities/universities.service";
import {FormatSemesterNamePipe} from "../../../pipes/format.semester.name.pipe";
import {ToastsManager} from "ng2-toastr";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit, AfterViewInit {

  /* @ViewChild(NgAutocompleteComponent) public universitySearch: NgAutocompleteComponent;

   public group = [CreateNewAutocompleteGroup('Find your university', 'universitySearch', [], {titleKey: 'name', childrenKey: null})];
 */
  /*
    changeUniversity(uni) {
      //this.selectedUniversity = item.item;
      this.updateSemesters(uni);
    }
  */

  targetUserId: number = null;
  userInfo: any;
  userRole: string;
  studentInfo: any;
  studentInfoToEdit: any;
  userInfoToEdit: any;
  studentInfoEditMode: boolean = false;
  userInfoEditDisabled = true;
  userInfoEditSaveButtonEnabled = false;

  selectedUniversity: University;
  searchFailed = true;
  semesters: Semester[];
  semesterIds: number[];
  myOptions: IMultiSelectOption[];
  selectOptions: any[];
  semesterNamePipe = new FormatSemesterNamePipe();

  private letterOfNomination: File;
  private cv: File;
  private diploma: File;
  private learningAgreement: File;
  private universityRecord: File;
  private picture: File;
  private passport: File;
  private proofOfEnglishSkill: File;
  private healthInsurance: File;

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private singInService: SignInService,
              private candidateService: CandidatesService,
              private userService: UserService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private semesterService: SemesterService,
              private universityService: UniversitiesService) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);
    this.loadData();

  }

  loadData() {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      if (params['userId']) {
        this.targetUserId = params['userId'];
      }
    });

    this.studentInfo = {};
    this.userInfo = {};
    this.studentInfoToEdit = {};
    this.userInfoToEdit = {};
    this.userRole = '';
    this.selectOptions = [];
    this.semesterIds = [];

    this.singInService.getCurrentUserDetails(this.targetUserId).then((data) => {
      let receivedUser = data && data.length > 0 ? data[0] : null;
      if (receivedUser) {
        console.log(receivedUser);
        this.userInfo = Object.assign({}, receivedUser);
        ;
        this.userInfoToEdit = receivedUser;
        this.userRole = this.targetUserId && receivedUser ? 'ADMIN' : 'STUDENT';
        // this.userRole=this.userInfo.role;
        this.candidateService.getStudent(receivedUser.id, this.toast).then(data => {
          this.studentInfo = Object.assign({}, data.data[0]);
          /* this.universityService.getUniversities({visible: true}).then(unis=>{
             this.universitySearch.SetValues('universitySearch', unis);
           })*/
          this.studentInfoToEdit = data.data[0];
          console.log("stud", this.studentInfoToEdit);

          this.universityService.getUniversities2(null, this.studentInfoToEdit.universityId, this.toast)
            .subscribe(response => {
              this.selectedUniversity = response.data[0];
              this.semesterService.getAccessibleSemesters(this.selectedUniversity.id, this.toast).subscribe(response => {
                this.semesters = response.data.map(item => new Semester(item));
                this.semesters.forEach((sem) => {
                  this.selectOptions.push(
                    {
                      id: sem.semesterId, name: this.semesterNamePipe.transform(sem, 'en')
                    }
                  );
                });
                this.myOptions = this.selectOptions;
              });
              this.studentInfo.semesters.forEach(semester => {
                this.semesterIds.push(semester.semesterId);
              });
              console.log("semesters", this.semesterIds);
            });


        })
      } else {
        this.router.navigateByUrl("/login")
      }
    })
  }

  ngAfterViewInit(): void {

  }

  searchUniversity = (text$: Observable<string>) =>
    text$
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(inputString =>
        this.universityService.getUniversities2(inputString, null, this.toast)
          .do(() => this.searchFailed = false)
          .catch(() => {
            this.searchFailed = true;
            return Observable.of([])
          }));

  updateSemesters(selectedUniversity: any) {
    this.selectOptions = [];
    this.myOptions = [];
    this.semesterIds = [];
    if (selectedUniversity instanceof Object && selectedUniversity.hasOwnProperty("id")) {
      this.selectedUniversity = selectedUniversity;
      this.studentInfoToEdit.universityId = this.selectedUniversity.id;
      this.semesterService.getAccessibleSemesters(this.selectedUniversity.id, this.toast).subscribe(response => {
        this.semesters = response.data.map(item => new Semester(item));
        this.semesters.forEach((sem) => {
          this.selectOptions.push(
            {
              id: sem.semesterId, name: this.semesterNamePipe.transform(sem, 'eng')
            }
          );
        });
        this.myOptions = this.selectOptions
      });
    }
  }

  universityFormatter = (x: { name: string }) => x.name;

  saveUserInfo() {
    var requestData = {
      id: this.userInfo.id,
      email: this.userInfo.email,
      firstName: this.userInfo.firstName,
      lastName: this.userInfo.lastName,
    };
    this.userService.changeUserInfo(requestData).subscribe(res => {
      if (res.success) {
        this.userInfo = res.result.data[0];
        this.userInfoEditDisabled = true;
        this.userInfoEditSaveButtonEnabled = false;
      } else {
        //todo throw error
      }
    });
  }

  saveStudentInfo() {
    console.log(this.studentInfoToEdit);
    if (!(this.studentInfoToEdit.universityId &&
      this.semesterIds.length > 0 &&
      this.studentInfoToEdit.passportNumber &&
      this.studentInfoToEdit.gender &&
      this.studentInfoToEdit.citizenship &&
      this.studentInfoToEdit.countryOfResidence &&
      this.studentInfoToEdit.birthDate &&
      this.studentInfoToEdit.address &&
      this.studentInfoToEdit.city &&
      this.studentInfoToEdit.country &&
      this.studentInfoToEdit.phone &&
      this.studentInfoToEdit.contactPersonName &&
      this.studentInfoToEdit.contactPersonAddress &&
      this.studentInfoToEdit.contactPersonPhone &&
      this.studentInfoToEdit.contactPersonRelationship
    )) {
      this.toast.error("Student Data Property Is Null");
      return;
    }
    this.studentInfoToEdit.semesterIds = this.semesterIds;
    this.userService.changeStudentInfo(this.studentInfoToEdit).subscribe(res => {
      if (res.success) {
        this.studentInfo = Object.assign({}, res.result.data[0]);
        this.studentInfoToEdit = res.result.data[0];
        this.studentInfoEditMode = false;
      } else {
        //todo throw error
      }
    });
  }

  onChange() {
    console.log(this.semesterIds);
  }

  roleBackStudentData() {
    this.studentInfoToEdit = Object.assign({}, this.studentInfo);
  }

  roleBackUserData() {
    this.userInfoToEdit = Object.assign({}, this.userInfo);
  }


  fileChange(event: any, type: number) {
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      this.getFileByType(type, fileList[0]);
    } else if (fileList.length == 0) {
      this.getFileByType(type, null);
    }
  }

  getFileByType(type: number, file: File) {
    switch (type) {
      case 1: {
        this.letterOfNomination = file;
        break;
      }
      case 2: {
        this.cv = file;
        break;
      }
      case 3: {
        this.diploma = file;
        break;
      }
      case 4: {
        this.learningAgreement = file;
        break;
      }
      case 5: {
        this.universityRecord = file;
        break;
      }
      case 6: {
        this.picture = file;
        break;
      }
      case 7: {
        this.passport = file;
        break;
      }
      case 8 : {
        this.proofOfEnglishSkill = file;
        break;
      }
      case 9 : {
        this.healthInsurance = file;
        break;
      }
    }
  }

  updateStudentDocumentsData() {

    let formData: FormData = new FormData();

    if (this.cv) {
      formData.append('cv', this.cv, this.cv.name);
    }
    if (this.letterOfNomination) {
      formData.append("letterOfNomination", this.letterOfNomination, this.letterOfNomination.name);
    }
    if (this.picture) {
      formData.append("picture", this.picture, this.picture.name);
    }
    if (this.passport) {
      formData.append("passport", this.passport, this.passport.name);
    }
    if (this.diploma) {
      formData.append("diploma", this.diploma, this.diploma.name);
    }

    if (this.learningAgreement) {
      formData.append("learningAgreement", this.learningAgreement, this.learningAgreement.name);
    }

    if (this.universityRecord) {
      formData.append("universityRecord", this.universityRecord, this.universityRecord.name);
    }

    if (this.proofOfEnglishSkill) {
      formData.append("proofOfEnglishSkill", this.proofOfEnglishSkill, this.proofOfEnglishSkill.name);
    }

    if (this.healthInsurance) {
      formData.append("healthInsurance", this.healthInsurance, this.healthInsurance.name);
    }

    this.userService.updateStudentDocumentsData(formData, this.toast).then(res => {
      if (res.success) {
        this.loadData();
      }
    });
  }


  hasToUploadDocument(){
    return !this.studentInfoToEdit.letterOfAcceptancePath
      || !this.studentInfoToEdit.letterOfNominationPath
      || !this.studentInfoToEdit.cvPath
      || !this.studentInfoToEdit.diplomaPath
      || !this.studentInfoToEdit.universityRecordPath
      || !this.studentInfoToEdit.picturePath
      || !this.studentInfoToEdit.passportPath
      || !this.studentInfoToEdit.healthInsurancePath
  }

}

interface University {
  id: number,
  name: string
}
