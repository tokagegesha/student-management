import {Component, Inject, ViewContainerRef} from '@angular/core';
import {ProgrammeService} from "./programme.service";
import {ActivatedRoute} from "@angular/router";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-programme',
  templateUrl: './programme.component.html',
  styleUrls: ['./programme.component.css'],
  providers: [ProgrammeService]
})
export class ProgrammeComponent {
  sub: any;
  programmeId: number;
  semesterId: number;
  universityId: number;
  programmeSubjects: any[];

  constructor(
    public toast: ToastsManager,
    private vRef: ViewContainerRef,
    @Inject(ActivatedRoute) private route: ActivatedRoute,
    private programmeService: ProgrammeService) {
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.sub = this.route.params.subscribe(params => {
      this.programmeId = +params['id'];
      this.semesterId = +params['semesterId'];
      this.programmeService.getProgrammeSubjects(this.programmeId, this.semesterId, this.toast).then(res => {
          this.programmeSubjects = res.data;
          console.log(this.programmeSubjects)
        }, error => {
          console.log('error', error)
        }
      ).then(obj => console.log(obj));
    });

    this.route.queryParams.subscribe(params => {
      this.universityId = +params['universityId'];
    });


  }

}
