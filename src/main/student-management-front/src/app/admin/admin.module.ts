import {NgModule} from '@angular/core';
import {AdminHomeComponent} from "./home/admin.home.component";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {AdminRoutingModule} from "./admin.routing";
import {AdminComponent} from "./admin.component";
import {CandidatesService} from "./candidates/candidates.service";
import {CandidatesComponent} from "./candidates/candidates.component";
import {HttpModule} from "@angular/http";
import {FormatDatePipe} from "../shared/pipes/format.date.pipe";
import {UniversityComponent} from "./university/university.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {SubjectComponent} from "./subject/subject.component";
import {ProgrammeComponent} from "./programme/programme.component";
import {SignInService} from "../shared/user/account/sign-in/sign-in.service";
import {SemesterComponent} from './semester/semester.component';
import {MyDatePickerModule} from 'mydatepicker';
import {PipesModule} from "../pipes/pipes.module";
import {JWBootstrapSwitchModule} from 'jw-bootstrap-switch-ng2';
import {GradesComponent} from "./grades/grades.component";
import {ExchangeProgrammesComponent} from './exchange-programmes/exchange-programmes.component';
import {ExchangeProgrammeService} from "../shared/exchange-programme/exchange-programme.service";
import {RequestHelperService} from "../shared/services/request-helper.service";
import {NgAutoCompleteModule} from "ng-auto-complete";
import {HomepageEditorComponent} from "./homepage-editor/homepage-editor.component";
import {EditorItsealfComponent} from "./homepage-editor/editor-itsealf/editor-itsealf.component";
import {MultiselectDropdownModule} from "angular-2-dropdown-multiselect";


@NgModule({
  imports: [NgbModule.forRoot(), BrowserModule, FormsModule, HttpModule, AdminRoutingModule, MyDatePickerModule, PipesModule.forRoot(),JWBootstrapSwitchModule, NgAutoCompleteModule,MultiselectDropdownModule],
  declarations: [AdminHomeComponent, AdminComponent, CandidatesComponent, FormatDatePipe, UniversityComponent, SubjectComponent, ProgrammeComponent, SemesterComponent, GradesComponent, ExchangeProgrammesComponent, HomepageEditorComponent, EditorItsealfComponent],
  providers: [CandidatesService, SignInService,ExchangeProgrammeService, RequestHelperService]
})
export class AdminModule {
}
