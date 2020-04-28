import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {UniversitiesService} from "../../shared/universities/universities.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CountriesService} from "../../shared/countries/countries.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-university',
  templateUrl: './university.component.html',
  styleUrls: ['./university.component.css'],
  providers: [UniversitiesService, CountriesService]
})
export class UniversityComponent implements OnInit {
  public universities: any[];
  public countries: any[];
  public university: University;
  public selectedCountryId: number;
  public initialCountryId: number;
  public selectedUniversity: University = null;

  public selectedUniversityEdit: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };

  public newUniversity: { working: boolean; result: { success: boolean; data: any } } = {
    working: false,
    result: null
  };

  constructor(public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private universityService: UniversitiesService,
              private modalService: NgbModal,
              private countryService: CountriesService) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.fetchUniversities();
    this.countryService.getCountries({}, this.toast).then(obj => {
      this.countries = obj.data;
      this.initialCountryId = this.countries[0].id;
    }, error => {
      console.log('error', error);
      this.toast.error(error, 'შეცდომა!')
    });
    this.university = {
      id: null,
      countryId: null,
      name: '',
      address: '',
      countryName: '',
      info: '',
      orderNumber:null
    }
  }

  fetchUniversities() {
    this.universityService.getUniversities({}, this.toast).then(response => this.universities = response.data);
  }


  open(content) {
    this.selectedUniversityEdit = {
      working: false,
      result: null
    };
    this.newUniversity = {
      working: false,
      result: null
    };
    this.modalService.open(content).result.then((result) => {
    });
  }

  editUniversity(id: number, name: string, address: string, countryId: number, info: string, orderNumber: number) {
    this.selectedUniversityEdit.working = true;
    console.log(1111111111111111,orderNumber);
    this.universityService.editUniversity(id, name, address, countryId, info, orderNumber, this.toast).then(res => {
      if (res.data && res.data.length == 1) {
        this.selectedUniversityEdit.result = {success: true, data: res.data[0]};
        this.fetchUniversities();
      } else {
        this.selectedUniversityEdit.result = {success: false, data: null};
      }
      this.selectedUniversityEdit.working = false;
    }, error2 => this.selectedUniversityEdit.working = false);

  }

  addNewUniversity(name: string, address: string, countryId: number, info: string) {
    this.newUniversity.working = true;
    this.universityService.addUniversity(name, address, countryId, info, this.toast).then(res => {
      if (res.data && res.data.length == 1) {
        this.newUniversity.result = {success: true, data: res.data[0]};
        this.fetchUniversities();
      } else {
        this.newUniversity.result = {success: false, data: null};
      }
      this.newUniversity.working = false;
    }, error2 => this.newUniversity.working = false);
  }

}

interface University {
  id: number;
  countryId: number;
  name: string;
  address: string;
  countryName: string;
  info: string;
  orderNumber: number;
}
