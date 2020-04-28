import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ProgrammesComponent} from "./programmes/programmes.component";
import {ProgrammeComponent} from "./programme/programme.component";
import {RegistrationComponent} from "./registration/registration.component";
import {PublicComponent} from "./public.component";
import {AccountComponent} from "../shared/user/account/account.component";
import {SignUpComponent} from "../shared/user/account/sign-up/sign-up.component";
import {SignInComponent} from "../shared/user/account/sign-in/sign-in.component";
import {HomeComponent} from "./home/home.component";
import {RegistrationSuccessComponent} from "./registration-success/registration-success.component";
import {ActivationComponent} from "../shared/user/activation/activation.component";
import {UserHomeComponent} from "./user/home.component";
import {ProfileComponent} from "./user/profile/profile.component";
import {RecordsComponent} from "./user/records/records.component";
import {SubjectsComponent} from "./user/subjects/subjects.component";
import {ExamsComponent} from "./user/exams/exams.component";
import {StudentLoginGuard} from "./login.guard";

const routes: Routes = [
  {
    path: '',
    component: PublicComponent,
    children: [
      {path: '', component: HomeComponent},
      {path: 'programmes', component: ProgrammesComponent},
      {path: 'programme/:id/semester/:semesterId', component: ProgrammeComponent},
      {path: 'candidate/registration', component: RegistrationComponent},
      {path: 'candidate/registration/success', component: RegistrationSuccessComponent},
    ],
  },
  {
    path: '',
    component: AccountComponent,
    children: [
      {path: 'login', component: SignInComponent},
      {path: 'sign-up', component: SignUpComponent},
    ]
  },
  {
    path: 'user',
    component: PublicComponent,
    canActivateChild: [StudentLoginGuard],
    children: [
      {
        path: '', component: UserHomeComponent, children: [
          {path: '', redirectTo: 'profile', pathMatch: 'full'},
          {path: 'profile', component: ProfileComponent},
          {path: 'records', component: RecordsComponent},
          {path: 'subjects', component: SubjectsComponent},
          {path: 'exams', component: ExamsComponent},
        ]
      },
      {path: 'activation', component: ActivationComponent},
    ]
  },

  {
    path: 'users/activation',
    component: ActivationComponent
  },
  /*{path: 'login', component: LoginComponent},*/
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule {

}
