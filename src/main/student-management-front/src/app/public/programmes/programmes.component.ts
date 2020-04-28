import {AfterViewInit, Component, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {UniversitiesService} from "../../shared/universities/universities.service";
import {ActivatedRoute, Params} from "@angular/router";
import "rxjs/add/operator/map";
import "rxjs/add/operator/debounceTime";
import "rxjs/add/operator/distinctUntilChanged";
import "rxjs/add/operator/switchMap";
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/of';
import {ProgrammeService} from "../programme/programme.service";
import {SemesterService} from "../semester/semester.service";
import {Semester} from "../../shared/SemesterModified";
import {ToastsManager} from "ng2-toastr";
import {CreateNewAutocompleteGroup, NgAutocompleteComponent, SelectedAutocompleteItem} from "ng-auto-complete";

@Component({
  selector: 'app-programmes',
  templateUrl: './programmes.component.html',
  styleUrls: ['./programmes.component.css'],
  providers: [ProgrammeService, UniversitiesService, SemesterService]
})
export class ProgrammesComponent implements OnInit, AfterViewInit {

  @ViewChild(NgAutocompleteComponent) public universitySearch: NgAutocompleteComponent;


  public group = [
    CreateNewAutocompleteGroup(
      'Find your university',
      'universitySearch',
      [
        {name: 'Option 1', id: '1'},
      ],
      {titleKey: 'name', childrenKey: null}
    ),
  ];

  Selected(item: SelectedAutocompleteItem) {
    //this.selectedUniversity = item.item;
    if (item.item != null) {
      this.updateProgrammes(item.item);
    }
  }

  universities: University[];
  selectedUniversity: any;
  universityProgrammes: UniversityProgramme[];
  semesters: Semester[];
  selectedSemester: Semester;
  activationMessage: string;


  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private universityService: UniversitiesService,
              private programmeService: ProgrammeService,
              private semesterService: SemesterService,
              private activatedRoute: ActivatedRoute) {
    this.universityProgrammes = [];
    universityService.getUniversities({visible: true}, this.toast)
      .then(response => this.universities = response.data);
  }

  ngOnInit() {
  }


  ngAfterViewInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.universityProgrammes = [];
    this.universityService.getUniversities({visible: true}, this.toast)
      .then(response => {
        this.universities = response.data;
        this.universitySearch.SetValues('universitySearch', response.data)
        this.activatedRoute.queryParams.subscribe(params => {
          let universityId = +params['universityId'];
          let semesterId = +params['semesterId'];
          this.selectedUniversity = this.universities.find(function (element) {
            return element.id == universityId;
          });
          if(this.selectedUniversity){
            this.universitySearch.SelectItem('universitySearch', this.selectedUniversity.id);
            this.semesterService.getAccessibleSemesters(this.selectedUniversity.id, this.toast).subscribe(response => {
              this.semesters = response.data.map(item => new Semester(item));
              this.selectedSemester = this.semesters.find(function (element) {
                return element.semesterId == semesterId;
              });
              this.loadProgrammes(this.selectedUniversity.id, this.selectedSemester.id)
            });
          }


        });

      });


    // subscribe to router event
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      if (params['am']) {
        this.activationMessage = "Verification email was sent on " + params['am'];
      }
    });

  }

  private loadProgrammes(universityId: number, semesterId: number) {
    console.log(121212, universityId, semesterId);
    universityId && semesterId && this.programmeService.getUniversityProgrammes(universityId, semesterId, this.toast).then(response => this.universityProgrammes = response.data).then(obj => console.log(obj));
  }

  updateProgrammes(selectedUniversity: any) {
    this.selectedUniversity = selectedUniversity;
    let semesterCache = this.selectedSemester;
    this.selectedSemester = null;
    this.semesters = [];
    if (this.selectedUniversity) {
      //თუ რამე უნივერსიტეტი არჩეულია
      this.semesterService.getAccessibleSemesters(this.selectedUniversity.id, this.toast).subscribe(response => {
        this.semesters = response.data.map(item => new Semester(item));
        this.semesters.forEach((sem) => {
          if (semesterCache && sem.year == semesterCache.year && sem.season == semesterCache.season) {
            //თუ ახლად არჩეულ უნივერსიტეტთან ვთანამშრომლობთ უკვე მონიშნულ სემესტრში, აღარ გავაქრობ რა სემესტრიც იყო არჩეული
            this.selectedSemester = sem;
          }
          if (this.selectedSemester) {
            this.loadProgrammes(this.selectedUniversity.id, this.selectedSemester.id)
          }
        })
      });
    }
    //if(this.selectedUniversity && this.selectedSemester) this.loadProgrammes(this.selectedUniversity.id, this.selectedSemester.id);
  }

  /*  search = (text$: Observable<string>) =>
      text$
        .debounceTime(200).distinctUntilChanged()
        .merge(this.focus$)
        .merge(this.click$.filter(() => !this.instance.isPopupOpen()))
        .switchMap(term => {
          console.log('term', term);
          return this.universityService.getUniversities2(term).catch(() => Observable.of([]))
        });*/
  /*
    universityFormatter = (x: { name: string }) => x.name;*/

}

interface University {
  id: number,
  name: string
}

interface UniversityProgramme {
  "id": number,
  "universityAddress": string,
  "universityCountryId": number,
  "universityName": string,
  "universityInfo": string,
  "universityId": number,
  "credits": number,
  "finishDate": Date,
  "startDate": Date,
  "regStartDate": Date,
  "regFinishDate": Date,
  "programId": number,
  "type": number,
  "versionId": number,
  "programmeName": string
}
