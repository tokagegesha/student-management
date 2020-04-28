import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {PublicModule} from "./public/public.module";
import {AdminModule} from "./admin/admin.module";
import {AppRoutingModule} from "./app.routing";
import {LoginGuard} from "./admin/login.guard";
import {StudentLoginGuard} from "./public/login.guard";
import {ToastModule, ToastOptions} from "ng2-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


export class CustomOption extends ToastOptions {
  animate = 'flyRight'; // you can override any options available
  newestOnTop = true;
  showCloseButton = false;
  positionClass = 'toast-bottom-right';
}

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule, FormsModule,
    PublicModule, AdminModule,
    AppRoutingModule, ToastModule.forRoot(), BrowserAnimationsModule
  ],
  providers: [LoginGuard, StudentLoginGuard, {provide: ToastOptions, useClass: CustomOption}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
