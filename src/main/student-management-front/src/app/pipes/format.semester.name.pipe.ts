import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'formatSemesterName'
})
export class FormatSemesterNamePipe implements PipeTransform {
  private semesters = {
    ge: ['შემოდგომა', "გაზაფხული"],
    en: ['Autumn', "Spring"],
  };

  transform(value: any, language:any): any {
    return value.year + "-" + (value.year + 1) + " " + this.semesters[language || 'en'][value.season - 1];
  }
}
