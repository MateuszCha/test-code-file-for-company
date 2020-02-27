import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { LogUserService } from './log-user.service';

@Injectable({
  providedIn: 'root'
})// add to app module
export class UserGuardService implements CanActivate { // add special method"canActivate" in appRoutingModule with path ./edit


   private logUserService: LogUserService;
   private rooter: Router;

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if ( this.logUserService.getUser()) {
      return true;
    }
    this.rooter.navigate(['/start']);
    return false;
  }

  constructor(logUserService: LogUserService, rooter: Router ) {
    this.logUserService = logUserService;
    this.rooter = rooter;
   }
}
