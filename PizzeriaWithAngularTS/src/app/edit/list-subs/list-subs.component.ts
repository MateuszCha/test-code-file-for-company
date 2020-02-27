import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ListaSubs } from 'src/app/model/ListPrzpraw';
import { ServiceInfo } from 'src/app/service/ServiceInfo';
import { ListaChoice } from 'src/app/model/listChoise';

@Component({
  selector: 'app-list-subs',
  templateUrl: './list-subs.component.html',
  styleUrls: ['./list-subs.component.css']
})
export class ListSubsComponent implements OnInit {
  private value: number;
  private listOfSub: Array<ListaSubs>;
  private ArrayService: ServiceInfo;
  private ArryChoise: Array<ListaChoice>;
  private Flag: string;

  constructor(arrayService: ServiceInfo) {
    this.ArrayService = arrayService;
    this.listOfSub = this.ArrayService.getArrayListSub();
    this.value = 1;
    this.ArryChoise = [];
    this.Flag = '';
  }
  @Output()
  zmienna = new EventEmitter<string>();
  @Output()
  zmienna2 = new EventEmitter<string>();

  savaAll() {
    for (const list of this.ArryChoise) {
     this.Flag += list.getName();
    }
    this.zmienna2.emit(this.Flag);
    this.zmienna.emit('true');
    this.Flag = '';
    this.ArryChoise = [];
  }
  getFlag(): string {
    return this.Flag;
  }
  cancel() {
    this.ArryChoise = [];
    this.value = 1;
    this.Flag = '';
    this.zmienna.emit('true');
  }
  setPositionOfElement(index: string) {
    try {
      const i = parseInt(index, 10);
      this.ArryChoise.push(new ListaChoice(this.value, ((this.listOfSub[i] as ListaSubs).getSymbol()
      + (this.listOfSub[i] as ListaSubs).getName())));
      this.value++;
    } catch (err) {
      console.log('errrpsr mesage' + err);
    }
  }
  getListOfSub(): Array<ListaSubs> {
    return this.listOfSub;
  }
  getListaValue(): Array<ListaChoice> {
    return this.ArryChoise;
  }
  remove(i: number) {
    console.log(i + ' ' + typeof(i));
    this.ArryChoise.splice(i, 1);
    this.setIdList();
    console.log(this.ArryChoise);
    this.value--;
  }
  setIdList() {
    for (let i = 0; i < this.ArryChoise.length; i++) {
    (this.ArryChoise[i] as ListaChoice).setID(i + 1);
    }
  }
  ngOnInit() {
  }

}
