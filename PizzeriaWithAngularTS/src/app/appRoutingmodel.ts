import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent} from './about/about.component';
import { MenuComponent } from './menu/menu.component';
import { StartComponent } from './start/start.component';
import { LoginComponent } from './login/login.component';
import { EditComponent } from './edit/edit.component';
import { UserGuardService } from './service/user-guard.service';

const appRouts: Routes = [
{
  path: 'about',
  component : AboutComponent
},
{
  path: 'menu',
  component: MenuComponent
},
{
  path: 'start',
  component: StartComponent
},
{
  path: 'logIn',
 component: LoginComponent
},
{
  path: 'edit',
  component: EditComponent,
  canActivate: [UserGuardService]
},
{
  path : '',
  redirectTo: '/start',
  pathMatch: 'full'
},
{
  path: '**',
  component: StartComponent
}];


@NgModule({
  imports: [RouterModule.forRoot(appRouts)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}

