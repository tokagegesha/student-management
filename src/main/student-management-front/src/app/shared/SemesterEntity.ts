export class SemesterEntity {
  constructor(semester: any) {
    this.year = semester.year;
    this.season = semester.season;
    this.id = semester.id;
  }

  id: number;
  year: number;
  season: number;
}
