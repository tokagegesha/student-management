import {AfterViewInit, Component, Inject, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {ProgrammeService} from "../programme/programme.service";
import {UniversitiesService} from "../../shared/universities/universities.service";
import {IMultiSelectOption} from 'angular-2-dropdown-multiselect';
//import {Observable} from "rxjs/Observable";
import {ActivatedRoute, Router} from "@angular/router";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {SemesterService} from "../semester/semester.service";
import {SignInService} from "../../shared/user/account/sign-in/sign-in.service";
import {ExchangeProgrammeService} from "../../shared/exchange-programme/exchange-programme.service";
import {FormatSemesterNamePipe} from "../../pipes/format.semester.name.pipe";
import {ToastsManager} from "ng2-toastr";
import {Semester} from "../../shared/SemesterModified";
import {CreateNewAutocompleteGroup, NgAutocompleteComponent, SelectedAutocompleteItem} from "ng-auto-complete";


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [ProgrammeService, UniversitiesService, SemesterService, SignInService, ExchangeProgrammeService]
})
export class RegistrationComponent implements AfterViewInit, OnInit {
  @ViewChild(NgAutocompleteComponent) public regUniversitySearch: NgAutocompleteComponent;

  sub: any;
  /*programmeId: number;
   universityId: number;*/
  candidate: any = {};
  searching = false;
  searchFailed = true;
  selectedUniversity: any;
  selectedProgramme: any;
  email: string;
  firstName: string;
  lastName: string;
  gender: number;
  citizenship: string;
  countryOfResidence: string;
  birthDate: Date;
  address: string;
  passportNumber: string;
  city: string;
  zipCode: string;
  country: string;
  phone: string;
  contactPersonName: string;
  contactPersonAddress: string;
  contactPersonPhone: string;
  contactPersonRelationship: string;
  accommodation: boolean=false;
  degree:string;
  status: number;

  semesters: any[];
  degreeTypes:any[];
  semesterIds: number[];
  myOptions: IMultiSelectOption[];
  selectOptions = [];
  files: { [fileType: number]: File } = {};
  currentUser: any;
  exchangeProgrammes: any[];
  selectedExchangeProgramme: any;
  semesterNamePipe = new FormatSemesterNamePipe();


  public group = [CreateNewAutocompleteGroup('Find your university', 'regUniversitySearch', [], {titleKey: 'name', childrenKey: null})];

