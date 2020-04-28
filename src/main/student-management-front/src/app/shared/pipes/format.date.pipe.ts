import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name: 'formatDate'
})
export class FormatDatePipe implements PipeTransform{
    private months = ['იანვარი', 'თებერვალი', 'მარტი', 'აპრილი', 'მაისი', 'ივნისი', 'ივლისი', 'აგვისტო', 'სექტემბერი', 'ოქტომბერი', 'ნოემბერი', 'დეკემბერი'];
    private shortMonths = ['იან', 'თებ', 'მარ', 'აპრ', 'მაი', 'ივნ', 'ივლ', 'აგვ', 'სექ', 'ოქტ', 'ნოე', 'დეკ'];
    transform(value: number, short: boolean, showTime: boolean): any {
        let date: Date = new Date(value);
        let del = short ? '-' : ' ';
        return (date.getFullYear() + del + this[short ? 'shortMonths' : 'months'][date.getMonth()] + del + date.getDate()) + (showTime ? ' ' + date.getHours() + ':' + date.getMinutes() : '');
    }
}
