import { ListIntrface } from '../interface/listInterface';

export class ListaSubs implements ListIntrface  {
  private id: number;
  private name: string;
  private symbol: string;

  constructor(id: number, name: string, symbol?: string, subscirbie?: string) {
    this.name = name;
    this.id = id;
    this.symbol = symbol;
  }
  getID(): number {
    return this.id;
  }
  getName(): string {
    return this.name;
  }
  getSymbol(): string {
    return this.symbol;
  }
  setID( id: number) {
    this.id = id;
  }
  setName(name: string) {
    this.name = name;
  }
  setSymbol(symbol: string) {
    this.symbol = symbol;
  }
  toString(): string {
    return this.id + ' ' + this.name + ' ' + this.symbol;
  }
}
