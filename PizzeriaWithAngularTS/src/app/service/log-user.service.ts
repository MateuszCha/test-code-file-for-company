import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AngularFireAuth } from '@angular/fire/auth';
import { User } from 'firebase';
import { Router } from '@angular/router';

@Injectable({ // add to app module
  providedIn: 'root'
})
export class LogUserService {

    private angularFire: AngularFireAuth;
    private user: User;
    private roter: Router;

    constructor(angularFire: AngularFireAuth, router: Router) {
      this.angularFire = angularFire;
      this.roter = router;
      angularFire.authState.subscribe(user => {
      this.user = user;
    });

  }

  logiIn(formDate: NgForm): boolean {
    this.angularFire.auth.signInWithEmailAndPassword(formDate.value.email, formDate.value.passwordd)
    .then( user => {
      console.log(user);
      this.roter.navigate(['/start']);
    })
    .catch(err => {
      console.log(err);
    });
    return true;
  }

  logOut(): boolean {
    console.log(this.user);
    this.angularFire.auth.signOut()
    .then( value => {
      console.log(value);
      this.roter.navigate(['/start']);
    }).catch(err => {
      console.log(err);
    });
    return true;
  }
  getUser(): User {
    return this.user;
  }

}
