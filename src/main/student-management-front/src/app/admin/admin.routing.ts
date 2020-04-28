import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AdminHomeComponent} from "./home/admin.home.component";
import {AdminComponent} from "./admin.component";
import {CandidatesComponent} from "./candidates/candidates.component";
import {UniversityComponent} from "./university/university.component";
import {SubjectComponent} from "./subject/subject.component";
import {ProgrammeComponent} from "./programme/programme.component";
import {LoginGuard} from "./login.guard";
import {SemesterComponent} from "./semester/semester.component";
import {GradesComponent} from "./grades/grades.component";
import {ExchangeProgrammesComponent} from "./exchange-programmes/exchange-programmes.component";
import {HomepageEditorComponent} from "./homepage-editor/homepage-editor.component";

const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    canActivateChild:[LoginGuard],
    children: [
      {path: '', redirectTo: 'home', pathMatch: 'full'},
      {path: 'home', component: AdminHomeComponent},
      {path: 'candidates', component: CandidatesComponent},
      {path: 'exchangeProgrammes', component: ExchangeProgrammesComponent},
      {path: 'universities', component: UniversityComponent},
      {path: 'subjects', component: SubjectComponent},
      {path: 'programmes', component: ProgrammeComponent},
      {path: 'semesters', component: SemesterComponent},
      {path: 'grades', component: GradesComponent},
      {path: 'homepageEditor', component: HomepageEditorComponent},
    /*  {path: 'programme/:id', component: ProgrammeComponent},
      {path: 'candidate/registration', component: RegistrationComponent},
      {path: 'login', component: LoginComponent},
      {path: 'user/home', component: UserHomeComponent},*/
    ],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {

}
