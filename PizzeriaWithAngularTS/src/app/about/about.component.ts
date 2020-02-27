import { Component, OnInit } from '@angular/core';
import { FirmeInffo } from '../FirmeInfo';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  idP = 'test1';
  Firminfo: FirmeInffo;

 constructor() {
   this.Firminfo = new FirmeInffo();
  }

 ngOnInit() {
 }

}
