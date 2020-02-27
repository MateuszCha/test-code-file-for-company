import { Component, OnInit } from '@angular/core';
import { LogUserService } from '../service/log-user.service';

@Component({
  selector: 'app-menu-button',
  templateUrl: './menu-button.component.html',
  styleUrls: ['./menu-button.component.css']
})
export class MenuButtonComponent implements OnInit {

  private LogUserService: LogUserService;
  constructor( logUserService: LogUserService) {
    this.LogUserService = logUserService;
   }



  ngOnInit() {
  }
  getLogUser(): LogUserService {
    return this.LogUserService;
  }
  logOut() {
    this.LogUserService.logOut();

  }
}
