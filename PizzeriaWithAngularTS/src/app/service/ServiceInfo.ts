import { Injectable } from '@angular/core';
import { ListaMenu } from '../model/ListMenu';
import { ListaSubs } from '../model/ListPrzpraw';
import { ListaModel } from '../model/ListModel';

@Injectable()
export class ServiceInfo { // add to app.component.ts

   private arrayListMenu: Array<ListaMenu> = [];
   private arrayList: Array<ListaModel> = [];
   private arrayListSubs: Array<ListaSubs> = [];
   private fooList: Array<any>;
   private test: number;

  constructor() {
    this.arrayListMenu = new Array<ListaMenu>();
    this.arrayList = new Array<ListaModel>();
    this.arrayListSubs = new Array<ListaSubs>();
    this.fooList = this.arrayList;
    this.test = 4;
    this.arrayListSubs.push(new ListaSubs(1, 'uwielbiamk pisac kod', 'azc'));
    this.arrayListSubs.push(new ListaSubs(2, 'uwielbiamk pisac kod', 'azc'));
    this.arrayListSubs.push(new ListaSubs(3, 'uwielbiamk pisac kod', 'azc'));
    this.arrayListMenu.push(new ListaMenu(1, 'cos', 1));
    this.arrayListMenu.push(new ListaMenu(2, 'sss', 1));
    this.arrayListMenu.push(new ListaMenu(3, 'xxxx', 0));
    this.arrayListMenu.push(new ListaMenu(4, 'ttttt', 1));
    this.arrayList.push(new ListaModel(1, 'QQQ', 'qqq', 1));
    this.arrayList.push(new ListaModel(2, 'ZZZ', 'zzz', 2));
  }
 pokaz() {
   console.log('liczba test z servisu :' + this.test);
 }
 getTest(): number {
   this.pokaz();
   return this.test;
 }
 serTest(test: number ) {
     this.test = test;
 }
  getArrayListMenu(): Array<ListaMenu> {
    return this.arrayListMenu;
  }
  getArrayList(): Array<ListaModel> {
    return this.arrayList;
  }
  getArrayListSub(): Array<ListaSubs> {
    return this.arrayListSubs;
  }
  getFooList(): Array<any> { // nieuzywane
    return this.fooList;
  }
  setFooList(fooLIst: Array<any>) {
    this.fooList = fooLIst;
  }
  changeList(id: number = 0): void { // git
    switch (id) {
      case 0:
              this.fooList = this.getArrayList();
              break;
      case 1:
              this.fooList = this.getArrayListMenu();
              break;
      case 2:
              this.fooList = this.getArrayListSub();
              break;
      default:
               this.fooList = this.getArrayList();
    }
  }
}