  Selected(item: SelectedAutocompleteItem) {
    console.log('selected called');
    this.selectedUniversity = item.item;
    if (item.item != null) {
      //this.updateProgrammes(item.item);
    }
  }

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              @Inject(ActivatedRoute)  private route: ActivatedRoute,
              private programmeService: ProgrammeService,
              private universityService: UniversitiesService,
              private semesterService: SemesterService,
              private router: Router,
              private signInService: SignInService,
              private exchangeProgrammeService: ExchangeProgrammeService,
              private http: Http) {

  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.exchangeProgrammeService.getExchangeProgrammes(null).subscribe(programmes => {
      this.exchangeProgrammes = programmes;
    })
  }

  ngAfterViewInit() {

    this.signInService.getCurrentUserDetails().then(data => {
      if (data && data.length > 0) {
        this.currentUser = data[0];
        this.universityService.getUniversities({visible: true},this.toast).then(response => {
          this.regUniversitySearch.SetValues('regUniversitySearch', response.data);
        });
      }


      this.selectOptions = [];
      this.sub = this.route.queryParams.subscribe(params => {
        this.candidate.universityId = +params['uniId'];
        this.candidate.universityId && this.universityService.getUniversities2(null, this.candidate.universityId,this.toast)
          .subscribe(response => {
            this.selectedUniversity = response.data[0];
            this.semesterService.getAccessibleSemesters(this.selectedUniversity.id, this.toast).subscribe(response => {
              this.semesters = response.data.map(item => new Semester(item));
              console.log(this.semesters);
              this.semesters.forEach((sem) => {
                console.log(sem);
                this.selectOptions.push(
                  {
                    id: sem.semesterId, name: this.getSemesterName(sem)
                  }
                );
              });
              this.myOptions = this.selectOptions
            });
          });
      });
    });

    this.universityService.getStudentsType(this.toast).then(response=>{
      console.log('asdasdasd',response)
      this.degreeTypes=response.data[0];
    })

  }

  onChange() {
    console.log(this.semesterIds);
  }

  getSemesterName(semester: any) {
    return semester.year + ' - ' + (semester.year + 1) + ' ' + (semester.season == 1 ? 'Autumn' : 'Spring');
  }

  updateSemesters(selectedUniversity: any) {
    this.selectOptions = [];
    this.myOptions = [];
    this.semesterIds = [];
    if (selectedUniversity instanceof Object && selectedUniversity.hasOwnProperty("id")) {
      this.selectedUniversity = selectedUniversity;
      this.semesterService.getAccessibleSemesters(this.selectedUniversity.id, this.toast).subscribe(response => {
        this.semesters = response.data.map(item => new Semester(item));

        this.semesters.forEach((sem) => {
          this.selectOptions.push(
            {
              id: sem.id, name: this.semesterNamePipe.transform(sem, 'en')
            }
          );
        });
        this.myOptions = this.selectOptions
      });
    }
  }

  searchUniversity = (text$: Observable<string>) =>
    text$
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(inputString =>
        this.universityService.getUniversities2(inputString,null,this.toast)
          .do(() => this.searchFailed = false)
          .catch(() => {
            this.searchFailed = true;
            return Observable.of([])
          }));


  universityFormatter = (x: { name: string }) => x.name;
  exchangeProgrammeFormatter = (x: { name: string }) => x.name;
  programmeFormatter = (x: { programmeName: string }) => x.programmeName;

  private letterOfNomination: File;
  private cv: File;
  private diploma: File;
  private learningAgreement: File;
  private universityRecord: File;
  private picture: File;
  private passport: File;
  private proofOfEnglishSkill: File;
  private healthInsurance: File;


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

  formNotReady() {
    /* if (this.selectedUniversity &&
     this.selectedProgramme &&
     this.firstName &&
     this.lastName &&
     this.email &&
     this.cv ) {
     console.log(1111)
     } else {
     console.log(222222)
     }
     ;*/
    return !(
      this.selectedUniversity &&
      this.firstName &&
      this.lastName &&
      this.email &&
      this.gender &&
      this.citizenship &&
      this.countryOfResidence &&
      this.birthDate &&
      this.address &&
      this.passportNumber &&
      this.country &&
      this.phone &&
      this.picture &&
      this.cv &&
      this.letterOfNomination &&
      this.diploma &&
      this.learningAgreement &&
      this.universityRecord &&
      this.picture &&
      this.passport &&
      this.proofOfEnglishSkill &&
      this.healthInsurance &&
      this.semesterIds.length > 0)
  }


  signUpOnProgramme() {

    console.log(this.selectedUniversity.id);
    // console.log("submitting", this.picture.name + this.passport.name + this.cv.name);
    let formData: FormData = new FormData();

    let candidate = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      universityId: this.selectedUniversity.id,
      gender: this.gender,
      citizenship: this.citizenship,
      countryOfResidence: this.countryOfResidence,
      birthDate: this.birthDate,
      address: this.address,
      passportNumber: this.passportNumber,
      city: this.city,
      zipCode: this.zipCode,
      country: this.country,
      phone: this.phone,
      semesterIds: this.semesterIds,
      contactPersonName: this.contactPersonName,
      contactPersonAddress: this.contactPersonAddress,
      contactPersonPhone: this.contactPersonPhone,
      contactPersonRelationship: this.contactPersonRelationship,
      accommodation: this.accommodation,
      degree:this.degree,
      //1 ნიშნავს ახლად გამოჩეკილს
      status: 1,
      userId: this.currentUser.id,
      exchangeProgrammeId: this.selectedExchangeProgramme.id
    };

    formData.append('cv', this.cv, this.cv.name);
    formData.append("letterOfNomination", this.letterOfNomination, this.letterOfNomination.name);
    formData.append("picture", this.picture, this.picture.name);
    formData.append("passport", this.passport, this.passport.name);
    formData.append("diploma", this.diploma, this.diploma.name);
    formData.append("learningAgreement", this.learningAgreement, this.learningAgreement.name);
    formData.append("universityRecord", this.universityRecord, this.universityRecord.name);
    formData.append("proofOfEnglishSkill", this.proofOfEnglishSkill, this.proofOfEnglishSkill.name);
    formData.append("healthInsurance", this.healthInsurance, this.healthInsurance.name);
    formData.append('student', new Blob([JSON.stringify(candidate)], {type: "application/json"}));


    //let headers = new Headers({'Content-Type': undefined});
    // headers.append('Content-Type', undefined);
    //headers.append('Accept', 'application/json');
    //let options = new RequestOptions({headers: headers});
    this.http.post('/unsecured/api/student/register', formData)
      .map(res => {
          if (res.json().success) {
            this.router.navigateByUrl("/candidate/registration/success")
          }
          res.json()
        }
      )
      .catch(error => Observable.throw(error))
      .subscribe(
        error => console.log(error));
  }
}

interface ProgrammeCandidate {
  id: number;
  programmeReleaseId: number;
  universityId: number;
  email: string;
  status: string;
  firstName: string;
  lastName: string;
  cvPath: string;
  motivationLetter: string;
  picturePath: string;
  passportPath: string;
  studentStatusDocumentPath: string;
  gradeDocumentPath: string;
  recommendationLetterPath: string;
  educationDeclarationPath: string;
}

interface University {
  id: number,
  name: string
}
