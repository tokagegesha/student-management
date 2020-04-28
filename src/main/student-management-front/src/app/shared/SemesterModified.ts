export class Semester {
  constructor(semester: any) {
    this.year = semester.year;
    this.season = semester.season;
    this.id = semester.semesterId;
    this.semesterId = semester.semesterId;
  }

  id: number;
  year: number;
  season: number;
  semesterId:number;
}
