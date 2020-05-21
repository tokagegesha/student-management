export class Semester {
  constructor(semester: any) {
    this.year = semester.semester.year;
    this.season = semester.semester.season;
    this.id = semester.id;
    this.semesterId = semester.semester.id;
  }

  id: number;
  year: number;
  season: number;
  semesterId:number;
}
