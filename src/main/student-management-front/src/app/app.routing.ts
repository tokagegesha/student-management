import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AdminHomeComponent} from "./admin/home/admin.home.component";
import {HomeComponent} from "./public/home/home.component";

const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },{
    path:'admin',
    component:AdminHomeComponent
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [ RouterModule]
})
export class AppRoutingModule{

}
