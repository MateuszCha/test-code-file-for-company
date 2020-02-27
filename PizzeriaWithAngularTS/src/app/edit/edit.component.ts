import { Component } from '@angular/core';
import { LogUserService } from '../service/log-user.service';
import { ServiceInfo } from '../service/ServiceInfo';
import { Router } from '@angular/router';
import { ListaSubs } from '../model/ListPrzpraw';
import { ListaMenu } from '../model/ListMenu';
import { ListaModel } from '../model/ListModel';
import { ListIntrface } from '../interface/listInterface';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent  {

  private LogUser: LogUserService;
  private router: Router;
  private arrayLista: Array<any> = [];
  private ArrayService: ServiceInfo ;
  private id = 1;
  private isVisableMenuList = false;
  private isVisableSubsList = false;
  private isVisableMainList = false;
  private buttonDisable = false;
  private isHidde: boolean;
  private subList: string;

  constructor(LogUse: LogUserService , ArrayService: ServiceInfo ) {
  this.LogUser = LogUse;
  this.ArrayService = ArrayService;
  this.isHidde = true;
  this.changeList();
  }
  cos(event) {
    console.log(event.target.placeholder);
  }
  getLogUser(): LogUserService {
    return this.LogUser;
  }
  getArrayLista(): Array<any> {
    return this.arrayLista;
  }

  getService(): ServiceInfo {
    return this.ArrayService;
  }
  getisVisableMenuList(): boolean {
    return this.isVisableMenuList;
  }
  getisVisableSubsList(): boolean {
    return this.isVisableSubsList;
  }
  getisVisableMainList(): boolean {
    return this.isVisableMainList;
  }
  getButtonDisable(): boolean {
    return this.buttonDisable;
  }
  changeList(id: number = 0): void {
    switch (id) {
      case 0: this.arrayLista = new Array<ListaModel> ();
              this.setVisable(0);
              break;
      case 1: this.arrayLista = new Array<ListaMenu> ();
              this.setVisable(1);
              break;
      case 2: this.arrayLista = new Array<ListaSubs> ();
              this.setVisable(2);
              break;
      default: this.arrayLista = new Array<ListaModel> ();
               this.setVisable(0);
    }
    this.ArrayService.changeList(id);
  }
  setVisable(i: number): void { // git
    this.isVisableMainList = false;
    this.isVisableMenuList = false;
    this.isVisableSubsList = false;
    switch (i) {
      case 0: this.isVisableMainList = true;
              break;
      case 1: this.isVisableMenuList = true;
              break;
      case 2: this.isVisableSubsList = true;
              break;
    }
  }
  moveUper(uId: number): void {
    if (uId > 0) {
      const array: Array<any> = this.ArrayService.getFooList()[uId - 1];
      this.ArrayService.getFooList()[uId - 1] = this.ArrayService.getFooList()[uId];
      this.ArrayService.getFooList()[uId] = array;
      this.setNewIdd(this.ArrayService.getFooList());
    }
  }
  moveDown(nId: number): void {
    if (nId < (this.ArrayService.getFooList().length - 1) && nId >= 0 ) {
      const array: Array<any> = this.ArrayService.getFooList()[nId + 1];
      this.ArrayService.getFooList()[nId + 1] = this.ArrayService.getFooList()[nId];
      this.ArrayService.getFooList()[nId] = array;
      this.setNewIdd(this.ArrayService.getFooList());
    }
  }
  removeOne(idT: number, arrayLista: Array<any>) { // zmiana z this
    arrayLista.splice(idT, 1);
    this.id = 1;
    this.setNewIdd(arrayLista);
  }
  removeAll() {
    this.arrayLista = [];
    this.id = 1;
  }
  // sprawdzenie trzeba bd nadtym troche posiedziec xD
  isInputNull(inputName: string): string {
    if (inputName == null || inputName === '' || inputName[0] === String.fromCharCode(32) ) {
      this.id = this.arrayLista.length + 1;
      return 'null';
    }
    this.id = this.arrayLista.length + 1;
    return inputName;
  }

  addNewElemnetMainList(form: NgForm) {
      let value = 0;
      for (const l of this.ArrayService.getArrayList()) {
      if (value <= l.getIdMenu()) {
        value = l.getIdMenu() + 1; }
      }
      let list: ListaModel;
      list = new ListaModel(this.id++, form.value.name, form.value.shortcutNameMenu, value);
      this.arrayLista.push(list);
      form.resetForm();
  }
  addNewElemnetMenuList(form: NgForm) {
    let list: ListaMenu;
    list = new ListaMenu(this.id++, form.value.name, form.value.mainMenu, form.value.prize, 'URLimage', form.value.ListOfSubs);
    this.arrayLista.push(list);
    // form.resetForm();
  }
  addNewElemnetSubsList(form: NgForm) {
    let list: ListaSubs;
    list = new ListaSubs(this.id++, form.value.name, form.value.SubCode);
    this.arrayLista.push(list);
    // form.resetForm();
   }
   saveEditedList() {
    for (const i of this.arrayLista) {
        this.ArrayService.getFooList().push(i);
      }
    this.removeAll();
    this.setNewIdd(this.ArrayService.getFooList());
   }
   private setNewIdd(arrayValue: Array<ListIntrface>) {
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < arrayValue.length; i++) {
      (arrayValue[i] as ListIntrface).setID(this.id);
      this.id++;
    }
    this.id = 1;
   }
   newLista() {
     this.isHidde = false;
   }
   // Chatch emited value from list-subs.component
   changeFlag(event) {
    if (event === 'false') {
      this.isHidde = false;
    } else {  this.isHidde = true; }
   }
  catchEmitedList(event) {
    this.subList = event;
   }
   //////////////////////////////////////////
  getIsHidde(): boolean {
    return this.isHidde;
   }
   getSubList(): string {
     return this.subList;
   }
  }


