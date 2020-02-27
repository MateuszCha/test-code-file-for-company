import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AngularFireModule } from '@angular/fire';
import { AngularFireAuthModule } from '@angular/fire/auth';
// import { environment } from '../environments/environment';

import { AppComponent } from './app.component';
import { StartComponent } from './start/start.component';
import { MenuComponent } from './menu/menu.component';
import { AboutComponent } from './about/about.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MainComponentComponent } from './main-component/main-component.component';
import { AppRoutingModule } from './appRoutingmodel';
import { MenuButtonComponent } from './menu-button/menu-button.component';
import { FormsModule } from '@angular/forms';
import { LogUserService } from './service/log-user.service';
import { EditComponent } from './edit/edit.component';
import { UserGuardService } from './service/user-guard.service';
import { ListSubsComponent } from './edit/list-subs/list-subs.component';



const firebaseConfig = {
  apiKey: 'AIzaSyB2BjfqW9yTGHzytEB48RaxWiRzWZ2C5p8',
  authDomain: 'testy-85616.firebaseapp.com',
  databaseURL: 'https://testy-85616.firebaseio.com',
  projectId: 'testy-85616',
  storageBucket: 'testy-85616.appspot.com',
  messagingSenderId: '91899572379',
  appId: '1:91899572379:web:d7a831481ab0a79edd963e',
  measurementId: 'G-S9BBW0732R'
};

@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
    MenuComponent,
    AboutComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    MainComponentComponent,
    MenuButtonComponent,
    EditComponent,
    ListSubsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AngularFireAuthModule,
    AngularFireModule.initializeApp(firebaseConfig)
  ],
  providers: [LogUserService, UserGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
