import { Component, OnInit, ElementRef, AfterViewInit } from '@angular/core';
import { ServiceInfo } from '../service/ServiceInfo';
import { ListaSubs } from '../model/ListPrzpraw';
import { ListaMenu } from '../model/ListMenu';
import { ListaModel } from '../model/ListModel';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  private value = 0;
  private tabOfArray: Array<any> = [];
  private tab2: Array<ListaMenu> = [];
  private service: ServiceInfo;
  private isVisablity: boolean;
  private isVisablityTab: boolean [] = [];
  private init = 0;
  private tabTab: Array<Array<ListaMenu>> = [];

  // private elRef: ElementRef;

  constructor(service: ServiceInfo) {
    this.service = service;
    this.isVisablity = true;
    this.tab2 = this.service.getArrayListMenu();
    this.tabTab = new Array<Array<ListaMenu>>();

    // tslint:disable-next-line: prefer-for-of
    for (const i of this.service.getArrayList()) {
      this.tabTab.push(new Array<ListaMenu>());
    }
    this.tabTab.push(new Array<ListaMenu>()); // plus jedna na nieprzypisane elemnty
    let z: number;
    /*
    for (let j = 0; j < this.service.getArrayList.length; j++) {
      const lokal = (this.service.getArrayList()[j] as ListaModel).getIdMenu();

      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < this.service.getArrayListMenu().length; i++) {
        if (lokal === (this.service.getArrayListMenu[j] as ListaMenu).getMainCatalog()) {
          this.tabTab[j].push((this.service.getArrayListMenu()[i] as ListaMenu));
    } } }
    */
    for (let i = 0; i < this.service.getArrayListMenu().length; i++) {
      z = (this.service.getArrayListMenu()[i] as ListaMenu).getMainCatalog();
      console.log('z' + z);
      console.log('i' + i);
      console.log(this.service.getArrayListMenu());
      this.tabTab[z].push((this.service.getArrayListMenu()[i] as ListaMenu));
    }

    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.service.getArrayList().length; i++) {
      this.isVisablityTab.push(true);
    }
  }
  getTabTab(index: number): Array<ListaMenu> {
    return this.tabTab[index];

  }
  getServie(): ServiceInfo {
   return this.service;
  }
  getListOfMenu(id: number): ListaMenu {
   return this.tabOfArray[id];
  }
  ngOnInit() {
  }
  getVisablity(): boolean {
    return this.isVisablity;
  }
  show(e: any) {
    this.isVisablity = true;
  }
  setIsVisablityTab(index: number) {
    this.isVisablityTab[index] = !this.isVisablityTab[index];
  }
  getIsVisablityTab(index: number) {
    console.log(this.value);
    this.value++;
    return this.isVisablityTab[index];
  }
  isSomeValue(value1: string, value2: string): boolean {
    this.init++;
    if (parseInt(value1, 10) === parseInt(value2, 10)) {
      return true;
    }
    return false;
  }
}
