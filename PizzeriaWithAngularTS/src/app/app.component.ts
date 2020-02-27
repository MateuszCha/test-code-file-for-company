import { Component } from '@angular/core';
import { ServiceInfo } from './service/ServiceInfo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ServiceInfo]
})
export class AppComponent {
  title = 'Pizzeria';
}
