import { Component, OnInit } from '@angular/core';
import { FirmeInffo } from '../FirmeInfo';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  private flag1 = true;
  private flag2 = true;
  value1 = 100;
  value2 = 25;

  idP = 'test1';
   Firminfo: FirmeInffo;

  constructor() {
    this.Firminfo = new FirmeInffo();
   }

  ngOnInit() {
  }


 clickLike() {
  if (this.flag1) {
    this.value1++;
    this.flag1 = false;
    if (!this.flag2) {
      this.flag2 = true;
      this.value2 --;
    }
  } else {
    this.value1--;
    this.flag1 = true;
  }
}
clickDisLike() {
  if (this.flag2) {
    this.value2++;
    this.flag2 = false;
    if (!this.flag1) {
      this.flag1 = true;
      this.value1 --;
    }
  } else {
    this.value2--;
    this.flag2 = true;
  }
}

}
