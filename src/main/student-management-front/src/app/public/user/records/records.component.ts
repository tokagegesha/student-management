import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {CandidatesService} from "../../../admin/candidates/candidates.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-records',
  templateUrl: './records.component.html',
  styleUrls: ['./records.component.css'],
  providers: [CandidatesService]
})
export class RecordsComponent implements OnInit {
  public records: any[];

  constructor(private candidateService: CandidatesService,
              public toast: ToastsManager,
              private vRef: ViewContainerRef) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.candidateService.getStudentSubjectRecord(this.toast).then(res => {
        console.log(res);
        res.data.forEach(record => {
          record.gradeSum = 0;
          record.maxGradeSum = 0;
          for (let i = 0; i < record.grades.length; i++) {
            record.gradeSum = record.gradeSum + (record.grades[i].grade || 0);
            record.maxGradeSum = record.maxGradeSum + (record.grades[i].maxGrade || 0);
          }
        });
        this.records = res.data;
      }
    )
  }

}
