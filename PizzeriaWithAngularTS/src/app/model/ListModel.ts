import { ListIntrface } from '../interface/listInterface';

export class ListaModel implements ListIntrface {
  private id: number;
  private name: string;
  private shortCutName: string;
  private idMenu = 0; // potrzebne do sprawedzenie w ktorej liscie bd dany elemnt listyMenu

  constructor(id: number, name: string, shortCutName = 'null', idMenu) {
    this.name = name;
    this.id = id;
    this.shortCutName = shortCutName;
    this.idMenu = idMenu;
  }

  getID(): number {
    return this.id;
  }
  getName(): string {
    return this.name;
  }
  getshortCutName(): string {
    return this.shortCutName;
  }
  getIdMenu(): number {
    return this.idMenu;
  }
  setID( id: number) {
    this.id = id;
  }
  setName(name: string) {
     this.name = name;
  }
  setshortCutName(shortCutName: string) {
    this.shortCutName = shortCutName ;
  }
    toString(): string {
    return this.id + ' ' + this.name + '( ' + this.shortCutName + ' )';
  }
}
