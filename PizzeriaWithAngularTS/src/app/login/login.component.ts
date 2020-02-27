import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LogUserService } from '../service/log-user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

   private LogUserService: LogUserService;
   private router: Router;


  constructor(User: LogUserService,  router: Router) {
    this.LogUserService = User;
    this.router = router;
   }

  ngOnInit() {
  }


  login(formDate: NgForm) {
   if (this.LogUserService.logiIn(formDate)) {
   console.log('xd');
   }
  }
  logOut() {
    if (this.LogUserService.logOut()) {
      console.log('wylogowno');
    }
    // this.router.navigate(['/start']);
  }
}
