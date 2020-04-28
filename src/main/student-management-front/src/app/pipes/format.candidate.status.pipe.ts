import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'formatCandidateStatus'
})
export class FormatCandidateStatusPipe implements PipeTransform {
  private semesters = {
    ge: ['მომლოდინე', "მიღებული", 'უარყოფილი'],
    en: ['Pending', 'Approved', "Rejected"],
  };

  transform(value: any, language:any): any {
    return this.semesters[language || 'en'][value-1];
  }
}
