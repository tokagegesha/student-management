import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {PublicRoutingModule} from "./public.routing";
import {ProgrammeComponent} from './programme/programme.component';
import {ProgrammesComponent} from './programmes/programmes.component';
import {RegistrationComponent} from './registration/registration.component';
import {PublicComponent} from "./public.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {SignUpComponent} from "../shared/user/account/sign-up/sign-up.component";
import {AccountComponent} from "../shared/user/account/account.component";
import {SignInComponent} from "../shared/user/account/sign-in/sign-in.component";
import {HomeComponent} from "./home/home.component";
import {RegistrationSuccessComponent} from './registration-success/registration-success.component';
import {ActivationComponent} from "../shared/user/activation/activation.component";
import {UserHomeComponent} from "./user/home.component";
import {ProfileComponent} from './user/profile/profile.component';
import {RecordsComponent} from './user/records/records.component';
import {SubjectsComponent} from './user/subjects/subjects.component';
import {ExamsComponent} from './user/exams/exams.component';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {UserService} from "../shared/user/user.service";
import {SemesterService} from "./semester/semester.service";
import {UniversitiesService} from "../shared/universities/universities.service";
import {PipesModule} from "../pipes/pipes.module";
import {ExchangeProgrammeService} from "../shared/exchange-programme/exchange-programme.service";
import {NgAutoCompleteModule} from "ng-auto-complete";


@NgModule({
  imports: [BrowserModule, FormsModule, PublicRoutingModule, MultiselectDropdownModule, NgbModule, PipesModule.forRoot(), NgAutoCompleteModule],
  declarations: [
    HomeComponent,
    PublicComponent,
    ProgrammeComponent,
    ProgrammesComponent,
    RegistrationComponent,
    SignUpComponent,
    AccountComponent,
    SignInComponent,
    RegistrationSuccessComponent,
    ActivationComponent,
    UserHomeComponent,
    ProfileComponent,
    RecordsComponent,
    SubjectsComponent,
    ExamsComponent
  ],
  providers: [UserService, SemesterService, UniversitiesService, ExchangeProgrammeService]
})
export class PublicModule {
}
