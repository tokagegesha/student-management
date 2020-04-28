import {NgModule} from '@angular/core';
import {FormatSemesterNamePipe} from "./format.semester.name.pipe";
import {FormatCandidateStatusPipe} from "./format.candidate.status.pipe";

@NgModule({
  declarations: [FormatSemesterNamePipe, FormatCandidateStatusPipe],
  exports: [FormatSemesterNamePipe, FormatCandidateStatusPipe],
  imports: [],
})
export class PipesModule {
  static forRoot() {
    return {
      ngModule: PipesModule,
      providers: [],
    };
  }
}
