import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class UserHomeComponent implements OnInit {

  public targetUserId: number;

  constructor() {
  }


  ngOnInit() {
    console.log("user home components")
  }
}
