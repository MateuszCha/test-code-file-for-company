import { ListIntrface } from '../interface/listInterface';

export class ListaMenu implements ListIntrface {
  private id: number;
  private name: string;
  private subscribe: string;
  private imageUrl: string;
  private mainCatalog: number;
  private prize: number;
  private idSub: number;

  constructor(id: number, name: string = 'bbb' , mainCatalog: number = 1, prize: number = 0.0,
              imageUrl?: string, subscirbie?: string) {

      imageUrl = '../assets/photos/brakzdje.png';
      this.name = name;
      this.id = id;
      this.subscribe = subscirbie;
      this.imageUrl = imageUrl;
      this.mainCatalog = mainCatalog; // z tym
      this.prize = prize;
      this.idSub = 0; // powielenie tego
  }
  getImageUrl(): string {
    return this.imageUrl;
  }
  getName(): string {
    return this.name;
  }
  getID(): number {
    return this.id;
  }
  getPrize(): number {
    return this.prize;
  }
  getSubscribe(): string {
    return this.subscribe;
  }
  getMainCatalog(): number {
   return this.mainCatalog;
  }
  setName(name: string) {
     this.name = name;
  }
  setID( id: number) {
    this.id = id;
  }
  toString(): string {
    return this.id + ' '  + this.name + String.fromCharCode(10) + ' ' + this.subscribe + ' URL ' + this.imageUrl ;
  }
}
